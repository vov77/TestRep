package test.mantis.appmanager;

import org.openqa.selenium.WebDriver;

public class RegistrationManager {

  private final AppManager app;
  private WebDriver driver;

  public RegistrationManager(AppManager app) {
    this.app = app;
    driver = app.getDriver();
  }

  public void start(String username, String email) {
    driver.get(app.getProperty("web.baseUrl") + "/signup_page.php");
  }
}
