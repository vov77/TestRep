package test.selenium.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.selenium.model.ContactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ModifyContact extends TestBase {

  @BeforeMethod
  public void preconditions() {
    app.goTo().contacts();
    if (app.contact().list().size() == 0){
      app.contact().create(new ContactData("Test9", "Test99", "Test999", "555555555", "test9"), true);
    }
  }
  @Test
  public void contactMod(){
    Set<ContactData> before = app.contact().set();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData(modifiedContact.getContactId(),"Test123", "Test988", "Test898", "7777788", null);
    app.contact().modify(contact);
    Set<ContactData> after = app.contact().set();

    String new_Name = contact.getLastName() + " " + contact.getFirstName() + " " + contact.getHomeNumber();
    before.remove(modifiedContact);
    before.add(new ContactData(contact.getContactId(), new_Name, null, null, null, null));

    //Comparator<? super ContactData> byId = (o1, o2) -> Integer.compare(o1.getContactId(), o2.getContactId());
    //Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getContactId);
    //before.sort(byId);
    //after.sort(byId);
    Assert.assertEquals(before, after);

  }


}
