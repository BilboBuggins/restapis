package stepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class restAPIStepDefinition {

    private RequestSpecification requestSpecification;
    private Response response;

    /**
     * This method take a String as the URL and constructs a Request Specification
     * @param string
     */
    @Given("User send the URL {string}")
    public void user_send_the_url(String string) {
        requestSpecification=given().log().all().baseUri(string);
    }

    /**
     * This method take data in a data table format with key value pair and then converts the data table into
     * a List of Sting map. It later iterates using a for loop and adds the data as headers to the existing
     * Request specification.
     * @param dataTable
     */
    @When("User send the parameters as Key Value pair")
    public void user_send_the_parameters_as_key_value_pair(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>>data=dataTable.asMaps(String.class, String.class);
        for(Map<String,String> pairs:data){
            requestSpecification=requestSpecification.queryParams(pairs.get("Key"), pairs.get("Value"));
        }
    }

    /**
     * This method take data in a data table format with key value pair and then converts the data table into
     * a List of Sting map. It later iterates using a for loop and adds the data as query parameters to the existing
     * Request specification.
     * @param dataTable
     */
    @When("User send the headers as Key Value pair")
    public void user_send_the_headers_as_key_value_pair(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>>data=dataTable.asMaps(String.class, String.class);
        for(Map<String, String> pair:data){
            requestSpecification=requestSpecification.headers(pair.get("Key"), pair.get("Value"));
        }
    }

    @When("User adds the {string} body to the request")
    public void user_adds_the_body_to_the_request(String body) throws IOException {
        requestSpecification=requestSpecification.body(new String(Files.readAllBytes(Paths.get("src/main/java/requestBody/"+body))));
    }

    /**
     * This method takes Resources and the call as String Parameters and makes the corresponding call along
     * with the resources and adds it to the existing Request Specification and returns a response
     * @param resources
     * @param call
     */
    @When("User send the resources as {string} with {string} call")
    public void user_send_the_resources_as(String resources, String call) {
        if(call.equalsIgnoreCase("get")){
            response=requestSpecification.log().all().when().get(resources);
        }
        else if(call.equalsIgnoreCase("post")){
            response=requestSpecification.log().all().when().post(resources);
        }
        else if(call.equalsIgnoreCase("put")){
            response=requestSpecification.log().all().when().put(resources);
        }
        else if(call.equalsIgnoreCase("patch")){
            response=requestSpecification.log().all().when().patch(resources);
        }
        else if(call.equalsIgnoreCase("delete")){
            response=requestSpecification.log().all().when().delete(resources);
        }

    }

    /**
     * This method take the expected status code as an int parameter and checks it against the code of the
     * response.
     * @param statusCode
     */
    @Then("User expect a response as {int}")
    public void user_expect_a_response_as(int statusCode) {
        response=response.then().statusCode(statusCode).log().all().extract().response();
    }


}
