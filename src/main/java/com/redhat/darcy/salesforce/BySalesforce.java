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
import com.redhat.darcy.ui.api.Context;
import com.redhat.darcy.ui.api.Locator;

import java.util.List;
import java.util.Objects;

public abstract class BySalesforce {
    public static BySalesforceLabel label(String subHeading, String label) {
        return new BySalesforceLabel(subHeading, label);
    }

    public static class BySalesforceLabel implements Locator {
        private final String subHeading;
        private final String label;

        /** The computed locator from the given subHeading and label. */
        private final Locator locator;

        public BySalesforceLabel(String subHeading, String label) {
            this.subHeading = Objects.requireNonNull(subHeading, "subHeading");
            this.label = Objects.requireNonNull(label, "label");

            String xpath = "//div[@class='pbSubsection']//td[" + withClass("dataCol") + " and " +
                    "preceding-sibling::td[1][@class='labelCol' and text()='" + label + "']]";

            locator = By.xpath(xpath);
        }

        @Override
        public <T> List<T> findAll(Class<T> type, Context context) {
            return locator.findAll(type, context);
        }

        @Override
        public <T> T find(Class<T> type, Context context) {
            return locator.find(type, context);
        }

        @Override
        public int hashCode() {
            return Objects.hash(subHeading, label);

        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }

            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            BySalesforceLabel that = (BySalesforceLabel) o;

            return label.equals(that.label) && subHeading.equals(that.subHeading);

        }

        @Override
        public String toString() {
            return "BySalesforceLabel: {" +
                    "subHeading='" + subHeading + '\'' +
                    ", label='" + label + '\'' +
                    ", locator=" + locator +
                    '}';
        }

        private String withClass(String className) {
            return "contains(concat(' ', normalize-space(@class), ' '), ' " + className + " ')";
        }
    }
}
