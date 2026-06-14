package tests;

import config.CalculationTypes;
import io.qameta.allure.Issue;
import org.testng.annotations.Test;

import static config.TestConfig.*;
import static constants.TestConstants.HUNGARY_COUNTRY_CODE;
import static constants.TestConstants.VAT_27;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class CalculationTests extends BaseTest {

    @Test(testName = "Calculate VAT and Gross amount from Net amount",
    description = "Calculate VAT and Gross amount from Net amount",
    groups = {REGRESSION})
    public void vatAndGrossCalculationFromNet() {
        calculatorPage.selectCountry(HUNGARY_COUNTRY_CODE);
        assertTrue(calculatorPage.checkDefaultVatRate(VAT_27));
        assertTrue(calculatorPage.checkDefaultNetCalculationType());
        calculatorPage.typeAmountForCalculationInputField("1000", CalculationTypes.NET);
        assertEquals("270.00", calculatorPage.getCalculationTypeValue(CalculationTypes.VAT));
        assertEquals("1270.00", calculatorPage.getCalculationTypeValue(CalculationTypes.GROSS));
    }

    @Issue("[JIRA bug ticket number should come here]")
    @Test(testName = "Negative amount is not allowed",
            description = "Negative amount is not allowed",
            groups = {QUARANTINE})
    public void negativeInputParameter() {
        calculatorPage.selectCountry(HUNGARY_COUNTRY_CODE);
        calculatorPage.checkDefaultVatRate(VAT_27);
        calculatorPage.selectCalculationType(CalculationTypes.GROSS);
        calculatorPage.typeAmountForCalculationInputField("-1", CalculationTypes.GROSS);
        assertTrue(calculatorPage.errorMessageIsVisible());

    }

    @Issue("[JIRA bug ticket number should come here]")
    @Test(testName = "Amount above maximum limit is not allowed",
            description = "Amount above maximum limit is not allowed",
            groups = {QUARANTINE})
    public void amountAboveMaximumLimitInputParameter() {
        calculatorPage.selectCountry(HUNGARY_COUNTRY_CODE);
        calculatorPage.checkDefaultVatRate(VAT_27);
        calculatorPage.selectCalculationType(CalculationTypes.VAT);
        calculatorPage.typeAmountForCalculationInputField("1000000000", CalculationTypes.VAT);
        assertTrue(calculatorPage.errorMessageIsVisible());
    }
}
