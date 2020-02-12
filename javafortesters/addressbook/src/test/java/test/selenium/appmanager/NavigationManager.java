package test.selenium.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationManager extends BaseManager {

  public NavigationManager(WebDriver driver) {
    super(driver);
  }

  public void gotoGroups() {
    if (isElementPresent(By.tagName("h1"))
      && driver.findElement(By.tagName("h1")).getText().equals("Groups")
            && isElementPresent(By.name("new"))) {
      return;
    }
    click(By.xpath("//a[contains(text(),'groups')]"));
  }
  public void gotoContacts() {
    if (isElementPresent(By.id("maintable"))){
      return;
    }
    click(By.xpath("//a[contains(text(),'add new')]"));
  }
}
