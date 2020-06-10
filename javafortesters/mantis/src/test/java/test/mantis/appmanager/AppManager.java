package test.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import test.mantis.testcases.TestBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class AppManager {
  private final Properties properties;
  private WebDriver driver;
  private String browser;
  private RegistrationManager registrationManager;
  private FtpManager ftp;
  private MailManager mailManager;

  public AppManager(String browser) {
    this.browser = browser;
    properties = new Properties();
  }

  public void initial() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
  }

  public void finish() {
    if (driver != null){
      driver.quit();
      driver = null;
    }
   }

  public HttpSession newSession() {
    return new HttpSession(this);
  }

  public String getProperty(String key) {
    return properties.getProperty(key);
  }

  public RegistrationManager registration() {
    if (registrationManager == null){
      registrationManager = new RegistrationManager(this);
    }
    return registrationManager;
  }

  public FtpManager ftp() {
    if (ftp == null){
      ftp = new FtpManager(this);
    }
    return ftp;
  }

  public WebDriver getDriver() {
    if (driver == null) {
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
    return driver;
  }

  public MailManager mail(){
    if (mailManager == null) {
      mailManager = new MailManager(this);
    }
    return mailManager;
  }
}

