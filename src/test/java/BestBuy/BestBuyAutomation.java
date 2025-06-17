package BestBuy;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BestBuyAutomation {

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            // 1. Navigate to Best Buy
            driver.get("https://www.bestbuy.com");
            driver.manage().window().maximize();

            // Close popup (if exists)
            try {
                driver.findElement(By.xpath("//button[contains(text(),'Continue')]")).click();
            } catch (Exception ignored) {}

            // 2. Search for product
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.id("gh-search-input"))).sendKeys("AirPods Pro" + Keys.RETURN);

            // 3. Select first product
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("div.sku-item"))).click();

            // 4. Add to cart
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("button.add-to-cart-button"))).click();

            // 5. View cart (but don't checkout)
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[contains(text(),'Go to Cart')]"))).click();

            System.out.println("SUCCESS: Added product to Best Buy cart!");

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}

