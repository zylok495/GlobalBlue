# Test design

## Happy path test cases

```gherkin

#1 Test case
Scenario: Display correct VAT rates for selected country
When "Germany" as country selected
Then "7%" and "19%" VAT rate should be displayed

#2 Test case
Scenario: Amount with two decimal places is accepted
Given "Hungary" as country selected
And "27%" VAT rate is selected
And "Price without VAT (Net)" checkbox is selected
When user set amount is 1000.99
Then the calculation should be successful

#3 Test case 
Scenario: Calculate VAT and Gross amount from Net amount
Given "Hungary" as country selected
And "27%" VAT rate is selected
And "Price without VAT (Net)" checkbox is selected
When user set 1000 amount
Then VAT should be 270.00
And Gross should be 1270.00

#4 Test case
Scenario: Calculate Net and VAT amount from Gross amount
Given "Hungary" as country selected
And "27%" VAT rate is selected
And "Price incl. VAT (Gross)" checkbox is selected
When user set 1270 amount
Then Net amount should be 1000.00
And VAT amount should be 270.00

#5 Test case
Scenario: Calculate Net and Gross amount from VAT amount
Given "Hungary" as country selected
And "27%" VAT rate is selected
And "Value-Added Tax" checkbox is selected
When user set 270 amount
Then the Net amount should be 1000.00
And Gross amount should be 1270.00

#6 Test case
Scenario: Maximum allowed amount is accepted
Given "Hungary" as country selected
And "27%" VAT rate is selected
And "Price without VAT (Net)" checkbox is selected
When user set 999.999.999 amount
Then the calculation should be successful

```

## Negative test cases

```gherkin

#1 Test case
Scenario: Negative amount is not allowed
Given "Hungary" as country selected
And "27%" VAT rate is selected
And "Price without VAT (Net)" checkbox is selected
When user set -1 amount
Then error message should be displayed
And the error message should explain that negative values are not allowed

#2 Test case
Scenario: Amount above maximum limit is not allowed
Given "Hungary" as country selected
And "27%" VAT rate is selected
And "Price incl. VAT (Gross)" checkbox is selected
When user set 1.000.000.000 amount
Then an error message should be displayed
And the error message should explain that the amount exceeds the allowed limit

#3 Test case
Scenario: Amount with more than two decimal places
Given "Hungary" as country selected
And "27%" VAT rate is selected
And "Price without VAT (Net)" checkbox is selected
When the Net amount is 1000.999
Then the application should reject the value
And an error message should be displayed

NOTE: AC does not mention "non-numerical" input such as letters or special characters. Also "zero" as value. Potential risk, needs to inform PO.

```
