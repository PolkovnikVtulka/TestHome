import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;


public class TestHome extends CoreTestCase {

    public MainPageObject MainPageObject;

    public void setUp() throws Exception {
        super.setUp();

        MainPageObject = new MainPageObject(driver);
    }

    @Test
    public void testSearch() {

        SearchPageObject SearchpageObject = new SearchPageObject(driver);
        SearchpageObject.clickSkip();
        SearchpageObject.initSearchInput();
        SearchpageObject.typeSearchLine("Java");
    }

    @Test
    public void testCancelSearch() {

        SearchPageObject SearchpageObject = new SearchPageObject(driver);
        SearchpageObject.clickSkip();
        SearchpageObject.initSearchInput();
        SearchpageObject.typeSearchLine("Java");
        SearchpageObject.waitForCancelButtonToAppear();
        SearchpageObject.clickCanselSearch();
        SearchpageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testCompareArticleTitle() {

        SearchPageObject SearchpageObject = new SearchPageObject(driver);
        ArticlePageObject ArticlePageobject = new ArticlePageObject(driver);

        SearchpageObject.clickSkip();
        SearchpageObject.initSearchInput();
        SearchpageObject.typeSearchLine("Java");
        SearchpageObject.waitForSearchResult("Object-oriented programming language");
        SearchpageObject.clickByArticleWithSubstring("Object-oriented programming language");
        String article_title = ArticlePageobject.getArticleTitle();
        Assert.assertEquals("не совпадают", "Object-oriented programming language", article_title);
    }

    @Test
    public void testSwipe() {
        SearchPageObject SearchpageObject = new SearchPageObject(driver);
        ArticlePageObject ArticlePageobject = new ArticlePageObject(driver);

        SearchpageObject.clickSkip();
        SearchpageObject.initSearchInput();
        SearchpageObject.typeSearchLine("Appium");
        SearchpageObject.clickByArticleWithSubstring("Automation for Apps");
        ArticlePageobject.swipeFooter();
    }

    @Test
    public void testSave() {

        SearchPageObject SearchpageObject = new SearchPageObject(driver);
        ArticlePageObject ArticlePageobject = new ArticlePageObject(driver);
        Navigation Navigation = new Navigation(driver);
        MyListPageObject MyListPageObject = new MyListPageObject(driver);
        SearchpageObject.clickSkip();
        SearchpageObject.initSearchInput();
        SearchpageObject.typeSearchLine("Java");
        SearchpageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageobject.addArticleToMtList();
        ArticlePageobject.closeArticle();
        Navigation.goToMySave();
        MyListPageObject.clickOnPage();
        MyListPageObject.deleteArticle("Java (programming language)");
        MyListPageObject.articleNoPresent("Java (programming language)");





//            MainPageObject.waitForElementPresent(By.id("pcs-edit-section-title-description"), "не увидели статью", 5);
//            WebElement titleElement = MainPageObject.waitForElementPresent(By.id("Object-oriented programming language"), "не увидили заголовок статьи", 5); // Object-oriented programming language
//            String headerAttribute = titleElement.getAttribute("contentDescription");
//            System.out.println(headerAttribute);
//            Assert.assertEquals("статьи разные", "Object-oriented programming language", headerAttribute);
//
//            driver.rotate(ScreenOrientation.LANDSCAPE);
        }

        @Test
        public void testAssertElementPresentTitle () {

            MainPageObject.elementToClickSkip(By.xpath("//*[contains(@text,'Skip')]"));

            MainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                    "не нашл строку на главном экране",
                    5);

            MainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),
                    "Java",
                    "не написали Java",
                    5);

            MainPageObject.waitForElementAndClick(By.xpath("//*[@text = 'Java (programming language)']"),
                    "не нашли нужную статью",
                    5);

            MainPageObject.assertElementPresent(By.xpath("//*[@content-desc = 'Java (programming language)']"), "не увидели статью");
        }


    }


