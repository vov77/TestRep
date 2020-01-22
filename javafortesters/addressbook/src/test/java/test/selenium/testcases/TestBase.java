package test.selenium.testcases;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import test.selenium.appmanager.AppManager;

public class TestBase {

  protected final AppManager appManager = new AppManager(BrowserType.CHROME);

  @BeforeTest

  public void start() throws Exception {
    appManager.initial();

  }

  @AfterTest
  public void stop(){
    appManager.finish();
  }


}
