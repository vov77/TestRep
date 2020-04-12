package test.selenium.testcases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.selenium.model.ContactData;
import test.selenium.model.Contacts;

public class ContactPhone extends TestBase{

  @BeforeMethod
  public void preconditions() {
    app.goTo().contacts();
    if (app.contact().list().size() == 0){
      app.contact().create(new ContactData("Test9", "Test99", "Test999", "555555555", "test9"), true);
    }
  }

  @Test
  public void contactPhone (){
    app.goTo().contacts();
    ContactData contact = app.contact().set().iterator().next();
    ContactData infoFromEdit = app.contact().infoFromEdit(contact);
  }



}
