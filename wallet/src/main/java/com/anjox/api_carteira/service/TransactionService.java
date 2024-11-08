package com.anjox.api_carteira.service;

import com.anjox.api_carteira.dto.RequestTransactionDTO;
import com.anjox.api_carteira.entity.TransactionsEntity;
import com.anjox.api_carteira.entity.UserEntity;
import com.anjox.api_carteira.entity.WalletEntity;
import com.anjox.api_carteira.repository.TransactionsRepository;
import com.anjox.api_carteira.repository.UserRepository;
import com.anjox.api_carteira.repository.WalletRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;

@Service
public class TransactionService {
    private final TransactionsRepository transactionsRepository;
    private final UserRepository userRepository;
    private final WalletRepository walletRepository;

    public TransactionService(TransactionsRepository transactionsRepository, UserRepository userRepository, WalletRepository walletRepository) {
        this.transactionsRepository = transactionsRepository;
        this.userRepository = userRepository;
        this.walletRepository = walletRepository;
    }
    @Transactional
    public TransactionsEntity createTransaction(RequestTransactionDTO dto){
        try {
            UserEntity user = userRepository.findByid(dto.userid());
            if(user == null){
                throw new RuntimeException("usuario nao encontrado");
            }
            WalletEntity wallet = user.getWallet();
            if (wallet == null) {
                throw new RuntimeException("carteira nao encontrada");
            }

            TransactionsEntity transaction = new TransactionsEntity();
            transaction.setMenssagem(dto.Message());
            transaction.setValorTransacao(dto.value());
            ZonedDateTime now = ZonedDateTime.now(ZoneId.of("-03:00"));
            transaction.setDateTransacao(now.toLocalDateTime());
            transaction.setWallet(wallet);

            if (dto.type() == 1) { // SUBTRACT
                if (wallet.getBalance().compareTo(dto.value()) < 0) {
                    throw new RuntimeException("valor insuficiente");
                }
                wallet.setBalance(wallet.getBalance().subtract(dto.value()));
                transaction.setTypeTransacao(dto.type());
            } else if (dto.type() == 2) {
                wallet.setBalance(wallet.getBalance().add(dto.value()));
                transaction.setTypeTransacao(dto.type());
            } else {
                throw new RuntimeException("tipo invalido");
            }
            walletRepository.save(wallet);
            return transactionsRepository.save(transaction);


        } catch (Exception e) {
            throw new RuntimeException("erro ao criar transaçao");
        }
    }
}
