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

import static com.redhat.darcy.ui.Elements.text;
import static com.redhat.darcy.web.By.htmlTag;

import com.redhat.darcy.ui.AbstractViewElement;
import com.redhat.darcy.ui.annotations.RequireAll;
import com.redhat.darcy.ui.api.Locator;
import com.redhat.darcy.ui.api.elements.Element;
import com.redhat.darcy.ui.api.elements.Text;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Text output for a value that corresponds to a Picklist field on 
 * a Salesforce object.
 */
@RequireAll
public class PicklistOutputField extends AbstractViewElement implements Text {

    private Element parent = super.parent;
    
    private Text nestedText = text(byInner(htmlTag("div")));
    
    public static PicklistOutputField picklistOutputField(Locator locator) {
        return new PicklistOutputField(locator);
    }
    
    /**
     * Text which corresponds to a picklist field on a Salesforce object.  Takes 
     * the locator returned from BySalesforce and finds the text nested 
     * below.
     * 
     * @param locator  Locator returned from BySalesforce
     * @return TextOutputField
     */
    public PicklistOutputField(Locator parent) {
        super(parent);
    }
    
    @Override
    public boolean isDisplayed() {
        return nestedText.isDisplayed();
    }

    @Override
    public boolean isPresent() {
        return nestedText.isPresent();
    }

    @Override
    public String getText() {
        return nestedText.getText();
    }
    
    public List<String> getSelectedOptions(){
        return Arrays.stream(nestedText.getText().split(";"))
            .filter(Objects::nonNull)
            .peek(String::trim)
            .filter(String::isEmpty)
            .collect(Collectors.toList());
    }

}
