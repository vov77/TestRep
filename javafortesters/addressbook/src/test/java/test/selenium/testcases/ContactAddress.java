package test.selenium.testcases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.selenium.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddress extends TestBase{

  @BeforeMethod
  public void preconditions() {
    app.goTo().contacts();
    if (app.db().contacts().size() == 0){
      app.contact().create(new ContactData().withFirstName("Test19").withLastName("Test99")
              .withCompanyName("Test999"), true);
    }
  }

  @Test
  public void contactAddress (){
    app.goTo().contacts();
    ContactData contact = app.contact().set().iterator().next();
    ContactData infoFromEdit = app.contact().infoFromEdit(contact);
    assertThat(contact.getAddress(), equalTo(infoFromEdit.getAddress()));
  }
}
