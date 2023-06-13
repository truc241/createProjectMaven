package CodePom.testcase;

import CodePom.base.BaseSetup1;
import CodePom.base.ValiDateHelper;
import CodePom.pages.AddProjectPage;
import CodePom.pages.DashbordPage;
import CodePom.pages.ProjectPage;
import CodePom.pages.SignInPage;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.lang.reflect.Method;

public class SignInTest {  //extends BaseSetup1

    private WebDriver driver;
    private SignInPage signInPage;
    private ValiDateHelper valiDateHelper;
    private DashbordPage dashbordPage;
    private ProjectPage projectPage;
    public AddProjectPage addProjectPage;


    @BeforeClass
    public void setUp() {

        //driver = getDriver(); C1 extends BaseSetup1
        driver = new BaseSetup1().setAndGetDriver("chrome");  // khởi tạo Browser

        valiDateHelper = new ValiDateHelper(driver);
    }

    // Nó sẽ thực thi sau mỗi lần thực thi testcase (@Test)
    @AfterMethod
    public void takeScreenshot(ITestResult result) {
        valiDateHelper.waitForPageLoaded();
        // Khởi tạo đối tượng result thuộc ITestResult để lấy trạng thái và tên của từng Step
        // Ở đây sẽ so sánh điều kiện nếu testcase passed hoặc failed
        // passed = SUCCESS và
        // failed = FAILURE
        if (ITestResult.FAILURE== result.getStatus()) {
            try {
                // Tạo tham chiếu của TakesScreenshot
                TakesScreenshot ts = (TakesScreenshot) driver;
                // Gọi hàm capture screenshot - getScreenshotAs
                File source = ts.getScreenshotAs(OutputType.FILE);
                //Kiểm tra folder tồn tại. Nêu không thì tạo mới folder
                File theDir = new File("./Screenshots/");
                if (!theDir.exists()) {
                    theDir.mkdirs();
                }
                // result.getName() lấy tên của test case xong gán cho tên File chụp màn hình
                FileHandler.copy(source, new File("./Screenshots/" + result.getName() + ".png"));
                System.out.println("Đã chụp màn hình: " + result.getName());
            } catch (Exception e) {
                System.out.println("Exception while taking screenshot " + e.getMessage());
            }
        }
    }


    @Test(priority = 0)
    // public void signIn(ITestContext result) throws Exception {  // result sẽ in ra tên của Dự án là  CreateProjectWithMaven

    public void signIn(Method result) throws Exception {   // result sẽ in ra tên của tên của phương thức là signIn
        System.out.println("  Browser là:    " + driver);
        valiDateHelper.waitForPageLoaded();

        //    driver.get("https://rise.fairsketch.com/signin"); c1  truyền trực tiếp or
        driver.get(new BaseSetup1().getURL());    // gọi lại URL để chạy,          setup sắn

        signInPage = new SignInPage(driver);
        // Tạo tham chiếu của TakesScreenshot với driver hiện tại
//        TakesScreenshot ts = (TakesScreenshot) driver;
//// Gọi hàm capture screenshot - getScreenshotAs
//        File source = ts.getScreenshotAs(OutputType.FILE);
////Kiểm tra folder tồn tại. Nêu không thì tạo mới folder
//        File theDir = new File("./Screenshots/");
//        if (!theDir.exists()) {
//            theDir.mkdirs();
//        }
//// result.getName() lấy tên của test case xong gán cho tên File chụp màn hình luôn
//        FileHandler.copy(source, new File("./Screenshots/" + result.getName() + ".png"));
//        System.out.println("Screenshot taken: " + result.getName());
//

        dashbordPage = signInPage.signIn("client@demo.com", "riseDemo");
        valiDateHelper.waitForPageLoaded();
    }

    @Test(priority = 1)
    public void openProjectPage() throws Exception {
        valiDateHelper.waitForPageLoaded();

        projectPage = dashbordPage.openProjectPage();

    }

    @Test(priority = 2)
    public void openAdd() throws Exception {
        valiDateHelper.waitForPageLoaded(); // khi chuyển trang thì sd waitForPageLoaded

        addProjectPage = projectPage.addProject();
    }

    @Test(priority = 3)
    public void addProject() throws Exception {
        valiDateHelper.waitForPageLoaded();
        addProjectPage.saveProject();
    }

    @Test(priority = 4)
    public void checkSearchTableColumn() throws Exception {
        projectPage.enterSearchValue("logo");
        projectPage.checkSearchTableColumn(2, "logo");

    }


}
