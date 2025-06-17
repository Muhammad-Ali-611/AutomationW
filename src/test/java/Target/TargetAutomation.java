package Target;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class TargetAutomation {

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            // 1. Navigate to Target
            driver.get("https://www.target.com");
            driver.manage().window().maximize();

            // 2. Search for product
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.id("search"))).sendKeys("Coffee Maker" + Keys.RETURN);

            // 3. Take screenshot of results
            File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileHandler.copy(screenshot, new File("target_search_results.png"));
            System.out.println("Screenshot saved!");

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
