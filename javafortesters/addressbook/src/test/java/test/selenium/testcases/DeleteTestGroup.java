package test.selenium.testcases;

import org.testng.Assert;
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
    groupCountBefore = appManager.getGroupManager().getGroupCount();
    appManager.getGroupManager().selectGroup(groupCountBefore-1);
    appManager.getGroupManager().submitToDeleteGroup();
    appManager.getNavigationManager().gotoGroups();
    groupCountAfter = appManager.getGroupManager().getGroupCount();
    Assert.assertEquals(groupCountBefore -1, groupCountAfter );
  }

}


