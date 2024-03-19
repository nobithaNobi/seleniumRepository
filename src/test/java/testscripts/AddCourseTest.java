package testscripts;

import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericLibraries.BaseClass;
import genericLibraries.IConstantPath;

public class AddCourseTest extends BaseClass {
	@Test
	public void addCourseTest()  {
		SoftAssert soft = new SoftAssert();
		home.clickCoursesTab();
		home.clickCourseListLink();
		soft.assertTrue(course.getPageHeader().contains("Course List"));
		course.clickNewButton();
		jutil.pause(2000);
		soft.assertEquals(addCourse.getPageHeader(), "Add New Course");

		Map<String, String> map = excel.readFromExcel("Sheet1", "Add Course");
		addCourse.setName(map.get("Name"));
		addCourse.selectCategory(web, map.get("Category"));
		addCourse.setPrice(map.get("price"));
		addCourse.uploadPhoto(map.get("photo"));
		addCourse.addDescription(web, map.get("Description"));
		addCourse.clickSave();

		soft.assertTrue(course.getSuccessAlertMessage().contains("Course added successfully"));
		course.deleteCourse(web, map.get("Name"));
		soft.assertTrue(course.getSuccessAlertMessage().contains("Product deleted successfully"));
		if(course.getSuccessAlertMessage().contains("Product deleted successfully")) 
			excel.updateTestStatus("Sheet1", "Add Course", "Pass", IConstantPath.EXCEL_PATH);
		else
			excel.updateTestStatus("Sheet1", "Add Course", "Fail", IConstantPath.EXCEL_PATH);

		soft.assertAll();
	}
}
