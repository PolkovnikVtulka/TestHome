package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject{

    private static final String
    TITLE = "//*[@content-desc = 'Object-oriented programming language']",
    FOOTER = "CC BY-SA 4.0";

    public ArticlePageObject (AppiumDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement(){
        return this.waitForElementPresent(By.xpath(TITLE),"не нашли статью",5);
    }

    public String getArticleTitle (){
        WebElement titleElement = waitForTitleElement();
        return titleElement.getAttribute("contentDescription");
    }

    public void swipeFooter (){
        this.swipeUpAndFindElement(By.id(FOOTER),"не нашли футер",20);
        System.out.println(FOOTER);
    }
}
