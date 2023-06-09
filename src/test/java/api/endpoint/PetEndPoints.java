package api.endpoint;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import api.payload.Pet;


public class PetEndPoints {
	
	public static Response addPet(Pet petpayload)
	{
		Response res = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(petpayload)
			
		.when()
			.post(routes.pet_post_url);
		
		return res;
	}
	
	public static Response getPet(int petId)
	{
		Response res = given()
			.pathParam("petId", petId)
			
		.when()
			.get(routes.pet_get_url);
		
		return res;
	}
	
	public static Response updatePet(int petId, Pet petpayload)
	{
		Response res = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("petId", petId)
			.body(petpayload)
			
		.when()
			.put(routes.pet_update_url);
		
		return res;
	}
	
	public static Response deletePet(int petId)
	{
		Response res = given()
			.pathParam("petId", petId)
			
		.when()
			.delete(routes.pet_delete_url);
		
		return res;
	}

}