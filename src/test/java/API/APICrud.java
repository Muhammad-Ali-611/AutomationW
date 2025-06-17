package API;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.classic.methods.HttpPut;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class APICrud {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://demoqa.com/books");

            // CREATE (Add a book to collection)
            driver.findElement(By.id("login")).click();
            driver.findElement(By.id("userName")).sendKeys("testuser");
            driver.findElement(By.id("password")).sendKeys("Password123!");
            driver.findElement(By.id("login")).click();
            driver.findElement(By.id("see-book-Git Pocket Guide")).click();
            driver.findElement(By.cssSelector("button.text-right")).click();
            System.out.println("Book added to collection!");

            // READ (Check if book exists)
            boolean isBookDisplayed = driver.findElement(By.id("see-book-Git Pocket Guide")).isDisplayed();
            System.out.println("Is book visible? " + isBookDisplayed);

            // UPDATE (Change user profile)
            driver.findElement(By.id("userName-value")).click();
            driver.findElement(By.id("edit-profile")).click();
            driver.findElement(By.id("firstName")).clear();
            driver.findElement(By.id("firstName")).sendKeys("UpdatedName");
            driver.findElement(By.id("submit")).click();
            System.out.println("Profile updated!");

            // DELETE (Remove book from collection)
            driver.findElement(By.id("delete-record-undefined")).click();
            driver.findElement(By.id("closeSmallModal-ok")).click();
            System.out.println("Book deleted!");

        } finally {
            driver.quit();
        }
    }
}
