package com.redhat.darcy.salesforce;

import com.redhat.darcy.ui.AbstractViewElement;
import com.redhat.darcy.ui.annotations.RequireAll;
import com.redhat.darcy.ui.api.Locator;
import com.redhat.darcy.ui.api.elements.Element;
import com.redhat.darcy.ui.api.elements.Text;

import static com.redhat.darcy.ui.Elements.text;
import static com.redhat.darcy.web.By.htmlTag;

/**
 * Text for a value that corresponds to a text field on 
 * a Salesforce object.
 */

@RequireAll
public class TextOutputField extends AbstractViewElement implements Text {

    private Element parent = super.parent;
    
    private Text nestedText = text(byInner(htmlTag("div")));
    
    /**
     * Text which corresponds to a text field on a Salesforce object.  Takes 
     * the locator returned from BySalesforce and finds the text nested 
     * below.
     * 
     * @param locator  Locator returned from BySalesforce
     * @return TextOutputField
     */
    public static TextOutputField textOutputField(Locator locator) {
        return new TextOutputField(locator);
    }
    
    public TextOutputField(Locator parent) {
        super(parent);
    }
    
    @Override
    public boolean isDisplayed() {
        return nestedText.isDisplayed();
    }

    @Override
    public boolean isPresent() {
        return nestedText.isPresent();
    }

    @Override
    public String getText() {
        return nestedText.getText();
    }

}
