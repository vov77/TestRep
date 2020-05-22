package test.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class AppManager {
  private final Properties properties;
  public WebDriver driver;
  public WebDriverWait wait;
  public String browser;


  public AppManager(String browser) {
    this.browser = browser;
    properties = new Properties();
  }

  public void initial() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

    switch (browser) {
      case BrowserType.CHROME:
        driver = new ChromeDriver();
        break;
      case BrowserType.EDGE:
        driver = new EdgeDriver();
        break;
      case BrowserType.FIREFOX:
        driver = new FirefoxDriver();
        break;
    }

    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.get(properties.getProperty("web.baseUrl"));
  }

  public void finish() {
    driver.close();
    driver = null;
  }

}

