package CodePom.testcase;

import CodePom.base.BaseSetup1;
import CodePom.base.ValiDateHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestLinkTrucTiep {
    private WebDriver driver  ;
    private BaseSetup1 baseSetup1;
    private ValiDateHelper valiDateHelper;

    @BeforeClass
    public  void setup(){
        //BaseSetup1  baseSetup1= new BaseSetup1();   //c1
        //driver = baseSetup1.setAndGetDriver("chrome");
        driver= new BaseSetup1().setAndGetDriver("firefox");
        valiDateHelper =new ValiDateHelper(driver);
    }
    @Test
    public void signIn() throws Exception {


        driver.get(" https://anhtester.com");
        valiDateHelper.veryfyURL("//anhtester.com");
        valiDateHelper.getPageTitle();
        valiDateHelper.rightCLickElement(By.xpath("//a[@id='btn-login']"));

        Thread.sleep(2000);
driver.quit();
    }



}
