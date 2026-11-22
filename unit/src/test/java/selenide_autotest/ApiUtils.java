package selenide_autotest;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

public class ApiUtils {

    private static final RequestSpecification basicRQ = new RequestSpecBuilder()
            .setBaseUri("http://localhost:8080")
            .setContentType(ContentType.JSON)
            .build();

    public static int createGood(String name, double price) {
        String requestBody = "{\"name\": \"" + name + "\", \"price\": " + price + "}";
        return given()
                .spec(basicRQ)
                .auth()
                .basic("admin", "secret123")
                .body(requestBody)
                .when()
                .post("/goods/add")
                .then()
                .statusCode(200)
                .extract().path("data.id");
    }

    public static int getGoodIdByName(String name) {
        return given()
                .spec(basicRQ)
                .auth().basic("admin", "secret123")
                .queryParam("page", 0)
                .queryParam("size", 1000)
                .when()
                .get("/goods/list")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getInt("goods.find { it.name == '" + name + "' }.id");
    }

    public static void deleteGood(int id) {
        given()
                .spec(basicRQ)
                .auth().basic("admin", "secret123")
                .when()
                .delete("/goods/" + id)
                .then()
                .statusCode(200);
    }
}
