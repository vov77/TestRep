package test.selenium.testcases;

import org.testng.annotations.Test;
import test.selenium.model.GroupData;

public class DeleteTestGroup extends TestBase {

  @Test
  public void deleteGroup(){

    appManager.getNavigationManager().gotoGroups();
    if (!appManager.getGroupManager().isThereGroup()){
      appManager.getGroupManager().createGroup(new GroupData("Test100", "Test110", "Test111"));
      appManager.getNavigationManager().gotoGroups();
    }
    appManager.getGroupManager().selectGroup();
    appManager.getGroupManager().submitToDeleteGroup();
    appManager.getNavigationManager().gotoGroups();
  }

}


