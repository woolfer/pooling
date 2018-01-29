
package com.epam.banch.anpavlenko.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * @author an.pavlenko
 */
@Configuration
@PropertySource(value = "classpath:/properties/file.properties")
public class SpringConfig {

  @Bean
  public static PropertySourcesPlaceholderConfigurer propertyConfigIn() {
    return new PropertySourcesPlaceholderConfigurer();
  }
}
