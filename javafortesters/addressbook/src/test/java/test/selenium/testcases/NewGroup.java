package test.selenium.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import test.selenium.model.GroupData;

import java.util.HashSet;
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

    int max = 0;
    for (GroupData g : after) {
      if (g.getGroupId() > max) {
        max = g.getGroupId();
      }
    }
    group.setGroupId(max);
    before.add(group);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

  }
}


