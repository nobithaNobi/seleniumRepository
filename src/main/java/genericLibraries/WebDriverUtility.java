package genericLibraries;

import java.io.File;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * This class contains reusable methods to perform driver related operations
 * @author Srinivas Yadav
 */

public class WebDriverUtility {
	private WebDriver driver;
	private WebDriverWait wait;
	private Actions action;
	private Select select;
	
	/**
	 * This method launches the browser and maximizes it
	 * @param browser
	 * @return
	 */
	public WebDriver launchAndMaximizeBrowser(String browser) {
		switch(browser) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		default:
			System.out.println("Invalid browser info");
		}
		driver.manage().window().maximize();
		return driver;
	}
	
	/**
	 * This method is used to navigate to the application
	 * @param url
	 */
	public void navigateToApp(String url) {
		driver.get(url);
	}
	
	/**
	 * This method is used to wait until element/elements are found
	 * @param time
	 */
	public void waitTillElementFound(long time) {
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}
	
	/**
	 * This method waits until the element is visible on the web page
	 * @param time
	 * @param element
	 * @return
	 */
	public WebElement explicitWait(long time, WebElement element) {
		wait = new WebDriverWait(driver, time);
		return wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	/**
	 * This method waits until element is enabled to click
	 * @param element
	 * @param time
	 * @return
	 */
	public WebElement explicitWait(WebElement element, long time) {
		wait = new WebDriverWait(driver, time);
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	/**
	 * This method waits until title of the web page is displayed
	 * @param title
	 * @param time
	 * @return
	 */
	public Boolean explicitWait(String title, long time) {
		wait = new WebDriverWait(driver, time);
		return wait.until(ExpectedConditions.titleContains(title));
	}
	
	/**
	 * This method is used to mouse hover on an element
	 * @param element
	 */
	public void mouseHoverToElement(WebElement element) {
		action = new Actions(driver);
		action.moveToElement(element).perform();
	}
	
	/**
	 * This method is used to double click on an element
	 * @param element
	 */
	public void doubleClickOnElement(WebElement element) {
		action = new Actions(driver);
		action.doubleClick(element).perform();
	}
	
	/**
	 * This method is used to right click on an element
	 * @param element
	 */
	public void rightClick(WebElement element) {
		action = new Actions(driver);
		action.moveToElement(element).perform();
	}
	
	/**
	 * This method is used to drag and drop an element to target location
	 * @param element
	 * @param target
	 */
	public void dragAndDropElement(WebElement element, WebElement target) {
		action = new Actions(driver);
		action.dragAndDrop(element, target).perform();
	}
	
	/**
	 * This method is used to select an element from drop down based on index
	 * @param element
	 * @param index
	 */
	public void selectFromDropdown(WebElement element, int index) {
		select = new Select(element);
		select.selectByIndex(index);
	}
	
	/**
	 * This method is used to select an element from drop down based on value
	 * @param element
	 * @param value
	 */
	public void selectFromDropdown(WebElement element, String value) {
		select = new Select(element);
		select.selectByValue(value);
	}
	
	/**
	 * This method is used to select an element from drop down based on text
	 * @param text
	 * @param element
	 */
	public void selectFromDropdown(String text, WebElement element) {
		select = new Select(element);
		select.selectByVisibleText(text);
	}
	
	/**
	 * This method fetches screenshot of the web page
	 * @param driver
	 * @param jutil
	 * @param className
	 */
	public void takeScreenshot(WebDriver driver, JavaUtility jutil, String  className) {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File("./Screenshots/ "+ className+"_"+ jutil.getCurrentTime()+".png");
		try {
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method is used to scroll till the element
	 * @param element
	 */
	public void scrollTillElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true)", element);
	}
	
	/**
	 * This method is used to switch to frame based on frame index
	 * @param index
	 */
	public void switchToFrame(int index) {
		driver.switchTo().frame(index);
	}
	
	/**
	 * This method is used to switch to frame based on id or name attribute in frame
	 * @param idOrName
	 */
	public void switchToFrame(String idOrName) {
		driver.switchTo().frame(idOrName);
	}
	
	/**
	 * This method is used to switch to frame based on frame element reference
	 * @param frameElement
	 */
	public void switchToFrame(WebElement frameElement) {
		driver.switchTo().frame(frameElement);
	}
	
	/**
	 * This method is used to switch back from the frame
	 */
	public void switchBackFromFrame() {
		driver.switchTo().defaultContent();
	}
	
	/**
	 * This method is used to handle alert pop up
	 * @param status
	 */
	public void handleAlert(String status) {
		Alert al = driver.switchTo().alert();
		if(status.equalsIgnoreCase("ok"))
			al.accept();
		else
			al.dismiss();
	}
	
	/**
	 * This method is used to switch to child browser
	 */
	public void switchToChildBrowser() {
		Set<String> windowIDs = driver.getWindowHandles();
		for (String id : windowIDs) {
			driver.switchTo().window(id);
		}
	}
	
	/**
	 * This method fetches parent window address
	 * @return
	 */
	public String getParentID() {
		return driver.getWindowHandle();
	}
	
	/**
	 * This method switches the control to specified window address
	 * @param windowID
	 */
	public void switchToWindow(String windowID) {
		driver.switchTo().window(windowID);
	}
	
	/**
	 * This method is used to close current window or tab
	 */
	public void closeWindow() {
		driver.close();
	}
	
	/**
	 * This method is used to close all tabs and windows
	 */
	public void quitAllWindows() {
		driver.quit();
	}
	
	/**
	 * This method converts dynamic xpath to web element
	 * @param path
	 * @param replaceData
	 * @return
	 */
	public WebElement convertDynamicXpathToElement(String path, String replaceData) {
		String requiredPath = String.format(path, replaceData);
		return driver.findElement(By.xpath(requiredPath));
	}
}
