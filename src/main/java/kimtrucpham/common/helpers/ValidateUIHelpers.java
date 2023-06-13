package kimtrucpham.common.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class ValidateUIHelpers {
    private WebDriver driver;
    private Actions action;
    private Select select;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    public ValidateUIHelpers(WebDriver driver) {

        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        js = (JavascriptExecutor) driver;   //C2   JavascriptExecutor js = (JavascriptExecutor) driver;
        action = new Actions(driver);
    }

    public String getPageTitle() {
        waitForPageLoaded();
        String title = driver.getTitle();
        System.out.println(" Current Page Title : " + driver.getTitle());
        return title;
    }

    public boolean verifyPageTitle(String pageTitle) {
        waitForPageLoaded();
        return getPageTitle().equals(pageTitle);
    }

    public boolean verifyPageUrl(String pageURL) {
        System.out.println("Current URL is:   " + driver.getCurrentUrl());
        return driver.getCurrentUrl().contains(pageURL);  // true/ false
    }

    public void setText(By element, String value) {
        waitForPageLoaded();
        wait.until(ExpectedConditions.elementToBeClickable(element));

        driver.findElement(element).click(); // C1
        driver.findElement(element).clear();
        driver.findElement(element).sendKeys(value);
    }

    public void clickElement(By element) {
        waitForPageLoaded();
        //C2
        WebElement elementWait = wait.until(ExpectedConditions.elementToBeClickable(element));
        elementWait.click();
    }

    public void clickElementJS(By element) {
        waitForPageLoaded();
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(element));
        js.executeScript("arguments[0].click();", driver.findElement(element));
    }

    public void rightclickElement(By element) { // click = chuột phải
        waitForPageLoaded();
        wait.until(ExpectedConditions.elementToBeClickable(element));
        action.contextClick().build().perform(); // contextClick : click = chuột phải
    }

    public void selectOptionByText(By element, String text) {  // Handle Dropdown select Option
        select = new Select(driver.findElement(element));
        select.selectByVisibleText(text);
    }

    public void selectOptionByValue(By element, String value) {
        select = new Select(driver.findElement(element));
        select.selectByValue(value);
    }

    public void selectOptionByIndex(By element, int index) {
        select = new Select(driver.findElement(element));
        select.selectByIndex(index);
    }

    public void verifyOptionTotal(By element, int total ){
        select = new Select(driver.findElement(element));
        Assert.assertEquals(total, select.getOptions().size());
    }

    public boolean verifySelectByText  (By element,   String text){  // kiểm tra lại xem đã chọn đúng Option đó hay chưa
        select = new Select(driver.findElement(element));
        System.out.println("Option you Select is:  " + select.getFirstSelectedOption().getText()); // optin bạn chọn là
return select.getFirstSelectedOption().getText().equals(text) ;
    }

    public boolean verifySelectByValue(By element, String Value){  // . getAttribute
        select = new Select(driver.findElement(element));
        System.out.println("Option you Select is:  "  +select.getFirstSelectedOption().getAttribute("value"));
    return  select.getFirstSelectedOption().getAttribute("value").equals("Value");
    }




    public boolean verifyPageLoaded(String pageLoadedText) {
        waitForPageLoaded();
        Boolean ABC = false;
        ABC = driver.getPageSource().toString().contains(pageLoadedText);
        System.out.println("Page loaded" + ABC + "  :  " + pageLoadedText);
        List<WebElement> elementList = (driver.findElements(By.xpath(" //*[contains(text), '   " + pageLoadedText + "   ' )] ")));
        if (elementList.size() > 0) {

            ABC = true;
            System.out.println("Page loaded " + ABC + " : " + pageLoadedText + " true");
        } else {

            ABC = false;
            System.out.println("Page loaded " + ABC + " : " + pageLoadedText + " false");
        }
        return ABC;
    }

    public void waitForPageLoaded() {
        ExpectedCondition<Boolean> jQueryLoaded = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            }
        };
        // wait for JavaScrip to loaded
        ExpectedCondition<Boolean> jSLoaded = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {

                return ((JavascriptExecutor) driver).executeScript("return document.readyState")
                        .toString().equals("complete");
            }
        };

        try {
            wait = new WebDriverWait(driver, Duration.ofSeconds(20));

            wait.until(jSLoaded);
            wait.until(jQueryLoaded);

        } catch (Throwable error) {
            Assert.fail("Quá thời gian load trang!!");
        }
    }
}
