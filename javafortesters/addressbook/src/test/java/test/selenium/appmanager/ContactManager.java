package test.selenium.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import test.selenium.model.ContactData;
import test.selenium.model.GroupData;

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

  public void fillNewContactFields(ContactData contactData) {
    type(By.xpath("//input[@name='firstname']"), contactData.getFirstName());
    type(By.xpath("//input[@name='lastname']"), contactData.getLastName());
    type(By.xpath("//input[@name='company']"), contactData.getCompanyName());
    type(By.name("home"), contactData.getHomeNumber());

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
