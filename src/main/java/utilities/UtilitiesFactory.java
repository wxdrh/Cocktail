package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class UtilitiesFactory {

    Properties prop;
    HashMap<String, String> header = new HashMap<>();

    public static ExtentReports extent;
    public static ExtentTest testLog;
    private final static String envPropFile = "environment.properties";
    private final static String apiPropFile = "api.properties";
    private final static String searchByNameJson = "searchByName.json";
    private final static String searchByIngredientJson = "searchByIngredient.json";

    public static String reportLocation;
    public static String searchEndPoint;
    public static String invalidEndPoint;
    public static String baseURIPath;
    public static String queryParameterSearchIngredient;
    public static String queryParameterSearchCocktail;
    public static String searchByNameJsonText;
    public static String searchByIngredientJsonText;

    @BeforeTest
    public void setup() {
        RestAssured.baseURI = baseURIPath;
    }

    static {
        try {
            reportLocation = new FileLoaderFactory().getPropertyFile(envPropFile).getProperty("extent.report.folder");
            searchEndPoint = new FileLoaderFactory().getPropertyFile(apiPropFile).getProperty("end.point.search");
            invalidEndPoint = new FileLoaderFactory().getPropertyFile(apiPropFile).getProperty("invalid.end.point");
            baseURIPath = new FileLoaderFactory().getPropertyFile(apiPropFile).getProperty("base.uri");
            queryParameterSearchIngredient = new FileLoaderFactory().getPropertyFile(apiPropFile).getProperty("query.parameter.ingredient");
            queryParameterSearchCocktail = new FileLoaderFactory().getPropertyFile(apiPropFile).getProperty("query.parameter.search");
            searchByNameJsonText = new FileLoaderFactory().readFile(searchByNameJson);
            searchByIngredientJsonText = new FileLoaderFactory().readFile(searchByIngredientJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * get specific property value from property file
     *
     * @param property String
     * @return property
     */
    public String getProperty(String property) {
        return prop.getProperty(property);
    }

    /**
     * Set header value
     *
     * @param key   String
     * @param value String
     */
    public void setHeader(String key, String value) {
        header.put(key, value);
    }

    /**
     * return hashmap
     *
     * @return header
     */
    public HashMap<String, String> getHeader() {
        return header;
    }

    /**
     * @param queryParam1      queryParam1
     * @param queryParam2      queryParam2
     * @param get              get
     * @param expectedResponse expectedResponse
     * @param index            index
     * @param pojoClass        pojoClass
     * @param <T>              return jsonObject
     * @return json object
     */
    public static <T> T getJsonObject(String queryParam1, String queryParam2, String get, int expectedResponse, String index, Class<T> pojoClass) {
        try {
            return given().queryParam(queryParam1, queryParam2).log().all().get(get).then().assertThat().statusCode(expectedResponse).log().body()
                    .extract().response().jsonPath().getObject(index, pojoClass);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * return status code
     *
     * @param queryParam1 queryParam1
     * @param queryParam2 queryParam2
     * @return int statusCode
     */
    public static int getStatusCode(String queryParam1, String queryParam2, String get) {
        int statusCode;
        try {
            statusCode = given().queryParam(queryParam1, queryParam2).when().get(get).statusCode();
            return statusCode;

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Returns date type of object
     *
     * @param obj Object
     * @return datatype
     */
    public static String getDataType(Object obj) {
        return obj.getClass().getSimpleName();
    }

    /**
     * Validates the json schema of the API
     *
     * @param queryParam1 string
     * @param queryParam2 string
     * @param get         string
     * @param jsonString  string
     * @throws IOException
     */
    public static void validJsonSchemaOfAPI(String queryParam1, String queryParam2, String get, String jsonString) {
        given().queryParam(queryParam1, queryParam2).when().get(get).then().log().body().assertThat().body(JsonSchemaValidator.matchesJsonSchema(jsonString));
    }
}
