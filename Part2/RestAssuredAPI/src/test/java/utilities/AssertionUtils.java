package utilities;

import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import org.testng.Assert;

import java.util.List;


public class AssertionUtils {



	public static void statusCodeValidation(ResponseOptions<Response> response, String expectedStatusCode, String url) {

			int eStatuscode = Integer.parseInt(expectedStatusCode);
			int aStatuscode = response.getStatusCode();

			Assert.assertEquals(aStatuscode, eStatuscode,
					"Status Code verification failed. Actual:: " + aStatuscode
							+ " Expected:: " + eStatuscode + "for URL "+ url);


		}




	public static void IterateList(List<Object> tagvalue, String ExpectedtagValue) {
		for (int i = 0; i < tagvalue.size(); i++) {
			String ActualValue = tagvalue.get(i).toString();
			Assert.assertTrue(ExpectedtagValue.equals(ActualValue));
			Assert.assertTrue(ActualValue!=null);


		}
	}


}


