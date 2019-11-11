package qa;


import dataProvider.ConfigFileReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import qa.Pages.LoginPage;

import java.net.MalformedURLException;
import java.net.URI;

import static org.testng.AssertJUnit.assertTrue;

public class LoginTest {

  LoginPage loginPage;
  ConfigFileReader configFileReader;

  @BeforeTest
  private RemoteWebDriver getRemoteWebDriver() throws MalformedURLException {
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setBrowserName("chrome");
    capabilities.setVersion("78.0");
    capabilities.setCapability("enableVNC", true);
    capabilities.setCapability("enableVideo", false);
    return new RemoteWebDriver(
            URI.create("http://192.168.0.115:4444/wd/hub").toURL(), capabilities);
  }


  @Test
  public void LoginTest() throws Exception {
    RemoteWebDriver driver = getRemoteWebDriver();
    configFileReader= new ConfigFileReader();
    driver.get(configFileReader.getApplicationUrl());
    driver.manage().window().maximize();
    LoginPage loginPage = new LoginPage(driver);
    assertTrue(loginPage.isInitialized());

    loginPage.enterLogin(configFileReader.getApplicationLogin())
            .enterPassword(configFileReader.getApplicationPassword())
            .submit();
  }




  @AfterTest
  public void TearDown() throws MalformedURLException {
    getRemoteWebDriver().quit();
  }
}
