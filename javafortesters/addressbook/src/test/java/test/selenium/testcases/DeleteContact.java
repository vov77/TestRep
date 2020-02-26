package test.selenium.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import test.selenium.model.ContactData;

public class DeleteContact extends TestBase {
  @Test
  public void deleteContact(){
    if (!appManager.getContactManager().isThereContact()){
      appManager.getContactManager().createContact(new ContactData("Test9", "Test99", "Test999", "555555555", "test9"), true);
    }
    contactCountBefore = appManager.getContactManager().getContactCount();
    appManager.getContactManager().selectContact(contactCountBefore - 1);
    appManager.getContactManager().submitToDeleteContact();
    appManager.getNavigationManager().gotoContacts();
    contactCountAfter = appManager.getContactManager().getContactCount();
    Assert.assertEquals(contactCountBefore -1, contactCountAfter );
  }
}
