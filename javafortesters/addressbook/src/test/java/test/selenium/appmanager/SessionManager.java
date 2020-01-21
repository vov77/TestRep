package test.selenium.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionManager extends BaseManager{


  public SessionManager(WebDriver driver) {

    super(driver);
  }

  public void login(String username, String password) {
    driver.navigate().to("http://localhost/addressbook/");
    type(By.name("user"), username);
    type(By.name("pass"), password );
    click(By.xpath("//input[@value='Login']"));
  }
}
