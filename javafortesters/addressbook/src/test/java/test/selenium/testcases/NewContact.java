package test.selenium.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import test.selenium.model.ContactData;

import java.util.List;

public class NewContact extends TestBase {
  @Test
    public void newContact() {
    List<ContactData> before = appManager.getContactManager().getContactList();
    appManager.getContactManager().createContact(new ContactData("Test9", "Test99", "Test999", "555555555", "test9"), true);
    appManager.getNavigationManager().gotoContacts();
    List<ContactData> after = appManager.getContactManager().getContactList();
    Assert.assertEquals(after.size(), before.size()+1 );

  }

}
