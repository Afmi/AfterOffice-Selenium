package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutPage extends BasePage {
    private final By firstName = By.id("first-name");
    private final By lastName = By.id("last-name");
    private final By postalCode = By.id("postal-code");
    private final By continueBtn = By.id("continue");
    private final By finishBtn = By.id("finish");
    private final By confirmation = By.className("complete-header");

    public void enterCheckoutInformation(String first, String last, String postal) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstName)).sendKeys(first);
        driver.findElement(lastName).sendKeys(last);
        driver.findElement(postalCode).sendKeys(postal);
        driver.findElement(continueBtn).click();
    }

    public void completeCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(finishBtn)).click();
    }

    public boolean isCheckoutComplete() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(confirmation)).isDisplayed();
    }
}
