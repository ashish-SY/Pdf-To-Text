package com.example.pdfToText;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@ServletComponentScan
@EnableMongoRepositories
public class PdfToTextApplication {
	public static void main(String[] args) {
		SpringApplication.run(PdfToTextApplication.class, args);
	}

}
