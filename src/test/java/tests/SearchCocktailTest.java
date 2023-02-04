package tests;

import com.aventstack.extentreports.Status;
import enumfactory.ApiENUM;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojoclasses.DrinkPOJO;
import utilities.UtilitiesFactory;

public class SearchCocktailTest extends UtilitiesFactory {

    @Test(description = "Verify API response code and API and response is not null")
    public void validateCocktailSearchIsNotNullAndResponse() {

        String cockNameValid = ApiENUM.COCKTAIL_VALID_NAME.getValue();
        DrinkPOJO[] drinkPOJO = getJsonObject(queryParameterSearchCocktail, cockNameValid, searchEndPoint, 200, "drinks", DrinkPOJO[].class);

        //Assert Status Code and API response is not null.
        Assert.assertNotNull(drinkPOJO);
        testLog.log(Status.PASS, "SuccessFully Validated API response is not Null");

    }

    @Test(description = "Verify API response code and API and response is null in case of invalid search")
    public void validateNonExistentCocktailReturnsNullResult() {
        String cockNameInvalidValid = ApiENUM.COCKTAIL_INVALID_NAME.getValue();
        DrinkPOJO[] drinkPOJO = getJsonObject(queryParameterSearchCocktail, cockNameInvalidValid, searchEndPoint, 200, "drinks", DrinkPOJO[].class);

        //Assert Status Code and API response is null.
        Assert.assertNull(drinkPOJO);
        testLog.log(Status.PASS, "SuccessFully Validated API response is Null in case of invalid API");
    }

    @Test(description = "Verify API response code and API and response is not null in case of Case Insensitive Search")
    public void validateNameSearchCocktailCaseInsensitive() {
        String cockNameInvalidValid = ApiENUM.COCKTAIL_CASE_INSENSITIVE_NAME.getValue();
        DrinkPOJO[] drinkPOJO = getJsonObject(queryParameterSearchCocktail, cockNameInvalidValid, searchEndPoint, 200, "drinks", DrinkPOJO[].class);

        //Assert Status Code if cocktail name is case in-sensitive
        Assert.assertNotNull(drinkPOJO);
        testLog.log(Status.PASS, "SuccessFully Validated API response is not Null in case of in-sensitive case");
    }

    @Test(description = "Verify API response code and API and response is not null in case of searching with Spaces in cocktail name")
    public void validateNameSearchCocktailCaseWithSpaces() {
        String cockNameWithSpaces = ApiENUM.COCKTAIL_NAME_WITH_SPACE.getValue();
        DrinkPOJO[] drinkPOJO = getJsonObject(queryParameterSearchCocktail, cockNameWithSpaces, searchEndPoint, 200, "drinks", DrinkPOJO[].class);

        //Assert Status Code if cocktail name has spaces
        Assert.assertNotNull(drinkPOJO);
        testLog.log(Status.PASS, "SuccessFully Validated API response is not Null in case of Cocktail Name with spaces");
    }

    @Test(description = "Verify API response code and API returns a list in case of searching a generic cocktail")
    public void validateMultipleCocktailsReturnsValidResult() {
        String cockNameValid = ApiENUM.COCKTAIL_VALID_NAME.getValue();
        DrinkPOJO[] drinkPOJO = getJsonObject(queryParameterSearchCocktail, cockNameValid, searchEndPoint, 200, "drinks", DrinkPOJO[].class);

        Assert.assertTrue(drinkPOJO.length > 1);
        testLog.log(Status.PASS, "SuccessFully Validated API returns a list in case of generic cocktail search");
    }

    @Test(description = "Verify API response code and API return 404 in case of incorrect endpoints")
    public void validateResponseValuesForIncorrectAPIEndPoint() {
        String cockNameValid = ApiENUM.COCKTAIL_VALID_NAME.getValue();
        int statusCode;

        statusCode = getStatusCode(queryParameterSearchCocktail, cockNameValid, invalidEndPoint);
        Assert.assertEquals(statusCode, 404);
        testLog.log(Status.PASS, "SuccessFully Validated that 404 error is return in case of invalid endpoint");
    }

    @Test(description = "Verify API response code and API and required keys are not null")
    public void validateRequiredKeyAreNotNull() {
        String cockNameValid = ApiENUM.COCKTAIL_VALID_NAME.getValue();
        DrinkPOJO[] drinkPOJO = getJsonObject(queryParameterSearchCocktail, cockNameValid, searchEndPoint, 200, "drinks", DrinkPOJO[].class);

        //Assertion for values are not null in case of Alcoholic Beverage
        Assert.assertNotNull(drinkPOJO[0].getStrDrink());
        testLog.log(Status.PASS, "SuccessFully Validated that getStrDrink is not null: " + drinkPOJO[0].getStrDrink());
        Assert.assertNotNull(drinkPOJO[0].getStrTags());
        testLog.log(Status.PASS, "SuccessFully Validated that getStrDrink is not null: " + drinkPOJO[0].getStrTags());
        Assert.assertNotNull(drinkPOJO[0].getStrCategory());
        testLog.log(Status.PASS, "SuccessFully Validated that getStrDrink is not null: " + drinkPOJO[0].getStrCategory());
        Assert.assertNotNull(drinkPOJO[0].getStrAlcoholic());
        testLog.log(Status.PASS, "SuccessFully Validated that getStrDrink is not null: " + drinkPOJO[0].getStrAlcoholic());
        Assert.assertNotNull(drinkPOJO[0].getStrInstructions());
        testLog.log(Status.PASS, "SuccessFully Validated that getStrDrink is not null: " + drinkPOJO[0].getStrInstructions());
        Assert.assertNotNull(drinkPOJO[0].getStrGlass());
        testLog.log(Status.PASS, "SuccessFully Validated that getStrDrink is not null: " + drinkPOJO[0].getStrGlass());
        Assert.assertNotNull(drinkPOJO[0].getStrIngredient1());
        testLog.log(Status.PASS, "SuccessFully Validated that getStrDrink is not null: " + drinkPOJO[0].getStrIngredient1());
        Assert.assertNotNull(drinkPOJO[0].getStrMeasure1());
        testLog.log(Status.PASS, "SuccessFully Validated that getStrDrink is not null: " + drinkPOJO[0].getStrMeasure1());
        Assert.assertNotNull(drinkPOJO[0].getStrCreativeCommonsConfirmed());
        testLog.log(Status.PASS, "SuccessFully Validated that getStrDrink is not null: " + drinkPOJO[0].getStrCreativeCommonsConfirmed());
        Assert.assertNotNull(drinkPOJO[0].getDateModified());
        testLog.log(Status.PASS, "SuccessFully Validated that getStrDrink is not null: " + drinkPOJO[0].getDateModified());
    }

    @Test(description = "Verify the schema of the Search by Name API")
    public void validateJsonSchemaOfAPI() {
        String cockNameValid = ApiENUM.COCKTAIL_VALID_NAME.getValue();
        int statusCode;

        statusCode = getStatusCode(queryParameterSearchCocktail, cockNameValid, searchEndPoint);
        Assert.assertEquals(statusCode, 200);
        testLog.log(Status.PASS, "SuccessFully Validated that API response is 200");
        validJsonSchemaOfAPI(queryParameterSearchCocktail, cockNameValid, searchEndPoint, searchByNameJsonText);
        testLog.log(Status.PASS, "SuccessFully Validated the schema of API");
    }

}
