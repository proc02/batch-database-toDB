package com.tfa.reader;

import javax.persistence.EntityManagerFactory;

import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.stereotype.Component;

import com.tfa.entity.RepriseEntity;

@Component
public class RepriseReaderJpa extends JpaPagingItemReader<RepriseEntity> {

	public RepriseReaderJpa(EntityManagerFactory entityManagerFactory) {
		setPageSize(10000);
		setQueryString("SELECT ID,USERNUM,CARDNUM,COMPTENUM,CODESTATUS,SOLDE,nbr_reprise FROM REPRISE");
		setEntityManagerFactory(entityManagerFactory);
	}
}
