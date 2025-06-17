package Wallmart;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WallmartAutomation {

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            // 1. Navigate to Walmart
            driver.get("https://www.walmart.com");
            driver.manage().window().maximize();

            // 2. Search for product
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.id("global-search-input"))).sendKeys("Nintendo Switch" + Keys.RETURN);

            // 3. Get first product details
            WebElement firstProduct = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("div.search-result-product")));

            String title = firstProduct.findElement(By.cssSelector("a.product-title-link")).getText();
            String price = firstProduct.findElement(By.cssSelector("span.price-characteristic")).getText();

            System.out.println("Walmart Results:");
            System.out.println("Title: " + title);
            System.out.println("Price: $" + price);

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
