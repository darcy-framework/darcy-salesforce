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
import static com.redhat.darcy.ui.By.nested;
import static com.redhat.darcy.web.By.htmlTag;

import com.redhat.darcy.ui.AbstractViewElement;
import com.redhat.darcy.ui.Elements;
import com.redhat.darcy.ui.annotations.NotRequired;
import com.redhat.darcy.ui.annotations.RequireAll;
import com.redhat.darcy.ui.api.Locator;
import com.redhat.darcy.ui.api.elements.Element;
import com.redhat.darcy.ui.api.elements.Requireable;
import com.redhat.darcy.ui.api.elements.Select;
import com.redhat.darcy.ui.api.elements.SelectOption;

import java.util.List;
import java.util.Optional;

/**
 * An HTML select element for a value that corresponds to a picklist field on 
 * a Salesforce object.
 */
@RequireAll
public class PicklistInputField extends AbstractViewElement 
    implements Select<SelectOption>, Requireable {

    private Element parent = super.parent;
    
    private Select<SelectOption> select = Elements.select(
            nested(parent, htmlTag("select")));
    
    @NotRequired
    private RequiredInput requiredInput = requiredInput(parent);

    /**
     * HTML select element for a value that corresponds to a text field on 
     * a Salesforce object.  Takes the locator returned from BySalesforce 
     * and finds the select tag nested below.
     * 
     * @param locator  Locator returned from BySalesforce
     * @return PicklistInputField
     */
    public static PicklistInputField picklistInputField(Locator locator) {
        return new PicklistInputField(locator);
    }
    
    public PicklistInputField(Locator parent) {
        super(parent);
    }
    
    public PicklistInputField(Element parent) {
        super(parent);
    }

    @Override
    public boolean isEnabled() {
        return select.isEnabled();
    }

    @Override
    public boolean isDisplayed() {
        return select.isDisplayed();
    }

    @Override
    public boolean isPresent() {
        return select.isPresent();
    }

    @Override
    public boolean isRequired() {
        return requiredInput.isDisplayed();
    }

    @Override
    public void select(Locator locator) {
        select.select(locator);
    }

    @Override
    public List<SelectOption> getOptions() {
        return select.getOptions();
    }

    @Override
    public Optional<SelectOption> getSelectedOption() {
        return select.getSelectedOption();
    }

}
