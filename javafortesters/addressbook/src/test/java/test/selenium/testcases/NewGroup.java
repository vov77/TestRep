package test.selenium.testcases;

import org.testng.annotations.Test;
import test.selenium.model.GroupData;

public class NewGroup extends TestBase {

  @Test
  public void createNewGroup(){

    appManager.getNavigationManager().gotoGroups();
    appManager.getGroupManager().createGroup(new GroupData("Test100", "Test110", "Test111"));
    appManager.getNavigationManager().gotoGroups();
  }
}


