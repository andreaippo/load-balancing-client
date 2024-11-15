package hello;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@LoadBalancerClient(name = "placeholder", configuration = GreetingServiceConfiguration.class)
public class WebClientConfig {

  @LoadBalanced
  @Bean
  WebClient.Builder webClientBuilder() {
    return WebClient.builder();
  }

}