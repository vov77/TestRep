package test.selenium.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class AppManager {
  private final GroupManager groupManager = new GroupManager();
  public WebDriverWait wait;

  public void login(String username, String password) {
    groupManager.driver.navigate().to("http://localhost/addressbook/");
    groupManager.driver.findElement(By.name("user")).click();
    groupManager.driver.findElement(By.name("user")).clear();
    groupManager.driver.findElement(By.name("user")).sendKeys(username);
    groupManager.driver.findElement(By.name("pass")).click();
    groupManager.driver.findElement(By.name("pass")).clear();
    groupManager.driver.findElement(By.name("pass")).sendKeys(password);
    groupManager.driver.findElement(By.xpath("//input[@value='Login']")).click();
  }

  public void gotoGroups() {
    groupManager.driver.findElement(By.xpath("//a[contains(text(),'groups')]")).click();
  }

  public void initial() {
    groupManager.driver = new ChromeDriver();
    groupManager.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    wait = new WebDriverWait(groupManager.driver,10);
    //login
    login("admin", "secret");
  }

  public void finish() {
    groupManager.driver.close();
    groupManager.driver = null;
  }

  public GroupManager getGroupManager() {
    return groupManager;
  }
}
