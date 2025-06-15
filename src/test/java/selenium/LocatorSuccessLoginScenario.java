package selenium;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class LocatorSuccessLoginScenario {
    public WebDriver webDriver;

    @BeforeSuite
    public void startBrowser() {
        System.out.println("Browser Start...");
        System.setProperty("webdriver.chrome.driver",
                "C:\\Project\\afteroffice-selenium\\Rest-Assured-TestNG\\drivers\\chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        webDriver.get("https://www.saucedemo.com/");
    }

    @Test
    public void loginSuccess() {
        WebElement usernameInput = webDriver.findElement(By.id("user-name"));
        WebElement passwordInput = webDriver.findElement(By.id("password"));
        WebElement loginButton = webDriver.findElement(By.id("login-button"));

        usernameInput.sendKeys("standard_user");
        passwordInput.sendKeys("secret_sauce");
        loginButton.click();

        // Verifikasi: user diarahkan ke halaman beranda (inventory page)
        WebElement inventoryContainer = webDriver.findElement(By.id("inventory_container"));
        assert inventoryContainer.isDisplayed();
    }

    @AfterSuite
    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
        System.out.println("Browser Closed.");
    }
}
