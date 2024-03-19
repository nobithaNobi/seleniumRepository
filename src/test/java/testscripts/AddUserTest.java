package testscripts;

import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericLibraries.BaseClass;

public class AddUserTest extends BaseClass {
	@Test
	public void addUserTest() {
		SoftAssert soft = new SoftAssert();
		home.clickUsersTab();
		soft.assertTrue(users.getPageHeader().contains("Users"));
		users.clickNewButton();
		soft.assertEquals(addUser.getPageHeader(), "Add New User");
		
		Map<String, String> map = excel.readFromExcel("Sheet1", "Add User");
		addUser.setEmail(map.get("Email"));
		addUser.setPwd(map.get("password"));
		addUser.setFirstName(map.get("FirstName"));
		addUser.setLastName(map.get("lastname"));
		addUser.setAddress(map.get("Address"));
		addUser.setContact(map.get("Contact Info"));
		addUser.uploadPhoto(map.get("photo"));
		addUser.clickSave();
		
		soft.assertTrue(users.getSuccessAlertMessage().contains("Success"));
		soft.assertAll();
	}
}
