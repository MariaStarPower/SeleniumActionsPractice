package selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	public WebDriver driver;
	
	// Set the WebDriver to work for the browser of choice
	public WebDriver setWebDriver() {
		
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		
		return driver;
	}

}
