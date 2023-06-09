package api.test;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import api.endpoint.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTest {
	
	@Test (priority=1, dataProvider="Data", dataProviderClass=DataProviders.class)
	public void testPOstUser(String UserID, String UserName, String FirstName, String LastName, String Email, String Password, String Phone)
	{
		User userpayload = new User();
		
		userpayload.setId(Integer.parseInt(UserID));
		userpayload.setUsername(UserName);
		userpayload.setFirstName(FirstName);
		userpayload.setLastName(LastName);
		userpayload.setPassword(Password);
		userpayload.setEmail(Email);
		userpayload.setPhone(Phone);
		
	}
	
	@Test (priority=2, dataProvider="UserNames", dataProviderClass=DataProviders.class)
	public void testDeleteUser(String UserName)
	{
		Response res = UserEndPoints.deleteUser(UserName);
		
		AssertJUnit.assertEquals(res.statusCode(), 404);
		
	}
	

}
