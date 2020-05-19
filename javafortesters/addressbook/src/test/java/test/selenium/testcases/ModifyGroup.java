package test.selenium.testcases;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.selenium.model.GroupData;
import test.selenium.model.Groups;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ModifyGroup extends TestBase {


  @BeforeMethod
  public void preconditions() {


    if (app.db().groups().size() == 0){
      app.goTo().groups();
      app.group().create(new GroupData().withName("Test100").withFooter("Test110").withHeader("Test111"));
      app.goTo().groups();
    }
  }
  @Test
  public void groupMod(){
    app.goTo().groups();
    Groups before = app.db().groups();
    GroupData modifiedGroup = before.iterator().next();
    GroupData group = new GroupData().withId(modifiedGroup.getGroupId()).withName("test9").withHeader("test99").withFooter("footer9");
    app.group().modify(group);
    assertThat(app.group().count(), equalTo(before.size()));
    Groups after = app.db().groups();

    //before.remove(modifiedGroup);
    //before.add(group);
    //Comparator<? super GroupData> byId = (o1, o2) -> Integer.compare(o1.getGroupId(), o2.getGroupId());
    //Comparator<? super GroupData> byId = Comparator.comparingInt(GroupData::getGroupId);
    //before.sort(byId);
    //after.sort(byId);
    //Assert.assertEquals(before, after);
    assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
    verifyGroupsUi();

  }




}
