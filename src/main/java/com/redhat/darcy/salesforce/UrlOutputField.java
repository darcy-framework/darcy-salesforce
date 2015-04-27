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

import com.redhat.darcy.ui.AbstractViewElement;
import com.redhat.darcy.ui.annotations.NotRequired;
import com.redhat.darcy.ui.annotations.RequireAll;
import com.redhat.darcy.ui.api.Locator;
import com.redhat.darcy.ui.api.elements.Element;
import com.redhat.darcy.ui.api.elements.Link;

/**
 * An HTML link element for a value that corresponds to a URL field on 
 * a Salesforce object.
 */
@RequireAll
public class UrlOutputField extends AbstractViewElement implements Link {

    @NotRequired
    private Link nestedLink = link(byInner(xpath("./div/a | ./a")));
    
    /**
     * Link which corresponds to a URL field on a Salesforce object.  Takes
     * the locator returned from BySalesforce and finds the anchor tag nested 
     * below.
     * 
     * @param locator  Locator returned from BySalesforce
     * @return URLOutputField
     */
    public static UrlOutputField urlOutputField(Locator locator) {
        return new UrlOutputField(locator);
    }
    
    public UrlOutputField(Locator parent) {
        super(parent);
    }
    
    public UrlOutputField(Element parent) {
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
    public String getLinkText() {
        return nestedLink.getText();
    }

}