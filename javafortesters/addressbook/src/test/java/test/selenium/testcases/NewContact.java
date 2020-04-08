package test.selenium.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import test.selenium.model.ContactData;
import test.selenium.model.Contacts;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.*;

public class NewContact extends TestBase {
  @Test
    public void newContact() {

    app.goTo().contacts();
    Contacts before = app.contact().set();
    ContactData contact = new ContactData("Test19", "Test99", "Test999", "555555555", "test9");
    app.contact().create(contact, true);
    app.goTo().contacts();
    Contacts after = app.contact().set();
    assertEquals(after.size(), before.size()+1 );

    String new_Name = contact.getLastName() + " " + contact.getFirstName() + " " + contact.getHomeNumber();
    //before.add(new ContactData((after.stream().mapToInt(ContactData::getContactId).max().getAsInt()), new_Name, null, null, null, null));
    // assertEquals(before, after);
    assertThat(after, equalTo(before.withAdded(new ContactData((after.stream().mapToInt(ContactData::getContactId).max().getAsInt()), new_Name, null, null, null, null))));
  }

}
