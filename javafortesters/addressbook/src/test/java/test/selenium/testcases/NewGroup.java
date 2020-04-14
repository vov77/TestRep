package test.selenium.testcases;

import org.testng.annotations.Test;
import test.selenium.model.GroupData;
import test.selenium.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class NewGroup extends TestBase {

  @Test
  public void createNewGroup(){
    app.goTo().groups();
    Groups before = app.group().set();
    GroupData group = new GroupData().withName("Test100").withFooter("Test110").withHeader("Test111");
    app.group().create(group);
    app.goTo().groups();
    assertThat(app.group().count(), equalTo(before.size()+1));
    Groups after = app.group().set();
    // Assert.assertEquals(after.size(), before.size()+1 );
    assertThat(after.size(), equalTo(before.size()+1));
    group.withId(after.stream().mapToInt(GroupData::getGroupId).max().getAsInt());
   // Assert.assertEquals(before, after);

  }
  @Test
  public void createNotGroup(){
    app.goTo().groups();
    Groups before = app.group().set();
    GroupData group = new GroupData().withName("'").withFooter("Test110").withHeader("Test111");
    app.group().create(group);
    app.goTo().groups();
    assertThat(app.group().count(), equalTo(before.size()));
    Groups after = app.group().set();
    assertThat(after, equalTo(before));
  }
}


