package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.SelectOption;
import config.CalculationTypes;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CalculatorPage {

    protected static final Logger log = LoggerFactory.getLogger(CalculatorPage.class);
    private final Page page;

    private final Locator agreePrivacyPolicyButton;
    private final Locator agreeCookieButton;

    private final Locator countryDropdown;
    private final Locator netCheckbox;
    private final Locator netInputField;
    private final Locator vatCheckbox;
    private final Locator vatInputField;
    private final Locator grossCheckbox;
    private final Locator grossInputField;
    private final Locator errorMessage;

    public CalculatorPage(Page page) {
        this.page = page;
        this.agreePrivacyPolicyButton = page.locator("[data-role='b_agree']");
        this.agreeCookieButton = page.locator(".fc-cta-consent");
        this.countryDropdown = page.locator("select[name='Country']");
        this.netCheckbox = page.locator("label[for='F1']");
        this.netInputField = page.locator("#NetPrice");
        this.vatCheckbox = page.locator("label[for='F2']");
        this.vatInputField = page.locator("#VATsum");
        this.grossCheckbox = page.locator("label[for='F3']");
        this.grossInputField = page.locator("#Price");
        this.errorMessage = page.getByText("the specified parameter is outside the limit value");
    }

    @Step("Select country")
    public void selectCountry(String countryCode) {
        log.info("Select country: " + countryCode);
        countryDropdown.selectOption((new SelectOption().setValue(countryCode)));
    }

    @Step("Check default VAT rate")
    public Boolean checkDefaultVatRate(String vatRate) {
        log.info("Check default VAT rate: " + vatRate);
        return page.locator("label[for='" + vatRate + "']").isChecked();
    }

    @Step("Check default NET calculation type")
    public Boolean checkDefaultNetCalculationType() {
        log.info("Check default NET calculation type");
        return netCheckbox.isChecked();
    }


    @Step("Select calculation type")
    public void selectCalculationType(CalculationTypes type) {
        switch (type) {
            case NET -> {
                log.info("Select NET checkbox");
                netCheckbox.click();
            }
            case GROSS -> {
                log.info("Select GROSS checkbox");
                grossCheckbox.click();
            }
            case VAT -> {
                log.info("Select VAT checkbox");
                vatCheckbox.click();
            }
        }
    }

    @Step("Type amount for calculation input field")
    public void typeAmountForCalculationInputField(String amount, CalculationTypes type) {
        switch (type) {
            case NET -> {
                log.info("Type {} amount for NET input field", amount);
                netInputField.fill("");
                netInputField.pressSequentially(amount);
            }
            case GROSS -> {
                log.info("Type {} amount for GROSS input field", amount);
                grossInputField.fill("");
                grossInputField.pressSequentially(amount);
            }
            case VAT -> {
                log.info("Type {} amount for VAT input field", amount);
                vatInputField.fill("");
                vatInputField.pressSequentially(amount);
            }
        }
    }

    @Step("Get calculation type value")
    public String getCalculationTypeValue(CalculationTypes type) {
        switch (type) {
            case NET -> {
                return netInputField.inputValue();
            }
            case GROSS -> {
                return grossInputField.inputValue();
            }
            case VAT -> {
                return vatInputField.inputValue();
            }
        }

        throw new IllegalArgumentException("Unsupported calculation type: " + type);
    }

    @Step("Error message is visible")
    public boolean errorMessageIsVisible() {
        return this.errorMessage.isVisible();
    }

    @Step("Agree privacy policy")
    public void agreePrivacyPolicy() {
        agreePrivacyPolicyButton.click();
    }

    @Step("Agree cookies")
    public void agreeCookies() {
        agreeCookieButton.click();
    }
}
