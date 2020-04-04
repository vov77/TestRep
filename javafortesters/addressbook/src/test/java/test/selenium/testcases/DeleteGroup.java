package test.selenium.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.selenium.model.GroupData;

import java.util.List;
import java.util.Set;

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
    Set<GroupData> before = app.group().set();
    GroupData deletedGroup = before.iterator().next();
    app.group().delete(deletedGroup);
    Set<GroupData> after = app.group().set();

    before.remove(deletedGroup);
    Assert.assertEquals(before, after);

  }


}


