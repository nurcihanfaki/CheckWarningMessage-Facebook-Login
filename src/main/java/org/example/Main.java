package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Main {
    WebDriver driver;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void startTest() throws InterruptedException {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        // facebook sayfasına git
        driver.get("https:www.facebook.com");

        // cookies leri kabul et
        //driver.findElement(By.xpath("//button[@title:'Allow essential and optional cookies']")).click();

        // email kutusuna git
        WebElement epostaBox = driver.findElement(By.id("email"));

        // eposta kutusuna rastgele bir eposta gir
        epostaBox.sendKeys("randomrandomrandomrandomrandomrandomrandomrandomrandomrandom");

        // şifre kutusuna git
        WebElement passwordBox = driver.findElement(By.id("pass"));

        // şifre kutusuna rastgele bir şifre yaz
        passwordBox.sendKeys("şifreşifreşifreşifreşifreşifreşifreşifreşifre");


        // giriş yap butonuna tıkla
        driver.findElement(By.name("login")).click();

        // uyarı olarak "Girdiğin e-posta veya cep telefonu numarası bir hesaba bağlı değil. Hesabını bul ve giriş yap." mesajını al
        WebElement resultTextElement = driver.findElement(By.xpath("//*[@id=\"email_container\"]/div[2]"));

        // mesajın çıktığını test et
        String expectedResultLetter = "Girdiğin e-posta veya cep telefonu numarası bir hesaba bağlı değil. Hesabını bul ve giriş yap.";
        String actualResultLetter = resultTextElement.getText();

        if (expectedResultLetter.equals(actualResultLetter)) {
            System.out.println("girilemedi testi passed");
        } else {
            System.out.println("girilemedi testi failed");
        }

        // sayfayı kapat
        driver.close();
    }

    @After
    public void endTest() {
        driver.quit();
    }
}