package api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;
import java.util.Random;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Tag;

@Tag("api")
public class GoodsCrudTest {

    private RequestSpecification basicRQ = new RequestSpecBuilder()
            .setBaseUri("http://localhost:8080")
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    private String randomName() {
        return "Test" + new Random().nextInt(100000);
    }

    private int createGood(String name, double price) {
        String requestBody = "{\"name\": \"" + name + "\", \"price\": " + price + "}";

        int id = given()
                .spec(basicRQ)
                .auth()
                .basic("admin", "secret123")
                .body(requestBody)
                .when()
                .post("/goods/add")
                .then()
                .statusCode(200)
                .extract().path("data.id");

        return id;
    }

    private void deleteGood(int id) {
        given()
                .spec(basicRQ)
                .auth()
                .basic("admin", "secret123")
                .when()
                .delete("/goods/" + id)
                .then()
                .statusCode(200);
    }

    @Test
    void addGood_ShouldReturn200() {
        String name = randomName();
        String requestBody = "{\"name\": \"" + name + "\", \"price\": 10.5}";

        Response response = given()
                .spec(basicRQ)
                .auth()
                .basic("admin", "secret123")
                .body(requestBody)
                .when()
                .post("/goods/add")
                .then()
                .log().all()
                .extract().response();

        assertThat(response.statusCode())
                .as("Control status-code")
                .isEqualTo(200);
        assertThat(response.jsonPath().getString("message"))
                .as("Control message")
                .isEqualTo("success");
        assertThat(response.jsonPath().getInt("data.id"))
                .as("Control data.id > 0")
                .isGreaterThan(0);

        deleteGood(response.jsonPath().getInt("data.id"));
    }

    @Test
    void addGoodTwice_ShouldReturn400() {
        String name = randomName();
        int id = createGood(name, 10.5);
        String requestBody = "{\"name\": \"" + name + "\", \"price\": 10.5}";

        int statusCode = given()
                .spec(basicRQ)
                .auth()
                .basic("admin", "secret123")
                .body(requestBody)
                .when()
                .post("/goods/add")
                .then()
                .log().all()
                .extract().statusCode();

        assertThat(statusCode)
                .as("duplicate " + name)
                .isEqualTo(400);

        deleteGood(id);
    }

    @Test
    void addGoodWithEmptyName_ShouldReturn400() {
        String requestBody = "{\"name\": \"\", \"price\": 10.5}";

        int statusCode = given()
                .spec(basicRQ)
                .auth()
                .basic("admin", "secret123")
                .body(requestBody)
                .when()
                .post("/goods/add")
                .then()
                .log().all()
                .extract().statusCode();

        assertThat(statusCode)
                .as("Status-code when name is null")
                .isEqualTo(400);
    }

    @Test
    void getGoodById_ShouldReturn200() {
        String name = randomName();
        int id = createGood(name, 10);

        Response response = given()
                .spec(basicRQ)
                .auth()
                .basic("admin", "secret123")
                .when()
                .get("/goods/" + id)
                .then()
                .log().all()
                .extract().response();

        assertThat(response.statusCode())
                .as("Control status-code")
                .isEqualTo(200);
        assertThat((Object) response.jsonPath().get("id"))
                .as("Search id")
                .isNotNull();
        assertThat((Object) response.jsonPath().get("name"))
                .as("Search name")
                .isNotNull();
        assertThat((Object) response.jsonPath().get("price"))
                .as("Search price")
                .isNotNull();
        assertThat(response.jsonPath().getInt("id"))
                .as("Correct id")
                .isEqualTo(id);
        assertThat(response.jsonPath().getString("name"))
                .as("Correct name")
                .isEqualTo(name);
        assertThat(response.jsonPath().getDouble("price"))
                .as("Correct price")
                .isEqualTo(10.0);

        deleteGood(id);
    }

    @Test
    void getGoodById_ShouldReturn404() {
        int id = createGood(randomName(), 10.5);
        deleteGood(id);

        int statusCode = given()
                .spec(basicRQ)
                .auth()
                .basic("admin", "secret123")
                .when()
                .get("/goods/" + id)
                .then()
                .log().all()
                .extract().statusCode();

        assertThat(statusCode)
                .as("Not found " + id)
                .isEqualTo(404);
    }

