package test.mantis.testcases;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import test.mantis.appmanager.AppManager;

public class TestBase {

  protected static final AppManager app =
          new AppManager(System.getProperty("browser", BrowserType.CHROME));

  public static AppManager getApp() {
    return app;
  }

  @BeforeSuite
  public void start() throws Exception {
    app.initial();

  }

  @AfterSuite(alwaysRun = true)
  public void stop(){
    app.finish();
  }

}
