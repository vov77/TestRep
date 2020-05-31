package test.mantis.testcases;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import test.mantis.appmanager.AppManager;

import java.io.File;
import java.io.IOException;

public class TestBase {

  protected static final AppManager app =
          new AppManager(System.getProperty("browser", BrowserType.CHROME));

  public static AppManager getApp() {
    return app;
  }

  @BeforeSuite
  public void start() throws Exception {
    app.initial();
    app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.bak");

  }

  @AfterSuite(alwaysRun = true)
  public void stop() throws IOException {
    app.ftp().restore("config_inc.php.bak", "config_inc.php");
    app.finish();
  }

}
