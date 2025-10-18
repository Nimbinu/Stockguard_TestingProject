package com.example.tests.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ExistingProductPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private String pageUrl = "http://localhost/capstone/admin/existing-product.php";

    // Example selectors — change to match your HTML
    private By productTable = By.cssSelector("table#productTable"); // or .table
    private By productRows = By.cssSelector("table#productTable tbody tr");
    private By addProductBtn = By.id("addProduct"); // if present
    private By searchInput = By.id("search"); // if present

    public ExistingProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open() {
        driver.get(pageUrl);
        wait.until(ExpectedConditions.visibilityOfElementLocated(productTable));
    }

    public int getProductCount() {
        List<WebElement> rows = driver.findElements(productRows);
        return rows.size();
    }

    public boolean isTablePresent() {
        try {
            return driver.findElement(productTable).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void clickEditForRow(int rowIndex) {
        // Example: assume each row has .edit-btn
        List<WebElement> rows = driver.findElements(productRows);
        if (rowIndex < 0 || rowIndex >= rows.size()) {
            throw new IllegalArgumentException("Invalid row index");
        }
        WebElement editBtn = rows.get(rowIndex).findElement(By.cssSelector(".edit-btn"));
        editBtn.click();
    }

    public void clickDeleteForRow(int rowIndex) {
        List<WebElement> rows = driver.findElements(productRows);
        WebElement deleteBtn = rows.get(rowIndex).findElement(By.cssSelector(".delete-btn"));
        deleteBtn.click();
        // handle confirm dialog if any:
        try {
            driver.switchTo().alert().accept();
        } catch (NoAlertPresentException ignored) {}
    }

    // Example to search for a product name in table rows
    public boolean isProductPresentByName(String productName) {
        List<WebElement> rows = driver.findElements(productRows);
        for (WebElement r : rows) {
            if (r.getText().contains(productName)) {
                return true;
            }
        }
        return false;
    }
}
