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

import static com.redhat.darcy.ui.matchers.DarcyMatchers.present;
import static com.redhat.synq.Synq.after;
import static com.redhat.synq.Synq.expect;

import com.redhat.darcy.ui.By;
import com.redhat.darcy.ui.api.Locator;
import com.redhat.darcy.ui.api.View;
import com.redhat.darcy.ui.api.elements.Element;

import java.time.temporal.ChronoUnit;
import java.util.function.Supplier;

public abstract class StandardActions {
    public static <T extends View> ActionLink<T> edit(Supplier<T> editView) {
        return (e, b) -> after(b.find().link(byActionText("Edit", e))::click)
                .expect(b.transition().to(editView.get()))
                .waitUpTo(2, ChronoUnit.MINUTES);
    }

    public static <T extends View> ActionLink<T> delete(Supplier<T> reloadView) {
        return (e, b) -> {
            after(b.find().link(By.nested(e, By.linkText("Del")))::click)
                    .expect(b.find().alert(), present())
                    .waitUpTo(10, ChronoUnit.SECONDS)
                    .accept();

            return expect(b.transition().to(reloadView.get()))
                    .waitUpTo(1, ChronoUnit.MINUTES);
        };
    }

    public static <T extends View> ActionLink<T> reassign(Supplier<T> reassignView) {
        return (e, b) -> after(b.find().link(byActionText("Reassign", e))::click)
                .expect(b.transition().to(reassignView.get()))
                .waitUpTo(2, ChronoUnit.MINUTES);
    }

    public static <T extends View> ActionLink<T> approveReject(Supplier<T> approveRejectView) {
  	    return (e, b) -> after(b.find().link(byActionText("Approve / Reject", e))::click)
                .expect(b.transition().to(approveRejectView.get()))
                .waitUpTo(2, ChronoUnit.MINUTES);
    }

    private static Locator byActionText(String named, Element actionCell) {
        return By.nested(actionCell, By.linkText(named));
    }
}
