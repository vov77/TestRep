package test.selenium.testcases;

import org.testng.annotations.Test;
import test.selenium.model.GroupData;

public class NewTestGroup extends TestBase {

  @Test
  public void createNewGroup(){

    appManager.gotoGroups();
    appManager.getGroupManager().startNewGroup();
    appManager.getGroupManager().fillNewGroupFields(new GroupData("Test100", "Test110", "Test111"));
    appManager.getGroupManager().submitNewGroup();
    appManager.gotoGroups();
  }
}


