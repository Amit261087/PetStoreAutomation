package api.endpoint;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPointsUsingProp {
	
	static ResourceBundle getURL()
	{
		ResourceBundle routes = ResourceBundle.getBundle("routes");
		return routes;
	}
	
	public static Response createUser(User payload)
	{
		String user_post_url = getURL().getString("user_post_url");
		
		Response res = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
			
		.when()
			.post(user_post_url);
		
		return res;
			
	}
	
	public static Response getUser(String username)
	{
		String user_get_url = getURL().getString("user_get_url");
		
		Response res = given()
			.pathParam("username", username)
			
		.when()
			.get(user_get_url);
		
		return res;
	}
	
	public static Response updateUser(String username, User payload)
	{
		String user_update_url = getURL().getString("user_update_url");
		
		Response res = given()
			.pathParam("username", username)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
			
		.when()
			.put(user_update_url);
		
		return res;
			
	}
	
	public static Response deleteUser(String username)
	{
		String user_delete_url = getURL().getString("user_delete_url");
		
		Response res = given()
			.pathParam("username", username)
			
		.when()
			.delete(user_delete_url);
		
		return res;
	}
	
	
	

}
