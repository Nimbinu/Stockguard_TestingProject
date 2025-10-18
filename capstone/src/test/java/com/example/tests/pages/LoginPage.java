package com.example.tests.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By usernameField = By.name("email");
    private By passwordField = By.name("password");
    private By loginBtn = By.id("signin-btn");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open() {
        driver.get("http://localhost/capstone/login.php");
    }

    public void loginAs(String user, String pass) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField)).clear();
        driver.findElement(usernameField).sendKeys(user);

        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(pass);

        driver.findElement(loginBtn).click();
    }

    public boolean isLoggedIn() {
        try {
            wait.until(ExpectedConditions.urlContains("dashboard.php"));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
