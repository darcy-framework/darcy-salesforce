package com.redhat.darcy.salesforce;

import com.redhat.darcy.salesforce.CheckableActionCell;
import com.redhat.darcy.salesforce.CheckableActionHeader;
import com.redhat.darcy.salesforce.StaticCheckbox;
import com.redhat.darcy.ui.api.Locator;
import com.redhat.darcy.ui.api.elements.Checkbox;
import com.redhat.darcy.ui.api.elements.Element;
import com.redhat.darcy.ui.api.elements.Findable;
import com.redhat.darcy.ui.internal.FindsById;
import com.redhat.darcy.ui.internal.FindsByNested;
import com.redhat.darcy.ui.internal.FindsByXPath;
import com.redhat.darcy.web.By;
import com.redhat.darcy.web.api.Alert;
import com.redhat.darcy.web.api.Browser;
import com.redhat.darcy.web.api.WebContext;
import com.redhat.darcy.web.api.WebSelection;
import com.redhat.darcy.web.api.elements.HtmlElement;
import com.redhat.darcy.web.internal.AbstractWebSelection;
import com.redhat.darcy.web.internal.FindsByClassName;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

/**
 * Created by jvervaec on 2/6/15.
 */
public class CheckableActionCellTest {

    private TestBrowser mockBrowser = mock(TestBrowser.class);
    private Element mockActionCell = mock(Element.class);
    private Checkbox mockCheckbox = mock(Checkbox.class);

    private CheckableActionCell checkableActionCell = new CheckableActionCell(mockActionCell, mockBrowser);

    @Before
    public void stubOutMocks() {
        when(mockBrowser.findByNested(Checkbox.class, mockActionCell, By.name("ids"))).thenReturn(mockCheckbox);
        when(mockBrowser.find()).thenReturn(new TestWebSelection(mockBrowser));
    }


    @Test
    public void shouldBeAbleToCheck () {
        checkableActionCell.check();
        verify(mockCheckbox).check();
        verifyNoMoreInteractions(mockCheckbox);
    }

    @Test
    public void shouldBeAbleToUnCheck() {
        checkableActionCell.uncheck();
        verify(mockCheckbox).uncheck();
        verifyNoMoreInteractions(mockCheckbox);

    }

    private static interface TestContext extends WebContext, FindsById, FindsByXPath, FindsByClassName, FindsByNested {
        @Override
        WebSelection find();
    }

    private static interface TestBrowser extends Browser, FindsByNested {
        @Override
        WebSelection find();

    }

    private static class TestWebSelection extends AbstractWebSelection {

        public TestWebSelection(WebContext webContext) {
            super(webContext);
        }

        @Override
        public Alert alert() {
            return null;
        }
    }
}
