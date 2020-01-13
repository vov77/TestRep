package test.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestGroup {
  private WebDriver driver;
  private WebDriverWait wait;

  @BeforeTest

  public void start() {
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    wait = new WebDriverWait(driver,10);
    //login
    login("admin", "secret");

  }

  public void login(String username, String password) {
    driver.navigate().to("http://localhost/addressbook/");
    driver.findElement(By.name("user")).click();
    driver.findElement(By.name("user")).clear();
    driver.findElement(By.name("user")).sendKeys(username);
    driver.findElement(By.name("pass")).click();
    driver.findElement(By.name("pass")).clear();
    driver.findElement(By.name("pass")).sendKeys(password);
    driver.findElement(By.xpath("//input[@value='Login']")).click();
  }

  @Test
  public void createNewGroup(){

    gotoGroups();
    startNewGroup();
    fillNewGroupFields(new GroupData("Test100", "Test110", "Test111"));
    submitNewGroup();
    gotoGroups();

    startNewGroup();


  }

  public void submitNewGroup() {
    driver.findElement(By.xpath("//input[@name='submit']")).click();
  }

  public void fillNewGroupFields(GroupData groupData) {
    driver.findElement(By.xpath("//input[@name='group_name']")).click();
    driver.findElement(By.xpath("//input[@name='group_name']")).sendKeys(groupData.getGroupName());
    driver.findElement(By.xpath("//textarea[@name='group_header']")).click();
    driver.findElement(By.xpath("//textarea[@name='group_header']")).sendKeys(groupData.getGroupHeader());
    driver.findElement(By.xpath("//textarea[@name='group_footer']")).click();
    driver.findElement(By.xpath("//textarea[@name='group_footer']")).sendKeys(groupData.getGroupFooter());
  }

  public void startNewGroup() {
    driver.findElement(By.xpath("//input[@name='new']")).click();
  }

  public void gotoGroups() {
    driver.findElement(By.xpath("//a[contains(text(),'groups')]")).click();
  }

  @AfterTest
  public void stop(){
    driver.close();
    driver = null;
    }
  }


