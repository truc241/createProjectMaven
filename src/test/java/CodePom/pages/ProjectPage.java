package CodePom.pages;

import CodePom.base.ValiDateHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;
import java.util.Locale;

public class ProjectPage {
    private WebDriver driver;
    private ValiDateHelper valiDateHelper;

    public ProjectPage(WebDriver driver) {
        this.driver = driver;
        valiDateHelper = new ValiDateHelper(driver);
    }

    private String pageText = "Projects";
    private String url = "/projects/all_projects";

    private By headertitlePage = By.xpath("//h1[normalize-space()='Projects']");
    private By addProjectBtn = By.xpath("//a[normalize-space()='Add project']");

//public boolean verifyElement() {
//    return (driver.findElement(headertitlePage).getText().equals(pageText) );
//}   Đã viết thành hàm chung. Giờ chỉ cần valiDateHelper. verifyElementText   là có thể dùng lại

    private By searchProjectInput = By.xpath("//input[@placeholder='Search']");

    public AddProjectPage   addProject() {

        valiDateHelper.verifyElementText(headertitlePage, pageText);

        valiDateHelper.clickElement(addProjectBtn);

        return new AddProjectPage(driver);
    }

    public void enterSearchValue(String valueSearch) {
        valiDateHelper.setText(searchProjectInput, valueSearch);
    }

    public void checkSearchTableColumn(int column, String text) throws Exception {
        valiDateHelper.waitForPageLoaded();
        // xác định số dòng (tất cả kết quả) của table sau khi search ->enter
        List<WebElement> row = driver.findElements(By.xpath("//table//tbody/tr"));
        int rowtotal = row.size();
        System.out.println("Co " + rowtotal + " dong dc tim thay");

        for (int i = 1; i <= rowtotal; i++) {
            WebElement elementCheck = driver.findElement(By.xpath("//table//tbody/tr[" + i + "]/td[" + column + "]/span[1]"));

            valiDateHelper.scrolltoElementJS(elementCheck);// cuộn xuống

            System.out.print("Tu khoa ban muon tim kiem: " + text.toUpperCase() + " --  ");
            System.out.println("KQ " + elementCheck.getText().toUpperCase());

            System.out.println("Dong thu " + i + " ket qua la:  " + elementCheck.getText());
            Assert.assertTrue(elementCheck.getText().toUpperCase().contains(text.toUpperCase()), "Dong thu " + i + "k tim thay gia tri tim kiem ");

        }
    }
}
