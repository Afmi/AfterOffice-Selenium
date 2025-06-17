package selenium;

import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class LocatorSuccessLoginScenario {
    public WebDriver webDriver;

    @BeforeSuite
    public void startBrowser() {
        System.out.println("Browser Start...");

        // Dapatkan path absolut ke chromedriver
        String projectPath = System.getProperty("user.dir");
        String driverPath = projectPath + "\\drivers\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", driverPath);

        // Konfigurasi ChromeOptions
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--incognito"); // opsional: untuk bersihkan cache
        options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
        options.setExperimentalOption("prefs", Map.of(
                "credentials_enable_service", false,
                "profile.password_manager_enabled", false));

        // Buat ChromeDriver dengan options
        webDriver = new ChromeDriver(options);

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

    @Test(dependsOnMethods = "loginSuccess")
    public void addItemToCart() {
        // Tambah item backpack ke cart
        WebElement addToCartBtn = webDriver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        addToCartBtn.click();

        // Verifikasi tombol berubah menjadi Remove
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement removeBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("remove-sauce-labs-backpack")));
        assert removeBtn.isDisplayed();
        System.out.println("Item berhasil ditambahkan ke cart.");
    }

    @Test(dependsOnMethods = "addItemToCart")
    public void goToCartPage() {
        // Klik ikon cart
        WebElement cartLink = webDriver.findElement(By.className("shopping_cart_link"));
        cartLink.click();

        // Verifikasi jumlah item di cart
        WebElement cartBadge = webDriver.findElement(By.className("shopping_cart_badge"));
        assert cartBadge.getText().equals("1");
        System.out.println("Navigasi ke halaman cart berhasil, dan item terlihat.");
    }

    @Test(dependsOnMethods = "goToCartPage")
    public void completeCheckoutProcess() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

        // Step 1: Klik tombol Checkout
        WebElement checkoutButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("checkout")));
        checkoutButton.click();

        // Step 2: Isi informasi pengguna
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name"))).sendKeys("John");
        webDriver.findElement(By.id("last-name")).sendKeys("Doe");
        webDriver.findElement(By.id("postal-code")).sendKeys("12345");

        // Step 3: Klik tombol Continue
        WebElement continueBtn = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("continue")));
        continueBtn.click();

        // Step 4: Klik tombol Finish
        WebElement finishBtn = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("finish")));
        finishBtn.click();

        System.out.println("✅ Checkout berhasil diselesaikan.");
    }

    // @AfterSuite
    // public void tearDown() {
    // if (webDriver != null) {
    // webDriver.quit();
    // }
    // System.out.println("Browser Closed.");
    // }
}
