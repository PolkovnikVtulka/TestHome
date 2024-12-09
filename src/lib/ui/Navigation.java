package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class Navigation extends MainPageObject {
    private static final String
    GO_TO_SAVE = "//*[@text = 'Saved']";


    public Navigation(AppiumDriver driver) {
        super(driver);
    }

    public void goToMySave() {
        this.waitForElementAndClick(
                By.xpath(GO_TO_SAVE), "не перешли в сохраненые статьи", 5);

    }

}
