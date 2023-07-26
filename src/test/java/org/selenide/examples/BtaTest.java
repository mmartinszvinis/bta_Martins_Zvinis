package org.selenide.examples;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BtaTest {

   @Before
    public void setup(){
        open("https://www.bta.lv/");
        System.out.println("bta.lv should be opened");
        try{
            boolean cookiesPromptIsDisplayed = $("#module-284 > div").isDisplayed();
            if(cookiesPromptIsDisplayed){
                clickElement("#module-284 > div > div.buttons > button:nth-child(3)"); // accept cookies
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void travelerPoliciesFullFlow(){
        OpenCelojumi();

        changeDestinationToIndia();
        changeActivitytoSport();
        clickOnGetOffers();

        offerScreenIsDisplayed();
        openOptimalPlusDetails();
        optimalPlusDetailsAreDisplayed();
        closeOptimalPlusDetails();
        selectOptimalPlusAndContinue();

        travelerDataInputScreenIsDisplayed();
        travelerDataInputFieldsAreDisplayed();
        travelerDataInputFieldsAreEmpty();
    }

    // home screen

    public void OpenCelojumi(){
        clickElement("#module-28 > div > div > div.list > a:nth-child(2)");
    }

    // celojumi screen

    public void changeDestinationToIndia(){
        clickElement("#regionalSelectorRegion-open > span.text.text-icon");
        clickElement("#regionalSelectorCountry-showListSearch");
        clickElement("#regionalSelectorCountry > div.popups > aside > div > div.elements > div > div.selected-countries");
        clickElement("#regionalSelectorCountry-addCountry > span");
        $(By.xpath("//*[text()='Indija']")).click();

        assertElementText("#regionalSelectorCountry-removeCountry-0 .text", "Indija");

        clickElement("#regionalSelectorCountry-applyButton");
        assertElementText("#regionalSelectorRegion-open .text", "Visa pasaule");
    }

    public void changeActivitytoSport(){
       clickElement("#travelActivities-open > span.text.text-icon");
       clickElement("#travelActivities-popup-select-option-3");
       assertElementText("#travelActivities-open .text", "Sports");
       $("#form-travel-insurance > div:nth-child(7)").shouldBe(visible);
    }

    public void clickOnGetOffers(){
       clickElement("#form-travel-insurance > div:nth-child(8) > div > button");
    }

    // offers screen

    public void offerScreenIsDisplayed(){
       $("#module-888 > div > div > section.header").shouldBe(visible);
       assertElementText("#module-888 > div > div > section.header", "Izvēlies programmu");
    }

    public void openOptimalPlusDetails(){
        clickElement("#form-travel-policy > section > div:nth-child(2) > button.button-covered-popup");
    }

    public void closeOptimalPlusDetails(){
        clickElement("#page-one-travel > div.popups > aside > div > button");
    }

    public void optimalPlusDetailsAreDisplayed(){
       $("#page-one-travel > div.popups > aside > div").shouldBe(visible);
    }

    public void selectOptimalPlusAndContinue(){
       clickElement("#form-travel-policy > section > div:nth-child(2) > button.button.hide-on-mobile.red");
    }

    // traveler data input screen

    public void travelerDataInputScreenIsDisplayed(){
       assertElementText("#form-travel-travellers > div.header", "Ceļotāju dati");
    }

    public void travelerDataInputFieldsAreDisplayed(){
        nameInputFieldIsDisplayed();
        surnameInputFieldIsDisplayed();
        identityNumberInputFieldIsDisplayed();
    }

    public void nameInputFieldIsDisplayed(){
       $("#travelerFirstName0").shouldBe(visible);
    }

    public void surnameInputFieldIsDisplayed(){
        $("#travelerLastName0").shouldBe(visible);
    }

    public void identityNumberInputFieldIsDisplayed(){
        $("#travelerIdentityNumber0").shouldBe(visible);
    }

    public void travelerDataInputFieldsAreEmpty(){
        nameInputFieldIsEmpty();
        surnameInputFieldIsEmpty();
        identityNumberInputFieldIsEmpty();
    }

    public void nameInputFieldIsEmpty(){
       $("#travelerFirstName0-text").shouldBe(empty);
    }

    public void surnameInputFieldIsEmpty(){
        $("#travelerLastName0-text").shouldBe(empty);
    }

    public void identityNumberInputFieldIsEmpty(){
        $("#travelerIdentityNumber0-text").shouldBe(empty);
    }

    // reusable methods

    private void clickElement(String elementId) {
        $(elementId).click();
    }

    private void assertElementText(String selector, String expectedText) {
        String actualText = $(selector).getText();
        assertEquals(expectedText, actualText, "Expected text is not equal to actual text!");
    }

}
