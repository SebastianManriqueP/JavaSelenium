package org.selenium;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        //Chrome Browser
        driver.get("https://mxtoolbox.com/blacklists.aspx");
    }
}