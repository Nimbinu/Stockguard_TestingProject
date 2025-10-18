package com.example.tests.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class ProductTests {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void verifyExistingProductDisplayed() {
        // Step 1: Go to the Capstone admin "Existing Product" page
        driver.get("http://localhost/capstone/admin/existing-product.php");

        // OPTIONAL: login step (uncomment if login is required)
        /*
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("admin123");
        driver.findElement(By.id("loginButton")).click();
        wait.until(ExpectedConditions.urlContains("existing-product.php"));
        */

        // Step 2: Wait for the product table to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("table")));

        // Step 3: Define the product name you want to search
        String productName = "Laptop"; // change this to your actual product name in DB/UI

        // Step 4: Check if the product name exists in the table
        boolean productFound = driver.findElements(
                By.xpath("//*[@id=\"signin-btn\"]'" + productName + "')]")
        ).size() > 0;

        System.out.println("Checking for product: " + productName);
        System.out.println("Product found? " + productFound);

        // Step 5: Validate the result
        Assert.assertTrue(productFound, "❌ Product '" + productName + "' not found in existing-product list!");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
