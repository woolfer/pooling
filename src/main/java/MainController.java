/**
 * @author an.pavlenko
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EntityScan(basePackages = {"com.epam.banch.anpavlenko.entity"})
@ComponentScan("com.epam.banch.anpavlenko.controller")
@EnableJpaRepositories
@EnableAutoConfiguration
@EnableTransactionManagement
public class MainController {

  @Value("${HOMEPATH}")
  private String testProp;

  @RequestMapping("/")
  @ResponseBody
  String getTestProp() {
    return testProp;
  }

  public static void main(String[] args) throws Exception {
    SpringApplication.run(MainController.class, args);
  }
}
