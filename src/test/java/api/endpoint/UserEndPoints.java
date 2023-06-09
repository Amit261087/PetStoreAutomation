package api.endpoint;

import static io.restassured.RestAssured.given;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints {
	
	public static Response createUser(User payload)
	{
		Response res = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
			
		.when()
			.post(routes.user_post_url);
		
		return res;
			
	}
	
	public static Response getUser(String username)
	{
		Response res = given()
			.pathParam("username", username)
			
		.when()
			.get(routes.user_get_url);
		
		return res;
	}
	
	public static Response updateUser(String username, User payload)
	{
		Response res = given()
			.pathParam("username", username)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
			
		.when()
			.put(routes.user_update_url);
		
		return res;
			
	}
	
	public static Response deleteUser(String username)
	{
		Response res = given()
			.pathParam("username", username)
			
		.when()
			.delete(routes.user_delete_url);
		
		return res;
	}
	
	
	

}
