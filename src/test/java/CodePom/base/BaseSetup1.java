package CodePom.base;

import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.*;


public class BaseSetup1 {
    private WebDriver driver;
    private String url= "https://rise.fairsketch.com/signin";
    public WebDriver getDriver() {
        return driver;
    }
    public  String getURL() {
        return url;
    }

    //Hàm này để tùy chọn Browser. Cho chạy trước khi gọi class này (BeforeClass)

    private void setDriver(String browserType, String appURL) {
        switch (browserType) {
            case "chrome":
                driver = initChromeDriver();
                driver.navigate().to(appURL);
                break;
            case "firefox":
                driver = initFirefoxDriver();
                driver.navigate().to(appURL);
                break;
            case "edge":
                driver = initEdgeDriver();
                driver.navigate().to(appURL);
                break;
            case "safari":
                driver = initSafariDriver();
                driver.navigate().to(appURL);
                break;
            default:
                System.out.println("Browser : " + browserType + " is invalid, Launching Chrome  Browser");
                driver = initChromeDriver();
                driver.navigate().to(appURL);
        }

    }
    public WebDriver setAndGetDriver(String browserType) {
        switch (browserType.trim().toLowerCase() ) {
            case "chrome":
                driver = initChromeDriver();
                break;
            case "firefox":
                driver = initFirefoxDriver();
                break;
            case "edge":
                driver = initEdgeDriver();
                break;
            case "safari":
                driver = initSafariDriver();
                break;
            default:
                System.out.println("Browser : " + browserType + " is invalid, Launching Chrome  Browser");
                driver = initChromeDriver();
        }
        return driver;
    }


    //Khởi tạo cấu hình của các Browser để đưa vào Switch Case


    private static WebDriver initChromeDriver() {
        System.out.println("Launching Chrome browser...");
        WebDriverManager.chromedriver().setup();
//        System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        return driver;
    }

    private static WebDriver initFirefoxDriver() {
        System.out.println("Launching Firefox browser...");
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        return driver;
    }

    private static WebDriver initEdgeDriver() {
        System.out.println("Launching Opera browser...");
        WebDriverManager.edgedriver().setup();
        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        return driver;
    }

    private static WebDriver initSafariDriver() {
        System.out.println("Launching Firefox browser...");
        WebDriverManager.safaridriver().setup();
        WebDriver driver = new SafariDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        return driver;
    }

    // Chạy hàm initializeTestBaseSetup trước hết khi class này được gọi
    @Parameters({"browserType", "appURL"})
    @BeforeClass
    //     public void initializeTestBaseSetup(String browserType, String appURL ) {
    public void initializeTestBaseSetup(@Optional() String browserType, @Optional() String appURL) {

        try {
            // Khởi tạo driver và browser
            setDriver(browserType, appURL);
        } catch (Exception e) {
            System.out.println("Error..." + e.getStackTrace());
        }
    }

    @AfterClass
    public void tearDown() throws Exception {
        Thread.sleep(4000);
        driver.quit();
    }
}
//    public void initializeTestBaseSetup(@Optional("chrome")String browserType, @Optional("https://rise.fairsketch.com/signin")String appURL) {

// public void initializeTestBaseSetup(@Optional()String browserType, @Optional()String appURL) {
