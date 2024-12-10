package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject {

    //Константы
    private static final String
            BUTTON_SKIP = "//*[contains(@text,'Skip')]",
            SEARCH_INIT_ELEMENT = "//*[contains(@text, 'Search Wikipedia')]",
            SEARCH_INPUT = "org.wikipedia:id/search_src_text",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[contains(@text, '{SUBSTRING}')]",
            SEARCH_CANSEL_BUTTON = "Navigate up",
            SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL = "//*[(@text, '{TITLE}')]/following-sibling::*[contains(@text, '{DESCRIPTION}')]";


    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }


    private static String getSearchResultElementByText(String substing) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substing);
    }

    public void clickSkip() {
        this.waitForElementAndClick(By.xpath(BUTTON_SKIP), "не нажали на скип", 5);
    }

    public void initSearchInput() {
        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find search input after clicking search init element", 5);
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find and click search init element", 5);
    }

    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(By.id(SEARCH_CANSEL_BUTTON), "стрелка осталась на странице", 5);
    }

    public void clickCanselSearch() {
        this.waitForElementAndClick(By.id(SEARCH_CANSEL_BUTTON), "не нажали на кнопку стрелки", 5);
    }


    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(By.id(SEARCH_CANSEL_BUTTON), "нет стрелки", 5);
    }

    public void typeSearchLine(String searchLine) {
        this.waitForElementAndSendKeys(By.id(SEARCH_INPUT), searchLine, "не вписали в строку", 5);
    }

    public void waitForSearchResult(String substirng) {
        String searchResult = getSearchResultElementByText(substirng);
        this.waitForElementNotPresent(By.xpath(searchResult), "не видим нужной статьи" + substirng, 5);
    }

    public void clickByArticleWithSubstring(String substirng) {
        String searchResult = getSearchResultElementByText(substirng);
        this.waitForElementAndClick(By.xpath(searchResult), "не видим нужной статьи и не нажимаем на нее " + substirng, 5);
    }


    private static String getSearchResultElement(String title, String description) {
        return SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL.replace("{TITLE}", title).replace("{DESCRIPTION}", description);

    }

    public void waitForElementByTitleAndDescription(String title, String description) {
        String searchResultByXpath = getSearchResultElement(title, description);
        this.waitForElementPresent(By.xpath(searchResultByXpath), "не нашли статью с " + title + " и таким описанием " + description, 10);


    }


}
