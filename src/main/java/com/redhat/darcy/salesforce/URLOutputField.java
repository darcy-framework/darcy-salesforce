package com.redhat.darcy.salesforce;

import com.redhat.darcy.ui.AbstractViewElement;
import com.redhat.darcy.ui.annotations.RequireAll;
import com.redhat.darcy.ui.api.Locator;
import com.redhat.darcy.ui.api.elements.Element;
import com.redhat.darcy.ui.api.elements.Link;

import static com.redhat.darcy.ui.Elements.link;
import static com.redhat.darcy.web.By.htmlTag;

/**
 * An HTML link element for a value that corresponds to a URL field on 
 * a Salesforce object.
 */

@RequireAll
public class URLOutputField extends AbstractViewElement implements Link {


    private Element parent = super.parent;
    
    private Link nestedLink = link(byInner(htmlTag("a")));
    
    /**
     * Link which corresponds to a URL field on a Salesforce object.  Takes
     * the locator returned from BySalesforce and finds the anchor tag nested 
     * below.
     * 
     * @param locator  Locator returned from BySalesforce
     * @return URLOutputField
     */
    public static URLOutputField urlOutputField(Locator locator) {
        return new URLOutputField(locator);
    }
    
    public URLOutputField(Locator parent) {
        super(parent);
    }
    
    @Override
    public void click() {
        nestedLink.click();
    }

    @Override
    public boolean isEnabled() {
        return nestedLink.isEnabled();
    }

    @Override
    public String getText() {
        return nestedLink.getText();
    }

    @Override
    public boolean isDisplayed() {
        return nestedLink.isDisplayed();
    }

    @Override
    public boolean isPresent() {
        return nestedLink.isPresent();
    }

    @Override
    public String getLinkText() {
        return nestedLink.getText();
    }

}
