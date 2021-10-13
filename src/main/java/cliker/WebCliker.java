package cliker;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.FindBy;
import utils.GetSettings;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class WebCliker {

    public static WebDriver driver;
    private Cookie cookie;
    private GetSettings settings;
    private Proxy proxy;
    private ArrayList<WebElement> list_change_buttons;



    @FindBy (xpath = "/html/body/table[2]/tbody/tr[2]/td[2]/table[2]/tbody/tr[1]/td/table/tbody/tr[2]/td[2]/div[2]/table[2]/tbody/tr[1]/td[2]/table/tbody/tr/td[2]/a")
    private WebElement btn_change;

    @FindBy (xpath = "/html/body/table[2]/tbody/tr[2]/td[2]/table[2]/tbody/tr[1]/td/table/tbody/tr[2]/td[2]/div[2]/table[2]")
    private WebElement table_2_colocation;


    public WebCliker(String pathChromeDriver,String url) throws FileNotFoundException {
        DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
        desiredCapabilities.setCapability("proxy",addProxy());
        System.setProperty("webdriver.chrome.driver",pathChromeDriver);
        driver = new ChromeDriver(desiredCapabilities);
        driver.manage().window().maximize();
        settings = new GetSettings();
        settings.getSettingsFromFile();
        this.cookie = settings.getCookie();
        driver.get(url);
    }

    public void start_page(String url)
    {
        driver.get(url);
        driver.manage().addCookie(cookie);
    }




    private Proxy addProxy()
    {
        proxy = new Proxy();
        proxy.setHttpProxy("192.168.0.254:2121");
        return proxy;

    }
}
