package statuses;

import common.RestUtilities;
import constants.EndPoints;
import constants.Path;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.testng.AssertJUnit.assertTrue;

public class UserTimelineTest extends RestUtilities {

    RequestSpecification reqSpec;
    ResponseSpecification resSpec;

    @BeforeClass
    public void setUp() {
        reqSpec = getRequestSpecification();
        reqSpec.queryParam("user_id", "RaheeIbrahimk");
        reqSpec.basePath(Path.STATUSES);

        resSpec = getResponseSpecification();
    }

    @Test
    public void ReadTweetsOne() {
        given()
                .spec(createQueryParam(reqSpec, "count", "1"))
                .when().get(EndPoints.STATUSES_USER_TIMELINE)
                .then()
                //.log().all()
                .spec(resSpec)
                .body("user.screen_name", hasItem("RaheeIbrahimk"));
    }

    @Test
    public void ReadTweetsTwo() {
        setEndPoint(EndPoints.STATUSES_USER_TIMELINE);
        Response res = RestUtilities.getResponse(
                (createQueryParam(reqSpec, "count", "1")), "GET");
        ArrayList<String> screenNameList = res.path("user.screen_name");
        System.out.println(screenNameList);
        assertTrue(screenNameList.contains("RaheeIbrahimk"));
    }
}
