package test.selenium.testcases;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.selenium.model.GroupData;
import test.selenium.model.Groups;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

public class DeleteGroup extends TestBase {

  @BeforeMethod
  public void preconditions() {
    app.goTo().groups();
    if (app.group().list().size() == 0){
      app.group().create(new GroupData("Test100", "Test110", "Test111"));
      app.goTo().groups();
    }
  }

  @Test
  public void deleteGroup(){
    Groups before = app.group().set();
    GroupData deletedGroup = before.iterator().next();
    app.group().delete(deletedGroup);
    Groups after = app.group().set();

    //before.remove(deletedGroup);
    //assertEquals(before, after);
    assertThat(after, equalTo(before.without(deletedGroup)));

  }


}


