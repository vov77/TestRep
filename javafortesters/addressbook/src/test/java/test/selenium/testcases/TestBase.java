package test.selenium.testcases;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import test.selenium.appmanager.AppManager;

public class TestBase {

  protected final AppManager appManager = new AppManager();

  @BeforeTest

  public void start() throws Exception {
    appManager.initial();

  }

  @AfterTest
  public void stop(){
    appManager.finish();
  }


}
