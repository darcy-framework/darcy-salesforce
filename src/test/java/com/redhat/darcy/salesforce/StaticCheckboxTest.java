package com.redhat.darcy.salesforce;

import com.redhat.darcy.salesforce.StaticCheckbox;
import com.redhat.darcy.ui.api.Locator;
import com.redhat.darcy.ui.api.elements.Element;
import com.redhat.darcy.ui.internal.FindsById;
import com.redhat.darcy.ui.internal.FindsByNested;
import com.redhat.darcy.ui.internal.FindsByXPath;
import com.redhat.darcy.web.By;
import com.redhat.darcy.web.api.WebContext;
import com.redhat.darcy.web.api.WebSelection;
import com.redhat.darcy.web.api.elements.HtmlElement;
import com.redhat.darcy.web.internal.FindsByClassName;
import org.junit.Assert;
import org.mockito.Mockito;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by jvervaec on 2/6/15.
 */
public class StaticCheckboxTest {

    private TestContext mockContext = mock(TestContext.class);
    private HtmlElement mockBackingImage = mock(HtmlElement.class);

    private StaticCheckbox staticCheckbox = new StaticCheckbox(By.id("img"));
    private StaticCheckbox staticCheckbox2 = StaticCheckbox.salesforceCheckbox((By.id("img")));
    private StaticCheckbox spyOnStaticCheckbox = spy(new StaticCheckbox(By.id("img")));

    @Before
    public void stubOutMocks() {
        when(mockContext.findById(HtmlElement.class, "img")).thenReturn(mockBackingImage);

    }

    @Before
    public void assignContextToList() {
        staticCheckbox.setContext(mockContext);
        spyOnStaticCheckbox.setContext(mockContext);

    }

    @Test
    public void shouldBeAbleToCheck() {
        when(mockBackingImage.getAttribute("src")).thenReturn("unchecked");
        spyOnStaticCheckbox.check();
        verify(spyOnStaticCheckbox).check();
    }

    @Test
    public void shouldBeAbleToUnCheck() {
        when(mockBackingImage.getAttribute("src")).thenReturn("checked");
        spyOnStaticCheckbox.uncheck();
        verify(spyOnStaticCheckbox).uncheck();
    }

    @Test
    public void shouldBeCheckedWhenBackingImageIsChecked(){
        when(mockBackingImage.getAttribute("src")).thenReturn("checked");

        assertTrue(staticCheckbox.isChecked());
    }

    @Test
    public void shouldNotBeCheckedWhenBackingImageIsUnchecked(){
        when(mockBackingImage.getAttribute("src")).thenReturn("unchecked");

        assertFalse(staticCheckbox.isChecked());
    }

    @Test
    public void shouldBeAbleToToggle() {
        spyOnStaticCheckbox.toggle();
        verify(spyOnStaticCheckbox).toggle();
    }

    @Test
    public void shouldBeAbleToClick() {
        spyOnStaticCheckbox.click();
        verify(spyOnStaticCheckbox).click();
    }

    @Test
    public void shouldBeDisplayedWhenBackingImageIsDisplayed() {
        when(mockBackingImage.isDisplayed()).thenReturn(true);

        assertTrue(staticCheckbox.isDisplayed());
    }

    @Test
    public void shouldNotBeDisplayedWhenBackingImageIsNotDisplayed() {
        when(mockBackingImage.isDisplayed()).thenReturn(false);

        assertFalse(staticCheckbox.isDisplayed());
    }

    @Test
    public void shouldBePresentdWhenBackingImageIsPresent() {
        when(mockBackingImage.isPresent()).thenReturn(true);

        assertTrue(staticCheckbox.isPresent());
    }

    @Test
    public void shouldNotBePresentWhenBackingImageIsNotPresent() {
        when(mockBackingImage.isPresent()).thenReturn(false);

        assertFalse(staticCheckbox.isPresent());
    }

    @Test
    public void shouldNeverBeEnabled() {
        assertFalse(staticCheckbox.isEnabled());
    }

    private static interface TestContext extends WebContext, FindsById, FindsByXPath, FindsByClassName, FindsByNested {
        @Override
        WebSelection find();
    }

}
