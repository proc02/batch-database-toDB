package com.tfa.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfa.entity.TransactionEntity;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

}
