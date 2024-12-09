package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    private static final String
            TITLE = "//*[@content-desc = 'Object-oriented programming language']",
            FOOTER = "CC BY-SA 4.0",
            BUTTON_SAVE = "org.wikipedia:id/page_save",
            SEARCH_CANSEL_BUTTON = "Navigate up";

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(By.xpath(TITLE), "не нашли статью", 5);
    }

    public String getArticleTitle() {
        WebElement titleElement = waitForTitleElement();
        return titleElement.getAttribute("contentDescription");
    }

    public void swipeFooter() {
        this.swipeUpAndFindElement(By.id(FOOTER), "не нашли футер", 20);
        System.out.println(FOOTER);
    }

    public void addArticleToMtList() {
        this.waitForElementAndClick(By.id(BUTTON_SAVE),"не сохранили статью",5);

    }
    public void closeArticle(){
        this.waitForElementAndClick(By.id(SEARCH_CANSEL_BUTTON),"не нажали на стрелку",5);
        this.waitForElementAndClick(By.id(SEARCH_CANSEL_BUTTON),"не нажали на стрелку",5);
    }
}
