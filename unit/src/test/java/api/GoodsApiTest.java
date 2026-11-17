package api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.empty;
import io.restassured.http.ContentType;
import java.util.Random;
import static org.hamcrest.Matchers.hasItem;
import io.restassured.response.Response;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class GoodsApiTest {

    private RequestSpecification basicRQ = new RequestSpecBuilder()
            .setBaseUri("http://localhost:8080")
            .log(LogDetail.ALL)
            .build();


@Test
void goodsListGivenWhenThenTest() {
    given()
            .baseUri("http://localhost:8080")
            .queryParam("page", 0)
            .queryParam("size", 10)
            .auth()
            .basic("admin", "secret123")
            .log().all()
            .when()
            .get("/goods/list")
            .then()
            .log().all()
            .statusCode(200)
            .body("goods", empty());
    }

    @Test
    void goodsListWithoutAuthTest() {
        given()
                .spec(basicRQ)
                .queryParam("page", 1)
                .queryParam("size", 1)
                .when()
                .get("/goods/list")
                .then()
                .log().all()
                .statusCode(401);
    }

    @Test
    void goodsListRequestSpecificationTest() {
        given()
                .spec(basicRQ)
                .queryParam("page", 1)
                .queryParam("size", 1)
                .auth()
                .basic("admin", "secret123")
                .when()
                .get("/goods/list")
                .then()
                .log().all()
                .statusCode(200)
                .body("goods", empty());
    }

    @Test
    void createGoodAndCheckListRestAssuredTest() {
        String name = "Test" + new Random().nextInt(100000);
        double price = 10.5;
        String requestBody = "{\"name\": \"" + name + "\", \"price\": " + price + "}";

        int id = given()
                .spec(basicRQ)
                .auth()
                .basic("admin", "secret123")
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/goods/add")
                .then()
                .log().all()
                .statusCode(200)
                .extract().path("data.id");

        given()
                .spec(basicRQ)
                .auth().basic("admin", "secret123")
                .queryParam("page", 0)
                .queryParam("size", 100)
                .when()
                .get("/goods/list")
                .then()
                .log().all()
                .statusCode(200)
                .body("goods.id", hasItem(id))
                .body("goods.name", hasItem(name));

        given()
                .spec(basicRQ)
                .auth().basic("admin", "secret123")
                .when()
                .delete("/goods/" + id)
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    void createGoodAndCheckListAssertJTest() {
        String name = "Test" + new Random().nextInt(100000);
        double price = 10.5;
        String requestBody = "{\"name\": \"" + name + "\", \"price\": " + price + "}";

        int id = given()
                .spec(basicRQ)
                .auth()
                .basic("admin", "secret123")
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/goods/add")
                .then()
                .log().all()
                .statusCode(200)
                .extract().path("data.id");

        Response response = given()
                .spec(basicRQ)
                .auth().basic("admin", "secret123")
                .queryParam("page", 0)
                .queryParam("size", 100)
                .when()
                .get("/goods/list")
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();

        List<Integer> ids = response.jsonPath().getList("goods.id", Integer.class);
        List<String> names = response.jsonPath().getList("goods.name", String.class);

        assertThat(ids)
                .as("The list must contain created item. " + id)
                .contains(id);
        assertThat(names)
                .as("This list contains " + name)
                .contains(name);

        int index = ids.indexOf(id);
        double actualPrice = response.jsonPath().getDouble("goods[" + index + "].price");

        assertThat(actualPrice)
                .as("Prise must be " + price)
                .isEqualTo(price);


        given()
                .spec(basicRQ)
                .auth().basic("admin", "secret123")
                .when()
                .delete("/goods/" + id)
                .then()
                .log().all()
                .statusCode(200);
    }
}
