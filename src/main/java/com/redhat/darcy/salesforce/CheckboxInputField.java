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
import static com.redhat.darcy.ui.Elements.checkbox;
import static com.redhat.darcy.web.By.htmlTag;

import com.redhat.darcy.ui.AbstractViewElement;
import com.redhat.darcy.ui.annotations.Require;
import com.redhat.darcy.ui.api.Locator;
import com.redhat.darcy.ui.api.elements.Checkbox;
import com.redhat.darcy.ui.api.elements.Element;
import com.redhat.darcy.ui.api.elements.Requireable;

/**
 * An HTML input element for a value that corresponds to a checkbox field on 
 * a Salesforce object.
 */
public class CheckboxInputField extends AbstractViewElement implements Checkbox,
    Requireable {

    @Require
    private Element parent = super.parent;
    
    @Require
    private Checkbox nestedCheckbox = checkbox(byInner(htmlTag("input")));
    
    private RequiredInput requiredInput;

    /**
     * An HTML input element for a value that corresponds to a checkbox field 
     * on a Salesforce object.  Takes the locator returned from BySalesforce 
     * and finds the input tag nested below.
     * 
     * @param locator  Locator returned from BySalesforce
     * @return CheckboxInputField
     */
    public static CheckboxInputField checkboxInputField(Locator locator) {
        return new CheckboxInputField(locator);
    }

    public CheckboxInputField(Locator parent) {
        super(parent);
        requiredInput = requiredInput(parent);
    }

    @Override
    public void check() {
        nestedCheckbox.check();
    }

    @Override
    public void uncheck() {
        nestedCheckbox.uncheck();
    }

    @Override
    public boolean isChecked() {
        return nestedCheckbox.isChecked();
    }

    @Override
    public void toggle() {
        nestedCheckbox.toggle();
    }

    @Override
    public void click() {
        nestedCheckbox.click();
    }

    @Override
    public boolean isEnabled() {
        return nestedCheckbox.isEnabled();
    }

    @Override
    public boolean isDisplayed() {
        return nestedCheckbox.isDisplayed();
    }

    @Override
    public boolean isPresent() {
        return nestedCheckbox.isPresent();
    }

    @Override
    public boolean isRequired() {
        return requiredInput.isDisplayed();
    }
}