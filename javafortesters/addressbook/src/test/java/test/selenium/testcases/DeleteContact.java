package test.selenium.testcases;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.selenium.model.ContactData;
import test.selenium.model.Contacts;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class DeleteContact extends TestBase {
  @BeforeMethod
  public void preconditions() {
    app.goTo().contacts();
    if (app.db().contacts().size() == 0){
      app.contact().create(new ContactData().withFirstName("Test19").withLastName("Test99")
              .withCompanyName("Test999"), true);
    }
  }
  @Test
  public void deleteContact(){
    Contacts before = app.db().contacts();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    assertThat(app.contact().count(), equalTo(before.size()-1 ));
    Contacts after = app.db().contacts();
    //before.remove(deletedContact);
    //Assert.assertEquals(before, after);
    assertThat(after, equalTo(before.without(deletedContact)));
  }



}
