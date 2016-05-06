package cadastro.controllers;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import cadastro.UserEventHandler;

@SpringBootApplication // same as @Configuration @EnableAutoConfiguration
						// @ComponentScan
public class Application extends RepositoryRestConfigurerAdapter {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);

		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		for (String beanName : beanNames) {
			System.out.println("***** beanName: "+beanName);
		}
	}

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		// TODO Auto-generated method stub
		super.configureRepositoryRestConfiguration(config);
		config.setReturnBodyOnCreate(true);
	}
	
	@Bean
	UserEventHandler userEventHandler(){
		return new UserEventHandler();
	}

}