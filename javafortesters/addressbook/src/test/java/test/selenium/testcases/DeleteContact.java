package test.selenium.testcases;

import org.testng.annotations.Test;

public class DeleteContact extends TestBase {
  @Test
  public void deleteContact(){
    appManager.getContactManager().selectContact();
    appManager.getContactManager().submitToDeleteContact();
    appManager.getNavigationManager().gotoContacts();
  }
}
