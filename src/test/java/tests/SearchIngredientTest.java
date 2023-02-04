package tests;

import com.aventstack.extentreports.Status;
import enumfactory.ApiENUM;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojoclasses.IngredientPOJO;
import utilities.UtilitiesFactory;

public class SearchIngredientTest extends UtilitiesFactory {

    @Test(description = "Verify API response values are not null in case of Alcohol")
    public void validateResponseValuesAreNotNullInCaseOfAlcohol() {

        String ingredientName = ApiENUM.INGREDIENT_VALID_ALCOHOLIC.getValue();
        IngredientPOJO ingredient = getJsonObject(queryParameterSearchIngredient, ingredientName, searchEndPoint, 200, "ingredients[0]", IngredientPOJO.class);

        //Assertion for values are not null in case of Alcoholic Beverage
        Assert.assertNotNull(ingredient.getIdIngredient());
        testLog.log(Status.PASS, "SuccessFully Validated Ingredient Id is not Null: " + ingredient.getIdIngredient());
        Assert.assertNotNull(ingredient.getStrIngredient());
        testLog.log(Status.PASS, "SuccessFully Validated Ingredient is not Null: " + ingredient.getStrIngredient());
        Assert.assertNotNull(ingredient.getStrDescription());
        testLog.log(Status.PASS, "SuccessFully Validated Description is not Null: " + ingredient.getStrDescription());
        Assert.assertNotNull(ingredient.getStrType());
        testLog.log(Status.PASS, "SuccessFully Validated STR Type is not Null: " + ingredient.getStrType());
        Assert.assertNotNull(ingredient.getStrAlcohol());
        testLog.log(Status.PASS, "SuccessFully Validated Alcohol is not Null: " + ingredient.getStrAlcohol());
        Assert.assertNotNull(ingredient.getStrABV());
        testLog.log(Status.PASS, "SuccessFully Validated STR ABV is not Null: " + ingredient.getStrABV());
    }

    @Test(description = "Verify API response values are not null in case of case insensitive search")
    public void validateResponseValuesAreNotNullInCaseOfCaseInsensitive() {

        String caseInsensitiveIngredientName = ApiENUM.INGREDIENT_VALID_CASE_INSENSITIVE.getValue();
        IngredientPOJO[] ingredient = getJsonObject(queryParameterSearchIngredient, caseInsensitiveIngredientName, searchEndPoint, 200, "ingredients", IngredientPOJO[].class);

        //Assertion for values are not null in case of case-insensitive search
        Assert.assertNotNull(ingredient);
        testLog.log(Status.PASS, "SuccessFully Validated that API response is not null in case of case insensitive search");
    }

    @Test(description = "Verify API response values are not null in case of non Alcoholic expect ABV")
    public void validateResponseValuesAreNotNullInCaseOfNonAlcohol() {

        String ingredientName = ApiENUM.INGREDIENT_VALID_NON_ALCOHOLIC.getValue();

        IngredientPOJO ingredient = getJsonObject(queryParameterSearchIngredient, ingredientName, searchEndPoint, 200, "ingredients[0]", IngredientPOJO.class);

        //Assertion for values are not null in case of non Alcoholic expect ABV
        Assert.assertNotNull(ingredient.getIdIngredient());
        testLog.log(Status.PASS, "SuccessFully Validated Ingredient Id is not Null: " + ingredient.getIdIngredient());
        Assert.assertNotNull(ingredient.getStrIngredient());
        testLog.log(Status.PASS, "SuccessFully Validated Ingredient is not Null: " + ingredient.getStrIngredient());
        Assert.assertNotNull(ingredient.getStrDescription());
        testLog.log(Status.PASS, "SuccessFully Validated Description is not Null: " + ingredient.getStrDescription());
        Assert.assertNotNull(ingredient.getStrType());
        testLog.log(Status.PASS, "SuccessFully Validated STR Type is not Null: " + ingredient.getStrType());
        Assert.assertNotNull(ingredient.getStrAlcohol());
        testLog.log(Status.PASS, "SuccessFully Validated Alcohol is not Null: " + ingredient.getStrAlcohol());
        Assert.assertNull(ingredient.getStrABV());
        testLog.log(Status.PASS, "SuccessFully Validated STR ABV is not Null: " + ingredient.getStrABV());
    }

    @Test(description = "Verify API response is null in case of invalid search")
    public void validateResponseValuesAreNullForInvalidSearch() {
        String ingredientName = ApiENUM.INGREDIENT_INVALID.getValue();

        IngredientPOJO ingredient = getJsonObject(queryParameterSearchIngredient, ingredientName, searchEndPoint, 200, "ingredients", IngredientPOJO.class);

        //Assertion for Invalid Search
        Assert.assertNull(ingredient);
        testLog.log(Status.PASS, "SuccessFully Validated that is case of invalid search Ingredient is Null: " + ingredient);
    }

    @Test(description = "Verify API response for incorrect API end point")
    public void validateResponseValuesForIncorrectAPIEndPoint() {
        String ingredientName = ApiENUM.INGREDIENT_VALID_NON_ALCOHOLIC.getValue();
        int statusCode;
        //Assertion for Status code of invalid end point

        statusCode = getStatusCode(queryParameterSearchIngredient, ingredientName, invalidEndPoint);
        Assert.assertEquals(statusCode, 404);
        testLog.log(Status.PASS, "SuccessFully Validated API response in case of incorrect end point");
    }

    @Test(description = "Verify the schema of the Search by Ingredients API")
    public void validateJsonSchemaOfAPIIngredientSearch() {
        String ingredientName = ApiENUM.INGREDIENT_VALID_ALCOHOLIC.getValue();
        int statusCode;

        statusCode = getStatusCode(queryParameterSearchIngredient, ingredientName, searchEndPoint);
        Assert.assertEquals(statusCode, 200);
        testLog.log(Status.PASS, "SuccessFully Validated that API response is 200");
        validJsonSchemaOfAPI(queryParameterSearchIngredient, ingredientName, searchEndPoint, searchByIngredientJsonText);
        testLog.log(Status.PASS, "SuccessFully Validated the schema of API");
    }

}
