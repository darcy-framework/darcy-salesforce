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
import static com.redhat.darcy.ui.Elements.textInput;
import static com.redhat.darcy.web.By.htmlTag;

import com.redhat.darcy.ui.AbstractViewElement;
import com.redhat.darcy.ui.annotations.NotRequired;
import com.redhat.darcy.ui.annotations.RequireAll;
import com.redhat.darcy.ui.api.Locator;
import com.redhat.darcy.ui.api.elements.Element;
import com.redhat.darcy.ui.api.elements.Requireable;
import com.redhat.darcy.ui.api.elements.TextInput;

/**
 * An HTML input element of type text which does not correspond to a field on 
 * a Salesforce object.
 */
@RequireAll
public class InputText extends AbstractViewElement implements TextInput, 
    Requireable {

    private TextInput nestedTextInput = textInput(byInner(htmlTag("input")));

    @NotRequired
    private RequiredInput requiredInput = requiredInput(parent);

    /**
     * An HTML input element of type text which does not correspond to a field 
     * on a Salesforce object.  Takes the locator returned from BySalesforce 
     * and finds the input tag nested below.
     * 
     * @param locator  Locator returned from BySalesforce
     * @return InputText
     */
    public static InputText inputText(Locator locator) {
        return new InputText(locator);
    }

    public InputText(Locator parent) {
        super(parent);
    }
    
    public InputText(Element parent) {
        super(parent);
    }

    @Override
    public void clearAndType(CharSequence... keysToType) {
        nestedTextInput.clearAndType(keysToType);
    }

    @Override
    public void type(CharSequence... keysToType) {
        nestedTextInput.type(keysToType);
    }

    @Override
    public void clear() {
        nestedTextInput.clear();
    }

    @Override
    public void click() {
        nestedTextInput.click();
    }

    @Override
    public boolean isEnabled() {
        return nestedTextInput.isEnabled();
    }

    @Override
    public boolean isDisplayed() {
        return nestedTextInput.isDisplayed();
    }

    @Override
    public boolean isPresent() {
        return nestedTextInput.isPresent();
    }

    @Override
    public String getValue() {
        return nestedTextInput.getValue();
    }


    @Override
    public boolean isRequired() {
        return requiredInput.isDisplayed();
    }
}
