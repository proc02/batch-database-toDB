package com.tfa.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.tfa.entity.RepriseEntity;
import com.tfa.entity.TransactionEntity;
import com.tfa.listener.RepriseJobListerner;
import com.tfa.listener.RepriseReaderListener;
import com.tfa.listener.RepriseWriterListener;
import com.tfa.processor.RepriseProcessor;
import com.tfa.reader.RepriseReader;
import com.tfa.reader.RepriseReaderJpa;
import com.tfa.writter.RepriseWritter;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;


@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class BatchConfiguration {

	
	private final JobBuilderFactory jobBuilderFactory;
	
	private final StepBuilderFactory stepBuilderFactory;
	
	private final RepriseReader repriseReader;
	private final RepriseReaderJpa repriseReaderJpa;
	
	private final RepriseProcessor repriseProcessor;
	
	private final RepriseWritter repriseWritter;
	
	private final RepriseReaderListener repriseReaderListener;
	
	private final RepriseWriterListener repriseWriterListener;
	
	private final RepriseJobListerner repriseJobListerner;
	
//	private final EntityManager entityManager;

	@Bean
	public Job createJob() {
		return  jobBuilderFactory.get("bank-job")
				.listener(repriseJobListerner)
				.start(createStep())
				.build();
	}

	public Step createStep() {
		return stepBuilderFactory.get("bank-reprise")
			.<RepriseEntity,TransactionEntity>chunk(1000)
			.listener(repriseJobListerner)
			.reader(repriseReader)
			.listener(repriseReaderListener)
			.processor(repriseProcessor)
			.writer(repriseWritter)
			.listener(repriseWriterListener) 
			.taskExecutor(taskAsyncExecutor())
			//.throttleLimit(100)
			.build();
	}

	//Beaucoup mieux
	public TaskExecutor taskAsyncExecutor(){
	    SimpleAsyncTaskExecutor asyncTaskExecutor=new SimpleAsyncTaskExecutor("async-reprise");
	    asyncTaskExecutor.setConcurrencyLimit(10000);
	    return asyncTaskExecutor;
	}
	//Pas performant
	public TaskExecutor taskSyncExecutor(){
	    SyncTaskExecutor asyncTaskExecutor=new SyncTaskExecutor();
	    return asyncTaskExecutor;
	}
//	private JpaItemWriter<TransactionEntity> writer() {
//		JpaItemWriter<TransactionEntity> writer = new JpaItemWriter<>();
//		writer.setEntityManagerFactory(entityManager.getEntityManagerFactory());
//		return writer;
//	}
}
