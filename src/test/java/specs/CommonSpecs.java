package specs;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.ALL;
import static io.restassured.http.ContentType.JSON;

public class CommonSpecs {
    public static RequestSpecification CommonRequestSpec = with()
        .filter(new AllureRestAssured())
        .baseUri("https://reqres.in")
        .log().all()
        .contentType(JSON);

    public static ResponseSpecification CommonResponseSpec = new ResponseSpecBuilder()
            .log(ALL)
            .build();

}


