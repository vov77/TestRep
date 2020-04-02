package test.selenium.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import test.selenium.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupModificationTests extends TestBase {
  @Test
  public void testGroupModification(){
    appManager.getNavigationManager().gotoGroups();

    if (!appManager.getGroupManager().isThereGroup()){
      appManager.getGroupManager().createGroup(new GroupData("Test100", "Test110", "Test111"));
      appManager.getNavigationManager().gotoGroups();
    }
    List<GroupData> before = appManager.getGroupManager().getGroupList();
    appManager.getGroupManager().selectGroup(before.size() -1);
    appManager.getGroupManager().initGroupMod();
    GroupData group = new GroupData(before.get(before.size()-1).getGroupId(),"test9", "test99", null);
    appManager.getGroupManager().fillNewGroupFields(group);
    appManager.getGroupManager().updateGroup();
    appManager.getNavigationManager().gotoGroups();
    List<GroupData> after = appManager.getGroupManager().getGroupList();
    Assert.assertEquals(before.size(), after.size());

    before.remove(before.size()- 1);
    before.add(group);
    //Comparator<? super GroupData> byId = (o1, o2) -> Integer.compare(o1.getGroupId(), o2.getGroupId());
    Comparator<? super GroupData> byId = Comparator.comparingInt(GroupData::getGroupId);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);

  }

}
