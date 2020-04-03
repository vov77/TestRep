package test.selenium.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.selenium.model.GroupData;

import java.util.List;

public class DeleteTestGroup extends TestBase {

  @BeforeMethod
  public void preconditions() {
    appManager.getNavigationManager().gotoGroups();
    if (!appManager.getGroupManager().isThereGroup()){
      appManager.getGroupManager().createGroup(new GroupData("Test100", "Test110", "Test111"));
      appManager.getNavigationManager().gotoGroups();
    }
  }

  @Test
  public void deleteGroup(){
    List<GroupData> before = appManager.getGroupManager().getGroupList();
    appManager.getGroupManager().selectGroup(before.size() -1);
    appManager.getGroupManager().submitToDeleteGroup();
    appManager.getNavigationManager().gotoGroups();
    List<GroupData> after = appManager.getGroupManager().getGroupList();
    Assert.assertEquals(before.size() -1, after.size() );
    before.remove(before.size() -1);
    Assert.assertEquals(before, after);

  }

}


