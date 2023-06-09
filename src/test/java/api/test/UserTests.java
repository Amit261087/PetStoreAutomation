package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoint.UserEndPoints;
import api.payload.User;
import ch.qos.logback.classic.Logger;
import io.restassured.response.Response;

public class UserTests {
	
	Faker faker;
	User userpayload;
	
	@BeforeClass
	public void setupData()
	{
		
		faker = new Faker();
		userpayload = new User();
		userpayload.setId(faker.idNumber().hashCode());
		userpayload.setUsername(faker.name().username());
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setPassword(faker.internet().password(5, 10));
		userpayload.setEmail(faker.internet().safeEmailAddress());
		userpayload.setPhone(faker.phoneNumber().cellPhone());		
	}
	
	@Test (priority=1)
	public void testCreateUser()
	{
		
		
		Response res = UserEndPoints.createUser(userpayload);
		res.then().log().all();
		
		Assert.assertEquals(res.getStatusCode(), 200);
	}
	
	@Test (priority=2)
	public void testGetUser()
	{
		Response res = UserEndPoints.getUser(this.userpayload.getUsername());
		res.then().log().all();
		
		Assert.assertEquals(res.statusCode(), 200);		
	}
	
	@Test (priority=3)
	public void testUpdate()
	{
		//Update user payload
		
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		
		Response res = UserEndPoints.updateUser(this.userpayload.getUsername(), userpayload);
		res.then().log().body().statusCode(200);
		res.then().log().body();		
		Assert.assertEquals(res.getStatusCode(), 200);
		
		//Checking data after update
		
		Response resAfterUpdate = UserEndPoints.getUser(this.userpayload.getUsername());
		resAfterUpdate.then().log().all();
		
		Assert.assertEquals(resAfterUpdate.statusCode(), 200);		
	}
	
	@Test (priority=4)
	public void testDeleteUser()
	{
		Response res = UserEndPoints.deleteUser(this.userpayload.getUsername());
		
		Assert.assertEquals(res.statusCode(), 200);
	}
}
