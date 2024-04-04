package org.selenium;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;


import java.io.FileReader;
import java.time.Duration;

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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement domainInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_ContentPlaceHolder1_ucToolhandler_txtToolInput")));
        domainInput.sendKeys(domain);
        WebElement senderButton = driver.findElement(By.id("ctl00_ContentPlaceHolder1_ucToolhandler_btnAction"));
        senderButton.click();
    }
}