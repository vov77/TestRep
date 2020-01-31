package test.selenium.testcases;

import org.testng.annotations.Test;
import test.selenium.model.ContactData;

public class NewContact extends TestBase {
  @Test
    public void newContact() {
    appManager.getContactManager().startNewContact();
    appManager.getContactManager().fillNewContactFields(new ContactData("Test9", "Test99", "Test999", "555555555", "Test100"), true);
    appManager.getContactManager().submitNewContact();
    appManager.getNavigationManager().gotoContacts();

  }

}
