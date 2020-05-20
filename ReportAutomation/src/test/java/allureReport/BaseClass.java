package allureReport;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

import io.github.bonigarcia.wdm.WebDriverManager;


public class BaseClass{

	public WebDriver driver;
	public static ThreadLocal<WebDriver> tdriver = new ThreadLocal<WebDriver>(); // Creating the threads in the local
	
	
	//This will create threads which is shared by multiple classes

	public WebDriver initialize_driver() {

		WebDriverManager.chromedriver().setup();
		System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	
		tdriver.set(driver); // tdriver is common variable for the diff class.
		return getDriver();
		
	}

	
	
	
	
	
	
	public static synchronized WebDriver getDriver() {   // created synchronized method for sharing the same driver with the different class i.e.ProjectTest and AllureListener
	return tdriver.get();
	}
	
	
}
