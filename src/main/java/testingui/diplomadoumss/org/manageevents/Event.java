package testingui.diplomadoumss.org.manageevents;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import testingui.diplomadoumss.org.core.DriverManager;

/**
 * @author Marcelo Garay
 * @project testingui.diplomadoumss.org
 */
public class Event {
    public static void clickWebElement(WebElement element){
        isWebElementClickable(element);
        element.click();
    }

    public static void fillWebElement(WebElement element, String value){
        isWebElementVisible(element);
        element.clear();
        element.sendKeys(value);
    }

    public static void isWebElementVisible(WebElement element){
        DriverManager.getInstance().getWebDriverWait().until(ExpectedConditions.visibilityOf(element));
    }

    public static boolean isAnElementVisible(WebElement element){
       return element.isDisplayed();
    }

    public static void isWebElementClickable(WebElement element){
        DriverManager.getInstance().getWebDriverWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void avoidToUse(int seconds){
        try{
            Thread.sleep(seconds * 1000);
        }catch (Exception e){
            System.out.println(e.fillInStackTrace());
        }
    }

    public static String getWebElementText(WebElement element) {
        return element.getText();
    }

    public static String getCssColorValue(WebElement element){
        return element.getCssValue("background-color");
    }

    public static boolean isDialogPresent()
    {
        try
        {
            DriverManager.getInstance().getWebDriver().switchTo().alert();
            return true;
        }
        catch (NoAlertPresentException ex)
        {
            return false;
        }
    }
}
