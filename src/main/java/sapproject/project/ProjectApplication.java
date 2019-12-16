package sapproject.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import sapproject.project.repository.AccountRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = AccountRepository.class)
public class ProjectApplication extends SpringBootServletInitializer {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(ProjectApplication.class, args);
	}
}
