package test.selenium.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import test.selenium.model.GroupData;

import java.util.List;

public class NewGroup extends TestBase {

  @Test
  public void createNewGroup(){

    appManager.getNavigationManager().gotoGroups();
    List<GroupData> before = appManager.getGroupManager().getGroupList();
    //groupCountBefore = appManager.getGroupManager().getGroupCount();
    appManager.getGroupManager().createGroup(new GroupData("Test100", "Test110", "Test111"));
    appManager.getNavigationManager().gotoGroups();
    List<GroupData> after = appManager.getGroupManager().getGroupList();
    //groupCountAfter = appManager.getGroupManager().getGroupCount();
    Assert.assertEquals(after.size(), before.size()+1 );
  }
}


