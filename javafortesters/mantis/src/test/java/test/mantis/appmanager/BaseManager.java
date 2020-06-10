package test.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class BaseManager {
  protected WebDriver driver;
  protected AppManager app;

  public BaseManager(AppManager app) {
    this.app = app;
    this.driver = app.getDriver();
  }

  protected void click(By locator) {
    driver.findElement(locator).click();
  }

  protected void type(By locator, String inputText) {
    click(locator);
    if(inputText != null) {
      String currentText = driver.findElement(locator).getAttribute("value");
      if(!currentText.equals(inputText)) {
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(inputText);
      }
    }
  }

  protected void attach (By locator, File file) {
    if(file != null) {
      driver.findElement(locator).sendKeys(file.getAbsolutePath());
    }
  }
  protected boolean isAlertPresent () {
    try{
      driver.switchTo().alert();
      return true;
    }
    catch (NoAlertPresentException e){
      return false;
    }
  }

  protected boolean isElementPresent(By locator) {
    try {
      driver.findElement(locator);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }
}