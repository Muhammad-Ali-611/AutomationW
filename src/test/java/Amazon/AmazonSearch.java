package Amazon;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AmazonSearch {

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.amazon.in/");
        try {
            Thread.sleep(3000);

            WebElement search = driver.findElement(By.id("twotabsearchtextbox"));
            search.sendKeys("iPhone 15");
            driver.findElement(By.id("nav-search-submit-button")).click();

            Thread.sleep(4000);

            WebElement firstProduct = driver.findElement(By.cssSelector("div.s-main-slot div[data-component-type='s-search-result']"));
            String title = firstProduct.findElement(By.cssSelector("h2 span")).getText();
            String price = firstProduct.findElement(By.cssSelector(".a-price .a-offscreen")).getText();

            System.out.println("Amazon Result:");
            System.out.println("Title: " + title);
            System.out.println("Price: " + price);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
