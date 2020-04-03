package test.selenium.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import test.selenium.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class NewContact extends TestBase {
  @Test
    public void newContact() {

    app.goTo().contacts();
    List<ContactData> before = app.contact().list();
    ContactData contact = new ContactData("Test19", "Test99", "Test999", "555555555", "test9");
    app.contact().create(contact, true);
    app.goTo().contacts();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size()+1 );


    /*int max = 0;
    for (ContactData c : after) {
      if (c.getContactId() > max) {
        max = c.getContactId();
      }
    }*/
   // contact.setContactId(after.stream().max((o1, o2) -> Integer.compare(o1.getContactId(), o2.getContactId())).get().getContactId());


    String new_Name = contact.getLastName() + " " + contact.getFirstName() + " " + contact.getHomeNumber();

    before.add(new ContactData(contact.getContactId(), new_Name, null, null, null, null));

    Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getContactId);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);

  }

}
