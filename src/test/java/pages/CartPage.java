package pages;

import org.openqa.selenium.By;

public class CartPage extends BasePage {
    private final By cartBadge = By.className("shopping_cart_badge");
    private final By checkoutBtn = By.id("checkout");

    public String getCartItemCount() {
        return driver.findElement(cartBadge).getText();
    }

    public void clickCheckout() {
        driver.findElement(checkoutBtn).click();
    }
}
