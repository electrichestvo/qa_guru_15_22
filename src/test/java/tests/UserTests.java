package tests;

import models.lombock.BodyLombockModel;
import models.lombock.BodyResponseLombokModel;
import models.pojo.BodyPojoModel;
import models.pojo.BodyResponsePojoModel;
import org.junit.jupiter.api.Test;

import static specs.Spec.requestSpec;
import static specs.Spec.responseSpec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UserTests {


    @Test
    void createUserTest() {
        var user = new BodyPojoModel ();
        user.setName ("morpheus");
        user.setJob ("leader");

        BodyResponsePojoModel response = given ()
                .spec (requestSpec)
                .body (user)
                .post ("/api/users")
                .then ()
                .log ().all ()
                .statusCode (201)
                .extract ()
                .as (BodyResponsePojoModel.class);

        assertThat (response.getName (), equalTo ("morpheus"));
        assertThat (response.getJob (), equalTo ("leader"));
        assertThat (response.getCreatedAt (), notNullValue ());
        assertThat (response.getId (), notNullValue ());
    }

    @Test
    void userLoginTest() {
        BodyLombockModel user = new BodyLombockModel ();
        user.setEmail ("eve.holt@reqres.in");
        user.setPassword ("pistol");

        given ()
                .spec (requestSpec)
                .body (user)
                .post ("/api/login")
                .then ()
                .spec (responseSpec)
                .body ("token", notNullValue ());
    }

    @Test
    void userRegisterTest() {
        BodyLombockModel user = new BodyLombockModel ();
        user.setEmail ("eve.holt@reqres.in");
        user.setPassword ("pistol");

        BodyResponseLombokModel response = given ()
                .spec (requestSpec)
                .body (user)
                .post ("/api/register")
                .then ()
                .spec (responseSpec)
                .extract ()
                .as (BodyResponseLombokModel.class);

        assertThat (response.getId (), notNullValue ());
        assertThat (response.getToken (), notNullValue ());
    }

    @Test
    void changeUserNameTest() {
        BodyPojoModel user = new BodyPojoModel ();
        user.setName ("morpheus");
        user.setJob ("leader");

        given ()
                .spec (requestSpec)
                .body (user)
                .patch ("/api/user/2")
                .then ()
                .spec (responseSpec)
                .body ("name", is ("morpheus"))
                .body ("job", is ("leader"))
                .body ("updatedAt", notNullValue ());
    }


    @Test
    void deleteUserTest() {

        given ()
                .spec (requestSpec)
                .delete ("/api/users/2")
                .then ()
                .statusCode (204);

    }
}