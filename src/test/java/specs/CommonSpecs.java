package specs;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.*;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.core.IsNull.notNullValue;

public class CommonSpecs {
    public static RequestSpecification CommonRequestSpec = with()
            .filter(new AllureRestAssured())
            .baseUri("https://reqres.in")
            .log().all()
            .contentType(JSON);

}


