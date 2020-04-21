package test.selenium.testcases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.selenium.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactInfoPage extends TestBase{

  @BeforeMethod
  public void preconditions() {
    app.goTo().contacts();
    if (app.contact().list().size() == 0){
      app.contact().create(new ContactData().withFirstName("Test19").withLastName("Test99")
              .withCompanyName("Test999"), true);
    }
  }


  @Test
  public void contactInfoPage (){
    app.goTo().contacts();
    ContactData contact = app.contact().set().iterator().next();
    ContactData infoFromInfoPage = app.contact().infoFromInfoPage(contact);
    assertThat(mergeAllFields(infoFromInfoPage), equalTo(mergeAllFields(contact)));
  }

  private String mergeAllFields(ContactData contact) {
    return Arrays.asList(contact.getFirstName(), contact.getLastName(), contact.getAddress(), contact.getAllPhones(),
             contact.getEmails(), contact.getInfoPageText())
            .stream().filter(s -> ! s.equals(""))
            .map(ContactInfoPage::cleaned)
            .collect(Collectors.joining(""));
  }

  public static String cleaned (String item) {
    return item.replaceAll("\n", "").replaceAll(" ", "").replaceAll("H:", "").replaceAll("M:", "").replaceAll("W:", "");
  }


}