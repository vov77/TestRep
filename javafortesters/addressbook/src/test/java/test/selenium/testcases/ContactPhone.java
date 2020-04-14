package test.selenium.testcases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.selenium.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhone extends TestBase{

  @BeforeMethod
  public void preconditions() {
    app.goTo().contacts();
    if (app.contact().list().size() == 0){
      app.contact().create(new ContactData().withFirstName("Test19").withLastName("Test99")
              .withCompanyName("Test999"), true);
    }
  }

  @Test
  public void contactPhone (){
    app.goTo().contacts();
    ContactData contact = app.contact().set().iterator().next();
    ContactData infoFromEdit = app.contact().infoFromEdit(contact);

    //assert phone types - won't work if some phones missing
    //assertThat(contact.getHomeNumber(), equalTo(cleaned(infoFromEdit.getHomeNumber())));
    //assertThat(contact.getMobileNumber(), equalTo(cleaned(infoFromEdit.getMobileNumber())));
    //assertThat(contact.getWorkNumber(), equalTo(cleaned(infoFromEdit.getWorkNumber())));

    //assert merged in one line phones - works for 3 numbers only!!
    assertThat(contact.getAllPhones(), equalTo(mergePhones(infoFromEdit)));
  }

  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getHomeNumber(), contact.getMobileNumber(), contact.getWorkNumber())
            .stream().filter(s -> ! s.equals(""))
            .map(ContactPhone::cleaned)
            .collect(Collectors.joining("\n"));
  }

  public static String cleaned (String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }


}
