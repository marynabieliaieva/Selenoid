package qa.Pages;

import dataProvider.ConfigFileReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.AssertJUnit.assertTrue;

public class LoginPage extends PageObject {

  @FindBy(id = "login-input-id")
  private WebElement loginField;

  @FindBy(css = "button.button-style-fullwidth:nth-child(2)")
  private WebElement nextLoginButton;

  @FindBy(css = ".login-input")
  private WebElement passwordField;

  @FindBy(css = "button.button-style-fullwidth:nth-child(3)")
  private WebElement submitButton;


  @FindBy(css = "button.button-style-fullwidth:nth-child(2)")
  private WebElement forgetButton;

  @FindBy(css = ".profile-widget__avatar")
  private WebElement profileAvatar;

  public LoginPage(WebDriver driver) {
    super(driver);
  }

  private long timeoutInSeconds;
  WebDriverWait wait = new WebDriverWait(driver, 50);

  ConfigFileReader configFileReader;

  public boolean isInitialized() {
    return this.loginField.isDisplayed();
  }

  public LoginPage enterLogin(String login) throws InterruptedException {
    this.loginField.clear();
    this.loginField.sendKeys(login);
    this.nextLoginButton.click();
    return this;
  }

  public LoginPage enterPassword(String password) throws InterruptedException {
    wait.until(ExpectedConditions.elementToBeClickable(this.passwordField));
    this.passwordField.clear();
    this.passwordField.sendKeys(password);
    this.submitButton.click();
    return this;
  }

  public LoginPage submit() {
    wait.until(ExpectedConditions.elementToBeClickable(this.forgetButton));
    this.forgetButton.click();
    wait.until(ExpectedConditions.elementToBeClickable(this.profileAvatar));
    assertTrue(this.profileAvatar.isDisplayed());
    return new MainPage(driver);
  }

}
