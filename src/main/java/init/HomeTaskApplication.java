package init;

import init.config.RedisDetailsConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
@EnableConfigurationProperties(value = { RedisDetailsConfig.class })
public class HomeTaskApplication {
	public static void main(String[] args) {
		SpringApplication.run(HomeTaskApplication.class, args);
	}
}
