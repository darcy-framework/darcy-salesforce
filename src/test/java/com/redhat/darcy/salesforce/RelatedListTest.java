package com.redhat.darcy.salesforce;

import com.redhat.darcy.ui.api.Locator;
import com.redhat.darcy.ui.api.elements.Element;
import com.redhat.darcy.ui.api.elements.Link;
import com.redhat.darcy.ui.api.elements.Text;
import com.redhat.darcy.ui.internal.FindsById;
import com.redhat.darcy.ui.internal.FindsByNested;
import com.redhat.darcy.ui.internal.FindsByXPath;
import com.redhat.darcy.web.By;
import com.redhat.darcy.web.api.WebContext;
import com.redhat.darcy.web.api.WebSelection;
import com.redhat.darcy.web.internal.FindsByClassName;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

/**
 */
public class RelatedListTest {
    private TestContext mockContext = mock(TestContext.class);
    private Element parent = mock(Element.class);
    private Element mockSpinner = mock(Element.class);
    private Text mockNoRows = mock(Text.class);
    private Link mockShowMore = mock(Link.class);

    private TestRelatedList list = new TestRelatedList(By.id("list"));

    @Before
    public void stubOutMocks() {
        when(mockContext.findById(Element.class, "list")).thenReturn(parent);
        when(mockContext.findByNested(Element.class, parent, By.className("loading")))
                .thenReturn(mockSpinner);
        when(mockContext.findByNested(Text.class, parent, By.className("noRowsHeader")))
                .thenReturn(mockNoRows);
        when(mockContext.findByNested(Link.class, parent, By.xpath(".//div[@class='pShowMore']/a")))
                .thenReturn(mockShowMore);

    }

    @Before
    public void assignContextToList() {
        list.setContext(mockContext);

    }

    @Test
    public void shouldBeLoadedWhenParentElementIsDisplayedAndLoadingSpinnerIsNotDisplayed() {
        when(parent.isDisplayed()).thenReturn(true);
        when(mockSpinner.isDisplayed()).thenReturn(false);

        assertTrue(list.isLoaded());
    }

    @Test
    public void shouldNotBeLoadedWhenParentElementIsDisplayedAndLoadingSpinnerIsDisplayed() {
        when(parent.isDisplayed()).thenReturn(true);
        when(mockSpinner.isDisplayed()).thenReturn(true);

        assertFalse(list.isLoaded());
    }

    @Test
    public void shouldNotBeLoadedWhenParentElementIsNotDisplayedAndLoadingSpinnerIsNotDisplayed() {
        when(parent.isDisplayed()).thenReturn(false);
        when(mockSpinner.isDisplayed()).thenReturn(false);

        assertFalse(list.isLoaded());
    }

    @Test
    public void shouldNotBeLoadedWhenParentElementIsNotDisplayed() {
        when(parent.isDisplayed()).thenReturn(false);
        when(mockSpinner.isDisplayed()).thenReturn(false);

        assertFalse(list.isLoaded());
    }

    @Test
    public void shouldNotBeLoadedWhenLoadingSpinnerIsDisplayed() {
        when(parent.isDisplayed()).thenReturn(true);
        when(mockSpinner.isDisplayed()).thenReturn(true);

        assertFalse(list.isLoaded());
    }

    @Test
    public void shouldGetRowCountZeroWhenRelatedListIsEmpty() {
        when(mockNoRows.isDisplayed()).thenReturn(true);

        assertEquals(0, list.getRowCount());
    }

    @Test
    public void canShowMoreWhenShowMoreLinkIsDisplayed() {
        when (mockShowMore.isDisplayed()).thenReturn(true);

        assertTrue(list.canShowMore());
    }

    @Test
    public void cannotShowMoreWhenShowMoreLinkIsNotDisplayed() {
        when (mockShowMore.isDisplayed()).thenReturn(false);

        assertFalse(list.canShowMore());
    }

    private static class TestRelatedList extends RelatedList<TestRelatedList> {

        public TestRelatedList(Locator parent) {
            super(parent);
        }

    }

    private static interface TestContext extends WebContext, FindsById, FindsByXPath, FindsByClassName, FindsByNested {
        @Override
        WebSelection find();
    }

 }
