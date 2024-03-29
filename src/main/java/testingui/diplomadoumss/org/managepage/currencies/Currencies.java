package testingui.diplomadoumss.org.managepage.currencies;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import testingui.diplomadoumss.org.core.DriverManager;
import testingui.diplomadoumss.org.managepage.BasePage;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

import static testingui.diplomadoumss.org.manageevents.Event.*;

public class Currencies extends BasePage {
    @FindBy(xpath = "//*[@class='xcrud-list-container']/table/tbody/tr")
    private WebElement rowTable;

    @FindBy(xpath = "//div[@class='xcrud-top-actions']/a")
    private WebElement addCurrencyButton;

    @FindBy(xpath = "//label[contains(.,'Name*')]/following::input[1]")
    private WebElement nameCurrency;

    @FindBy(xpath = "//label[contains(.,'Symbol')]/following::input[1]")
    private WebElement symbolCurrency;

    @FindBy(xpath = "//label[contains(.,'Code*')]/following::input[1]")
    private WebElement codeCurrency;

    @FindBy(xpath = "//a[text()='Save & Return']")
    private WebElement saveCurrencyButton;

    @FindBy(xpath = "//a[text()=' Print']")
    private WebElement printOption;

    @FindBy(xpath = "//a[text()='Return']")
    private WebElement returnOption;

    @FindBy(xpath = "//label[contains(.,'Rate*')]/following::input[1]")
    private WebElement rateCurrency;

    @FindBy(xpath = "//label[contains(.,'Active*')]/following::select[1]/option[@value='Yes']")
    private WebElement activeCurrency;

    @FindBy(xpath = "//a[text()=' Export into CSV']")
    private WebElement exportOption;

    public Currencies(){
        avoidToUse(5);
    }

    public ArrayList<String> getValuesNameColumnData(){
        ArrayList<String> result = new ArrayList<>();
        WebDriver webDriver = DriverManager.getInstance().getWebDriver();
        int rowCount = webDriver.findElements(By.xpath("//*[@class='xcrud-list-container']/table/tbody/tr")).size();
        System.out.println(rowCount + "Value of rows");
        //int colCount = webDriver.findElements(By.xpath("//*[@class='xcrud-list-container']/table/tbody/tr[1]/td")).size();

        for(int row=1; row<=rowCount; row++){
            String nameColPath = "//*[@class='xcrud-list-container']/table/tbody/tr["+row+"]/td[3]";
            String textNameCol = webDriver.findElement(By.xpath(nameColPath)).getText();
            result.add(textNameCol);
        }

        return result;
    }

    public Boolean arePresentStandardCurrencies(){
        ArrayList<String> standardCurrencies = new ArrayList<>();
        standardCurrencies.add("USD");
        standardCurrencies.add("EUR");

        ArrayList<String> actualCurrencies = getValuesNameColumnData();

        return actualCurrencies.containsAll(standardCurrencies);
    }

    public Currencies clickAddCurrencyButton(){
        clickWebElement(addCurrencyButton);
        return this;
    }

    public void fillDataNewCurrency(){
        avoidToUse(3);
        fillWebElement(nameCurrency, "ABC");
        fillWebElement(symbolCurrency, "#");
        fillWebElement(codeCurrency, "ABC");
        clickWebElement(saveCurrencyButton);
    }

    public Boolean isPrintOptionVisible(){
        return isAnElementVisible(printOption);
    }

    public WebElement getDeleteOption(){
        WebDriver webDriver = DriverManager.getInstance().getWebDriver();
        String removeOptionPath = "//*[@class='xcrud-list-container']/table/tbody/tr[1]/td[9]/span/a[@title='Remove']";

        return webDriver.findElement(By.xpath(removeOptionPath));
    }

    public Boolean isDangerColor(){
        WebElement deleteOption = getDeleteOption();
        System.out.println(getCssColorValue(deleteOption));
        return (getCssColorValue(deleteOption).compareToIgnoreCase("rgba(238, 95, 91, 1)")) == 0;
    }

    public void clickViewOption(){
        WebDriver webDriver = DriverManager.getInstance().getWebDriver();
        String viewOptionPath = "//*[@class='xcrud-list-container']/table/tbody/tr[1]/td[9]/span/a[@title='View']";

        WebElement viewOptionElement = webDriver.findElement(By.xpath(viewOptionPath));
        clickWebElement(viewOptionElement);
    }

    public Boolean isReturnOptionVisible(){
        avoidToUse(3);
        return isAnElementVisible(returnOption);
    }

    public Boolean thereIsOnlyOneDefaultCurrency(){
        WebDriver webDriver = DriverManager.getInstance().getWebDriver();
        int rowCount = webDriver.findElements(By.xpath("//*[@class='xcrud-list-container']/table/tbody/tr")).size();
        int defaultCount = 0;


        for(int row=1; row<=rowCount; row++){
            String defaultIconPath = "//*[@class='xcrud-list-container']/table/tbody/tr["+row+"]/td[8]/span/i[@class='fa fa-circle fa-2x']";
            if(existElement(defaultIconPath)){
                defaultCount++;
            }
        }

        return defaultCount==1;
    }

    public boolean existElement(String xpath) {
        try {
            WebDriver webDriver = DriverManager.getInstance().getWebDriver();
            webDriver.findElement(By.xpath(xpath));
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public Boolean existDefaultEntries(){
        avoidToUse(3);
        return (getWebElementText(activeCurrency).equalsIgnoreCase("Yes"));
    }

    public Boolean isExportOptionVisible(){
        return isAnElementVisible(exportOption);
    }

    public void clickDeleteOption(){
        avoidToUse(3);
        WebDriver webDriver = DriverManager.getInstance().getWebDriver();
        String deleteOptionPath = "//*[@class='xcrud-list-container']/table/tbody/tr[1]/td[9]/span/a[@title='Remove']";

        WebElement deleteOptionElement = webDriver.findElement(By.xpath(deleteOptionPath));
        clickWebElement(deleteOptionElement);
    }

    public boolean isConfirmationDialogPresent(){
        return isDialogPresent();
    }
}
