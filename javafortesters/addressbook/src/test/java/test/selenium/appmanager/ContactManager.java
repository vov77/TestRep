package test.selenium.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import test.selenium.model.ContactData;
import test.selenium.model.Contacts;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertFalse;
import static test.selenium.testcases.TestBase.getApp;

public class ContactManager extends BaseManager{

  public ContactManager(WebDriver driver) {
    super(driver);
  }
  private Contacts contactCache = null;
  public int count() {
    return driver.findElements(By.name("selected[]")).size();
  }

  public void startNew() {
    click(By.xpath("//a[contains(text(),'add new')]"));
  }
  public void submit() {
    click(By.xpath("//input[@name='submit']"));
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
    contactCache = null;
  }
  public void modify(ContactData contact) {
    selectById(contact.getId());
    edit(contact.getId());
    fillNewFields(contact, false);
    update();
    contactCache = null;
    getApp().goTo().contacts();
  }
  public void delete(ContactData contact) {
    selectById(contact.getId());
    submitToDelete();
    contactCache = null;
    getApp().goTo().contacts();
  }

  public void fillNewFields(ContactData contactData, boolean creation) {
    type(By.xpath("//input[@name='firstname']"), contactData.getFirstName());
    type(By.xpath("//input[@name='lastname']"), contactData.getLastName());
    type(By.xpath("//input[@name='company']"), contactData.getCompanyName());
    type(By.name("home"), contactData.getHomeNumber());
    type(By.name("mobile"), contactData.getMobileNumber());
    type(By.name("work"), contactData.getWorkNumber());
    attach(By.name("photo"), contactData.getPhoto()); //*[@id="content"]/form[1]/input[7]
    //select group dropdown
    if (creation) {
      new Select((driver.findElement(By.name("new_group")))).selectByVisibleText(contactData.getGroup());
    } else {
      assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  //list of contacts on a page
  public List<ContactData> list() {
    List<ContactData> contacts = new ArrayList<>();
    List<WebElement> elements = driver.findElements(By.name("entry"));
    for(WebElement element: elements){
      String name = element.getText();
      int id = Integer.parseInt(element.findElement(By.name("selected[]")).getAttribute("value"));
      ContactData contact = new ContactData().withId(id).withFirstName(name);
      contacts.add(contact);
    }
    return contacts;
  }

  //set of wrapped (decorated) contacts on a page
  public Contacts set() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> elements = driver.findElements(By.name("entry"));
    for(WebElement element: elements){
      int id = Integer.parseInt(element.findElement(By.name("selected[]")).getAttribute("value"));
      List<WebElement> cells = element.findElements(By.tagName("td"));
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      String address = cells.get(3).getText();
      String emails = cells.get(4).getText();
      //phone numbers split
      //String [] phoneNumber = cells.get(5).getText().split("\n");
      //ContactData contact = new ContactData(id, firstname, lastname, null, home, mobile, work);
      String allPhones = cells.get(5).getText();
      ContactData contact = new ContactData().withId(id).withFirstName(firstname).withLastName(lastname).withAllPhones(allPhones)
              .withAddress(address).withEmails(emails);
      contactCache.add(contact);
    }
    return new Contacts(contactCache);
  }

  //get contact info from Edit page
  public ContactData infoFromEdit(ContactData contact) {
    intitModById(contact.getId());
    String firstName = driver.findElement(By.name("firstname")).getAttribute("value");
    String lastName = driver.findElement(By.name("lastname")).getAttribute("value");
    String home = driver.findElement(By.name("home")).getAttribute("value");
    String mobile = driver.findElement(By.name("mobile")).getAttribute("value");
    String work = driver.findElement(By.name("work")).getAttribute("value");
    String address = driver.findElement(By.name("address")).getText();
    String email1 = driver.findElement(By.name("email")).getAttribute("value");
    String email2 = driver.findElement(By.name("email2")).getAttribute("value");
    String email3 = driver.findElement(By.name("email3")).getAttribute("value");
    driver.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstName(firstName).withLastName(lastName)
            .withHomeNumber(home).withMobileNumber(mobile).withWorkNumber(work).withAddress(address)
            .withEmail1(email1).withEmail2(email2).withEmail3(email3);
  }

  //find by contact id and click on Edit icon
  private void intitModById(int id) {
    //driver.findElement(By.xpath(String.format("//input[@value='%s']/../..td[8]/a", id))).click();
    //driver.findElement(By.xpath(String.format("//tr[.//input[@value='%s']]/td[8]/a", id))).click();
    driver.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
  }

  public ContactData infoFromInfoPage(ContactData contact) {
    intitInfoById(contact.getId());
    String infoText = driver.findElement(By.id("content")).getText();
    driver.navigate().back();
    return new ContactData().withId(contact.getId()).withInfoPageText(infoText);
  }
  private void intitInfoById(int id) {
    driver.findElement(By.cssSelector(String.format("a[href='view.php?id=%s']", id))).click();
  }
}
