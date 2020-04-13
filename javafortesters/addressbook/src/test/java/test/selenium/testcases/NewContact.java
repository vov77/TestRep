package test.selenium.testcases;

import org.testng.annotations.Test;
import test.selenium.model.ContactData;
import test.selenium.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class NewContact extends TestBase {
  @Test
    public void newContact() {
    app.goTo().contacts();
    Contacts before = app.contact().set();
    ContactData contact = new ContactData("Test19", "Test99", "Test999", "555555555", "test9");
    app.contact().create(contact, true);
    app.goTo().contacts();
    assertThat(app.contact().count(), equalTo(before.size()+1 ));
    Contacts after = app.contact().set();
    //before.add(new ContactData((after.stream().mapToInt(ContactData::getContactId).max().getAsInt()), new_Name, null, null, null, null));
    // assertEquals(before, after);
    assertThat(after, equalTo(before.withAdded(new ContactData((after.stream().
            mapToInt(ContactData::getId).max().getAsInt()),
            contact.getFirstName(), contact.getLastName(), null, contact.getHomeNumber(), contact.getMobileNumber(), contact.getWorkNumber()))));
  }

  @Test
  public void newBadContact() {

    app.goTo().contacts();
    Contacts before = app.contact().set();
    ContactData contact = new ContactData("'", null, null, null, null);
    app.contact().create(contact, true);
    app.goTo().contacts();
    assertThat(app.contact().count(), equalTo (before.size() ));
    Contacts after = app.contact().set();
    assertThat(after, equalTo(before));
    app.goTo().contacts();
  }

}
