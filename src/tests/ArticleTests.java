package tests;
import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {
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
        assertEquals("не совпадают", "Object-oriented programming language", article_title);
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

}
