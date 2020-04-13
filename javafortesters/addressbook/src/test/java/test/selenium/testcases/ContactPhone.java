package test.selenium.testcases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.selenium.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhone extends TestBase{

  @BeforeMethod
  public void preconditions() {
    app.goTo().contacts();
    if (app.contact().list().size() == 0){
      app.contact().create(new ContactData(Integer.MAX_VALUE, "Test99", "Test999", "test9", "654", "321654", "698754"), true);
    }
  }

  @Test
  public void contactPhone (){
    app.goTo().contacts();
    ContactData contact = app.contact().set().iterator().next();
    ContactData infoFromEdit = app.contact().infoFromEdit(contact);

    assertThat(contact.getHomeNumber(), equalTo(cleaned(infoFromEdit.getHomeNumber())));
    assertThat(contact.getMobileNumber(), equalTo(cleaned(infoFromEdit.getMobileNumber())));
    assertThat(contact.getWorkNumber(), equalTo(cleaned(infoFromEdit.getWorkNumber())));
  }

  public String cleaned (String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }


}
