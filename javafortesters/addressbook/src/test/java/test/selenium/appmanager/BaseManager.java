package test.selenium.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class BaseManager {
  protected WebDriver driver;

  public BaseManager(WebDriver driver) {
    this.driver = driver;
  }

  public void click(By locator) {
    driver.findElement(locator).click();
  }

  public void type(By locator, String inputText) {
    click(locator);
    if(inputText != null) {
      String currentText = driver.findElement(locator).getAttribute("value");
      if(!currentText.equals(inputText)) {
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(inputText);
      }
    }
  }

  public void attach (By locator, File file) {
    if(file != null) {
        driver.findElement(locator).sendKeys(file.getAbsolutePath());
    }
  }
  public boolean isAlertPresent () {
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
