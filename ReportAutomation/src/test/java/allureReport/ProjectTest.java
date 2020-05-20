package allureReport;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;

@Listeners({ AllureListener.class })
public class ProjectTest extends BaseClass {
	public WebDriver driver;

	@BeforeClass
	public void setUp() {

		BaseClass bs = new BaseClass();
		
		driver = bs.initialize_driver();
		driver.get("https://icedq.com/");
		driver.manage().window().maximize();

	}

	@Test(priority = 1)
	@Description("Verify logo presence on the home page")
	@Epic("US0001")
	@Feature("Feature1:Logo")
	@Story("Check logo presence")
	@Step("Check step of logo presence") // this can be added in the test method
	@Severity(SeverityLevel.MINOR)
	public void iCEDQlogoPresenceTest() {
		driver.get("https://icedq.com/");
		boolean displayStatus = driver.findElement(By.xpath("//img[@alt='iCEDQ']")).isDisplayed();
		Assert.assertEquals(displayStatus, true);
	}

	@Test(priority = 2)
	@Description("Verify login title test")
	@Epic("US0001")
	@Feature("Feature2:Title")
	@Story("Check correct title presence")
	@Step("verify correct title as expected") // this can be added in the test method
	@Severity(SeverityLevel.NORMAL)
	public void iCEDQloginTitleTest() {
		System.out.println("Started login title test");
		driver.getTitle();
		Assert.assertEquals(driver.getTitle(),
				"Automate ETL/ DW Testing, Data Migration Testing, BI Report Testing | iCEDQ1111"); // Failing the test

	}

	@Test(priority = 3)
	@Description("Verify Statndard Edition Page")
	@Epic("US0001")
	@Feature("Feature3:Statndard Edition")
	@Story("Check Statndard Edition Page")
	@Step("verify Statndard Edition page and validate title") // this can be added in the test method
	@Severity(SeverityLevel.CRITICAL)
	public void productStandardEtest() {
		Actions action = new Actions(driver);
		WebElement btn = driver.findElement(
				By.xpath("//header[@class='header-bar']//span[@class='menu-text'][contains(text(),'Product')]"));
		action.moveToElement(btn).perform();

		WebElement standardE = driver.findElement(By.xpath(
				"//li[@class='menu-item menu-item-type-custom menu-item-object-custom menu-item-has-children menu-item-13664 first has-children show-mega-menu-content']//span[@class='menu-text'][contains(text(),'Standard Edition')]"));
		standardE.click();

		driver.getTitle();
		Assert.assertEquals(driver.getTitle(), "iCEDQ Standard Edition | Complete Data Testing Solution");

	}

	@Test(priority = 4)
	@Description("Verify Data Connectors Page")
	@Epic("US0001")
	@Feature("Feature4:Data Connectors")
	@Story("Check Data Connectors Page")
	@Step("verify data connector link") // this can be added in the test method
	@Severity(SeverityLevel.BLOCKER)
	public void dataConnectorLinkTest() {
		driver.findElement(
				By.xpath("//header[@class='header-bar']//span[@class='menu-text'][contains(text(),'Data Connectors')]"))
				.click();
		driver.getTitle();
		Assert.assertEquals(driver.getTitle(), "Connect to Standards Hadoop & Database Systems | iCEDQ");
	}

	@Test(priority = 5)
	@Description("Verify Try Now option")
	@Epic("US0001")
	@Feature("Feature5:Try Now feature")
	@Story("Check Try Now Page")
	@Step("verify try now with user details") // this can be added in the test method
	@Severity(SeverityLevel.CRITICAL)
	public void tryNowTest() {
		driver.findElement(
				By.xpath("//a[@class='vc_general vc_btn3 vc_btn3-size-lg vc_btn3-shape-square vc_btn3-style-custom']"))
				.click();
		driver.findElement(By.id("input_2222")).sendKeys("Vivek");// Wrong ID
		driver.findElement(By.id("input_2222")).sendKeys("Ramteke");

	}

	@Test(priority = 6)
	@Description("Verify Resources Page")
	@Epic("US0001")
	@Feature("Feature6:Resources")
	@Story("Check Resources")
	@Step("Verify Resources page and details")
	@Severity(SeverityLevel.NORMAL)
	public void resourcesTest() {

		throw new SkipException("Skipping the test case");
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
