package test.selenium.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import test.selenium.model.GroupData;

public class GroupManager {
  private WebDriver driver;

  public GroupManager(WebDriver driver) {
    this.driver = driver;
  }

  public void startNewGroup() {
    driver.findElement(By.xpath("//input[@name='new']")).click();
  }

  public void submitNewGroup() {
    driver.findElement(By.xpath("//input[@name='submit']")).click();
  }

  public void fillNewGroupFields(GroupData groupData) {
    driver.findElement(By.xpath("//input[@name='group_name']")).click();
    driver.findElement(By.xpath("//input[@name='group_name']")).sendKeys(groupData.getGroupName());
    driver.findElement(By.xpath("//textarea[@name='group_header']")).click();
    driver.findElement(By.xpath("//textarea[@name='group_header']")).sendKeys(groupData.getGroupHeader());
    driver.findElement(By.xpath("//textarea[@name='group_footer']")).click();
    driver.findElement(By.xpath("//textarea[@name='group_footer']")).sendKeys(groupData.getGroupFooter());
  }

  public void selectGroup() {
    driver.findElement(By.name("selected[]")).click();
  }

  public void submitToDeleteGroup() {
    driver.findElement(By.xpath("(//input[@name='delete'])[2]")).click();
  }
}
