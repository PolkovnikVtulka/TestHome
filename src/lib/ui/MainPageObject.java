package lib.ui;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class MainPageObject {

    protected static AppiumDriver driver;
    public MainPageObject(AppiumDriver driver){
        this.driver = driver;
    }
    public void elementToClickSkip(By by) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.withMessage("Элемент не найден" + "\n");
        WebElement skip = wait.until(ExpectedConditions.presenceOfElementLocated(by));
        skip.click();
    }

    public static WebElement waitForElementPresent(By by, String errorMessage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement waitForElementAndClick(By by, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(By by, String value, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public boolean waitForElementNotPresent(By by, String errorMessage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public WebElement waitElementAndClear(By by, String errorMassage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, errorMassage, timeoutInSeconds);
        element.clear();
        return element;
    }

    public void assertElementVisible(By by, String errorMessage) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(by, 1));
    }

    public void listOnePiece(By by) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        List<WebElement> searchItems = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        for (int i = 0; i < searchItems.size(); i++) {
            String popa = searchItems.get(i).getText();
            Assert.assertTrue("Элемант под номером " + i + " не содержит нужный текст", popa.contains("Java"));
        }

    }

    public void swipeUp(int timeSwap) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);
        action.press(x, start_y).waitAction(timeSwap).moveTo(x, end_y).release().perform();
    }

    public void swipeUpQuick() {
        swipeUp(200);
    }

    public void swipeUpAndFindElement(By by, String errorMassage, int maxSwipes) {
        int alreadySwipes = 0;
        while (driver.findElements(by).isEmpty()) {

            if (alreadySwipes > maxSwipes) {
                waitForElementPresent(by, "не прокрутили. \n" + errorMassage, 0);
                return;
            }
            swipeUpQuick();
            ++alreadySwipes;
        }

    }

    public static void swipeLeft(By by, String errorMassage) {
        WebElement element = waitForElementPresent(by, errorMassage, 5);

        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(150)
                .moveTo(left_x, middle_y)
                .release()
                .perform();
    }

    private int getAmountOfElements(By by) {
        List elemets = driver.findElements(by);
        return elemets.size();
    }

    private void assertElementsNoPresent(By by, String errorMassage) {
        int amountOfElements = getAmountOfElements(by);
        if (amountOfElements > 0) {
            String defaultMessage = "An element " + by.toString() + "supposed to be no present";
            throw new AssertionError(defaultMessage + "" + errorMassage);

        }
    }

    private String waitForElementAndGetAttribute(By by, String attribute, String errorMassage, long timeoutOnSeconds) {
        WebElement element = waitForElementPresent(by, errorMassage, timeoutOnSeconds);
        System.out.println(element.getAttribute("contentDescription"));
        System.out.println(element.getAttribute("resourceId"));
        return element.getAttribute(attribute);
    }

    public static void assertElementPresent(By by, String errorMessage) {
        List element = driver.findElements(by);
        if (element.isEmpty()) {
            throw new AssertionError("Элемент отсутствует " + errorMessage);

        }


    }


}
