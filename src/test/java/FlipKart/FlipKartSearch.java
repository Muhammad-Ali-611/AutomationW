package FlipKart;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FlipKartSearch {

    public static void main(String[] args)  {
            // Setup WebDriver using WebDriverManager
            WebDriverManager.chromedriver().setup();
            WebDriver driver = new ChromeDriver();

            try {
                // Navigate to Flipkart
                driver.get("https://www.flipkart.com/");
                driver.manage().window().maximize();

                // Wait for page to load
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

                // Close login popup if present
                try {
                    WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button._2KpZ6l._2doB4z")));
                    closeButton.click();
                } catch (Exception e) {
                    System.out.println("Login popup not found or already closed");
                }

                // Search for iPhone 15
                WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));
                searchBox.sendKeys("iPhone 15");
                searchBox.submit();

                // Wait for search results
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div._1AtVbE section div._13oc-S")));

                // Get first product details
                WebElement firstProduct = driver.findElements(By.cssSelector("div._1AtVbE section div._13oc-S")).get(0);

                String title = firstProduct.findElement(By.cssSelector("div._4rR01T")).getText();
                String price = firstProduct.findElement(By.cssSelector("div._30jeq3._1_WHN1")).getText();

                // Print results
                System.out.println("\nFlipkart Search Results:");
                System.out.println("-----------------------");
                System.out.println("Title: " + title);
                System.out.println("Price: " + price);

            } catch (Exception e) {
                System.err.println("Error during Flipkart search:");
                e.printStackTrace();
            } finally {
                // Quit the driver
                driver.quit();
            }
        }
    }

