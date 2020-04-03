package test.selenium.testcases;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.selenium.model.GroupData;
import java.util.Comparator;
import java.util.List;

public class GroupModificationTests extends TestBase {


  @BeforeMethod
  public void preconditions() {
    appManager.getNavigationManager().gotoGroups();
    if (!appManager.getGroupManager().isThereGroup()){
      appManager.getGroupManager().createGroup(new GroupData("Test100", "Test110", "Test111"));
      appManager.getNavigationManager().gotoGroups();
    }
  }
  @Test
  public void testGroupModification(){
    List<GroupData> before = appManager.getGroupManager().getGroupList();
    int index = before.size()-1;
    GroupData group = new GroupData(before.get(index).getGroupId(),"test9", "test99", null);
    appManager.getGroupManager().modifyGroup(index, group);
    List<GroupData> after = appManager.getGroupManager().getGroupList();
    Assert.assertEquals(before.size(), after.size());
    before.remove(index);
    before.add(group);
    //Comparator<? super GroupData> byId = (o1, o2) -> Integer.compare(o1.getGroupId(), o2.getGroupId());
    Comparator<? super GroupData> byId = Comparator.comparingInt(GroupData::getGroupId);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);

  }



}
