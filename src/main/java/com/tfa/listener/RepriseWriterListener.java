package com.tfa.listener;

import java.util.List;

import org.springframework.batch.core.ItemWriteListener;
import org.springframework.stereotype.Component;

import com.tfa.entity.TransactionEntity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RepriseWriterListener implements ItemWriteListener<TransactionEntity> {

	@Override
	public void beforeWrite(List<? extends TransactionEntity> items) {
		log.info("Start to write ===> ");
	}

	@Override
	public void afterWrite(List<? extends TransactionEntity> items) {
		log.info("End to write ===> ");
	}

	@Override
	public void onWriteError(Exception exception, List<? extends TransactionEntity> items) {
		
	}

}
