package test.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationManager extends BaseManager {

  public RegistrationManager(AppManager app) {
    super(app);
  }

  public void start(String username, String email) {
    driver.get(app.getProperty("web.baseUrl") + "/signup_page.php");
    type(By.name("username"), username);
    type(By.name("email"), email);
    click(By.cssSelector("input[value='Signup']"));
  }

  public void finish(String confirmationLink, String password) {
    driver.get(confirmationLink);
    type(By.name("realname"), "test");
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    //click(By.cssSelector("input[value='Update User']"));
    click(By.xpath("//*[@id=\"account-update-form\"]/fieldset/span/button/span"));
  }
}
