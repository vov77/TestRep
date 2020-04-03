package test.selenium.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import test.selenium.model.ContactData;

import java.util.ArrayList;
import java.util.List;

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

  public void select(int index) {
    driver.findElements(By.name("selected[]")).get(index).click();
  }

  public void submitToDelete() {
    click(By.xpath("//input[@value='Delete']"));
    driver.switchTo().alert().accept();
  }

  public void edit(int index) {
    driver.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
  }

  public void update() {
    click(By.xpath("(//input[@name='update'])[2]"));
  }

  public void create(ContactData contactData, boolean creation) {
    startNew();
    fillNewFields(contactData, creation);
    submit();
  }

  public void modify(List<ContactData> before, ContactData contact) {
    edit(before.size() -1);
    fillNewFields(contact, false);
    update();
  }
  public void delete(int index) {
    select(index);
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
      //System.out.println(name);
    }
    return contacts;
  }
}
