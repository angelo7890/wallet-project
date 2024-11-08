package com.anjox.api_carteira.repository;

import com.anjox.api_carteira.entity.TransactionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface TransactionsRepository extends JpaRepository<TransactionsEntity, UUID> {

    List<TransactionsEntity> findBywalletid (Long walletid);
}
