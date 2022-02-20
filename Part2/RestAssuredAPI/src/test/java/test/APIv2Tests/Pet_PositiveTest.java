package test.APIv2Tests;


import cons.ApiMethods;
import endpoints.APIv2Endpoints;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.AssertionUtils;
import utilities.DataUtils;
import utilities.RequestBuilderUtil;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class Pet_PositiveTest {
	SoftAssert softAssert = new SoftAssert();



	@Test(description = "To verify a new pet", priority = 1)
	public void getPetById()  {


		//Form the url
		String url = APIv2Endpoints.BaseUrl + APIv2Endpoints.PET +"/"+DataUtils.NewPetId;



		//Make the RestAssured call
		RequestBuilderUtil request = new RequestBuilderUtil(url, ApiMethods.GET);
		ResponseOptions<Response> response = request.Execute();

		//Status Code Assertion
		AssertionUtils.statusCodeValidation(response, "200", url);

		//Verify Response
		JsonPath jsonPath = new JsonPath(response.getBody().asString());


		//Verify PetId
		Integer PetId = jsonPath.get("id");
		softAssert.assertTrue(PetId.equals(DataUtils.NewPetId),"Pet Id not found");

		//Verify Petname
		String Petname = jsonPath.get("name");
		softAssert.assertTrue(Petname.equals(DataUtils.NewPetName),"Pet name not found");


		//Verify Pet_Status
		String Pet_Status = jsonPath.get("status");
		softAssert.assertTrue(Pet_Status.equals(DataUtils.Status),"status not found");

		//Verify Pet_CategoryName
		String Pet_CategoryName = jsonPath.get("category.name");
		softAssert.assertTrue(Pet_CategoryName.equals(DataUtils.CategoryName),"category name not found");

		//Verify Pet_CategoryId
		Integer Pet_CategoryId = jsonPath.get("category.id");
		softAssert.assertTrue(Pet_CategoryId.equals(DataUtils.CategoryId),"category Id not found");

		//Verify Pet_TagId
		String Pet_TagId = Collections.singletonList(jsonPath.getList("tags.id").get(0)).toString();
		softAssert.assertTrue(Pet_TagId.equals(DataUtils.TagId),"tags Id not found");

		//Verify Pet_TagName
		String Pet_TagName = Collections.singletonList(jsonPath.getList("tags.name").get(0)).toString();
		softAssert.assertTrue(Pet_TagName.equals(DataUtils.TagName),"tags name not found");




	}

	@Test(description = "To create a new pet", priority = 0)
	public void createPet()  {


		//Form the url
		String url = APIv2Endpoints.BaseUrl + APIv2Endpoints.PET;

		String requestBody = "{\n" +
				"  \"id\": "+ DataUtils.NewPetId +",\n" +
				"  \"name\": \""+DataUtils.NewPetName+"\",\n" +
				"  \"category\": {\n" +
				"    \"id\": "+DataUtils.CategoryId+",\n" +
				"    \"name\": \""+DataUtils.CategoryName+"\"\n" +
				"  },\n" +
				"  \"photoUrls\": [\n" +
				"    \"string\"\n" +
				"  ],\n" +
				"  \"tags\": [\n" +
				"    {\n" +
				"    \"id\": " +DataUtils.TagId+",\n" +
				"    \"name\": \""+DataUtils.TagName+"\"\n" +
				"    }\n" +
				"  ],\n" +
				"  \"status\": \"" + DataUtils.Status+"\"}";




		//Make the RestAssured call
		RequestBuilderUtil request = new RequestBuilderUtil(url, ApiMethods.POST);
		ResponseOptions<Response> response = request.ExecuteWithBody(requestBody);

		//Status Code Assertion
		AssertionUtils.statusCodeValidation(response, "200", url);

		//Verify Response
		JsonPath jsonPath = new JsonPath(response.getBody().asString());


		//Verify PetId
		Integer PetId = jsonPath.get("id");
		softAssert.assertTrue(PetId.equals(DataUtils.NewPetId),"Pet Id not found");

		//Verify Petname
		String Petname = jsonPath.get("name");
		softAssert.assertTrue(Petname.equals(DataUtils.NewPetName),"Pet name not found");


		//Verify Pet_Status
		String Pet_Status = jsonPath.get("status");
		softAssert.assertTrue(Pet_Status.equals(DataUtils.Status),"status not found");

		//Verify Pet_CategoryName
		String Pet_CategoryName = jsonPath.get("category.name");
		softAssert.assertTrue(Pet_CategoryName.equals(DataUtils.CategoryName),"category name not found");

		//Verify Pet_CategoryId
		Integer Pet_CategoryId = jsonPath.get("category.id");
		softAssert.assertTrue(Pet_CategoryId.equals(DataUtils.CategoryId),"category Id not found");

		//Verify Pet_TagId
		String Pet_TagId = Collections.singletonList(jsonPath.getList("tags.id").get(0)).toString();
		softAssert.assertTrue(Pet_TagId.equals(DataUtils.TagId),"tags Id not found");

		//Verify Pet_TagName
		String Pet_TagName = Collections.singletonList(jsonPath.getList("tags.name").get(0)).toString();
		softAssert.assertTrue(Pet_TagName.equals(DataUtils.TagName),"tags name not found");




	}



	@Test(description = "To find pets by Tags", priority = 2)
	public void getPetsByTags() {


		//Form the url
		String url = APIv2Endpoints.BaseUrl + APIv2Endpoints.findByTags;

		//Adding querry Param
		Map<String, String> querryParam = new HashMap<String, String>();
		querryParam.put("tags",DataUtils.TagName);


		//Make the RestAssured call
		RequestBuilderUtil request = new RequestBuilderUtil(url, ApiMethods.GET);
		ResponseOptions<Response> response = request.ExecuteWithQueryParam(querryParam);

		//Status Code Assertion
		AssertionUtils.statusCodeValidation(response, "200", url);

	}
	@Test(description = "To verify pets by status", priority = 3)
	public void getPetsByStatus(){

		String PetStatus = "available";

		//Form the url
		String url = APIv2Endpoints.BaseUrl + APIv2Endpoints.findByStatus;

		//Adding querry Param
		Map<String, String> querryParam = new HashMap<String, String>();
		querryParam.put("status", PetStatus);


		//Make the RestAssured call
		RequestBuilderUtil request = new RequestBuilderUtil(url, ApiMethods.GET);
		ResponseOptions<Response> response = request.ExecuteWithQueryParam(querryParam);

		//Status Code Assertion
		AssertionUtils.statusCodeValidation(response, "200", url);

		JsonPath jsonPath = new JsonPath(response.getBody().prettyPrint());

		//Verify Pet_Status
		List checkstatus = jsonPath.get("status");

		AssertionUtils.IterateList(checkstatus,PetStatus);

	}



	@org.testng.annotations.Test(groups = {"APIv3"})
	public void updatePet()  {


		//Form the url
		String url = APIv2Endpoints.BaseUrl + APIv2Endpoints.PET;

		String requestBody = "{\n" +
				"  \"id\": "+ DataUtils.NewPetId +",\n" +
				"  \"name\": \""+DataUtils.updatePetName+"\",\n" +
				"  \"category\": {\n" +
				"    \"id\": "+DataUtils.CategoryId+",\n" +
				"    \"name\": \""+DataUtils.CategoryName+"\"\n" +
				"  },\n" +
				"  \"photoUrls\": [\n" +
				"    \"string\"\n" +
				"  ],\n" +
				"  \"tags\": [\n" +
				"    {\n" +
				"    \"id\": " +DataUtils.TagId+",\n" +
				"    \"name\": \""+DataUtils.TagName+"\"\n" +
				"    }\n" +
				"  ],\n" +
				"  \"status\": \"" +DataUtils.Status+"\"}";




		//Make the RestAssured call
		RequestBuilderUtil request = new RequestBuilderUtil(url, ApiMethods.POST);
		ResponseOptions<Response> response = request.ExecuteWithBody(requestBody);

		//Status Code Assertion
		AssertionUtils.statusCodeValidation(response, "200", url);

		//Verify Response
		JsonPath jsonPath = new JsonPath(response.getBody().asString());


		//Verify PetId
		Integer PetId = jsonPath.get("id");
		softAssert.assertTrue(PetId.equals(DataUtils.NewPetId),"Pet Id not found");

		//Verify Petname
		String Petname = jsonPath.get("name");
		softAssert.assertTrue(Petname.equals(DataUtils.updatePetName),"Pet name not found");


		//Verify Pet_Status
		String Pet_Status = jsonPath.get("status");
		softAssert.assertTrue(Pet_Status.equals(DataUtils.Status),"status not found");

		//Verify Pet_CategoryName
		String Pet_CategoryName = jsonPath.get("category.name");
		softAssert.assertTrue(Pet_CategoryName.equals(DataUtils.CategoryName),"category name not found");

		//Verify Pet_CategoryId
		Integer Pet_CategoryId = jsonPath.get("category.id");
		softAssert.assertTrue(Pet_CategoryId.equals(DataUtils.CategoryId),"category Id not found");

		//Verify Pet_TagId
		String Pet_TagId = Collections.singletonList(jsonPath.getList("tags.id").get(0)).toString();
		softAssert.assertTrue(Pet_TagId.equals(DataUtils.TagId),"tags Id not found");

		//Verify Pet_TagName
		String Pet_TagName = Collections.singletonList(jsonPath.getList("tags.name").get(0)).toString();
		softAssert.assertTrue(Pet_TagName.equals(DataUtils.TagName),"tags name not found");




	}



	@Test(description = "To delete a pet", priority = 5)
	public void deletePetsById()

	{

		//Form the url
		String url = APIv2Endpoints.BaseUrl + APIv2Endpoints.PET +"/" +DataUtils.NewPetId;


		//Make the RestAssured call
		RequestBuilderUtil request = new RequestBuilderUtil(url, ApiMethods.DELETE);
		ResponseOptions<Response> response = request.Execute();

		//Status Code Assertion
		AssertionUtils.statusCodeValidation(response, "200", url);


		Assert.assertTrue(response.body().asString().equals("Pet deleted"),"Pet not deleted");
	}



}