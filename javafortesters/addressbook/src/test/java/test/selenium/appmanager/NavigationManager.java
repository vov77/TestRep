package test.selenium.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationManager {
  private WebDriver driver;

  public NavigationManager(WebDriver driver) {
    this.driver = driver;
  }

  public void gotoGroups() {
    driver.findElement(By.xpath("//a[contains(text(),'groups')]")).click();
  }
}
