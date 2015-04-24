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

import static com.redhat.darcy.ui.By.xpath;
import static com.redhat.darcy.ui.Elements.link;
import static com.redhat.darcy.web.By.htmlTag;

import com.redhat.darcy.ui.AbstractViewElement;
import com.redhat.darcy.ui.annotations.NotRequired;
import com.redhat.darcy.ui.annotations.RequireAll;
import com.redhat.darcy.ui.api.Locator;
import com.redhat.darcy.ui.api.elements.Element;
import com.redhat.darcy.ui.api.elements.Link;

/**
 * ViewElement representing the Salesforce record's Owner field.  Includes the 
 * Avatar image link, Owner name link, and [Change] link.
 */
@RequireAll
public class Owner extends AbstractViewElement {

    private Element parent = super.parent;
    
    @NotRequired
    private Link change = link(byInner(htmlTag("div"),
            xpath("//a[text()='[Change]']")));

    private Link name = link(byInner(xpath("//div/span/a[2]")));

    private Link avatar  = link(byInner(xpath("//div/span/a[1]")));

    /**
     * A ViewElement that corresponds to the Salesforce Owner field on 
     * a Salesforce object.  Takes the locator returned from BySalesforce and 
     * finds the relevant tags nested below.
     * 
     * @param locator Locator returned from BySalesforce
     * @return Owner
     */
    public static Owner owner(Locator locator) {
        return new Owner(locator);
    }

    public Owner(Locator parent) {
        super(parent);
    }
    
    public Owner(Element parent) {
        super(parent);
    }

    public String getOwner() {
        return name.getText();
    }
    
    public void click() {
         name.click();
    }

    public void change() {
        change.click();
    }

}
