package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPageHelper extends PageBase {

    @FindBy(css=".text-primary")
    WebElement loginIcon;
    @FindBy(id = "login")
    WebElement loginButton;
    @FindBy(id = "user")
    WebElement emailField;
    @FindBy(id = "password")
    WebElement passwordField;
    @FindBy(id = "login-submit") //xpath = "//button[@id='login-submit']") "//input[@id='password']") // "//span[contains(text(),'Log in')]")
    WebElement submitLoginWithAtlassianButton;
    @FindBy(css = "p.error-message")
    WebElement errorMessage;

    public LoginPageHelper(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPageHelper openPage() {
        log4j.startMethod("LoginPageHelper() - openPage()");
        log4j.info("wait for 'Log In' to be clickable");
        waitUntilElementIsClickable(loginIcon, 30);
        loginIcon.click();
        log4j.endMethod("LoginPageHelper() - openPage()");
        return this;
    }

    public LoginPageHelper waitUntilLoginPageIsLoaded() {
        log4j.startMethod("LoginPageHelper() - waitUntilLoginPageIsLoaded()");
        log4j.info("wait for 'Log in' button to be clickable");
        waitUntilElementIsClickable(loginButton, 30);
        log4j.endMethod("LoginPageHelper() - waitUntilLoginPageIsLoaded()");
        return this;
    }

    public void login(String login, String password) {
        log4j.startMethod("LoginPageHelper() - login()");
        fillInEmailField(login);
        fillInPasswordField(password);
        submitLogin();
        log4j.endMethod("LoginPageHelper() - login()");
    }

    public void loginAtlassian(String login, String password) {
        log4j.startMethod("LoginPageHelper() - loginAtlassian()");
        fillInEmailField(login);
        submitLoginAtlassian();
        fillInPasswordAtlassianField(password);
        submitPasswordAtlassian();
        log4j.endMethod("LoginPageHelper() - loginAtlassian()");
    }

    public void fillInEmailField(String value) {
        log4j.startMethod("LoginPageHelper() - fillInEmailField()");
        log4j.info("wait for 'Log in' button to be clickable");
        waitUntilElementIsClickable(loginButton, 20);
        editField(emailField, value);
        log4j.endMethod("LoginPageHelper() - fillInEmailField()");
    }

    public void fillInPasswordField(String value) {
        log4j.startMethod("LoginPageHelper() - fillInPasswordField()");
        log4j.info("wait for 'password' field to be clickable");
        waitUntilElementIsClickable(passwordField, 20);
        editField(passwordField, value);
        log4j.endMethod("LoginPageHelper() - fillInPasswordField()");
    }

    public void submitLogin() {
        log4j.startMethod("LoginPageHelper() - submitLogin()");
        log4j.info("wait for 'Log in' button to be clickable");
        waitUntilElementIsClickable(loginButton, 20);
        loginButton.click();
        log4j.endMethod("LoginPageHelper() - submitLogin()");
    }

    public void submitLoginAtlassian() {
        log4j.startMethod("LoginPageHelper() - submitLoginAtlassian()");
        log4j.info("wait for 'Log in with Atlassian' button to be clickable");
        waitUntilElementIsClickable(loginButton,5);
        loginButton.click();
        log4j.endMethod("LoginPageHelper() - submitLoginAtlassian()");
    }

    public void fillInPasswordAtlassianField(String value) {
        log4j.startMethod("LoginPageHelper() - fillInPasswordAtlassianField()");
        log4j.info("wait for 'Log in' button to be clickable");
        waitUntilElementIsClickable(submitLoginWithAtlassianButton, 30);
        editField(passwordField, value);
        log4j.endMethod("LoginPageHelper() - fillInPasswordAtlassianField()");
    }

    public void submitPasswordAtlassian() {
        log4j.startMethod("LoginPageHelper() - submitPasswordAtlassian()");
        log4j.info("wait for 'Log in' button to be clickable");
        waitUntilElementIsClickable(submitLoginWithAtlassianButton, 20);
        submitLoginWithAtlassianButton.click();
        log4j.endMethod("LoginPageHelper() - submitPasswordAtlassian()");
    }

    public String getErrorMessage() {
        log4j.startMethod("LoginPageHelper - getErrorMessage()");
        log4j.info("wait for the error message to be visible");
        waitUntilElementIsVisible(errorMessage, 10);
        log4j.info("return error message");
        log4j.endMethod("LoginPageHelper() - getErrorMessage()");
        return errorMessage.getText();
    }

}
