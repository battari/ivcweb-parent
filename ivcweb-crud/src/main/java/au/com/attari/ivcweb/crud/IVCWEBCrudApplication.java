package au.com.attari.ivcweb.crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

// @ComponentScan({"au.com.attari.ivcweb.crud.springboot","au.com.attari.ivcweb.crud.controller","au.com.attari.ivcweb.crud.service", "au.com.attari.ivcweb.crud.repository"})
@SpringBootApplication
public class IVCWEBCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(IVCWEBCrudApplication.class, args);
	}

}
