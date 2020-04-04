package test.selenium.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import test.selenium.model.ContactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class NewContact extends TestBase {
  @Test
    public void newContact() {

    app.goTo().contacts();
    Set<ContactData> before = app.contact().set();
    ContactData contact = new ContactData("Test19", "Test99", "Test999", "555555555", "test9");
    app.contact().create(contact, true);
    app.goTo().contacts();
    Set<ContactData> after = app.contact().set();
    Assert.assertEquals(after.size(), before.size()+1 );

    String new_Name = contact.getLastName() + " " + contact.getFirstName() + " " + contact.getHomeNumber();
    before.add(new ContactData((after.stream().mapToInt(ContactData::getContactId).max().getAsInt()), new_Name, null, null, null, null));
    Assert.assertEquals(before, after);

  }

}
