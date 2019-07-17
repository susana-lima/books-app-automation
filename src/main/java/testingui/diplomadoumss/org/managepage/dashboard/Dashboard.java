package testingui.diplomadoumss.org.managepage.dashboard;

import static testingui.diplomadoumss.org.manageevents.Event.*;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import testingui.diplomadoumss.org.managepage.BasePage;

/**
 * @author Marcelo Garay
 * @project testingui.diplomadoumss.org
 */
public class Dashboard extends BasePage {

    @FindBy(xpath = "//ul[@id='social-sidebar-menu']//a[contains(text(), 'Bookings')]")
    private WebElement bookingLink;

    public Dashboard() {
        isWebElementVisible(bookingLink);
    }
}