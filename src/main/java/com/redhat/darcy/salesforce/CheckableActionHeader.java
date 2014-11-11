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

import com.redhat.darcy.ui.By;
import com.redhat.darcy.ui.api.Locator;
import com.redhat.darcy.ui.api.elements.Checkbox;
import com.redhat.darcy.ui.api.elements.Element;
import com.redhat.darcy.web.api.Browser;

public class CheckableActionHeader {
    private final Element actionsHeader;
    private final Browser context;

    public CheckableActionHeader(Locator actionsHeader, Browser context) {
        this(context.find().element(actionsHeader), context);
    }

    public CheckableActionHeader(Element actionsHeader, Browser context) {
        this.actionsHeader = actionsHeader;
        this.context = context;
    }

    public CheckableActionHeader checkAll() {
        getCheckbox().check();
        return this;
    }

    public CheckableActionHeader uncheckAll() {
        getCheckbox().uncheck();
        return this;
    }

    private Checkbox getCheckbox() {
        return context.find().checkbox(By.nested(actionsHeader, By.name("allBox")));
    }
}
