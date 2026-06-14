# VAT Calculator Automation Tests

### Overview:
This project contains automated UI tests for the Calkoo VAT Calculator application.

The solution is implemented using:

* Java 17
* Playwright
* TestNG
* Maven
* Allure Reporting

The automated scenarios focus on the most important business and validation flows:

* Calculate VAT and Gross amount from Net amount
* Negative amount validation
* Above limit amount validation

### Running the tests

```bash
mvn clean test
```

Run the regression suite:

```bash
mvn test -DsuiteXmlFile=Regression.xml
```

### Allure report

```bash
allure serve allure-results
```

### Assumptions
* VAT rates are provided by the backend.
* The VAT calculation formula is identical for all supported countries.

### Limitations
* Only a subset of the designed test scenarios were automated.
* Visual appearance and styling validations were not automated.
* Not every country/VAT rate combination was covered by UI automation.