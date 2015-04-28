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
import static com.redhat.darcy.web.By.htmlTag;

import com.redhat.darcy.ui.AbstractViewElement;
import com.redhat.darcy.ui.annotations.NotRequired;
import com.redhat.darcy.ui.annotations.RequireAll;
import com.redhat.darcy.ui.api.Locator;
import com.redhat.darcy.ui.api.elements.Element;
import com.redhat.darcy.ui.api.elements.Requireable;
import com.redhat.darcy.ui.api.elements.TextInput;
import com.redhat.darcy.ui.api.elements.Link;

/**
 * ViewElement representing the Salesforce record's Lookup field.  Includes 
 * the text input field and the Lookup (magnifying glass icon) link.
 */
@RequireAll
public class LookupInputField extends AbstractViewElement 
    implements Requireable {

    private TextInput nestedTextInput = textInput(byInner(htmlTag("span"), 
            htmlTag("input")));

    private Link nestedLink = link(byInner(htmlTag("span"), 
            htmlTag("a")));

    @NotRequired
    private RequiredInput requiredInput = requiredInput(parent);

    /**
     * A ViewElement that corresponds to a Salesforce Lookup field on 
     * a Salesforce object.  Takes the locator returned from BySalesforce and 
     * finds the input and anchor (magnifying glass link) nested below. 
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

    public LookupInputField(Element parent) {
        super(parent);
    }

    public boolean isEnabled() {
        return nestedTextInput.isEnabled() && nestedLink.isEnabled();
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