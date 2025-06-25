package stepdefinitions;

import io.cucumber.java.en.*;
import pages.*;
import org.junit.Assert;

public class LoginStepDefinitions {

    LoginPage loginPage = new LoginPage();
    InventoryPage inventoryPage = new InventoryPage();
    CartPage cartPage = new CartPage();
    CheckoutPage checkoutPage = new CheckoutPage();

    @Given("User opens the browser and navigates to the login page")
    public void openBrowser() {
        loginPage.openLoginPage();
    }

    @When("User logs in with username {string} and password {string}")
    public void login(String username, String password) {
        loginPage.login(username, password);
        Assert.assertTrue(inventoryPage.isInventoryPageDisplayed());
    }

    @When("User adds backpack item to cart")
    public void addItem() {
        inventoryPage.addBackpackToCart();
        Assert.assertTrue(inventoryPage.isBackpackRemoveButtonDisplayed());
    }

    @When("User navigates to the cart page")
    public void goToCart() {
        inventoryPage.goToCart();
        Assert.assertEquals("1", cartPage.getCartItemCount());
    }

    @When("User completes the checkout process")
    public void checkout() {
        cartPage.clickCheckout();
        checkoutPage.enterCheckoutInformation("John", "Doe", "12345");
        checkoutPage.completeCheckout();
    }

    @Then("Checkout should be successful")
    public void checkoutSuccess() {
        Assert.assertTrue(checkoutPage.isCheckoutComplete());
    }
}
