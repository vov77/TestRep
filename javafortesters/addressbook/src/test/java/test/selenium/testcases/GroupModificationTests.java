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
    app.goTo().groups();
    if (app.group().list().size() == 0){
      app.group().create(new GroupData("Test100", "Test110", "Test111"));
      app.goTo().groups();
    }
  }
  @Test
  public void testGroupModification(){
    List<GroupData> before = app.group().list();
    int index = before.size()-1;
    GroupData group = new GroupData(before.get(index).getGroupId(),"test9", "test99", null);
    app.group().modify(index, group);
    List<GroupData> after = app.group().list();
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
