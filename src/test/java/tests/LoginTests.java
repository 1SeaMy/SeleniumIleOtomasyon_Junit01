package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import sayfalar.AnaSayfa;
import sayfalar.GirisYapSayfasi;

public class LoginTests {
    private WebDriver driver;
    private AnaSayfa anaSayfa;
    private GirisYapSayfasi girisYapSayfasi;
    @BeforeEach
    void setup(){
        ChromeOptions options =new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable notifications");
        DesiredCapabilities cp=new DesiredCapabilities();
        cp.setCapability(ChromeOptions.CAPABILITY, options);
        options.merge(cp);

        driver=new ChromeDriver(options);
        driver.manage().window().maximize();

        anaSayfa= new AnaSayfa(driver);
        girisYapSayfasi=new GirisYapSayfasi(driver);

        driver.get("https://www.hepsiburada.com");
    }
    @Test
    void hepsiburadaLoginTesti() throws InterruptedException{

        anaSayfa.elementGozukeneKadarBekle(anaSayfa.hesabim);
        anaSayfa.tusaBas(anaSayfa.hesabim);
        anaSayfa.elementGozukeneKadarBekle(anaSayfa.girisYap);
        Thread.sleep(1000);
        anaSayfa.tusaBas(anaSayfa.girisYap);

        girisYapSayfasi.elementGozukeneKadarBekle(girisYapSayfasi.emailAdresi);
        girisYapSayfasi.alanaYaziYaz(girisYapSayfasi.emailAdresi, "da@da.com");
        girisYapSayfasi.elementGozukeneKadarBekle(girisYapSayfasi.girisYapTusu);
        girisYapSayfasi.tusaBas(girisYapSayfasi.girisYapTusu);
    }

    @Test
    @Disabled
    void urunAramaTesti() throws InterruptedException{
        Thread.sleep(1000);

        anaSayfa.elementGozukeneKadarBekleCss(anaSayfa.urunArama);
        Thread.sleep(1000);

        anaSayfa.alanaYaziYazCss(anaSayfa.urunArama, "Araba");
        Thread.sleep(1000);

        anaSayfa.klavyeTusunaBas(anaSayfa.urunArama, Keys.ENTER);
        Thread.sleep(1000);

    }
    @AfterEach
    void tearDown(){
        driver.quit();
    }
}