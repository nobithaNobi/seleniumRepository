package testscripts;

import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericLibraries.BaseClass;
import genericLibraries.IConstantPath;

public class AddCategoryTest extends BaseClass {
	@Test
	public void addCategoryTest() {
		SoftAssert soft = new SoftAssert();
		home.clickCoursesTab();
		home.clickCategoryLink();
		soft.assertTrue(category.getPageHeader().contains("Category"));
		category.clickNewButton();
		jutil.pause(2000);
		soft.assertEquals(addCategory.getPageHeader(), "Add New Category");

		Map<String, String> map = excel.readFromExcel("Sheet1", "Add Category");
		addCategory.setName(map.get("Name"));
		addCategory.clickSave();

		soft.assertTrue(course.getSuccessAlertMessage().contains("Category added successfully"));
		course.deleteCourse(web, map.get("Name"));
		soft.assertTrue(course.getSuccessAlertMessage().contains("Category deleted successfully"));
		if (course.getSuccessAlertMessage().contains("Category deleted successfully"))
			excel.updateTestStatus("Sheet1", "Add Category", "Pass", IConstantPath.EXCEL_PATH);
		else
			excel.updateTestStatus("Sheet1", "Add Category", "Fail", IConstantPath.EXCEL_PATH);

		soft.assertAll();
	}
}
