package hello;

import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author Olga Maciaszek-Sharma
 */
@Configuration
public class GreetingServiceConfiguration {

  @Bean
  @Primary
  ServiceInstanceListSupplier serviceInstanceListSupplier() {
    return new GreetingServiceInstanceListSupplier("placeholder");
  }

}