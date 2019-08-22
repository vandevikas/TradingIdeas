package testClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class HistoricalDataMoreThan3Months {
    List<LocalDate> sortedDates;

    @Test
    public void test() throws InterruptedException {


        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\IdeaProjects\\DataDrivenTestinginSeleniumMykolaKolisnyk\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.nseindia.com/products/content/equities/equities/eq_security.htm");

        driver.findElement(By.id("symbol")).sendKeys("ITC");
        Select pastDropdown = new Select(driver.findElement(By.id("dateRange")));
        pastDropdown.selectByVisibleText("3 months");
        driver.findElement(By.id("get")).click();

        Thread.sleep(2000);
        List<WebElement> allRows = driver.findElements(By.xpath("//*[@id='historicalData']/table/tbody/tr"));

        /*List<List<WebElement>> wholeTable = new ArrayList<>();
        for (int i = 1; i < allRows.size(); i++) {
            for (int j = 0; j < 15; j++) {
                allRows.get()
            }
        }
*/

        List<String> highPrice = new ArrayList<>();
        for (int i = 1; i < allRows.size(); i++) {
            highPrice.add(allRows.get(i).findElement(By.xpath("td[6]")).getText());
        }

        List<String> lowPrice = new ArrayList<>();
        for (int i = 1; i < allRows.size(); i++) {
            lowPrice.add(allRows.get(i).findElement(By.xpath("td[7]")).getText());
        }

        //Adding all Dates in one ArrayList
        List<String> listOfDates = new ArrayList<>();
        for (int i = 1; i < allRows.size(); i++) {
            listOfDates.add(allRows.get(i).findElement(By.xpath("td[3]")).getText());
        }

        for (String dateList : listOfDates) {
            String date = dateList.substring(0, 2);
            String month = dateList.substring(3, 6);
            String year = dateList.substring(7);

            //System.out.println(date + " " + month + " " + year);

            int monthInt = 0;
            int dateInt = Integer.parseInt(date);
            int yearInt = Integer.parseInt(year);

            if (month.equalsIgnoreCase("may")) {
                monthInt = 5;
            } else if (month.equalsIgnoreCase("jun")) {
                monthInt = 6;
            } else if (month.equalsIgnoreCase("jul")) {
                monthInt = 7;
            } else if (month.equalsIgnoreCase("aug")) {
                monthInt = 8;
            }

            sortedDates = new ArrayList<>();
            sortedDates.add(LocalDate.of(yearInt, monthInt, dateInt));
            System.out.println(sortedDates);
        }

        for (int i = 0; i < listOfDates.size(); i++) {
            //System.out.println("Date: " + listOfDates.get(i) + " High: "+highPrice.get(i) + " Low: "+lowPrice.get(i));
        }

        double high, low = -1;

        for (int i = 0; i < sortedDates.size(); i++) {
            if (sortedDates.get(i).getDayOfWeek().equals(DayOfWeek.FRIDAY)) {
                high = Double.valueOf(highPrice.get(i));
                low = Double.valueOf(lowPrice.get(i));
                for (int j = i + 1; j < i + 6; j++) {
                    if (Double.valueOf(highPrice.get(j)) > high) {
                        high = Double.valueOf(highPrice.get(j));
                    }
                    if (Double.valueOf(lowPrice.get(j)) < low) {
                        low = Double.valueOf(lowPrice.get(j));
                    }
                }
            }


        }
    }
}