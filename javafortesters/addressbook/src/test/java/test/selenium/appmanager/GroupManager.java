package test.selenium.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import test.selenium.model.GroupData;

import java.util.ArrayList;
import java.util.List;

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

  public void select(int index) {
    driver.findElements(By.name("selected[]")).get(index).click();
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
  public void modify(int index, GroupData group) {
    select(index);
    edit();
    fillNewFields(group);
    update();
    getApp().goTo().groups();
  }
  public void delete(int index) {
    select(index);
    submitToDelete();
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
}
