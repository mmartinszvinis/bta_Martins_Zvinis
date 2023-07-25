package org.selenide.examples;

import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class BtaTest {

    @Test
    public void OpenBta(){
        open("https://www.bta.lv/");
        $("#module-284 > div > div.buttons > button:nth-child(3)").click(); // accept cookies
    }

    @Test
    public void OpenCelojumi(){
        OpenBta();
        $("#module-28 > div > div > div.list > a:nth-child(2)").click();
    }

    @Test
    public void changeDestinationToIndia(){
        OpenCelojumi();
        $("#regionalSelectorRegion-open > span.text.text-icon").click();
        $("#regionalSelectorCountry-showListSearch").click();
        $("#regionalSelectorCountry > div.popups > aside > div > div.elements > div > div.selected-countries").click();
        $("#regionalSelectorCountry-addCountry > span").click();
        $(By.xpath("//*[text()='Indija']")).click();
        $("#regionalSelectorCountry-removeCountry-0").shouldHave(text("Indija"));
        $("#regionalSelectorCountry-applyButton").click();
        $("#regionalSelectorRegion-open > span.text.text-icon").shouldHave(text("Visa pasaule"));
        $("#regionalSelectorRegion-open .text").shouldHave(text("Visa pasaule"));
    }

    @Test
    public void changeActivity(){
        OpenCelojumi();
        clickElement("#travelActivities-open > span.text.text-icon");
        clickElement("#travelActivities-popup-select-option-3");
        assertElementText("#travelActivities-open", "Sports");
        $("#form-travel-insurance > div:nth-child(7)").shouldBe(visible);
        sleep(3000);
    }

    // reusable methods

    private void clickElement(String elementId) {
        $(elementId).click();
    }

    private void assertElementText(String selector, String expectedText) {
        $(selector).shouldHave(text(expectedText));
    }
}
