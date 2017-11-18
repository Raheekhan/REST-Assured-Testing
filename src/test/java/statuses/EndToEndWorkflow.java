package statuses;

import common.RestUtilities;
import constants.EndPoints;
import constants.Path;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class EndToEndWorkflow extends RestUtilities {

    RequestSpecification reqSpeq;
    ResponseSpecification resSpeq;
    String tweetID = "";

    @BeforeClass
    public void setUp() {
        reqSpeq = getRequestSpecification();
        reqSpeq.basePath(Path.STATUSES);
        resSpeq = getResponseSpecification();
    }

    @Test
    public void postTweet() {
        Response res =
        given().spec(createQueryParam(reqSpeq, "status", "Tweet From IntelliJ"))
                .when().post(EndPoints.STATUSES_TWEET_POST)
                .then().spec(resSpeq)
                .extract().response();

        JsonPath jsPath = getJsonPath(res);
        tweetID = jsPath.get("id_str");
    }

    @Test(dependsOnMethods = "postTweet")
    public void readTweet() {
        setEndPoint(EndPoints.STATUSES_TWEET_READ_SINGLE);
        Response res =
                getResponse(createQueryParam(reqSpeq, "id", tweetID), "GET");
        String text = res.path("text");
        System.out.println("The tweet text is: " + text);
    }

    @Test(dependsOnMethods = "readTweet")
    public void deleteTweet() {
        given()
                .spec(createPathParam(reqSpeq, "id", tweetID))
                .when()
                .post(EndPoints.STATUSES_TWEET_DESTROY)
                .then()
                .spec(resSpeq);
    }
}
