package test.selenium.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import test.selenium.model.GroupData;

public class GroupModificationTests extends TestBase {
  @Test
  public void testGroupModification(){
    appManager.getNavigationManager().gotoGroups();

    if (!appManager.getGroupManager().isThereGroup()){
      appManager.getGroupManager().createGroup(new GroupData("Test100", "Test110", "Test111"));
      appManager.getNavigationManager().gotoGroups();
    }
    groupCountBefore = appManager.getGroupManager().getGroupCount();
    appManager.getGroupManager().selectGroup();
    appManager.getGroupManager().initGroupMod();
    appManager.getGroupManager().fillNewGroupFields(new GroupData("test9", "test99", null));
    appManager.getGroupManager().updateGroup();
    appManager.getNavigationManager().gotoGroups();
    groupCountAfter = appManager.getGroupManager().getGroupCount();
    Assert.assertEquals(groupCountBefore, groupCountAfter);

  }

}
