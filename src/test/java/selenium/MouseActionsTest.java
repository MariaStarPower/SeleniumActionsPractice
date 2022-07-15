package selenium;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MouseActionsTest extends BaseClass {
	
	public WebDriver driver;
	
	public Actions actions;
	public WebElement link;
	public WebElement textBox;
	public WebElement draggable;
	public WebElement droppable;
	public WebElement hoverable;
	public WebElement mouseBox;
	
	@BeforeTest
	// Initialize the WebDriver before running the tests
	public void initializeDriver() {
		
		driver = setWebDriver();
		driver.get("https://www.selenium.dev/selenium/web/mouse_interaction.html");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
	}
	
	@AfterTest
	// Close down all running processes after the tests have run
	public void closeDown() {
		
		driver.quit();
	}
	
	@BeforeMethod
	// Initialize the Action object before each method
	public void initializeAction() {
		
		actions = new Actions(driver);
	}
	
	@Test
	// Verify that the link to the Results page works
	public void clickOnLink() {
		
		link = driver.findElement(By.id("click"));
		
		link.click();
		
		String resultsPageUrl = driver.getCurrentUrl();
		System.out.println(resultsPageUrl);
		
		String resultsPageTitle = driver.getTitle();
		System.out.println(resultsPageTitle);
		
		assertEquals(resultsPageTitle, "We Arrive Here");
		
		driver.navigate().back();
	}
	
	@Test
	// Verify that the clickable text box works
	public void clickTextBox() {
		
		textBox = driver.findElement(By.id("clickable"));
		
		actions.click(textBox);
		actions.perform();
		
		String clickStatus;
		
		clickStatus = driver.findElement(By.id("click-status")).getText();
		System.out.println(clickStatus);
		
		assertEquals(clickStatus, "focused");
		
		actions.doubleClick(textBox);
		actions.perform();
		
		clickStatus = driver.findElement(By.id("click-status")).getText();
		System.out.println(clickStatus);
		
		assertEquals(clickStatus, "double-clicked");
		
		actions.contextClick(textBox);
		actions.perform();
		
		clickStatus = driver.findElement(By.id("click-status")).getText();
		System.out.println(clickStatus);
		
		assertEquals(clickStatus, "context-clicked");
	}
	
	@Test
	// Verify that the draggable box can be dropped into the droppable box
	public void dragAndDrop() {
		
		draggable = driver.findElement(By.id("draggable"));
		droppable = driver.findElement(By.id("droppable"));
		
		actions.dragAndDrop(draggable, droppable);
		actions.perform();
		
		String dropStatus = driver.findElement(By.id("drop-status")).getText();
		System.out.println(dropStatus);
		
		assertEquals(dropStatus, "dropped");
	}
	
	@Test
	// Verify that the mouse hover feature works
	public void mouseHover() {
		
		hoverable = driver.findElement(By.id("hover"));
		
		actions.moveToElement(hoverable);
		actions.perform();
		
		String movedStatus = driver.findElement(By.id("move-status")).getText();
		System.out.println(movedStatus);
		
		assertEquals(movedStatus, "hovered");
	}
	
	@Test
	// Verify that I can go into the mouse tracker box and get my relative position
	public void enterMouseBox() {
		
		mouseBox = driver.findElement(By.id("mouse-tracker"));
		
		actions.moveToElement(mouseBox);
		actions.perform();
		
		String absolute = driver.findElement(By.id("absolute-location")).getText();
		String relative = driver.findElement(By.id("relative-location")).getText();
		
		System.out.println(absolute);
		System.out.println(relative);
	}

}
