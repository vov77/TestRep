package test.selenium.testcases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.selenium.model.ContactData;
import test.selenium.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ModifyContact extends TestBase {

  @BeforeMethod
  public void preconditions() {
    app.goTo().contacts();
    if (app.contact().list().size() == 0){
      app.contact().create(new ContactData().withFirstName("Test19").withLastName("Test99")
              .withCompanyName("Test999"), true);
    }
  }
  @Test
  public void contactMod(){
    Contacts before = app.contact().set();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstName("Test123")
            .withLastName("Test988").withCompanyName("Test898");

    app.contact().modify(contact);
    assertThat(app.contact().count(), equalTo(before.size() ));
    Contacts after = app.contact().set();
    // before.remove(modifiedContact);
    //before.add(new ContactData(contact.getContactId(), new_Name, null, null, null, null));
    //Comparator<? super ContactData> byId = (o1, o2) -> Integer.compare(o1.getContactId(), o2.getContactId());
    //Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getContactId);
    //before.sort(byId);
    //after.sort(byId);
    //Assert.assertEquals(before, after);
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(new ContactData()
            .withId(contact.getId()).withFirstName(contact.getFirstName()).withLastName(contact.getLastName())
            .withCompanyName(contact.getCompanyName()).withHomeNumber(contact.getHomeNumber()))));

  }


}
