package fr.mobileagri.julie_camille_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan
public class JulieCamilleApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(JulieCamilleApiApplication.class, args);
  }

}
