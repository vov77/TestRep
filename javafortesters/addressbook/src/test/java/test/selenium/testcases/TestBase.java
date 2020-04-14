package test.selenium.testcases;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import test.selenium.appmanager.AppManager;

public class TestBase {

  protected static final AppManager app = new AppManager(BrowserType.CHROME);

  public static AppManager getApp() {
    return app;
  }

  @BeforeSuite
  public void start() throws Exception {
    app.initial();

  }

  @AfterSuite
  public void stop(){
    app.finish();
  }


}
