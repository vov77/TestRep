package test.selenium.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import test.selenium.model.GroupData;

public class NewGroup extends TestBase {

  @Test
  public void createNewGroup(){

    appManager.getNavigationManager().gotoGroups();
    groupCountBefore = appManager.getGroupManager().getGroupCount();
    appManager.getGroupManager().createGroup(new GroupData("Test100", "Test110", "Test111"));
    appManager.getNavigationManager().gotoGroups();
    groupCountAfter = appManager.getGroupManager().getGroupCount();
    Assert.assertEquals(groupCountBefore +1, groupCountAfter );
  }
}


