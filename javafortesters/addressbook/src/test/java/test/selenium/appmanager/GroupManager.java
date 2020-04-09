package test.selenium.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import test.selenium.model.GroupData;
import test.selenium.model.Groups;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static test.selenium.testcases.TestBase.*;

public class GroupManager extends BaseManager {

  GroupManager(WebDriver driver) {
    super(driver);
  }

  public void startNew() {
    click(By.xpath("//input[@name='new']"));
  }
  public void submit() {
    click(By.xpath("//input[@name='submit']"));
  }
  public void fillNewFields(GroupData groupData) {
    type(By.xpath("//input[@name='group_name']"), groupData.getGroupName());
    type(By.xpath("//textarea[@name='group_header']"), groupData.getGroupHeader());
    type(By.xpath("//textarea[@name='group_footer']"), groupData.getGroupFooter());
  }
  public void selectById(int id) {
    driver.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }
  public void submitToDelete() {
    click(By.xpath("(//input[@name='delete'])[2]"));
  }
  private void edit() {
    click(By.name("edit"));
  }
  private void update() {
    click(By.name("update"));
  }
  public void create(GroupData groupData) {
    startNew();
    fillNewFields(groupData);
    submit();
  }
  public void modify(GroupData group) {
    selectById(group.getGroupId());
    edit();
    fillNewFields(group);
    update();
    groupCache = null;
    getApp().goTo().groups();
  }

  public void delete(GroupData group) {
    selectById(group.getGroupId());
    submitToDelete();
    groupCache = null;
    getApp().goTo().groups();
  }
  public List<GroupData> list() {
    List<GroupData> groups = new ArrayList<>();
    List<WebElement> elements = driver.findElements(By.cssSelector("span.group"));
    for(WebElement element: elements){
      String name = element.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      GroupData group = new GroupData(id, name, null, null);
      groups.add(group);
    }
    return groups;
  }

  private Groups groupCache = null;   //кэшируем сет групп

  public Groups set() {
    if (groupCache != null) {
      return new Groups(groupCache); // возвращаем копию groupCache
    }
    groupCache = new Groups();
    List<WebElement> elements = driver.findElements(By.cssSelector("span.group"));
    for(WebElement element: elements){
      String name = element.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      GroupData group = new GroupData(id, name, null, null);
      groupCache.add(group);
    }
    return new Groups(groupCache);
  }

}
