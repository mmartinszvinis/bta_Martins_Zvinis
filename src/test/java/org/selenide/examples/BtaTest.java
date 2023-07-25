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
        //sleep(3000);
    }

    @Test
    public void clickOnCelojumi(){
        OpenBta();
        $("#module-28 > div > div > div.list > a:nth-child(2)").click();
        sleep(3000);
    }

}
