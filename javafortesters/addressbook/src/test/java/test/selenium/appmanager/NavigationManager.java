package test.selenium.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationManager extends BaseManager {

  public NavigationManager(WebDriver driver) {
    super(driver);
  }

  public void gotoGroups() {
    click(By.xpath("//a[contains(text(),'groups')]"));
  }
  public void gotoContacts() {
    click(By.xpath("//a[contains(text(),'add new')]"));
  }
}
