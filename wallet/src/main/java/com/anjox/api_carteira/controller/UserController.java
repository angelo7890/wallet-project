package com.anjox.api_carteira.controller;

import com.anjox.api_carteira.dto.RequestUserDTO;
import com.anjox.api_carteira.dto.ResponseUserDTO;
import com.anjox.api_carteira.entity.UserEntity;
import com.anjox.api_carteira.entity.WalletEntity;
import com.anjox.api_carteira.repository.TransactionsRepository;
import com.anjox.api_carteira.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final TransactionsRepository transactionsRepository;

    public UserController(UserService userService, TransactionsRepository transactionsRepository) {
        this.userService = userService;
        this.transactionsRepository = transactionsRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseUserDTO> CreateUser(@RequestBody @Valid RequestUserDTO dto){
         UserEntity user = userService.saveUser(dto);
         ResponseUserDTO response = new ResponseUserDTO(
                 user.getId(),
                 user.getUsername(),
                 user.getEmail(),
                 user.getPassword(),
                 user.getWallet());
         return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping("/verify")
    public String verifyUser(@Param("code") String code){
        if(userService.verificationToken(code)){
            return "conta verficada com sucesso";
        } else {
            return "erro na verficaçao/token nao existe";
        }
    }
    @GetMapping("/all")
    public ResponseEntity<List<ResponseUserDTO>> getAllUsers(){
        List<ResponseUserDTO> response = userService.findAll();
        if (response.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseUserDTO> getUserById(@PathVariable("id") Long id){
        UserEntity user = userService.findById(id);
        if(user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        ResponseUserDTO response = new ResponseUserDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getWallet()
        );
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
