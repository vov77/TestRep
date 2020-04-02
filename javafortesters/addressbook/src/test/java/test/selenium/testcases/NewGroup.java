package test.selenium.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import test.selenium.model.GroupData;

import java.util.Comparator;
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

    /*int max1 = 0;
    for (GroupData g : after)
      if (g.getGroupId() > max) {
        max = g.getGroupId();
      }*/
    /*Comparator<? super GroupData> byId = new Comparator<GroupData>() {
      @Override
      public int compare(GroupData o1, GroupData o2) {
        return Integer.compare(o1.getGroupId(), o2.getGroupId());
      }
    };
    int max1 = after.stream().max(byId).get().getGroupId();
    group.setGroupId(max1);*/

    //group.setGroupId(after.stream().max((o1, o2) -> Integer.compare(o1.getGroupId(), o2.getGroupId())).get().getGroupId());
    before.add(group);
    Comparator<? super GroupData> byId = Comparator.comparingInt(GroupData::getGroupId);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);

  }
}


