package test.selenium.testcases;

import org.testng.annotations.Test;
import test.selenium.model.ContactData;

public class NewContact extends TestBase {
  @Test
    public void newContact() {
    appManager.getContactManager().createContact(new ContactData("Test9", "Test99", "Test999", "555555555", "test9"), true);
    appManager.getNavigationManager().gotoContacts();

  }

}
