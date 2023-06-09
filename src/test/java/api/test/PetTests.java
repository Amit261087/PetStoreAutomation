package api.test;



import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoint.PetEndPoints;
import api.payload.Pet;
import io.restassured.response.Response;
import org.testng.Assert;

public class PetTests {
	
	Faker faker;
	Pet petpayload;
	
	@BeforeClass
	public void setupData()
	{
		faker = new Faker();
		petpayload = new Pet();
		
		petpayload.setCategory_id(faker.idNumber().hashCode());
		petpayload.setCategory_name(faker.animal().name());
		petpayload.setId(faker.idNumber().hashCode());
		petpayload.setName(faker.animal().name());
		petpayload.setTag_id(faker.idNumber().hashCode());
		petpayload.setTag_name(faker.animal().name());		
	}
	
	@Test (priority=1)
	public void testAddPet()
	{
		Response res = PetEndPoints.addPet(petpayload);
		res.then().log().all();
		
		Assert.assertEquals(res.getStatusCode(), 200);
	}
	
	@Test (priority=2)
	public void testGetPet()
	{
		Response res = PetEndPoints.getPet(this.petpayload.getId());
		res.then().log().all();
		
		Assert.assertEquals(res.statusCode(), 200);		
	}
	
	@Test (priority=3)
	public void testUpdatePet()
	{
		//Update pet payload		
		
		petpayload.setName(faker.dog().name());
		
		Response res = PetEndPoints.updatePet(this.petpayload.getId(), petpayload);
		res.then().log().body();		
		Assert.assertEquals(res.getStatusCode(), 200);
		
		//Checking data after update
		
		Response resAfterUpdate = PetEndPoints.getPet(this.petpayload.getId());
		resAfterUpdate.then().log().all();
		
		Assert.assertEquals(resAfterUpdate.statusCode(), 200);		
	}
	
	@Test (priority=4)
	public void testDeletePet()
	{
		Response res = PetEndPoints.deletePet(this.petpayload.getId());
		
		Assert.assertEquals(res.statusCode(), 200);
	}
}