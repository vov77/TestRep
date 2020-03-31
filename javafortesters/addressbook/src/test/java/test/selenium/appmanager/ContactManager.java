package test.selenium.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import test.selenium.model.ContactData;

import java.util.ArrayList;
import java.util.List;

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
      assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void selectContact(int index) {
    driver.findElements(By.name("selected[]")).get(index).click();
  }

  public void submitToDeleteContact() {
    click(By.xpath("//input[@value='Delete']"));
    driver.switchTo().alert().accept();
  }

  public void initContactMod(int index) {
    driver.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
  }

  public void updateContact() {
    click(By.xpath("(//input[@name='update'])[2]"));
  }

  public void createContact(ContactData contactData, boolean creation) {
    startNewContact();
    fillNewContactFields(contactData, creation);
    submitNewContact();
  }

  public boolean isThereContact() {
    return isElementPresent(By.name("selected[]"));
  }
  public int getContactCount() {
    return driver.findElements(By.name("selected[]")).size();
  }

  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<>();
    List<WebElement> elements = driver.findElements(By.name("entry"));
    for(WebElement element: elements){
      String name = element.getText();
      String id = element.findElement(By.name("selected[]")).getAttribute("value");
      ContactData contact = new ContactData(id, name, null, null, null, null);
      contacts.add(contact);
      //System.out.println(name);
    }
    return contacts;
  }
}
