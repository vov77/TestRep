package test.selenium.testcases;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.selenium.model.ContactData;
import test.selenium.model.Contacts;
import test.selenium.model.GroupData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class NewContact extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContactsFromCsv() throws IOException {
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

  @DataProvider
  public Iterator<Object[]> validContactsFromJson() throws IOException {
    //reading data from json using Jackson
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")));
    String json = "";
    String line = reader.readLine();
    while (line != null){
      json += line;
      line = reader.readLine();
    }
    ObjectMapper mapper = new ObjectMapper();
    List<ContactData> contacts = mapper.readValue(json, new TypeReference<List<ContactData>>() {
      @Override
      public Type getType() {
        return super.getType();
      }
    });
    return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
  }

  @Test(dataProvider = "validContactsFromJson")
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
            .withAllPhones(contact.getMobileNumber()) // temporary hack - get mobile number to check all phones
            .withEmails(contact.getEmail1()) // temporary hack - get email1 to check emails
            .withAddress(contact.getAddress()))));
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
