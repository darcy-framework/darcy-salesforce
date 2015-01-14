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

import static com.redhat.darcy.ui.Elements.element;
import static com.redhat.darcy.ui.Elements.link;
import static com.redhat.darcy.ui.Elements.text;
import static com.redhat.synq.Synq.after;

import com.redhat.darcy.ui.AbstractViewElement;
import com.redhat.darcy.ui.annotations.Require;
import com.redhat.darcy.ui.api.Locator;
import com.redhat.darcy.ui.api.elements.Element;
import com.redhat.darcy.ui.api.elements.Link;
import com.redhat.darcy.ui.api.elements.Table;
import com.redhat.darcy.ui.api.elements.Text;
import com.redhat.darcy.web.By;
import com.redhat.darcy.web.api.Browser;

import org.hamcrest.Matchers;

import java.time.temporal.ChronoUnit;

public abstract class RelatedList<T extends RelatedList<T>> extends AbstractViewElement implements
        Table<T> {
    public static Locator byTitle(String title) {
        return By.xpath("//div[" +
                "contains(concat(' ', normalize-space(@class), ' '),' bRelatedList ')]" +
                "[.//td[@class='pbTitle']//*[text() = '" + title + "']]");
    }

    public static Locator byIdSuffix(String suffix) {
        int suffixOffset = suffix.length() - 1;

        String xpath = String.format("//div[substring(@id, string-length(@id) - %d) = '%s']",
                suffixOffset, suffix);

        return By.xpath(xpath);
    }

    public static class ActionColumn<U extends RelatedList<U>> implements Column<U, ActionCell> {
        @Override
        public ActionCell getCell(U table, int rowIndex) {
            return new ActionCell(table.byRowColumn(rowIndex, 1, "td"), table.getContext());
        }
    }

    public static class CheckableActionColumn<U extends RelatedList<U>> implements
            ColumnWithHeader<U, CheckableActionCell, CheckableActionHeader> {
        @Override
        public CheckableActionCell getCell(U table, int rowIndex) {
            return new CheckableActionCell(table.byRowColumn(rowIndex, 1, "td"),
                    table.getContext());
        }

        @Override
        public CheckableActionHeader getHeader(U table) {
            return new CheckableActionHeader(table.byHeader(1), table.getContext());
        }
    }

    private Text noRows = text(byInner(By.className("noRowsHeader")));
    private Element loadingSpinner = element(byInner(By.className("loading")));
    private Element innerTable = element(byInner(By.xpath(".//div[@class='pbBody']/table")));
    private Link showMore = link(byInner(By.xpath(".//div[@class='pShowMore']/a")));

    @Require
    private Element parent = super.parent;

    public RelatedList(Locator parent) {
        super(parent);
    }

    public RelatedList(Element parent) {
        super(parent);
    }

    @Override
    public boolean isLoaded() {
    	return super.isLoaded() && !loadingSpinner.isDisplayed();
    }

    public Browser getContext() {
        return (Browser) super.getContext();
    }

    @Override
    public int getRowCount() {
        if (isEmpty()) {
            return 0;
        }

        return getContext().find().elements(byInner(By.className("dataRow"))).size();
    }

    @Override
    public boolean isEmpty() {
        return noRows.isDisplayed();
    }

    public boolean canShowMore() {
        return showMore.isDisplayed();
    }

    public void showMore() {
         after(showMore::click)
            .expectCallTo(this::getRowCount, Matchers.greaterThan(getRowCount()))
            .waitUpTo(2, ChronoUnit.MINUTES);
    }
    
    protected Locator byButtonTitle(String buttonTitle) {
        return byInner(By.xpath(".//input[@title='" + buttonTitle + "']"));
    }

    protected Locator byRowColumn(int rowIndex, int colIndex, String tagName) {
        String xpath = String.format("./tbody/tr[%d]/%s[%d]", rowIndex + 1, tagName, colIndex);
        return By.nested(innerTable, By.xpath(xpath));
    }

    protected Locator byHeader(int colIndex) {
        return byRowColumn(-1, colIndex, "th");
    }
}
