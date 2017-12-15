package rnd.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by ekrylovich
 * on 20.11.17.
 */
@SpringBootApplication
@EnableEurekaServer
public class DiscoveryService {
    public static void main(String[] args) {
        SpringApplication.run(DiscoveryService.class, args);
    }
}
