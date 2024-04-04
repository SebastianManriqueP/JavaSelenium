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
import java.util.List;

public class Main {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        //Leer JSON
        JSONObject jsonObj;
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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement domainInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_ContentPlaceHolder1_ucToolhandler_txtToolInput")));
        domainInput.sendKeys(domain);
        WebElement senderButton = driver.findElement(By.id("ctl00_ContentPlaceHolder1_ucToolhandler_btnAction"));
        senderButton.click();

        //Esperar que cargue la tabla
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("tool-result-table")));

        //Extraer IP
        List<WebElement> ipDomain = driver.findElements(By.xpath("//strong"));

        //Extraer Blacklist
        List<WebElement> blacklist = driver.findElements(By.xpath("//table[contains(@class,'tool-result-table')]//tr//td[2]"));

        //Extraer LISTED
        List<WebElement> namesListed = driver.findElements(By.xpath("//table[contains(@class,'tool-result-table')]//td[contains(text(),'LISTED')]/following-sibling::td[1]"));

        //Imprimir datos
        System.out.println("* IP DOMAIN: "+ipDomain.get(1).getText());
        System.out.println("* Number of blacklists where the check was performed: "+blacklist.size());
        System.out.println("* Number of Blacklists where it is listed: "+namesListed.size());
        System.out.println("* Blacklists where it is listed: ");
        for(WebElement element:namesListed){
            System.out.println(element.getText());
        }

        //Cerrar driver
        driver.quit();

    }
}