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

import static com.redhat.darcy.ui.By.nested;
import static com.redhat.darcy.ui.By.xpath;
import static com.redhat.darcy.ui.Elements.element;

import com.redhat.darcy.ui.AbstractViewElement;
import com.redhat.darcy.ui.annotations.NotRequired;
import com.redhat.darcy.ui.annotations.RequireAll;
import com.redhat.darcy.ui.api.Locator;
import com.redhat.darcy.ui.api.elements.Element;

/**
 * Special element type used to identify when a Salesforce element is 
 * wrapped in a requiredInput div.
 */
@RequireAll
public class RequiredInput extends AbstractViewElement implements Element {

    private Element parent = super.parent;
    
    @NotRequired
    private Element requiredInput = element(nested(parent,
            xpath("//div[contains(@class, 'requiredInput')]")));

    public static RequiredInput requiredInput(Element parent) {
        return new RequiredInput(parent);
    }

    public RequiredInput(Locator parent) {
        super(parent);
    }
    
    public RequiredInput(Element parent) {
        super(parent);
    }

    @Override
    public boolean isPresent() {
        return requiredInput.isPresent();
    }

    @Override
    public boolean isDisplayed() {
        return requiredInput.isDisplayed();
    }
}
