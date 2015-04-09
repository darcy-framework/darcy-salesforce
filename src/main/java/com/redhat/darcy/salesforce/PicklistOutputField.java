package com.redhat.darcy.salesforce;

import com.redhat.darcy.ui.AbstractViewElement;
import com.redhat.darcy.ui.annotations.RequireAll;
import com.redhat.darcy.ui.api.Locator;
import com.redhat.darcy.ui.api.elements.Element;
import com.redhat.darcy.ui.api.elements.Text;

import static com.redhat.darcy.ui.Elements.text;
import static com.redhat.darcy.web.By.htmlTag;
import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;

/**
 * Text output for a value that corresponds to a Picklist field on 
 * a Salesforce object.
 */
@RequireAll
public class PicklistOutputField extends AbstractViewElement implements Text {

    private Element parent = super.parent;
    
    private Text nestedText = text(byInner(htmlTag("div")));
    
    public static PicklistOutputField picklistOutputField(Locator locator) {
        return new PicklistOutputField(locator);
    }
    
    /**
     * Text which corresponds to a picklist field on a Salesforce object.  Takes 
     * the locator returned from BySalesforce and finds the text nested 
     * below.
     * 
     * @param locator  Locator returned from BySalesforce
     * @return TextOutputField
     */
    public PicklistOutputField(Locator parent) {
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
    
    public String[] getSelectedValues(){
        List<String> values = new ArrayList<String>(
                asList(nestedText.getText().split(";")));
        values.removeAll(asList(null, ""));
        return values.toArray(new String[values.size()]);
    }

}
