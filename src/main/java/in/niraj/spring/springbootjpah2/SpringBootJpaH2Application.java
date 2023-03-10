package in.niraj.spring.springbootjpah2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SpringBootJpaH2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJpaH2Application.class, args);
	}
	 @Bean
	    public Docket productApi() {
	        return new Docket(DocumentationType.SWAGGER_2).select()
	                .apis(RequestHandlerSelectors.basePackage("in.niraj.spring.springbootjpah2")).build();
	    }
}
//http://localhost:8080/swagger-ui.html
//https://www.bezkoder.com/spring-boot-controlleradvice-exceptionhandler/
//https://www.bezkoder.com/spring-boot-webmvctest/
//https://bushansirgur.in/spring-boot-hibernate-jpa-and-h2-database-crud-rest-api-example/