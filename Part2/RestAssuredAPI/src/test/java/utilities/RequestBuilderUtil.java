package utilities;

import cons.ApiMethods;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

public class RequestBuilderUtil {

	private RequestSpecBuilder builder = new RequestSpecBuilder();
	private String apiMethod;
	private String url;



	public RequestBuilderUtil(String url, String apiMethod) {

		this.url = url;
		this.apiMethod = apiMethod;
		Map<String,String> header = getAuthenticationHeaders();

		builder.addHeaders(header);






	}

	public static Map<String, String> getAuthenticationHeaders() {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Accept-Encoding", "gzip, deflate");
		headers.put("Content-Type", "application/x-www-form-urlencoded");
		headers.put("Content-Type", " application/json");
		return headers;
	}

	private ResponseOptions<Response> executeAPI(){

		RequestSpecification requestSpecification = builder.build().log().all();
		RequestSpecification request = RestAssured.given().urlEncodingEnabled(false);
		request.spec(requestSpecification);

		if (this.apiMethod.equalsIgnoreCase(ApiMethods.POST)) {
			System.out.println("Request is POST request");
			return request.post(this.url);
		} else if (this.apiMethod.equalsIgnoreCase(ApiMethods.GET)) {
			System.out.println("Request is GET request");
			return request.get(this.url);
		} else if (this.apiMethod.equalsIgnoreCase(ApiMethods.PUT)) {
			System.out.println("Request is PUT request");
			return request.put(this.url);
		} else if (this.apiMethod.equalsIgnoreCase(ApiMethods.DELETE)) {
			System.out.println("Request is DELETE request");
			return request.delete(this.url);
		}

		return null;
	}


	public ResponseOptions<Response> Execute() {
		return executeAPI();
	}


	public ResponseOptions<Response> ExecuteWithQueryParam(Map<String, String> queryParams) {
		builder.addQueryParams(queryParams);
		return executeAPI();
	}




	public ResponseOptions<Response> ExecuteWithBody(String body) {
		builder.setBody(body);
		return executeAPI();
	}





}
