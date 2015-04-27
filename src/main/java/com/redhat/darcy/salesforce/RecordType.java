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
import static com.redhat.darcy.ui.Elements.text;
import static com.redhat.darcy.web.By.htmlTag;

import com.redhat.darcy.ui.AbstractViewElement;
import com.redhat.darcy.ui.annotations.NotRequired;
import com.redhat.darcy.ui.annotations.RequireAll;
import com.redhat.darcy.ui.api.Locator;
import com.redhat.darcy.ui.api.elements.Element;
import com.redhat.darcy.ui.api.elements.Link;
import com.redhat.darcy.ui.api.elements.Text;

/**
 * ViewElement representing the Salesforce record's Record Type field.  Includes 
 * the text name Record Type and [Change] link.
 */
@RequireAll
public class RecordType extends AbstractViewElement {

    private Text recordType = text(byInner(htmlTag("div")));
    
    @NotRequired
    private Link change = link(byInner(xpath("//a[text()='[Change]']")));

    /**
     * A ViewElement that corresponds to the Salesforce Record Type field on 
     * a Salesforce object.  Takes the locator returned from BySalesforce and 
     * finds the relevant tags nested below.
     * 
     * @param locator Locator returned from BySalesforce
     * @return RecordType
     */
    public static RecordType recordType(Locator locator) {
        return new RecordType(locator);
    }
    
    public RecordType(Locator parent) {
        super(parent);
    }
    
    public RecordType(Element parent) {
        super(parent);
    }

    public String getRecordType() {
        return recordType.getText();
    }

    public void change() {
        change.click();
    }

}
