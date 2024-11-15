package hello;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.DefaultServiceInstance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

class GreetingServiceInstanceListSupplier implements ServiceInstanceListSupplier {

  private final String serviceId;

  @Value("${greeting-service-host}")
  private String host;

  GreetingServiceInstanceListSupplier(String serviceId) {
    this.serviceId = serviceId;
  }

  @Override
  public String getServiceId() {
    return serviceId;
  }

  @Override
  public Flux<List<ServiceInstance>> get() {
    System.out.println(host);
    return Flux.just(Arrays.asList(new DefaultServiceInstance(serviceId + "1", serviceId, host, 8090, false),
      new DefaultServiceInstance(serviceId + "2", serviceId, host, 8091, false),
      new DefaultServiceInstance(serviceId + "3", serviceId, host, 8092, false)));
  }
}