import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

public class Test4_3 {
    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("appium:automationName", "Appium");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appium:deviceName", "AndroidTestDevice"); // emulator-5554
        capabilities.setCapability("appium:platformVersion", "8.0");
        capabilities.setCapability("appium:appPackage", "org.wikipedia");
        capabilities.setCapability("appium:appActivity", ".main.MainActivity");
        capabilities.setCapability("appium:app", "C:/JavaAppiumAutomation/JavaAppiumAutomation/apks/org.wikipedia_50492_apps.evozi.com.apk");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities); // /wd/hub
    }

    @After
    public void tearDown() {
        driver.rotate(ScreenOrientation.PORTRAIT);
        driver.quit();
    }


    @Test
    public void testSwipe() {

        elementToClickSkip(By.xpath("//*[contains(@text,'Skip')]"));


        waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "не нашл строку на главном экране",
                5);

        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "не написали Java",
                5);

        waitForElementAndClick(By.xpath("//*[@text = 'Java (programming language)']"),
                "не нашли нужную статью",
                5);
        waitForElementAndClick(
                By.id("org.wikipedia:id/page_save"), "не сохранили", 5);
        waitForElementAndClick(
                By.id("Navigate up"), "не нажали на стролку", 5);
        waitForElementAndClick(
                By.xpath("//*[@text = 'Island in Indonesia']"), "не нашли вторую статью", 5);
        waitForElementAndClick(
                By.id("org.wikipedia:id/page_save"), "не сохранили вторую статью", 5);
        waitForElementAndClick(
                By.id("Navigate up"), "не нажали на стрелку второй раз", 5);
        waitForElementAndClick(
                By.id("Navigate up"), "не нажали на стрелку для выхода на главную страницу", 15);
        waitForElementAndClick(
                By.xpath("//*[@text = 'Saved']"), "не перешли в сохраненые статьи", 15);
        waitForElementAndClick(
                By.id("org.wikipedia:id/item_title_container"), "не нажали на папку", 15);
        swipeLeft(
                By.xpath("//*[@text = 'Island in Indonesia']"), "не удалили статью");
        waitForElementNotPresent(
                By.xpath("//*[@text = 'Island in Indonesia']"), "не удалили первую статью", 5);
        waitForElementAndClick(
                By.xpath("//*[@text = 'Java (programming language)']"), "не перешли в статью", 5);


        waitForElementPresent(By.id("pcs-edit-section-title-description"), "не увидели статью", 5);
        WebElement titleElement = waitForElementPresent(By.id("Object-oriented programming language"), "не увидили заголовок статьи", 5); // Object-oriented programming language
        String headerAttribute = titleElement.getAttribute("contentDescription");
        System.out.println(titleElement);
        System.out.println(headerAttribute);
        Assert.assertEquals("статьи разные", "Object-oriented programming language", headerAttribute);

        driver.rotate(ScreenOrientation.LANDSCAPE);


    }

    @Test
    public void assertElementPresentTitle() {

        elementToClickSkip(By.xpath("//*[contains(@text,'Skip')]"));

        waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "не нашл строку на главном экране",
                5);

        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "не написали Java",
                5);

        waitForElementAndClick(By.xpath("//*[@text = 'Java (programming language)']"),
                "не нашли нужную статью",
                5);

        assertElementPresent(By.xpath("//*[@content-desc = 'Java (programming language)']"), "не увидели статью");


    }


    private void elementToClickSkip(By by) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.withMessage("Элемент не найден" + "\n");
        WebElement skip = wait.until(ExpectedConditions.presenceOfElementLocated(by));
        skip.click();
    }

    private WebElement waitForElementPresent(By by, String errorMessage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElementAndClick(By by, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String errorMessage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    private WebElement waitElementAndClear(By by, String errorMassage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, errorMassage, timeoutInSeconds);
        element.clear();
        return element;
    }

    private void assertElementVisible(By by, String errorMessage) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(by, 1));
    }

    private void listOnePiece(By by) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        List<WebElement> searchItems = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        for (int i = 0; i < searchItems.size(); i++) {
            String popa = searchItems.get(i).getText();
            Assert.assertTrue("Элемант под номером " + i + " не содержит нужный текст", popa.contains("Java"));
        }

    }

    private void swipeUp(int timeSwap) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);
        action.press(x, start_y).waitAction(timeSwap).moveTo(x, end_y).release().perform();
    }

    private void swipeUpQuick() {
        swipeUp(200);
    }

    private void swipeUpAndFindElement(By by, String errorMassage, int maxSwipes) {
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

    public void swipeLeft(By by, String errorMassage) {
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

    public void assertElementPresent(By by, String errorMessage) {
        List element = driver.findElements(by);
        if (element.isEmpty()) {
            throw new AssertionError("Элемент отсутствует " + errorMessage);

        }


    }
}


