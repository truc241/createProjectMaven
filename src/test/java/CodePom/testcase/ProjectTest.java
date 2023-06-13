package CodePom.testcase;

import CodePom.base.BaseSetup1;
import CodePom.pages.AddProjectPage;
import CodePom.pages.DashbordPage;
import CodePom.pages.ProjectPage;
import CodePom.pages.SignInPage;
import kimtrucpham.common.helpers.ExcelHelpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ProjectTest {
    private WebDriver driver;
    private BaseSetup1 baseSetup1;
    private SignInPage signInPage;
    private DashbordPage dashbordPage;
    private ProjectPage projectPage;
    private ExcelHelpers excel;

    @BeforeClass
    public void setUPDriver() {
        baseSetup1 = new BaseSetup1();  // c1
        driver = baseSetup1.setAndGetDriver("chrome");

        // driver = new BaseSetup1().setAndGetDriver("chrome");  //c2
        excel = new ExcelHelpers();

    }

    @Test
    public void loginPage() throws Exception {
           driver.get("https://rise.fairsketch.com/signin");
        driver.findElement(By.xpath("//button[contains(text(),'Sign in')]")).click();

        Thread.sleep(3000);
        driver.quit();
    }


    @Test(priority = 0)
    public void signInPage() throws Exception {
        signInPage = new SignInPage(driver);
        driver.get(baseSetup1.getURL());
        dashbordPage = new DashbordPage(driver);

        dashbordPage = signInPage.signIn("client@demo.com", "riseDemo");
        projectPage = dashbordPage.openProjectPage();
        Thread.sleep(2000);
    }

    @Test(priority = 2)
    public void openProjectPage() throws Exception {
        projectPage = dashbordPage.openProjectPage();
        Thread.sleep(2000);
    }

    @Test
    public void signInExcel() throws Exception {
        signInPage = new SignInPage(driver);
        driver.get("https://rise.fairsketch.com/signin");

        // set up đường dẫn file Excel
        excel.setExcelFile( ("src/test/reresources/fileExcel.xlsx" ), "Sheet1");

        // đọc data từ file Excel
        signInPage.signIn(excel.getCellData("username", 3),
                excel.getCellData("password", 3) );

        // ghi data vào file Excel
        excel.setCellData("Phamthikimtruc0204@gmail.com", 5, 0);

        driver.quit();
    }
@Test
    public void TestExcelWithFOR() throws Exception {
        signInPage= new SignInPage(driver);
        driver.get("https://rise.fairsketch.com/signin");
        excel.setExcelFile("src/test/reresources/fileExcel.xlsx","Sheet1");

        for (int i = 0; i < 6 ; i++ ) {
        signInPage.signIn(excel.getCellData("username", i )  , excel.getCellData("password",i));
Thread.sleep(2000);
    }

    for (int i = 6; i < 10   ; i++) {
excel.setCellData("ABCD@gmail.com", i,3);
    }

  driver.quit();
}



}
