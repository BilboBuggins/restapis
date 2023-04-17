package stepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class restAPIStepDefinition {

    private RequestSpecification requestSpecification;
    private Response response;

    @Given("I send the URL {string}")
    public void i_send_the_url(String string) {
        requestSpecification=given().log().all().baseUri(string);
    }
    @When("I send the parameters as Key Value pair")
    public void i_send_the_parameters_as_key_value_pair(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>>data=dataTable.asMaps(String.class, String.class);
        for(Map<String,String> pairs:data){
            requestSpecification=requestSpecification.log().all().headers(pairs.get("Key"), pairs.get("Value"));
        }
    }
    @When("I send the headers as Key Value pair")
    public void i_send_the_headers_as_key_value_pair(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>>data=dataTable.asMaps(String.class, String.class);
        for(Map<String, String> pair:data){
            requestSpecification=requestSpecification.log().all().queryParams(pair.get("Key"), pair.get("Value"));
        }
    }
    @When("I send the resources as {string}")
    public void i_send_the_resources_as(String resources) {
        response=requestSpecification.log().all().when().get(resources);
    }
    @Then("I expect a response as {int}")
    public void i_expect_a_response_as(int statusCode) {
        response=response.then().statusCode(statusCode).log().all().extract().response();
    }


}
