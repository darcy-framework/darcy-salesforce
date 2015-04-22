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

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

/**
 */
public class StaticCheckboxTest {

    private TestContext mockContext = mock(TestContext.class);
    private Locator locator = mock(Locator.class);
    private Element parent = mock(Element.class);
    private HtmlElement mockBackingImage = mock(HtmlElement.class);

    private StaticCheckbox staticCheckbox = new StaticCheckbox(By.id("blah"));

    @Before
    public void stubOutMocks() {
        when(mockContext.findByNested(HtmlElement.class, parent, By.xpath("./div/img | ./img"))).thenReturn(mockBackingImage);
        when(mockContext.findById(Element.class, "blah")).thenReturn(parent);
    }

    @Before
    public void assignContextToElement() {
        staticCheckbox.setContext(mockContext);

    }

    @Test
    public void shouldBeAbleToCheck() {
        when(mockBackingImage.getAttribute("src")).thenReturn("unchecked");
        
        staticCheckbox.check();
        verify(mockBackingImage).click();
        
    }

    @Test
    public void shouldBeAbleToUnCheck() {
        when(mockBackingImage.getAttribute("src")).thenReturn("checked");
        
        staticCheckbox.uncheck();
        verify(mockBackingImage).click();
        
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
    public void shouldBePresentWhenBackingImageIsPresent() {
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
