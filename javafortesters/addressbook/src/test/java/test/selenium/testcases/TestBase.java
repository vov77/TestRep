package test.selenium.testcases;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import test.selenium.appmanager.AppManager;

public class TestBase {

  protected static final AppManager appManager = new AppManager(BrowserType.CHROME);

  @BeforeSuite

  public void start() throws Exception {
    appManager.initial();

  }

  @AfterSuite
  public void stop(){
    appManager.finish();
  }


}
