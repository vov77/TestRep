package test.selenium.testcases;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;
import test.selenium.model.GroupData;
import test.selenium.model.Groups;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class NewGroup extends TestBase {

  @Test
  public void createNewGroup(){

    app.goTo().groups();
    Groups before = app.group().set();
    GroupData group = new GroupData("Test100", "Test110", "Test111");
    app.group().create(group);
    app.goTo().groups();
    Groups after = app.group().set();
    // Assert.assertEquals(after.size(), before.size()+1 );
    assertThat(after.size(), equalTo(before.size()+1));

    group.setGroupId(after.stream().mapToInt(GroupData::getGroupId).max().getAsInt());
   // Assert.assertEquals(before, after);
    assertThat(after, equalTo(before.withAdded(group)));
  }
}


