package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class InventoryPage extends BasePage {
    private final By inventoryContainer = By.id("inventory_container");
    private final By addToCart = By.id("add-to-cart-sauce-labs-backpack");
    private final By removeBtn = By.id("remove-sauce-labs-backpack");
    private final By cartLink = By.className("shopping_cart_link");

    public boolean isInventoryPageDisplayed() {
        return driver.findElement(inventoryContainer).isDisplayed();
    }

    public void addBackpackToCart() {
        driver.findElement(addToCart).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(removeBtn));
    }

    public boolean isBackpackRemoveButtonDisplayed() {
        return driver.findElement(removeBtn).isDisplayed();
    }

    public void goToCart() {
        driver.findElement(cartLink).click();
    }
}
