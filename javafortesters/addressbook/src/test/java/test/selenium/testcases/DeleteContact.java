package test.selenium.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.selenium.model.ContactData;

import java.util.List;

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
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    app.contact().delete(index);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(index, after.size() );
    before.remove(index);
    Assert.assertEquals(before, after);
  }



}