    @Test
    void deleteGood_ShouldReturn200() {
        int id = createGood(randomName(), 10.5);

        Response response = given()
                .spec(basicRQ)
                .auth()
                .basic("admin", "secret123")
                .when()
                .delete("/goods/" + id)
                .then()
                .log().all()
                .extract().response();

        assertThat(response.statusCode())
                .as("Good status-code delete")
                .isEqualTo(200);
        assertThat(response.asString())
                .as("Text delete")
                .contains("deleted successfully");
    }

    @Test
    void deleteGood_ShouldReturn404() {
        int id = createGood(randomName(), 10.5);
        deleteGood(id);

        int statusCode = given()
                .spec(basicRQ)
                .auth()
                .basic("admin", "secret123")
                .when()
                .delete("/goods/" + id)
                .then()
                .log().all()
                .extract().statusCode();

        assertThat(statusCode)
                .as("Nor found " + id)
                .isEqualTo(404);
    }

    @Test
    void patchGood_ShouldReturn200() {
        int id = createGood(randomName(), 10);
        String newName = randomName();
        String requestBody = "{\"name\": \"" + newName + "\", \"price\": 20.5}";

        Response response = given()
                .spec(basicRQ)
                .auth()
                .basic("admin", "secret123")
                .body(requestBody)
                .when()
                .patch("/goods/" + id)
                .then()
                .log().all()
                .extract().response();

        assertThat(response.statusCode())
                .as("Good status-code ")
                .isEqualTo(200);
        assertThat(response.jsonPath().getInt("id"))
                .as("Correct id ")
                .isEqualTo(id);
        assertThat(response.jsonPath().getString("name"))
                .as("Correct name ")
                .isEqualTo(newName);
        assertThat(response.jsonPath().getDouble("price"))
                .as("Correct price ")
                .isEqualTo(20.5);

        deleteGood(id);
    }

    @Test
    void patchGood_ShouldReturn404() {
        int id = createGood(randomName(), 10.5);
        deleteGood(id);
        String requestBody = "{\"name\": \"" + randomName() + "\", \"price\": 20.5}";

        int statusCode = given()
                .spec(basicRQ)
                .auth().basic("admin", "secret123")
                .body(requestBody)
                .when()
                .patch("/goods/" + id)
                .then()
                .log().all()
                .extract().statusCode();

        assertThat(statusCode)
                .as("Not found " + id)
                .isEqualTo(404);
    }

    @Test
    void patchGoodWithWrongPrice_ShouldReturn400() {
        String name = randomName();
        int id = createGood(name, 10.5);
        String requestBody = "{\"name\": \"" + name + "\", \"price\": \"abc\"}";

        int statusCode = given()
                .spec(basicRQ)
                .auth()
                .basic("admin", "secret123")
                .body(requestBody)
                .when()
                .patch("/goods/" + id)
                .then()
                .log().all()
                .extract().statusCode();

        assertThat(statusCode)
                .as("Bad request")
                .isEqualTo(400);

        double priceAfter = given()
                .spec(basicRQ)
                .auth().basic("admin", "secret123")
                .when()
                .get("/goods/" + id)
                .then()
                .extract().jsonPath().getDouble("price");

        assertThat(priceAfter)
                .as("Price not change ")
                .isEqualTo(10.5);

        deleteGood(id);
    }

    @Test
    void getGoodsList_ShouldContainCreatedGoods() {
        int firstId = createGood(randomName(), 10.5);
        int secondId = createGood(randomName(), 20.5);

        Response response = given()
                .spec(basicRQ)
                .auth()
                .basic("admin", "secret123")
                .queryParam("page", 0)
                .queryParam("size", 100)
                .when()
                .get("/goods/list")
                .then()
                .log().all()
                .extract().response();

        assertThat(response.statusCode())
                .as("List of goods ")
                .isEqualTo(200);
        assertThat(response.jsonPath().getList("goods.id", Integer.class))
                .as("Two create goods")
                .contains(firstId, secondId);

        deleteGood(firstId);
        deleteGood(secondId);
    }
}