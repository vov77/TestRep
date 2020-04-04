package test.selenium.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import test.selenium.model.ContactData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.assertFalse;
import static test.selenium.testcases.TestBase.getApp;

public class ContactManager extends BaseManager{
  public ContactManager(WebDriver driver) {
    super(driver);
  }

  public void startNew() {
    click(By.xpath("//a[contains(text(),'add new')]"));
  }

  public void submit() {
    click(By.xpath("//input[@name='submit']"));
  }

  public void fillNewFields(ContactData contactData, boolean creation) {
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

  public void selectById(int id) {
    driver.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void submitToDelete() {
    click(By.xpath("//input[@value='Delete']"));
    driver.switchTo().alert().accept();
  }

  public void edit(int id) {
    driver.findElement(By.cssSelector("a[href='" + "edit.php?id=" + id + "']")).click();
  }

  public void update() {
    click(By.xpath("(//input[@name='update'])[2]"));
  }

  public void create(ContactData contactData, boolean creation) {
    startNew();
    fillNewFields(contactData, creation);
    submit();
  }

  public void modify(ContactData contact) {
    selectById(contact.getContactId());
    edit(contact.getContactId());
    fillNewFields(contact, false);
    update();
  }

  public void delete(ContactData contact) {
    selectById(contact.getContactId());
    submitToDelete();
    getApp().goTo().contacts();
  }
  public List<ContactData> list() {
    List<ContactData> contacts = new ArrayList<>();
    List<WebElement> elements = driver.findElements(By.name("entry"));
    for(WebElement element: elements){
      String name = element.getText();
      int id = Integer.parseInt(element.findElement(By.name("selected[]")).getAttribute("value"));
      ContactData contact = new ContactData(id, name, null, null, null, null);
      contacts.add(contact);
    }
    return contacts;
  }
  public Set<ContactData> set() {
    Set<ContactData> contacts = new HashSet<>();
    List<WebElement> elements = driver.findElements(By.name("entry"));
    for(WebElement element: elements){
      String name = element.getText();
      int id = Integer.parseInt(element.findElement(By.name("selected[]")).getAttribute("value"));
      ContactData contact = new ContactData(id, name, null, null, null, null);
      contacts.add(contact);
    }
    return contacts;
  }

}
