package com.anjox.api_carteira.service;
import com.anjox.api_carteira.dto.RequestUserDTO;
import com.anjox.api_carteira.dto.ResponseUserDTO;
import com.anjox.api_carteira.entity.UserEntity;
import com.anjox.api_carteira.entity.WalletEntity;
import com.anjox.api_carteira.enums.UserEnum;
import com.anjox.api_carteira.repository.UserRepository;
import com.anjox.api_carteira.repository.WalletRepository;
import com.anjox.api_carteira.util.GenerateCode;
import com.anjox.api_carteira.util.VerifyPassword;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {


    private final UserRepository userRepository;
    private final WalletRepository walletRepository;


    public UserService(UserRepository userRepository, WalletRepository walletRepository) {
        this.userRepository = userRepository;
        this.walletRepository = walletRepository;
    }


    public UserEntity saveUser(RequestUserDTO dto) {
        if (userRepository.existsByemail(dto.email())){
            throw new RuntimeException("email ja existe");
        }
        List<String> errorpassword = VerifyPassword.verifyPassword(dto.password());
        if (!errorpassword.isEmpty()){
            throw new RuntimeException(errorpassword.toString());
        }
        WalletEntity wallet = new WalletEntity(
                BigDecimal.ZERO
        );
        walletRepository.save(wallet);
        String token = GenerateCode.generateCode(156);
        UserEntity user = new UserEntity(
                dto.username(),
                dto.email(),
                dto.password(),
                UserEnum.USER,
                token,
                false,
                wallet
        );
        userRepository.save(user);
        return user;

    }

    public boolean verificationToken(String token) {
        UserEntity user = userRepository.findBytoken(token);
        if(user == null || user.getEnabled()){
            return false;
        }else {
            user.setToken(null);
            user.setEnabled(true);
            userRepository.save(user);
            return true;
        }
    }

    public List<ResponseUserDTO> findAll() {
        List<UserEntity> users = userRepository.findAll();
        return users.stream().map(
                u -> new ResponseUserDTO(
                        u.getId(),
                        u.getUsername(),
                        u.getEmail(),
                        u.getPassword(),
                        u.getWallet()
                )
        ).collect(Collectors.toList());
    }

    public UserEntity findById(Long id) {
        return userRepository.findByid(id);
    }


}
