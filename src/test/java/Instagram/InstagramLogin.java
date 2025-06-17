package Instagram;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.Architecture;
import io.github.bonigarcia.wdm.config.OperatingSystem;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class InstagramLogin {

    public static void main(String[] args) {
        // ⛔ NO System.setProperty()
        // ✅ Force WebDriverManager to auto-select ARM64 driver
        WebDriverManager.chromedriver()
                .operatingSystem(OperatingSystem.MAC)
                .architecture(Architecture.ARM64)
                .setup();

        WebDriver driver = new ChromeDriver();

        driver.get("https://www.Instagram.com/accounts/login/");

        try {
            Thread.sleep(5000); // use WebDriverWait in real world

            WebElement username = driver.findElement(By.name("username"));
            WebElement password = driver.findElement(By.name("password"));
            WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));

            username.sendKeys("alimuzammal36@gmail.com");
            password.sendKeys("Hafiz123@");
            loginButton.click();

            Thread.sleep(8000); // pause to let user see result
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
