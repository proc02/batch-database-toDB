package com.tfa;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
@EnableScheduling
public class PersoBatchGestionRepriseApplication implements CommandLineRunner{

	private final JobLauncher jobLauncher;
	private final Job job;
	
	public static void main(String... args) {
		SpringApplication.run(PersoBatchGestionRepriseApplication.class, args);
	}

	@Scheduled(cron = "0 */1 * * * ?")
	@Override
	public void run(String... args) throws Exception {
		log.info("Batch job starting");
		long start = System.currentTimeMillis();
		JobParameters jobParameters = new JobParametersBuilder()
				.addLong("time", start).toJobParameters();
		jobLauncher.run(job, jobParameters);
		long end = System.currentTimeMillis();
		log.info("Batch job executed successfully en {} ms",(end-start));
	}
}
