package test.selenium.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import test.selenium.model.GroupData;
import test.selenium.testcases.TestBase;

import java.util.ArrayList;
import java.util.List;

import static test.selenium.testcases.TestBase.*;

public class GroupManager extends BaseManager {

  GroupManager(WebDriver driver) {
    super(driver);
  }

  public void startNewGroup() {
    click(By.xpath("//input[@name='new']"));
  }

  public void submitNewGroup() {
    click(By.xpath("//input[@name='submit']"));
  }

  public void fillNewGroupFields(GroupData groupData) {
    type(By.xpath("//input[@name='group_name']"), groupData.getGroupName());
    type(By.xpath("//textarea[@name='group_header']"), groupData.getGroupHeader());
    type(By.xpath("//textarea[@name='group_footer']"), groupData.getGroupFooter());
  }

  public void selectGroup(int index) {
    driver.findElements(By.name("selected[]")).get(index).click();
  }

  public void submitToDeleteGroup() {
    click(By.xpath("(//input[@name='delete'])[2]"));
  }

  private void initGroupMod() {
    click(By.name("edit"));
  }

  private void updateGroup() {
    click(By.name("update"));
  }

  public void createGroup(GroupData groupData) {
    startNewGroup();
    fillNewGroupFields(groupData);
    submitNewGroup();
  }

  public void modifyGroup(int index, GroupData group) {
    selectGroup(index);
    initGroupMod();
    fillNewGroupFields(group);
    updateGroup();
    getAppManager().getNavigationManager().gotoGroups();
  }

  public boolean isThereGroup() {
    return isElementPresent(By.name("selected[]"));
  }
  public int getGroupCount() {
    return driver.findElements(By.name("selected[]")).size();
  }

  public List<GroupData> getGroupList() {
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
