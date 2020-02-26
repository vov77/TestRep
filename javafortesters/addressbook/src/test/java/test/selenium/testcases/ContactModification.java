package test.selenium.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import test.selenium.model.ContactData;

public class ContactModification extends TestBase {
  @Test
  public void contactMod(){
    if (!appManager.getContactManager().isThereContact()){
      appManager.getContactManager().createContact(new ContactData("Test9", "Test99", "Test999", "555555555", "test9"), true);
    }
    contactCountBefore = appManager.getContactManager().getContactCount();
    appManager.getContactManager().selectContact(contactCountBefore-1);
    appManager.getContactManager().initContactMod();
    appManager.getContactManager().fillNewContactFields(new ContactData("Test7", "Test77", "Test777", "7777777", null), false);
    appManager.getContactManager().updateContact();
    contactCountAfter = appManager.getContactManager().getContactCount();
    Assert.assertEquals(contactCountBefore, contactCountAfter);

  }
}
