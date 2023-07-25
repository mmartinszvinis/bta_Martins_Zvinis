package org.selenide.examples;

import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;

public class BtaTest {

    @Test
    public void userCanOpenBta(){
        open("https://www.bta.lv/");
        sleep(3000);
    }

}
