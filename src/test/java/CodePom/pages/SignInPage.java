package CodePom.pages;

import CodePom.base.ValiDateHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class SignInPage {

    private WebDriver driver;

    private ValiDateHelper valiDateHelper;

    private By emailInput = By.xpath("//input[@id='email']");
    private By passwordInput = By.xpath("//input[@id='password']");
    private By signInBth = By.xpath("//button[contains(text(),'Sign in')]");

    public SignInPage(WebDriver driver) {   // hàm xây dựng: có tên biến giống hệt tên class
        this.driver = driver;
      valiDateHelper = new ValiDateHelper(driver);

    }

    public DashbordPage signIn(String email, String password) {
       // kiểm tra xem đã đến trang Sign In Page hay chưa

      //  valiDateHelper.waitForJQueryLoaded();
      valiDateHelper.waitForPageLoaded();

        Assert.assertTrue( valiDateHelper.verifyElementText(signInBth, "Sign in") , "k phai trang Sign In Page. Vui long kiem tra lai");

        valiDateHelper.setText(emailInput, email);
        valiDateHelper.setText(passwordInput, password);

        valiDateHelper.clickElement(signInBth);
          return new DashbordPage(this.driver);
    }
}
    // Khởi tạo class khi được gọi và truyền driver vào để các thành phần trong
    // class này đọc
    // Sau khi thực hiện click Submit thì khởi tạo trang DashboardPage


