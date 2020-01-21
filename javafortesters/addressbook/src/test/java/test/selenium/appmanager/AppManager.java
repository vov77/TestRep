package test.selenium.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class AppManager {
  private SessionManager sessionManager;
  private NavigationManager navigationManager;
  private GroupManager groupManager;
  private ContactManager contactManager;
  public WebDriver driver;
  public WebDriverWait wait;

  public void initial() {
    driver = new ChromeDriver();
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
