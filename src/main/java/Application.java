/**
 * @author an.pavlenko
 */

import java.nio.file.StandardWatchEventKinds;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.epam.banch.anpavlenko.service.DiskFilePathMonitoringService;

@Controller
@EntityScan(basePackages = {"com.epam.banch.anpavlenko.entity"})
@ComponentScan({"com.epam.banch.anpavlenko.controller", "com.epam.banch.anpavlenko.service", "com.epam.banch.anpavlenko.converter"})
@PropertySource(value = "classpath:/properties/file.properties")
@EnableJpaRepositories
@EnableAutoConfiguration
@EnableTransactionManagement
public class Application extends SpringBootServletInitializer {

  private static final Logger LOG = LoggerFactory.getLogger(Application.class);

  @Value("#{'${user.dir}'.concat('\\folderToCheck')}")
  private String filePath;

  @Value("${timeout:3}")
  private Long timeout;

  @Autowired
  private DiskFilePathMonitoringService monitoringService;

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  public static PropertySourcesPlaceholderConfigurer propertyConfigIn() {
    return new PropertySourcesPlaceholderConfigurer();
  }

  @PostConstruct
  public void listen() {
    try {
//      DiskFilePathMonitoringService monitoringService = new DiskFilePathMonitoringService();
      monitoringService.setTimeOut(timeout, TimeUnit.SECONDS);
      monitoringService.registerDirectory(filePath, StandardWatchEventKinds.ENTRY_CREATE);
      Thread thread = new Thread(monitoringService);
      thread.setName("FileChecking");
      thread.start();
//      monitoringService.run();
    } catch (InterruptedException e) {
      LOG.debug(e.getMessage());
    }


  }
}
