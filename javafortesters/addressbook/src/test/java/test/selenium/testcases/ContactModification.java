package test.selenium.testcases;

import org.testng.annotations.Test;
import test.selenium.model.ContactData;

public class ContactModification extends TestBase {
  @Test
  public void contactMod(){
    appManager.getContactManager().selectContact();
    appManager.getContactManager().initContactMod();
    appManager.getContactManager().fillNewContactFields(new ContactData("Test7", "Test77", "Test777", "7777777", null), false);
    appManager.getContactManager().updateContact();

  }
}
