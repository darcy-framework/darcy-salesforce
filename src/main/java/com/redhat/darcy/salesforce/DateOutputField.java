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

import com.redhat.darcy.ui.AbstractView;
import com.redhat.darcy.ui.annotations.RequireAll;
import com.redhat.darcy.ui.api.Locator;
import com.redhat.darcy.ui.api.elements.Text;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The text output of a Date field on a Salesforce object.
 */
@RequireAll
public class DateOutputField extends AbstractView implements Text {

    private DateTimeFormatter formatter;

    private Text nestedDate;

    /**
     * Text which corresponds to a Date field on a Salesforce object.  Takes 
     * the locator returned from BySalesforce and finds the text nested 
     * below.
     * 
     * @param locator  Locator returned from BySalesforce
     * @param formatter DateTimeFormatter Date time format to use when 
     * returning date values
     * @return DateOutputField
     */    
    public static DateOutputField dateOutputField(Locator locator,
            DateTimeFormatter formatter) {
        return new DateOutputField(locator, formatter);
    }

    public DateOutputField(Locator locator, DateTimeFormatter formatter) {
        nestedDate = text(locator);
        this.formatter = formatter;
    }

    @Override
    public boolean isDisplayed() {
       return nestedDate.isDisplayed();
    }

    @Override
    public boolean isPresent() {
        return nestedDate.isDisplayed();
    }

    @Override
    public String getText() {
        return nestedDate.getText();
    }

    public LocalDate getDate() {
        return LocalDate.parse(nestedDate.getText(), formatter);
    }

}