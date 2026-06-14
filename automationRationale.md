# Automation rationale

For automation I selected the following scenarios:

```gherkin
1. Calculate VAT and Gross amount from Net amount
2. Negative amount is not allowed
3. Amount above maximum limit is not allowed
```

### Calculate VAT and Gross amount from Net amount

This is the main business functionality for the application. The main purpose for the VAT calculator is to calculate proper tax-related values based on the user input.
It is essential to make sure that the application's primary business flow works correctly.

### Negative amount is not allowed AND Amount above maximum limit is not allowed

Automating this scenario provides regression coverage for input validation and error handling.

## The selected scenarios cover:

1. **Core business functionality**
2. **Critical calculation logic**
3. **Input validation**
4. **Boundary conditions**

## Not to automate

1. Visual appearances (such as the colour of the text)
2. every country and their VAT rate calcualtion: The calculation formula is identical for all countries.
3. VAT rates for countries: This validation can be processed on a lower testing level with Back-end automation (checking countries and their VAT rate)
4. Exploratory test cases: Exploratory testing relies on human investigation. It is better suited for manual testing than automation.
