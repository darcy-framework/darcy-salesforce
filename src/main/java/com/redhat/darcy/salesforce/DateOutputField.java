package com.redhat.darcy.salesforce;

import com.redhat.darcy.ui.AbstractViewElement;
import com.redhat.darcy.ui.annotations.RequireAll;
import com.redhat.darcy.ui.api.Locator;
import com.redhat.darcy.ui.api.elements.Element;
import com.redhat.darcy.ui.api.elements.Text;

import static com.redhat.darcy.ui.Elements.text;
import static com.redhat.darcy.web.By.htmlTag;

import java.time.LocalDate;

/**
 * The text output of a Date field on a Salesforce object.
 */
@RequireAll
public class DateOutputField extends AbstractViewElement implements Text {

    private Element parent = super.parent;
    
    private Text nestedDate  = text(byInner(htmlTag("div")));
    
    /**
     * Text which corresponds to a Date field on a Salesforce object.  Takes 
     * the locator returned from BySalesforce and finds the text nested 
     * below.
     * 
     * @param locator  Locator returned from BySalesforce
     * @return DateOutputField
     */    
    public static DateOutputField dateOutputField(Locator locator) {
        return new DateOutputField(locator);
    }
    
    public DateOutputField(Locator parent) {
        super(parent);
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
        return LocalDate.parse(nestedDate.getText());
    }

}
