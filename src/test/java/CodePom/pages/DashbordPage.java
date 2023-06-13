package CodePom.pages;

import CodePom.base.ValiDateHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class DashbordPage {
    private WebDriver driver;
    private ValiDateHelper  valiDateHelper;
// hàm xd
public DashbordPage(WebDriver driver) {
    this.driver = driver;
    valiDateHelper=new ValiDateHelper(driver);
}
private String url = "/dashboard";
private By projectMenu = By.xpath("//span[contains(@class,'menu-text')][normalize-space()='Projects']");
private By elementTotalInvoicedText =By.xpath("//span[text()='Total invoiced']");

    private By elementDashboard =By.xpath("//span[text()='Payments'] ");


public  ProjectPage openProjectPage(){
    System.out.println( valiDateHelper.veryfyURL(url)  );

    valiDateHelper.waitForPageLoaded();


    // kiểm tra Element có tồn tại
    Assert.assertTrue( valiDateHelper.verifyElementExist(elementDashboard) , "Element k tồn tại");

    // kiểm tra tra bằng nội dung trang = text Element
    Assert.assertTrue( valiDateHelper.verifyElementText(elementTotalInvoicedText, "Total invoiced") , "k phai noi dung trang Dashbord. Vui long kiem tra lai");

//    => kết hợp kiểm tra trang = 2 cách-> chính xác 100%

    // kiểm tra có đúng trang chưa = cách dùng URL
    Assert.assertTrue( valiDateHelper.veryfyURL(url) ,"Khong phải trang Dashboard  . kiem tra lai  !!1");
    valiDateHelper.clickElement(projectMenu);

return new ProjectPage(driver);
}
}
