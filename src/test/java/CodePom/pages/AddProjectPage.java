package CodePom.pages;

import CodePom.base.ValiDateHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class AddProjectPage {
    private WebDriver driver;
    private ValiDateHelper valiDateHelper;
    private Actions actions;

    public AddProjectPage(WebDriver driver) {

        this.driver = driver;
        valiDateHelper = new ValiDateHelper(driver);
    }

    private String pageText = "Add project";
    private String url = "/projects/all_projects";

    private By headerPageText = By.xpath("//h4[@id='ajaxModalTitle']");

    private By titleInput = By.xpath("//input[@id='title']");
    private By descriptionInput = By.xpath("//textarea[@id='description']");
    private By start_dateInput = By.xpath("//input[@id='start_date']");
    private By deadlineInput = By.xpath("//input[@id='deadline']");
    private By priceInput = By.xpath("//input[@id='price']");
    private By labelInput = By.xpath("/html[1]/body[1]/div[4]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[6]/div[1]/div[1]/div[1]/ul[1]/li[1]/input[1]");
    private By saveBtn = By.xpath("//button[normalize-space()='Save']");
    private By closeBtn = By.xpath("//form[@role='form']//button[@type='button'][normalize-space()='Close']");

    public void saveProject() throws Exception {
        Assert.assertTrue(valiDateHelper.verifyElementText(headerPageText, pageText), "Khong phai trang Add Project");

        valiDateHelper.setText(titleInput, "Where?");
        valiDateHelper.setText(descriptionInput, "Search project in the fulture.....");
        Thread.sleep(1000);
        valiDateHelper.setText(start_dateInput, "03-05-2023");
        valiDateHelper.setText(deadlineInput, "05-05-2023");
        valiDateHelper.setText(priceInput, "2000");


        valiDateHelper.scrolltoElementJS(driver.findElement(labelInput));

        valiDateHelper.setText(labelInput, "Wordpress");
        actions = new Actions(driver);
        actions.sendKeys(Keys.ENTER).build().perform();

        valiDateHelper.clickElement(closeBtn);

    }
}
