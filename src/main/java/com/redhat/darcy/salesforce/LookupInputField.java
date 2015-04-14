/*
 Copyright 2014 Red Hat, Inc. and/or its affiliates.

 This file is part of darcy-salesforce.

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.redhat.darcy.salesforce;

import static com.redhat.darcy.salesforce.RequiredInput.requiredInput;
import static com.redhat.darcy.ui.Elements.link;
import static com.redhat.darcy.ui.Elements.textInput;

import com.redhat.darcy.ui.AbstractViewElement;
import com.redhat.darcy.ui.annotations.Require;
import com.redhat.darcy.ui.api.Locator;
import com.redhat.darcy.ui.api.elements.Element;
import com.redhat.darcy.ui.api.elements.Requireable;
import com.redhat.darcy.ui.api.elements.TextInput;
import com.redhat.darcy.ui.api.elements.Link;
import com.redhat.darcy.web.By;

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
    
    private RequiredInput requiredInput = requiredInput(parent);
    
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
