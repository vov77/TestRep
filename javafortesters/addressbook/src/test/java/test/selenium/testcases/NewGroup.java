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
    GroupData group = new GroupData("Test100", "Test110", "Test111");
    appManager.getGroupManager().createGroup(group);
    appManager.getNavigationManager().gotoGroups();
    List<GroupData> after = appManager.getGroupManager().getGroupList();
    Assert.assertEquals(after.size(), before.size()+1 );

  }
}


