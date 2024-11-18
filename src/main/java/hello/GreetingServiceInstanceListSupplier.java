package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.DefaultServiceInstance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import reactor.core.publisher.Flux;

import java.util.List;

class GreetingServiceInstanceListSupplier implements ServiceInstanceListSupplier {

  private static final Logger log = LoggerFactory.getLogger(GreetingServiceInstanceListSupplier.class);
  private final String serviceId;

  @Value("${greeting-service-host}")
  private String host;

  private long hits;

  GreetingServiceInstanceListSupplier(String serviceId) {
    this.serviceId = serviceId;
  }

  @Override
  public String getServiceId() {
    return serviceId;
  }

  @Override
  public Flux<List<ServiceInstance>> get() {
    hits++;
    log.info("hits: {}", hits);
    // simulate a cyclic error rate of 30% by routing to unreachable hosts
    if (hits % 100 > 70) {
      return Flux.just(List.of(new DefaultServiceInstance(serviceId + "2", serviceId, host, 8091, false),
        new DefaultServiceInstance(serviceId + "3", serviceId, host, 8092, false)));
    }
    return Flux.just(List.of(new DefaultServiceInstance(serviceId + "1", serviceId, host, 8090, false)));
  }
}