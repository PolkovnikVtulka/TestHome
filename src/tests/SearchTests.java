package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class SearchTests extends CoreTestCase {
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
}
