package objectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericLibraries.WebDriverUtility;

public class CategoryPage {
	// Declaration
		@FindBy(xpath = "//h1[normalize-space(text())='Category']")
		private WebElement pageHeader;

		@FindBy(xpath = "//div[contains(@class,'alert-success')]")
		private WebElement successAlert;

		@FindBy(xpath = "//a[text()=' New']")
		private WebElement newButton;

		private String deleteButtonPath = "//td[text()='%s']/following-sibling::td/button[text()=' Delete']";

		@FindBy(name = "delete")
		private WebElement confirmDeleteButton;

		// Initialization
		public CategoryPage(WebDriver driver) {
			PageFactory.initElements(driver, this);
		}

		// Utilization
		public String getPageHeader() {
			return pageHeader.getText();
		}

		public String getSuccessAlertMessage() {
			return successAlert.getText();
		}

		public void clickNewButton() {
			newButton.click();
		}

		public void deleteCategory(WebDriverUtility web, String courseName) {
			web.convertDynamicXpathToElement(deleteButtonPath, courseName).click();
			confirmDeleteButton.click();
		}
}
