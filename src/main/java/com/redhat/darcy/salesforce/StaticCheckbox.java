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
import static com.redhat.darcy.web.HtmlElements.htmlElement;

import com.redhat.darcy.ui.AbstractViewElement;
import com.redhat.darcy.ui.annotations.RequireAll;
import com.redhat.darcy.ui.api.Locator;
import com.redhat.darcy.ui.api.elements.Checkbox;
import com.redhat.darcy.ui.api.elements.Element;
import com.redhat.darcy.web.api.elements.HtmlElement;

/**
 * A ViewElement which corresponds to the checkbox 'image' on 
 * a Salesforce page.  Takes the locator returned from BySalesforce 
 * and finds the image tag nested below.  Maps the image attributes 
 * to standard checkbox methods.
 */
@RequireAll
public class StaticCheckbox extends AbstractViewElement implements Checkbox {
    
    private Element parent = super.parent;
    
    private HtmlElement backingImage = htmlElement(byInner(xpath("./div/img | ./img")));

    /**
     * A ViewElement which corresponds to the checkbox 'image' on 
     * a Salesforce page.  Takes the locator returned from BySalesforce 
     * and finds the image tag nested below.  Maps the image attributes 
     * to standard checkbox methods.
     * 
     * @param locator  Locator returned from BySalesforce
     * @return StaticCheckbox
     */    
    public static StaticCheckbox salesforceCheckbox(Locator locator) {
        return new StaticCheckbox(locator);
    }

    public StaticCheckbox(Locator parent) {
        super(parent);
        
    }
    
    public StaticCheckbox(Element parent) {
        super(parent);
    }

    @Override
    public void check() {
        if (!isChecked()) {
            click();
        }
    }

    @Override
    public void uncheck() {
        if (isChecked()) {
            click();
        }
    }

    @Override
    public boolean isChecked() {
        System.out.println(backingImage.getAttribute("src"));
        return !backingImage.getAttribute("src").contains("unchecked");
    }

    @Override
    public void toggle() {
        click();
    }

    @Override
    public void click() {
        backingImage.click();
    }

    @Override
    public boolean isDisplayed() {
        return backingImage.isDisplayed();
    }

    @Override
    public boolean isPresent() {
        return backingImage.isPresent();
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
