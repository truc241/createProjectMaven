package kimtrucpham.common;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class ValiDateHelper {
    private WebDriver driver;
    private WebDriverWait wait;
    JavascriptExecutor js;
    private Select select;

    public ValiDateHelper(WebDriver driver) {   // hàm xây dựng contructor
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        js = (JavascriptExecutor) driver;

    }

    public void setText(By element, String value) {
        // sendKeys 1 giá trị Value cho element truyền vào
        wait.until(ExpectedConditions.elementToBeClickable(element));

        driver.findElement(element).clear();
        driver.findElement(element).sendKeys(value);
    }

    public void clickElement(By element) {
        // click vào một phần tử elemet
        wait.until(ExpectedConditions.elementToBeClickable(element));
        driver.findElement(element).click();

        // or  click theo js
        // js.executeScript("arguments[0].click();", driver.findElement(element) );
    }

    public void scrolltoElementJS(WebElement element) {
        waitForPageLoaded();
        js.executeScript("arguments[0].scrollIntoView(true);", (element));
    }

    public void clickElement_JS(By element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        // or  click theo js
        js.executeScript("arguments[0].click();", driver.findElement(element));
    }


    public void selecOptionByText(By element, String text) {
        // chuyển từ đối tượng By sang đối tượng Element thì thêm driver.findElement
        Select select = new Select(driver.findElement(element));
        select.selectByVisibleText(text);
    }

    public void selecOptionByValue(By element, String text) {
        // chuyển từ đối tượng By sang đối tượng Element thì thêm driver.findElement
        Select select = new Select(driver.findElement(element));
        select.selectByValue(text);
    }


    public boolean veryfyURL(String url) {

        System.out.println("Link hien tai la:   " + driver.getCurrentUrl());
        System.out.println("URL can tim la : " + url);

        return driver.getCurrentUrl().contains(url); // True false
    }

    // kiểm tra sự tồn tại của element, chứ k cần kiểm tra cái text của nó là gì
    public boolean verifyElementExist(By element) {
        // tạo List lưu tất cả đối tượng WebElement     findElements có s
        List<WebElement> list = driver.findElements(element);

        int total = list.size();
        if (total > 0) {
            return true;
        } else {
            return false;
        }

    }


    // kiểm tra cái element đó có tồn tại,
    // nếu nó tồn tại thì mới lấy dc text, còn k tồn tại thì văng ra Exception và thông báo Element đó k tồn tại
    public boolean verifyElementText(By element, String textValue) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));

        return driver.findElement(element).getText().equals(textValue); // True/ false
    }


    public void waitForJQueryLoaded() {
        // wait for jQuery to loaded
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
        try {
            wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(jQueryLoaded);

        } catch (Throwable error) {
            Assert.fail("Quá thời gian load trang chờ JQuery");
        }
    }


    public void waitForJSLoaded() {
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

        } catch (Throwable error) {
            Assert.fail("Quá thời gian load trang chờ JavaScrip!!");
        }
    }


    public void waitForPageLoaded() {  // gộp 2 cái wait js & wait jQuery vào cùng 1 hàm
        // khi test chỉ cần gọi 1 cái hàm này thì nó sẽ  wait cả js và jquery

        // wait for jQuery to loaded
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
