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
    if (app.contact().list().size() == 0){
      app.contact().create(new ContactData("Test9", "Test99", "Test999", "555555555", "test9"), true);
    }
  }
  @Test
  public void deleteContact(){
    Contacts before = app.contact().set();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    assertThat(app.contact().count(), equalTo(before.size()-1 ));
    Contacts after = app.contact().set();
    //before.remove(deletedContact);
    //Assert.assertEquals(before, after);
    assertThat(after, equalTo(before.without(deletedContact)));
  }



}
