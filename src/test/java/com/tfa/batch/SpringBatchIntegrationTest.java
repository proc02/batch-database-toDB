package com.tfa.batch;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@SpringBatchTest
public class SpringBatchIntegrationTest {

	 	@Autowired
	    private JobLauncherTestUtils jobLauncherTestUtils;

	 

	    private JobParameters defaultJobParameters() {
	        JobParametersBuilder paramsBuilder = new JobParametersBuilder();
	        paramsBuilder.addLong("time", System.currentTimeMillis());
	        return paramsBuilder.toJobParameters();
	   }
	    @Test
	    void givenReferenceOutput_whenJobExecuted_thenSuccess() throws Exception {
	        // given
	        //FileSystemResource expectedResult = new FileSystemResource(EXPECTED_OUTPUT);
	       // FileSystemResource actualResult = new FileSystemResource(TEST_OUTPUT);

	        // when
	        JobExecution jobExecution = jobLauncherTestUtils.launchJob(defaultJobParameters());
	        JobInstance actualJobInstance = jobExecution.getJobInstance();
	        ExitStatus actualJobExitStatus = jobExecution.getExitStatus();
	        
	        Assertions.assertEquals("COMPLETED", actualJobExitStatus.getExitCode());
	    }
}
