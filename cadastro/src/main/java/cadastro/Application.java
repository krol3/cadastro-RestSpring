package cadastro;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);

		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		for (String beanName : beanNames) {
			System.out.println("***** beanName: " + beanName);
		}
	}

	@Bean
	UserEventHandler userEventHandler() {
		return new UserEventHandler();
	}
	
	 @Autowired private RepositoryRestConfiguration repositoryRestConfiguration;

	    @PostConstruct
	    public void exposeIds() {
	        this.repositoryRestConfiguration.setReturnBodyForPutAndPost(true);
	    }

}