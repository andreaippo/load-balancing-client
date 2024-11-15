package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * @author Olga Maciaszek-Sharma
 */
@SpringBootApplication
@RestController
public class LoadBalancerApplication {

  private final WebClient.Builder loadBalancedWebClientBuilder;

  public LoadBalancerApplication(WebClient.Builder webClientBuilder) {
    this.loadBalancedWebClientBuilder = webClientBuilder;
  }

  public static void main(String[] args) {
    SpringApplication.run(LoadBalancerApplication.class, args);
  }

  @RequestMapping("/hi")
  public Mono<String> hi(@RequestParam(value = "name", defaultValue = "Mary") String name) {
    return loadBalancedWebClientBuilder.build()
      .get()
      .uri("http://placeholder/greeting")
      .retrieve()
      .bodyToMono(String.class)
      .map(greeting -> String.format("%s, %s!", greeting, name));
  }
}