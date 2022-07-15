package selenium;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class KeysText extends BaseClass {
	
	public WebDriver driver;
	
	public Actions actions;
	public WebElement inputBox;
	public String textValue;
	
	@BeforeTest
	// Initialize the WebDriver before running the tests
	public void initializeDriver() {
		
		driver = setWebDriver();
		driver.get("https://www.selenium.dev/selenium/web/single_text_input.html");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
	}
	
	@AfterTest
	// Close down all running processes after the tests have run
	public void closeDown() {
		
		driver.quit();
	}
	
	@BeforeMethod
	// Initialize the input text box and the Actions object before each method
	public void initializeInputBox() {
		
		inputBox = driver.findElement(By.id("textInput"));
		actions = new Actions(driver);
	}
	
	@AfterMethod
	// Clear the input text box after each method
	public void clearTextBox() {
		
		inputBox.clear();
	}
	
	@Test
	// Type something into the text box
	public void fillTextBox() {
		
		actions.sendKeys(inputBox, "selenium");
		actions.perform();
		
		textValue = inputBox.getAttribute("value");
		System.out.println(textValue);
		
		assertEquals(textValue, "selenium");
	}
	
	@Test
	// Type a capital letter into the text box with the Shift key
	public void keyDown() {

		actions.click(inputBox);
		actions.keyDown(Keys.SHIFT);
		actions.sendKeys("a");
		actions.perform();
		
		textValue = inputBox.getAttribute("value");
		System.out.println(textValue);
		
		assertEquals(textValue, "A");	
	}
	
	@Test
	// Type capital and lower-case letters into the text box with the Shift key
	public void keyUp() {

		actions.click(inputBox);
		actions.keyDown(Keys.SHIFT);
		actions.sendKeys("a");
		actions.keyUp(Keys.SHIFT);
		actions.sendKeys("b");
		actions.perform();
		
		textValue = inputBox.getAttribute("value");
		System.out.println(textValue);
		
		assertEquals(textValue, "Ab");
	}
}
