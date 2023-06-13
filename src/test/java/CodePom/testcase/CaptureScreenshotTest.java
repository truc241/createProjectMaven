package CodePom.testcase;



import io.github.bonigarcia.wdm.WebDriverManager;
import kimtrucpham.common.helpers.CaptureHelpers;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class CaptureScreenshotTest {

        private WebDriver driver;

        @BeforeClass
        public void setup() {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();

            driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        }

        @Test(priority = 1)
        public void homePage() throws Exception {
            driver.get("https://anhtester.com");
            //step này cố tình Fail để chụp màn hình lại
            Assert.assertEquals(driver.getTitle(), "Anh Tester - Automation Test");
        }

        @Test(priority = 2)
        public void loginPage() throws Exception {
            driver.findElement(By.id("btn-login")).click();
        }

        // Nó sẽ thực thi sau mỗi lần thực thi testcase (@Test)
        @AfterMethod
        public void takeScreenshot(ITestResult result) throws InterruptedException {
            Thread.sleep(1000);
            //Khởi tạo đối tượng result thuộc ITestResult để lấy trạng thái và tên của từng Test Case
            //Ở đây sẽ so sánh điều kiện nếu testcase passed hoặc failed
            //passed = SUCCESS và failed = FAILURE
            if (ITestResult.FAILURE == result.getStatus()) {
                try {
                    CaptureHelpers.captureScreenshot(driver, result.getName());
                       } catch (Exception e) {
                    System.out.println("Exception while taking screenshot " + e.getMessage());
                }
            }
        }

        @AfterClass
        public void tearDown() throws Exception {
            Thread.sleep(1000);
            driver.quit();
        }

    }
