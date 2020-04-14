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
    ContactData contact = new ContactData().withFirstName("Test19").withLastName("Test99")
            .withCompanyName("Test999").withGroup("test9");


    app.contact().create(contact, true);
    app.goTo().contacts();
    assertThat(app.contact().count(), equalTo(before.size()+1 ));
    Contacts after = app.contact().set();
    assertThat(after, equalTo(before.withAdded(new ContactData()
            .withId((after.stream().mapToInt(ContactData::getId).max().getAsInt()))
            .withFirstName(contact.getFirstName()).withLastName(contact.getLastName())
            .withHomeNumber(contact.getHomeNumber()).withMobileNumber(contact.getMobileNumber())
            .withWorkNumber(contact.getWorkNumber()))));
  }

  @Test
  public void newBadContact() {

    app.goTo().contacts();
    Contacts before = app.contact().set();
    ContactData contact = new ContactData().withFirstName("'");
    app.contact().create(contact, true);
    app.goTo().contacts();
    assertThat(app.contact().count(), equalTo (before.size() ));
    Contacts after = app.contact().set();
    assertThat(after, equalTo(before));
    app.goTo().contacts();
  }

}
