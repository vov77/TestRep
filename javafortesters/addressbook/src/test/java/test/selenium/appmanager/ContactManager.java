package test.selenium.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import test.selenium.model.ContactData;

import static org.testng.Assert.assertFalse;

public class ContactManager extends BaseManager{
  public ContactManager(WebDriver driver) {
    super(driver);
  }

  public void startNewContact() {
    click(By.xpath("//a[contains(text(),'add new')]"));
  }

  public void submitNewContact() {
    click(By.xpath("//input[@name='submit']"));
  }

  public void fillNewContactFields(ContactData contactData, boolean creation) {
    type(By.xpath("//input[@name='firstname']"), contactData.getFirstName());
    type(By.xpath("//input[@name='lastname']"), contactData.getLastName());
    type(By.xpath("//input[@name='company']"), contactData.getCompanyName());
    type(By.name("home"), contactData.getHomeNumber());
    //select group dropdown
    if (creation) {
      new Select((driver.findElement(By.name("new_group")))).selectByVisibleText(contactData.getGroup());
    } else {
      //assertFalse(isElementPresent(By.name("new_group"))); - returns true, isElementPresent doesn't work
      boolean s;
        try
        {
          driver.findElement(By.name("new_group"));
          s = true;
        }
        catch (Exception e)
        {
          s = false;
        }
        assertFalse(s);
    }
  }

  public void selectContact() {
    click(By.name("selected[]"));
  }

  public void submitToDeleteContact() {
    click(By.xpath("//input[@value='Delete']"));
    driver.switchTo().alert().accept();
  }

  public void initContactMod() {
    click(By.xpath("//img[@alt='Edit']"));
  }

  public void updateContact() {
    click(By.xpath("(//input[@name='update'])[2]"));
  }
}
