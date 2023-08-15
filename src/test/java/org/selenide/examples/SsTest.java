package org.selenide.examples;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.*;

public class SsTest {


    @Before
    public void setup() {
        Configuration.headless = true;
        //open("https://www.ss.lv/lv/real-estate/flats/ventspils-and-reg/ventspils/");

/*        open("https://www.ss.lv/lv/transport/cars/volkswagen/golf-7/filter/");
        $("#f_o_35").click();
        $("#f_o_35 > option:nth-child(2)").click();*/


/*        open("https://www.ss.lv/lv/real-estate/flats/ventspils-and-reg/ventspils/hand_over/");
        */


        open("https://www.ss.lv/lv/transport/cars/");
        $("#filter_tbl > tbody > tr > td:nth-child(2) > input").click();

        System.out.println("ss should be opened");
    }

    private Set<String> seenIds = new HashSet<>();
    private final String baseUrl = "https://api.telegram.org/bot6328543239:AAHFw0238VTkGd6MCIf67Q_3vLIv380kj7o/sendMessage";

    @Test
    public void monitorNewElement() throws InterruptedException {
        // Initial capture of all current listings' ids without sending messages
        initializeSeenIds();

        while (true) {
            try {
                for (SelenideElement element : $$("tr[id^='tr_']")) {
                    String currentId = element.getAttribute("id");

                    // Check if the element has the "a.am" link before accessing it
                    if (element.$$("a.am").size() > 0) {
                        String link = element.$("a.am").getAttribute("href");
                        if (!seenIds.contains(currentId)) {
                            seenIds.add(currentId);
                            sendMessage("New listing found: " + link);
                        }
                    }
                }
            } catch (Exception e) {
                // Handle exception
                e.printStackTrace();
                System.out.println("Error occurred while monitoring. Retrying...");
            }
            Thread.sleep(60000); // wait 1 min
            refresh(); // Selenide refresh command
        }
    }

    private final String[] chatIds = {"1985367813"}; // replace with your actual chat IDs

    private void sendMessage(String messageText) {
        for (String chatId : chatIds) {
            try {
                String urlString = baseUrl + "?chat_id=" + chatId + "&text=" + messageText;

                URL url = new URL(urlString);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                int responseCode = conn.getResponseCode();
                System.out.println("Response Code : " + responseCode);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void initializeSeenIds() {
        for (SelenideElement element : $$("tr[id^='tr_']")) {
            String currentId = element.getAttribute("id");
            seenIds.add(currentId);
        }
    }


}
