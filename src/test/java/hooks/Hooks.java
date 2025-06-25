package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import pages.BasePage;

public class Hooks {
    @Before
    public void setUp() {
        // Already initialized in BasePage
    }

    @After
    public void tearDown() {
        if (BasePage.driver != null) {
            BasePage.driver.quit();
        }
    }
}
