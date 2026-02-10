package com.tomazbr9.buildprice;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration;

@SpringBootApplication(
		exclude = BatchAutoConfiguration.class
)
public class Run {

	public static void main(String[] args) {
		SpringApplication.run(Run.class, args);
	}

}
