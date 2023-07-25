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
    public void clickOnCelojumi(){
        OpenBta();
        $("#module-28 > div > div > div.list > a:nth-child(2)").click();
    }

    @Test
    public void changeDestinationToIndia(){
        clickOnCelojumi();
        $("#regionalSelectorRegion-open > span.text.text-icon").click();
        $("#regionalSelectorCountry-showListSearch").click();
        $("#regionalSelectorCountry > div.popups > aside > div > div.elements > div > div.selected-countries").click();
        $("#regionalSelectorCountry-addCountry > span").click();
        $(By.xpath("//*[text()='Indija']")).click();
        $("#regionalSelectorCountry-removeCountry-0").shouldHave(text("Indija"));
        $("#regionalSelectorCountry-applyButton").click();
        assertElementText("#regionalSelectorRegion-open", "Visa Pasaule");
    }

    // reusable methods

    private void assertElementText(String elementId, String expectedText) {
        $(elementId).shouldHave(text(expectedText));
    }

}
