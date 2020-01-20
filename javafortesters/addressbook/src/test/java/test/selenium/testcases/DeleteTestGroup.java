package test.selenium.testcases;

import org.testng.annotations.Test;

public class DeleteTestGroup extends TestBase {

  @Test
  public void deleteGroup(){

    appManager.getNavigationManager().gotoGroups();
    appManager.getGroupManager().selectGroup();
    appManager.getGroupManager().submitToDeleteGroup();
    appManager.getNavigationManager().gotoGroups();
  }

}


