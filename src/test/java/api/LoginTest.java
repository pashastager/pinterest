
package api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.containsString;
import static org.pinterest.login.api.UserLoginApiResponse.*;

public class LoginTest {

    @Test
    @DisplayName("Авторизация c невалидным паролем")
    public void loginPasswordIncorrect() {
        String url = "https://ru.pinterest.com/resource/UserSessionResource/create/";
        given()
                .header("accept-language", "ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7")
                .header("content-type", "application/x-www-form-urlencoded")
                .header("cookie", "csrftoken=496f35ac9a56508abc41e8c50d1733dc; _pinterest_sess=TWc9PSYvU3NhVXp6ZU4rQW00QzZMdTZ2UHZ5eXhDQzhMdDZaWXUyWTVKdWljVWxLbjI3WG9VNFdEejlXVlNQSFA0NVJDa2c5b1o4OEk5SVpzZW55dFRZTzd0bzBhOExaSlpXV1BuTzQ4MHNzbStGcz0mMS9IV1RpaE1SVXV1ajV6QUcvRmhETUZJbTU0PQ==; _auth=0; _routing_id=\"bd74c948-023b-4fe3-a331-8f580d26c0ba\"; _b=\"AYMVboQPD7FIwaTc1wr4zjHtxarNBQJf8TpQGbIl1PEPVxD4psVc5ZPQ5sfbEhTx4gI=\"")
                .header("x-csrftoken", "496f35ac9a56508abc41e8c50d1733dc")
                .formParam("source_url", "/")
                .formParam("data", "{\"options\":{\"username_or_email\":\"1212122@gmail.com\",\"password\":\"2345345345345\",\"app_type_from_client\":5,\"visited_pages_before_login\":null,\"recaptchaV3Token\":\"03AFcWeA6YdCkowySZIsee1...\"},\"context\":{}}")
                .when()
                .post(url)
                .then()
                .statusCode(401)
                .body("resource_response.error.message", anyOf(containsString(INCORRECT_PASSWORD_ONE_MESSAGE), containsString(INCORRECT_PASSWORD_TWO_MESSAGE)));
    }

    @Test
    @DisplayName("Авторизация без пароля")
    public void loginWithoutPassword() {
        String url = "https://ru.pinterest.com/resource/UserSessionResource/create/";
        given()
                .header("accept-language", "ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7")
                .header("content-type", "application/x-www-form-urlencoded")
                .header("cookie", "csrftoken=496f35ac9a56508abc41e8c50d1733dc; _pinterest_sess=TWc9PSYvU3NhVXp6ZU4rQW00QzZMdTZ2UHZ5eXhDQzhMdDZaWXUyWTVKdWljVWxLbjI3WG9VNFdEejlXVlNQSFA0NVJDa2c5b1o4OEk5SVpzZW55dFRZTzd0bzBhOExaSlpXV1BuTzQ4MHNzbStGcz0mMS9IV1RpaE1SVXV1ajV6QUcvRmhETUZJbTU0PQ==; _auth=0; _routing_id=\"bd74c948-023b-4fe3-a331-8f580d26c0ba\"; _b=\"AYMVboQPD7FIwaTc1wr4zjHtxarNBQJf8TpQGbIl1PEPVxD4psVc5ZPQ5sfbEhTx4gI=\"")
                .header("x-csrftoken", "496f35ac9a56508abc41e8c50d1733dc")
                .formParam("source_url", "/")
                .formParam("data", "{\"options\":{\"username_or_email\":\"1212122@gmail.com\",\"app_type_from_client\":5,\"visited_pages_before_login\":null,\"recaptchaV3Token\":\"03AFcWeA6YdCkowySZIsee1...\"},\"context\":{}}")
                .when()
                .post(url)
                .then()
                .statusCode(400)
                .body("resource_response.error.message", containsString(INCORRECT_PARAMETERS_MESSAGE))
                .body("resource_response.error.extra_data.message", containsString(MISSING_PASSWORD_MESSAGE));
    }

    @Test
    @DisplayName("Авторизация без email")
    public void loginWithoutEmail() {
        String url = "https://ru.pinterest.com/resource/UserSessionResource/create/";
        given()
                .header("accept-language", "ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7")
                .header("content-type", "application/x-www-form-urlencoded")
                .header("cookie", "csrftoken=496f35ac9a56508abc41e8c50d1733dc; _pinterest_sess=TWc9PSYvU3NhVXp6ZU4rQW00QzZMdTZ2UHZ5eXhDQzhMdDZaWXUyWTVKdWljVWxLbjI3WG9VNFdEejlXVlNQSFA0NVJDa2c5b1o4OEk5SVpzZW55dFRZTzd0bzBhOExaSlpXV1BuTzQ4MHNzbStGcz0mMS9IV1RpaE1SVXV1ajV6QUcvRmhETUZJbTU0PQ==; _auth=0; _routing_id=\"bd74c948-023b-4fe3-a331-8f580d26c0ba\"; _b=\"AYMVboQPD7FIwaTc1wr4zjHtxarNBQJf8TpQGbIl1PEPVxD4psVc5ZPQ5sfbEhTx4gI=\"")
                .header("x-csrftoken", "496f35ac9a56508abc41e8c50d1733dc")
                .formParam("source_url", "/")
                .formParam("data", "{\"options\":{\"password\":\"2345345345345\",\"app_type_from_client\":5,\"visited_pages_before_login\":null,\"recaptchaV3Token\":\"03AFcWeA6YdCkowySZIsee1...\"},\"context\":{}}")
                .when()
                .post(url)
                .then()
                .statusCode(400)
                .body("resource_response.error.message", containsString(INCORRECT_PARAMETERS_MESSAGE))
                .body("resource_response.error.extra_data.message", containsString(MISSING_EMAIL_MESSAGE));
    }
}

