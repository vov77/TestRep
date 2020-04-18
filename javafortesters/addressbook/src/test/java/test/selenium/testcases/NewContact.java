package test.selenium.testcases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.selenium.model.ContactData;
import test.selenium.model.Contacts;
import test.selenium.model.GroupData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class NewContact extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContacts() throws IOException {
    List<Object[]> list = new ArrayList<>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")));
    String line = reader.readLine();
    while (line != null){
      String split[] = line.split(";");
      list.add(new Object[] {new ContactData().withFirstName(split[0]).withLastName(split[1]).withAddress(split[2])
      .withMobileNumber(split[3]).withPhoto(new File(split[4])).withAllPhones(split[3])});
      line = reader.readLine();
    }
    return list.iterator();
  }
  @Test(dataProvider = "validContacts")
    public void newContact(ContactData contact) {
    app.goTo().contacts();
    Contacts before = app.contact().set();
    app.contact().create(contact, true);
    app.goTo().contacts();
    assertThat(app.contact().count(), equalTo(before.size()+1 ));
    Contacts after = app.contact().set();
    assertThat(after, equalTo(before.withAdded(new ContactData()
            .withId((after.stream().mapToInt(ContactData::getId).max().getAsInt()))
            .withFirstName(contact.getFirstName()).withLastName(contact.getLastName())
            .withAllPhones(contact.getAllPhones()).withAddress(contact.getAddress()))));
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
