package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListPageObject;
import lib.ui.Navigation;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class MyListTest extends CoreTestCase {
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
    }
}
