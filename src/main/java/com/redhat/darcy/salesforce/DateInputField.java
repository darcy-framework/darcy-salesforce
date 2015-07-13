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
import static com.redhat.darcy.ui.By.xpath;
import static com.redhat.darcy.ui.Elements.link;
import static com.redhat.darcy.ui.Elements.textInput;
import static com.redhat.darcy.web.By.htmlTag;

import com.redhat.darcy.ui.AbstractViewElement;
import com.redhat.darcy.ui.annotations.NotRequired;
import com.redhat.darcy.ui.annotations.RequireAll;
import com.redhat.darcy.ui.api.Locator;
import com.redhat.darcy.ui.api.elements.DateInput;
import com.redhat.darcy.ui.api.elements.Element;
import com.redhat.darcy.ui.api.elements.Link;
import com.redhat.darcy.ui.api.elements.Requireable;
import com.redhat.darcy.ui.api.elements.TextInput;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * An ViewElement for a value that corresponds to a Date field on 
 * a Salesforce object.
 */
@RequireAll
public class DateInputField extends AbstractViewElement implements TextInput, 
    DateInput, Link, Requireable {

    private DateTimeFormatter formatter;

    private TextInput nestedTextInput = textInput(byInner( 
            xpath("//span[contains(@class,'dateInput')]"),
            htmlTag("input")));

    private Link nestedLink = link(byInner(
            xpath("//span[contains(@class,'dateInput')]"),
            htmlTag("span"), htmlTag("a")));

    @NotRequired
    private RequiredInput requiredInput = requiredInput(parent);

    /**
     * A ViewElement that corresponds to a Date field on
     * a Salesforce object.  Takes the locator returned from BySalesforce and 
     * finds the elements for the date text input and the [today] link.
     * 
     * @param locator  Locator returned from BySalesforce
     * @return DateInputField
     */
    public static DateInputField dateInputField(Locator locator, 
            DateTimeFormatter formatter) {
        return new DateInputField(locator, formatter);
    }

    public DateInputField(Locator parent, DateTimeFormatter formatter) {
        super(parent);
        this.formatter = formatter;
    }

    public DateInputField(Element parent, DateTimeFormatter formatter) {
        super(parent);
        this.formatter = formatter;
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

    /** Click the date input field.  @see todayLink() for the [today] link. */
    @Override
    public void click() {
        nestedTextInput.click();
    }

    @Override
    public boolean isEnabled() {
        return nestedTextInput.isEnabled();
    }

    @Override
    public String getValue() {
        return nestedTextInput.getValue();
    }

    @Override
    public void setDate(LocalDate date) {
        nestedTextInput.clearAndType(date.format(formatter));
    }

    @Override
    public LocalDate getDate() {
        return LocalDate.parse(nestedTextInput.getValue(), formatter);
    }

    @Override
    public boolean isRequired() {
        return requiredInput.isDisplayed();
    }  

    /** Click the [today] link next to the date input field. */
    public void todayLink() {
        nestedLink.click();
    }

    @Override
    public String getText() {
        return nestedLink.getText();

    }

    @Override
    public String getLinkText() {
        return nestedLink.getLinkText();

    }
}