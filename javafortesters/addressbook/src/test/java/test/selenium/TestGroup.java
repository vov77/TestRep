package test.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
    driver.navigate().to("http://localhost/addressbook/");
    driver.findElement(By.name("user")).click();
    driver.findElement(By.name("user")).clear();
    driver.findElement(By.name("user")).sendKeys("admin");
    driver.findElement(By.name("pass")).click();
    driver.findElement(By.name("pass")).clear();
    driver.findElement(By.name("pass")).sendKeys("secret");
    driver.findElement(By.xpath("//input[@value='Login']")).click();

  }

  @Test
  public void createGroup(){

    //create new group
    driver.findElement(By.xpath("//a[contains(text(),'groups')]")).click();
    driver.findElement(By.xpath("//input[@name='new']")).click();
    driver.findElement(By.xpath("//input[@name='group_name']")).click();
    driver.findElement(By.xpath("//input[@name='group_name']")).sendKeys("Test100");
    driver.findElement(By.xpath("//textarea[@name='group_header']")).click();
    driver.findElement(By.xpath("//textarea[@name='group_header']")).sendKeys("Test110");
    driver.findElement(By.xpath("//textarea[@name='group_footer']")).click();
    driver.findElement(By.xpath("//textarea[@name='group_footer']")).sendKeys("Test111");
    driver.findElement(By.xpath("//input[@name='submit']")).click();
    //new group check
    driver.findElement(By.xpath("//a[contains(text(),'group page')]")).click();

    driver.findElement(By.xpath("//input[@name='new']")).click();




  }
  @AfterTest
  public void stop(){
    driver.close();
    driver = null;
    }
  }


