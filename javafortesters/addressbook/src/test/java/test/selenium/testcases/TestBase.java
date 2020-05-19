package test.selenium.testcases;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import test.selenium.appmanager.AppManager;
import test.selenium.model.ContactData;
import test.selenium.model.Contacts;
import test.selenium.model.GroupData;
import test.selenium.model.Groups;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class TestBase {

  //new logger with slf4j Logback classic
  Logger logger = LoggerFactory.getLogger(TestBase.class);
  Logger logger2 = LoggerFactory.getLogger("FileLogger");

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

  @BeforeMethod
  public void logTestStart(Method m, Object[] p){
    logger.info("Start test " + m.getName() + " with parameters" + Arrays.asList(p));
    logger2.debug("Start test " + m.getName());
  }

  @AfterMethod(alwaysRun = true)
  public void logTestStop(Method m){
    logger.info("Stop test " + m.getName());
    logger2.debug("Stop test " + m.getName());
  }

  public void verifyGroupsUi() {

    if (Boolean.getBoolean("verifyUI")){
      Groups dbGroups = app.db().groups();
      Groups uiGroups = app.group().set();
      assertThat(uiGroups, equalTo(dbGroups.stream()
              .map((g) -> new GroupData().withId(g.getGroupId()).withName(g.getName()))
              .collect(Collectors.toSet())));
    }
  }

  public void verifyContactsUi() {

    if (Boolean.getBoolean("verifyUI")){
      Contacts dbContacts = app.db().contacts();
      Contacts uiContacts = app.contact().set();
      assertThat(uiContacts, equalTo(dbContacts.stream()
              .map((g) -> new ContactData().withId(g.getId()).withFirstName(g.getFirstName())
              .withLastName(g.getLastName()).withAddress(g.getAddress()))
              .collect(Collectors.toSet())));
    }
  }


}
