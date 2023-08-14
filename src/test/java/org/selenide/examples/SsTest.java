package org.selenide.examples;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;

import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.*;

public class SsTest {


    @Before
    public void setup() {
        Configuration.headless = true;
/*        open("https://www.ss.lv/lv/real-estate/flats/ventspils-and-reg/ventspils/hand_over/");
        System.out.println("ss should be opened");*/


        open("https://www.ss.lv/lv/transport/cars/");
        $("#filter_tbl > tbody > tr > td:nth-child(2) > input").click();
    }

    private String lastSeenId = "#tr_53621157";
    private final String baseUrl = "https://api.telegram.org/bot6328543239:AAHFw0238VTkGd6MCIf67Q_3vLIv380kj7o/sendMessage";
    // private final String chatId = "1985367813";

    @Test
    public void monitorNewElement() throws InterruptedException {
        while (true) {
            try {
                SelenideElement element = $("tr[id^='tr_']");
                String currentId = element.getAttribute("id");

                assert currentId != null;
                if (!currentId.equals(lastSeenId)) {
                    lastSeenId = currentId;
                    String link = element.$("a.am").getAttribute("href");
                    sendMessage("New listing found: " + link);
                }
            } catch (Exception e) {
                // Handle exception
                e.printStackTrace();
            }
            Thread.sleep(60000);// wait 1 min
            //Thread.sleep(30000);
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

}
