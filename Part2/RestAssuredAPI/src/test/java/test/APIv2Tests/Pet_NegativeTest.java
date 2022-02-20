package test.APIv2Tests;

import cons.ApiMethods;
import endpoints.APIv2Endpoints;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.AssertionUtils;
import utilities.DataUtils;
import utilities.RequestBuilderUtil;

import java.util.HashMap;
import java.util.Map;


public class Pet_NegativeTest {


    @Test(description = "To verify pet with non existing Id", priority = 0)
    public void getPetsById()
    {


        //Form the url
        String url = APIv2Endpoints.BaseUrl + APIv2Endpoints.PET +"/"+ DataUtils.NewPetId;



        //Make the RestAssured call
        RequestBuilderUtil request = new RequestBuilderUtil(url, ApiMethods.GET);
        ResponseOptions<Response> response = request.Execute();

        //Status Code Assertion
        AssertionUtils.statusCodeValidation(response, "404", url);

        Assert.assertTrue(response.body().asString().equals("Pet not found"),"Pet found");

    }

    @Test(description = "To create a new pet with invalid body", priority = 1)
    public void createPet()
    {

        //Form the url
        String url = APIv2Endpoints.BaseUrl + APIv2Endpoints.PET;

        String requestBody = "" +
                "  \"id\": " +",\n" +
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
                "  \"status\": \"" +DataUtils.Status+"\"}";




        //Make the RestAssured call
        RequestBuilderUtil request = new RequestBuilderUtil(url, ApiMethods.POST);
        ResponseOptions<Response> response = request.ExecuteWithBody(requestBody);

        //Status Code Assertion
        AssertionUtils.statusCodeValidation(response, "400", url);
        Assert.assertTrue(response.getStatusLine().equals("HTTP/1.1 400 Bad Request"));

        Assert.assertTrue(response.body().asString().equals("{\"code\":400,\"message\":\"Input error: unable to convert input to io.swagger.petstore.model.Pet\"}"));
    }

    @Test(description = "To verify a new pet with incorrect endpoint", priority = 2)
    public void getPetsByTags()
    {

        //Form the url
        String url = APIv2Endpoints.BaseUrl +  "/pet/findByTag" ;

        //Adding querry Param
        Map<String, String> querryParam = new HashMap<String, String>();
        querryParam.put("tags",DataUtils.TagName);


        //Make the RestAssured call
        RequestBuilderUtil request = new RequestBuilderUtil(url, ApiMethods.GET);
        ResponseOptions<Response> response = request.ExecuteWithQueryParam(querryParam);

        //Status Code Assertion
        AssertionUtils.statusCodeValidation(response, "400", url);

    }

    @Test(description = "To verify pets by invalid status", priority = 2)
    public void getPetsByStatus()
    {

        String PetStatus = "ready";

        //Form the url
        String url = APIv2Endpoints.BaseUrl + APIv2Endpoints.findByStatus;

        //Adding querry Param
        Map<String, String> querryParam = new HashMap<String, String>();
        querryParam.put("status", PetStatus);


        //Make the RestAssured call
        RequestBuilderUtil request = new RequestBuilderUtil(url, ApiMethods.GET);
        ResponseOptions<Response> response = request.ExecuteWithQueryParam(querryParam);

        //Status Code Assertion
        AssertionUtils.statusCodeValidation(response, "400", url);

        Assert.assertTrue(response.getStatusLine().equals("HTTP/1.1 400 Bad Request"));

        Assert.assertTrue(response.body().asString().equals("{\"code\":400,\"message\":\"Input error: query parameter `status value `ready` is not in the allowable values `[available, pending, sold]`\"}"));
    }


    @Test(description = "To create a new pet with invalid body", priority = 3)
    public void updatePet()
    {



        //Form the url
        String url = APIv2Endpoints.BaseUrl + APIv2Endpoints.PET;

        String requestBody = "  \"category\": {\n" +
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
        AssertionUtils.statusCodeValidation(response, "400", url);


    }



}
