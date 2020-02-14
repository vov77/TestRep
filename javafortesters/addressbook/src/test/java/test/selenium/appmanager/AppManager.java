package test.selenium.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class AppManager {
  public SessionManager sessionManager;
  public NavigationManager navigationManager;
  public GroupManager groupManager;
  public ContactManager contactManager;
  public WebDriver driver;
  public WebDriverWait wait;
  public String browser;

  public AppManager(String browser) {
    this.browser = browser;
  }

  public void initial() {

    switch (browser) {
      case BrowserType.CHROME:
        driver = new ChromeDriver();
        break;
      case BrowserType.EDGE:
        driver = new EdgeDriver();
        break;
      case BrowserType.FIREFOX:
        driver = new FirefoxDriver();
        break;
    }

    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    wait = new WebDriverWait(driver,10);
    groupManager = new GroupManager(driver);
    contactManager = new ContactManager(driver);
    navigationManager = new NavigationManager(driver);
    sessionManager = new SessionManager(driver);
    //login
    sessionManager.login("admin", "secret");
  }

  public void finish() {
    driver.close();
    driver = null;
  }

  public GroupManager getGroupManager() {
    return groupManager;
  }

  public NavigationManager getNavigationManager() {
    return navigationManager;
  }

  public SessionManager getSessionManager() {
    return sessionManager;
  }

  public ContactManager getContactManager() {
    return contactManager;
  }


}

