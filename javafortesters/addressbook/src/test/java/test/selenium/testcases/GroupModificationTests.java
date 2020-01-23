package test.selenium.testcases;

import org.testng.annotations.Test;
import test.selenium.model.GroupData;

public class GroupModificationTests extends TestBase {
  @Test
  public void testGroupModification(){
    appManager.getNavigationManager().gotoGroups();
    appManager.getGroupManager().selectGroup();
    appManager.getGroupManager().initGroupMod();
    appManager.getGroupManager().fillNewGroupFields(new GroupData("test9", "test99", null));
    appManager.getGroupManager().updateGroup();
    appManager.getNavigationManager().gotoGroups();

  }

}
