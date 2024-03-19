package objectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericLibraries.WebDriverUtility;

public class AddNewCoursePage {
	// Declaration
		@FindBy(xpath = "//b[text()='Add New Course']")
		private WebElement pageHeader;

		@FindBy(id = "name")
		private WebElement nameTF;

		@FindBy(id = "category")
		private WebElement categoryDropdown;

		@FindBy(xpath = "//input[@id='price' and @required]")
		private WebElement priceTF;

		@FindBy(xpath = "(//input[@id='photo'])[2]")
		private WebElement uploadPhotoButton;

		@FindBy(xpath = "//html/body/p")
		private WebElement descriptionBox;

		@FindBy(xpath = "//iframe[contains(@title,'editor1')]")
		private WebElement descriptionFrame;

		@FindBy(name = "add")
		private WebElement saveButton;

		// Initialization
		public AddNewCoursePage(WebDriver driver) {
			PageFactory.initElements(driver, this);
		}

		// Utilization
		public String getPageHeader() {
			return pageHeader.getText();
		}
		
		public void setName(String name) {
			nameTF.sendKeys(name);
		}
		
		public void selectCategory(WebDriverUtility web, String categoryName) {
			web.selectFromDropdown(categoryName, categoryDropdown);
		}
		
		public void setPrice(String price) {
			priceTF.sendKeys(price);
		}
		
		public void uploadPhoto(String photo) {
			uploadPhotoButton.sendKeys(photo);
		}
		
		public void addDescription(WebDriverUtility web, String description) {
			web.switchToFrame(descriptionFrame);
			descriptionBox.sendKeys(description);
			web.switchBackFromFrame();
		}
		
		public void clickSave() {
			saveButton.click();
		}
}
