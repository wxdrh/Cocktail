package enumfactory;

public enum ApiENUM {

    INGREDIENT_VALID_ALCOHOLIC("vodka"),
    INGREDIENT_VALID_NON_ALCOHOLIC("water"),
    INGREDIENT_VALID_CASE_INSENSITIVE("vODkA"),
    INGREDIENT_INVALID("xyz"),
    COCKTAIL_VALID_NAME("margarita"),
    COCKTAIL_INVALID_NAME("nonexistentcocktailname"),
    COCKTAIL_CASE_INSENSITIVE_NAME("MarGarItA"),
    COCKTAIL_NAME_WITH_SPACE("Old Fashioned");

    private String apiEums;

    ApiENUM(String apiEums) {
        this.apiEums = apiEums;
    }

    public String getValue() {
        return this.apiEums;
    }
}