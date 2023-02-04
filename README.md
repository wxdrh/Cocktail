# Cocktail Exercise

# Introduction 

The Cocktail DB is a public database of cocktails and drinks from around the world https://www.thecocktaildb.com/. 
The Cocktail DB has a public API to retrieve data about ingredients and drinks. See the documentation here https://www.thecocktaildb.com/api.php.

We have proposed some requirements for the cocktail API (this list is not exhaustive!).

# Your task is to:
1.	Write a minimum set of test cases to test the requirements below
2.	Write two additional test cases that are not covered by the requirements below.
3.	Automate the test cases using a language/framework of your choice.<br/>
    **I have created a Framework with RestAssured,TestNG,Maven,ExtentReport for Reporting Purposes in Java Language.<br/>
    I have also implemented POJO classes using jackson for serialization purposes to handle long api response in an efficient way.**
4.	Suggest two non-functional tests that you would design<br/>
      **a. Load Testing: To test the cocktail API performance under heavy traffic or concurrent user requests.<br/>
      b. Security Testing: To test the cocktail API security against threats such as injection attacks and other security vulnerabilities.<br/>**
5.	Suggest a framework that could be used to automate the non-functional test above.<br/>
    **a. For Load Testing we can you Jmeter as it is an open source tool and been widely used and have a huge community of users, It provides a GUI based interface and also we can do scripting on it in Java.<br/>
    b. For Security Testing against Injection Attacks we can OWASP ZAP.**
6.	Send your tests with instructions on how to execute the automated tests within 3 days.<br/>
    **a. As I am using mvn as build automation tool so test can be executed by using _mvn clean test -Dsurefire.suiteXmlFiles=src/test/java/runnerxmls/runner.xml_ this command in terminal.Before executing command maven should be installed<br/>
      b. Tests can also be executed by doing right on _runner.xml_ and click on run. Before this IntelliJ or Eclipse should be installed.<br/>
      c. Execution report is generated in target/executionReports folder**
7.	Always explain yourself clearly and let us know if any assumptions were made.


# Functional Requirements
Search Ingredients By Name: www.thecocktaildb.com/api/json/v1/1/search.php?i=vodka

* The system shall include a method to search by ingredient name and return the following fields: 
- Ingredient ID (string),
- Ingredient (string), 
- Description (string),
- Type (string), 
- Alcohol (string) and 
- ABV (string). 
* If an ingredient is non-alcoholic, Alcohol is null and ABV is null
* If an ingredient is alcoholic, Alcohol is yes and ABV is not null. 

Search Cocktails By Name: www.thecocktaildb.com/api/json/v1/1/search.php?s=margarita

*	The system shall include a method to search by cocktail name. 
*	If the cocktail does not exist in the cocktail DB, the API shall return drinks as null. 
*	Searching for a cocktail by name is case-insensitive
* API response must contain the following Schema properties:

|Element Name|Type|Required|
|------------|----|--------|
|drinks      |array  |yes|
|strDrink|string/null|	yes|
|strDrinkAlternative|string/null|	no|
|strTags|string/null|	yes|
|strVideo|string/null|	no|
|strCategory|string/null|	yes|
|strIBA|string/null|	no|
|strAlcoholic|string/null|	yes|
|strGlass|string/null|	yes
|strInstructions (ES/DE/FR/IT/ZH-HANS/ZH-HANT)	|string/null|	only strInstructions|
|strDrinkThumb|string/null|	no|
|strIngredient1-15|string/null|	only strIngredient1|
|strMeasure1-15	|string/null|	only strMeasure1|
|strImageSource|string/null|	no|
|strImageAttribution|string/null|	no|
|strCreativeCommonsConfirmed|string/null|	yes|
|dateModified|string/null|	yes|
