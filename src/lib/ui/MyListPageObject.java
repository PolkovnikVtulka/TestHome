package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListPageObject extends MainPageObject {
    public static final String
    JUMP_TO_LIST = "org.wikipedia:id/item_title_container",
    ARTICLE_BY_TITLE_TPL ="//*[@text = '{TITLE}']";


    public MyListPageObject(AppiumDriver driver){
        super(driver);
    }
    private static String getSaveArticleXpath (String articleTitle){
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}",articleTitle);

    }
    public void clickOnPage(){
        this.waitForElementAndClick(
                By.id(JUMP_TO_LIST), "не нажали на папку", 15);
    }
    public void deleteArticle(String articleTitle){
        String articleTitleByXpath = getSaveArticleXpath(articleTitle);
        this.swipeLeft(By.xpath(articleTitleByXpath), "не удалили статью");
    }
    public void articleNoPresent(String articleTitle){
        this.waitForElementNotPresent(By.xpath(articleTitle),"статья не удалилась",5);
    }

}
