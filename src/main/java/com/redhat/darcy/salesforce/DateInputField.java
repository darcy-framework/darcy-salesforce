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

import com.redhat.darcy.ui.AbstractViewElement;
import com.redhat.darcy.ui.annotations.Require;
import com.redhat.darcy.ui.api.Locator;
import com.redhat.darcy.ui.api.elements.DateInput;
import com.redhat.darcy.ui.api.elements.Element;
import com.redhat.darcy.ui.api.elements.Link;
import com.redhat.darcy.ui.api.elements.Requireable;
import com.redhat.darcy.ui.api.elements.TextInput;

import static com.redhat.darcy.salesforce.RequiredInput.requiredInput;
import static com.redhat.darcy.ui.By.xpath;
import static com.redhat.darcy.ui.Elements.link;
import static com.redhat.darcy.ui.Elements.textInput;
import static com.redhat.darcy.web.By.htmlTag;

import java.time.LocalDate;

/**
 * An ViewElement for a value that corresponds to a Date field on 
 * a Salesforce object.
 */
public class DateInputField extends AbstractViewElement implements TextInput, 
    DateInput, Link, Requireable {

    @Require
    private Element parent = super.parent;
    
    @Require
    private TextInput nestedTextInput = textInput(byInner( 
            xpath("//span[contains(@class,'dateInput')]"),
            htmlTag("input")));
    
    @Require
    private Link nestedLink = link(byInner(
            xpath("//span[contains(@class,'dateInput')]"),
            htmlTag("span"), htmlTag("a")));
    
    
    private RequiredInput requiredInput;
    
    /**
     * A ViewElement that corresponds to a Date field on
     * a Salesforce object.  Takes the locator returned from BySalesforce and 
     * finds the elements for the date text input and the [today] link.
     * 
     * @param locator  Locator returned from BySalesforce
     * @return DateInputField
     */
    public static DateInputField dateInputField(Locator locator) {
        return new DateInputField(locator);
    }

    public DateInputField(Locator parent) {
        super(parent);
        requiredInput = requiredInput(parent);

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
    
    /** Click the date input field.  @see today() for the [today] link. */
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
    public void setDate(LocalDate date) {
        nestedTextInput.clearAndType(date.toString());
    }

    @Override
    public LocalDate getDate() {
        return LocalDate.parse(nestedTextInput.getValue());
    }

    @Override
    public boolean isRequired() {
        return requiredInput.isDisplayed();
    }  
    
    /** Click the [today] link nest to the date imput field. */
    public void today() {
        nestedLink.click();
    }

    @Override
    public String getText() {
        nestedLink.getText();
        return null;
    }

    @Override
    public String getLinkText() {
        nestedLink.getLinkText();
        return null;
    }
}
