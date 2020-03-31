package test.selenium.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import test.selenium.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactModification extends TestBase {
  @Test
  public void contactMod(){
    if (!appManager.getContactManager().isThereContact()){
      appManager.getContactManager().createContact(new ContactData("Test9", "Test99", "Test999", "555555555", "test9"), true);
    }
    List<ContactData> before = appManager.getContactManager().getContactList();
    appManager.getContactManager().initContactMod(before.size() -1);
    ContactData contact = new ContactData(before.get(before.size()-1).getContactId(),"Test23", "Test988", "Test898", "7777788", null);
    appManager.getContactManager().fillNewContactFields(contact, false);
    appManager.getContactManager().updateContact();
    List<ContactData> after = appManager.getContactManager().getContactList();
    Assert.assertEquals(before.size(), after.size());


    String new_Name = contact.getLastName() + " " + contact.getFirstName() + " " + contact.getHomeNumber();
    before.remove(before.size()- 1);
    before.add(new ContactData(contact.getContactId(), new_Name, null, null, null, null));
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

  }
}
