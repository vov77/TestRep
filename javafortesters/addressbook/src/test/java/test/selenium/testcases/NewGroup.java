package test.selenium.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import test.selenium.model.GroupData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class NewGroup extends TestBase {

  @Test
  public void createNewGroup(){

    app.goTo().groups();
    Set<GroupData> before = app.group().set();
    GroupData group = new GroupData("Test100", "Test110", "Test111");
    app.group().create(group);
    app.goTo().groups();
    Set<GroupData> after = app.group().set();
    Assert.assertEquals(after.size(), before.size()+1 );

    group.setGroupId(after.stream().mapToInt(GroupData::getGroupId).max().getAsInt());
    before.add(group);
    Assert.assertEquals(before, after);
  }
}


