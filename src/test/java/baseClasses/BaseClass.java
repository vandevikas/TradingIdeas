package baseClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BaseClass {
    public WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\IdeaProjects\\DataDrivenTestinginSeleniumMykolaKolisnyk\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, 10);
    }

    public WebElement waitForElementClickable(By element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void click(By element) {
        waitForElementClickable(element).click();
    }

    public void sendKeys(By element, String text) {
        waitForElementClickable(element).sendKeys(text);
    }

    public void selectDropdownByText(By element, String visibleText) {
        Select dropdown = new Select(waitForElementClickable(element));
        dropdown.selectByVisibleText(visibleText);
    }

    public String getText(By element) {
        return waitForElementClickable(element).getText();
    }

    public double getDoubleValue(By element) {
        return Double.valueOf(getText(element).replaceAll(",", ""));
    }

}
