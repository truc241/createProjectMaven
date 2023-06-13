package CodePom.testcase;

import CodePom.base.BaseSetup1;
import CodePom.pages.SignInPage;
import kimtrucpham.common.ultilities.PropertiesFile;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PropertiesTest {
    private WebDriver driver;
  //  private PropertiesFile propertiesFile;
    private SignInPage signInPage;
@BeforeClass
    public void createDriver(){
    PropertiesFile.setPropertiesFile();

    // đọc data từ file properties với key là "browser"
    driver = new BaseSetup1().setAndGetDriver( PropertiesFile.getPropValue("browser"));
}

    @Test
    public void signIn_SetProperties() throws Exception {
        signInPage =new SignInPage(driver);
        driver.get("https://rise.fairsketch.com/signin");

// set 1 giá trị mới cho email
        PropertiesFile.setPropValue("browser","firefox");
        PropertiesFile.setPropValue("email","PTKT@GMAIL.com");
       PropertiesFile.setPropValue("password","02042001");

        System.out.println("gia trị emal và password:  ");
        signInPage.signIn(PropertiesFile.getPropValue("email"),
                PropertiesFile.getPropValue("password"));
PropertiesFile.setPropValue("Pin" , "9999");
PropertiesFile.setPropValue("ID" , "MT1)");

    }

@Test
    public void signIn_GetProperties() throws Exception {
    signInPage =new SignInPage(driver);
driver.get("https://rise.fairsketch.com/signin");

// đọc data từ file properties
    signInPage.signIn(PropertiesFile.getPropValue("email"),
            PropertiesFile.getPropValue("password"));
}

}
