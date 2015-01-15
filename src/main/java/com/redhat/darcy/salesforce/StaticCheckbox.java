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

import static com.redhat.darcy.web.HtmlElements.htmlElement;

import com.redhat.darcy.ui.AbstractView;
import com.redhat.darcy.ui.annotations.Require;
import com.redhat.darcy.ui.api.Locator;
import com.redhat.darcy.ui.api.elements.Checkbox;
import com.redhat.darcy.web.api.elements.HtmlElement;

public class StaticCheckbox extends AbstractView implements Checkbox {
    @Require
    private HtmlElement backingImage;

    public static StaticCheckbox salesforceCheckbox(Locator locator) {
        return new StaticCheckbox(locator);
    }

    public StaticCheckbox(Locator parent) {
        backingImage = htmlElement(parent);
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
