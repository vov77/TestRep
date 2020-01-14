package test.selenium.testcases;

import org.testng.annotations.Test;

public class DeleteTestGroup extends TestBase {

  @Test
  public void deleteGroup(){

    appManager.gotoGroups();
    appManager.getGroupManager().selectGroup();
    appManager.getGroupManager().submitToDeleteGroup();
    appManager.gotoGroups();
  }

}


