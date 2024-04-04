package org.selenium;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        //Leer JSON
        JSONObject jsonObj = new JSONObject();
        try {
            JSONParser parser = new JSONParser();
            Object object = parser.parse(new FileReader("src/main/resources/datos.json"));
            jsonObj = (JSONObject) object;
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        //Extraer data del JSON
        String url = jsonObj.get("url").toString();
        String domain = jsonObj.get("domain").toString();

        //Chrome Browser
        driver.get(url);
    }
}