package test.selenium.testcases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.selenium.model.GroupData;
import test.selenium.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeleteGroup extends TestBase {

  @BeforeMethod
  public void preconditions() {
    app.goTo().groups();
    if (app.group().list().size() == 0){
      app.group().create(new GroupData().withName("Test100").withFooter("Test110").withHeader("Test111"));
      app.goTo().groups();
    }
  }

  @Test
  public void deleteGroup(){
    Groups before = app.db().groups();
    GroupData deletedGroup = before.iterator().next();
    app.group().delete(deletedGroup);
    assertThat(app.group().count(), equalTo(before.size()-1));
    Groups after = app.db().groups();

    //before.remove(deletedGroup);
    //assertEquals(before, after);
    assertThat(after, equalTo(before.without(deletedGroup)));

  }


}


