package testClasses;

import baseClasses.BaseClass;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LastWeekData extends BaseClass {

    @BeforeMethod
    public void launchTestPage() {
        driver.get("https://www.nseindia.com/index_nse.htm");
    }

    String RELIANCE = "RELIANCE";
    By searchCompany = By.id("keyword");
    By productToClick = By.xpath("//*[@id='ajax_response']//span[text()='" + RELIANCE + "']");

    By historicalData = By.id("tab8");

    By periodDropDown = By.id("period");

    By getDataButton = By.id("get");

    By highPrice = By.xpath("//*[@id='historicalData']/table/tbody//td[@class='low-sel']");
    By lowPrice = By.xpath("//*[@id='historicalData']/table/tbody//td[@class='high-sel']");

    By lastPrice = By.id("lastPrice");

    By lastDayClose = By.xpath("//*[@id='historicalData']/table/tbody/tr[2]/td[8]");
    By lastDayOpen = By.xpath("//*[@id='historicalData']/table/tbody/tr[2]/td[4]");
    By todaysOpen = By.id("open");

    @Test
    public void DarvasBoxTheory() {
        sendKeys(searchCompany, RELIANCE);
        click(productToClick);

        click(historicalData);

        selectDropdownByText(periodDropDown, "7 Days");
        click(getDataButton);

        double low = getDoubleValue(lowPrice);
        double high = getDoubleValue(highPrice);

        double latest = getDoubleValue(lastPrice);

        System.out.println("As per last week data: ");
        System.out.println("Buy above: " + high + " short below: " + low);
        System.out.println("Price right now: " + latest);
        if (latest - high > 0)
            System.out.println("Darvas +");
        if (latest - low < 0) {
            System.out.println("Darvas - ");
        }
    }

    @Test
    public void maheshIntraDayTrend() {
        //last day close - last day open -> +
        //today's open - last day close -> +

        sendKeys(searchCompany, RELIANCE);
        click(productToClick);
        click(historicalData);

        selectDropdownByText(periodDropDown, "7 Days");
        click(getDataButton);

        double trendAsPerLastDay = getDoubleValue(lastDayClose) - getDoubleValue(lastDayOpen);
        if (trendAsPerLastDay > 0) {
            System.out.println("+ Last Day");
        } else {
            System.out.println("- Last Day");
        }
        double trendAsPerToday = getDoubleValue(todaysOpen) - getDoubleValue(lastDayClose);
        if (trendAsPerLastDay > 0) {
            System.out.println("+ Today");
        } else {
            System.out.println("- Today");
        }


    }

}