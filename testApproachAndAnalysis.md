# Test approach and analysis

### Assumptions:
 - VAT rate provided by back-end
 - Database is available which contains countires with their VAT rate

After careful review of the webapplication, I made the conclusion that the key properties will be the "Price without VAT (Net)", "Value-Added Tax" and "Price incl. VAT (Gross)" from QA point of view.
On the start page of the website the calculation method (VAT formula) is visible from which we can assume that the calculiton shared between all countries. In this case no tests required for each country.
Also in this situation it would offend the "Exhaustive Testing" testing principle.

## The order of the automation testing:

Prioritization of automation of test cases is influenced by business risk and core functionalities.
For example if I have 9 test cases and I need to select the first 3 cases to automatize, the following order would be implemented:

```gherkin
#1
Core function of the product
Scenario: Calculate VAT and Gross amount from Net amount

#2
Critical validation
Scenario: Negative amount is not allowed

#3
Critical validation
Scenario: Amount above maximum limit is not allowed

```


## Functional Risks:

### Not correspondent VAT rate for given Countries:
As the calculation is given, one of the main risk can be the VAT rate for countries. It can be a huge risk if bad VAT rate is loaded for country. In this case the result for the user would be deceptive.
To make sure VAT is correspondent for given country, I would use back-end test where we check the countries and their VAT rate in the database. After that I would check on FE only one, that the display FE logic works properly.
It is very essential to emphasize this needs to be done with BE testing as it significantly faster then we would use Front-end testing.

### Invalid value as input parameter
We need to make sure that the application can handle invalid parameters, for example negative values or too big values. Boundary value testing can be applied here ( -1, 0, 1, 999.999.999)

Acceptance criteria does not contains non-numerical inputs (such as letters or special characters like *) but it can be a functional risk as well. I would let my PO know that we should also prepare the application for such a scenario.

### Incorrect calculation
It might be the calculation modell is visible for users, but we need to assure that the actual calculation works properly.

### Rounding risk
We need to make sure that the rounding is proper.

## Quality Risks
### Browser compatibility
There is a risk that the application works differently for each browser type, so that it could be useful to check the behaviour of the application for different browser type.

### Response time
The calculation should be performed almost instantly. Unexpected delays could negatively impact user experience.