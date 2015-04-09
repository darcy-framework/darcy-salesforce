package com.redhat.darcy.salesforce;

import com.redhat.darcy.ui.AbstractViewElement;
import com.redhat.darcy.ui.annotations.Require;
import com.redhat.darcy.ui.api.Locator;
import com.redhat.darcy.ui.api.elements.Element;
import com.redhat.darcy.ui.api.elements.Requireable;
import com.redhat.darcy.ui.api.elements.TextInput;
import com.redhat.darcy.ui.api.elements.Link;
import com.redhat.darcy.web.By;

import static com.redhat.darcy.salesforce.RequiredInput.requiredInput;
import static com.redhat.darcy.ui.Elements.link;
import static com.redhat.darcy.ui.Elements.textInput;

/**
 * ViewElement representing the Salesforce record's Lookup field.  Includes 
 * the text input field and the Lookup (magnifying glass icon) link.
 */

public class LookupInputField extends AbstractViewElement 
    implements Requireable {
    
    @Require
    private Element parent = super.parent;
    
    @Require
    private TextInput nestedTextInput = textInput(byInner(By.htmlTag("span"), 
            By.htmlTag("input")));
    
    private Link nestedLink = link(byInner(By.htmlTag("span"), 
            By.htmlTag("input")));
    
    private RequiredInput requiredInput;
    
    /**
     * A ViewElement that corresponds to a Salesforce Lookup field on 
     * a Salesforce object.  Takes the locator returned from BySalesforce and 
     * finds the relevant tags nested below.
     * 
     * @param locator Locator returned from BySalesforce
     * @return LookupInputField
     */
    public static LookupInputField lookupInputfield(Locator locator) {
        return new LookupInputField(locator);
    }
    
    public LookupInputField(Locator parent) {
        super(parent);
        requiredInput = requiredInput(parent);
    }

    public boolean isEnabled() {
        return nestedTextInput.isEnabled() && nestedLink.isEnabled();
    }
    
    public boolean isDisplayed() {
        return nestedTextInput.isDisplayed() && nestedLink.isDisplayed();
    }

    public boolean isPresent() {
        return nestedTextInput.isPresent() && nestedLink.isPresent();
    }

    public String getValue() {
        return nestedTextInput.getValue();
    }

    public void clearAndType(CharSequence... keysToType) {
       nestedTextInput.clearAndType(keysToType);
    }

    public void type(CharSequence... keysToType) {
        nestedTextInput.type(keysToType);
    }

    public void clear() {
        nestedTextInput.clear();
    }
    
    public void lookup(){
        nestedLink.click();
    }

    @Override
    public boolean isRequired() {
        return requiredInput.isDisplayed();
    }

}
