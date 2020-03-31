package test.selenium.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import test.selenium.model.ContactData;

import java.util.List;

public class DeleteContact extends TestBase {
  @Test
  public void deleteContact(){
    if (!appManager.getContactManager().isThereContact()){
      appManager.getContactManager().createContact(new ContactData("Test9", "Test99", "Test999", "555555555", "test9"), true);
    }
    List<ContactData> before = appManager.getContactManager().getContactList();
    appManager.getContactManager().selectContact(before.size() - 1);
    appManager.getContactManager().submitToDeleteContact();
    appManager.getNavigationManager().gotoContacts();
    List<ContactData> after = appManager.getContactManager().getContactList();
    Assert.assertEquals(before.size() -1, after.size() );

    before.remove(before.size()-1);
    Assert.assertEquals(before, after);
  }

}
