package com.tfa.writter;

import javax.persistence.EntityManagerFactory;

import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.stereotype.Component;

import com.tfa.entity.TransactionEntity;

@Component
public class RepriseWritter extends JpaItemWriter<TransactionEntity> {

	public RepriseWritter(EntityManagerFactory entityManagerFactory) {
		setEntityManagerFactory(entityManagerFactory);
	}
}
