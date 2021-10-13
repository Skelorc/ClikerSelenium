package work_with_elements;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cliker.WebCliker.driver;
import static utils.Const.CODE;
import static utils.Const.URL;

public class WorkWithAddSite {

    private WebElement btn_add_site;
    private WebElement text_area_name_site;
    private WebElement btn_import_site_setting;
    private WebElement text_area_setting;
    private WebElement btn_import_settings;
    private WebElement btn_OK;
    private WebElement btn_add_region;
    private WebElement radio_btn_Customized_region;
    private WebElement check_button_Ck_Area_Self_America;
    private WebElement btn_Btn_SetUrl_SaveArea;
    private WebElement btn_change;
    private WebElement btn_diverted_site;
    private WebElement text_area_Text_SetUrl_MyUrls;
    private WebElement text_area_Text_SetUrl_MyUrlsPercent;
    private WebElement btn_Btn_SetUrl_AddUrls;
    private WebElement btn_Btn_SetUrl_SaveUrls;
    //Мапа с кнопками change.
    private Map<Integer, List<WebElement>> mapAlreadySites;
    private List<WebElement> listBtnsAddSite;
    public ArrayList<String> listRefs;
    //количество процентов для вставки.
    public int procent;
    //Количество ссылок, и итераций цикла для вставки.
    public int count_refs;
    //Остаток процентов, добавляющийся к каждому.
    public int remains;
    //Количество новых сайтов для добавления
    private int count_sites = 10;

    private final JavascriptExecutor js;
    public WorkWithAddSite() {
        listRefs = new ArrayList<>();
        js = (JavascriptExecutor) driver;
    }

    public void click_add_site() throws InterruptedException {

        for (int i = 0; i < listBtnsAddSite.size(); i++) {
            for (int j = 0; j < count_sites; j++) {
              /*  //ТЕСТИМ!!!
                btn_add_site = (WebElement) (js.executeScript("return document.querySelector(\"#Btn_Addurl\")", btn_add_site));*/
                //Рабочая версия!!!
                if(!mapAlreadySites.isEmpty())
                {
                    List<WebElement> webElements = mapAlreadySites.get(i);
                    for (WebElement btn_change : webElements)
                    {
                        btn_change.click();
                        createWork();
                        j++;
                    }
                }
                else {
                    btn_add_site = listBtnsAddSite.get(i);
                    clickButton(btn_add_site);
                    createWork();
                }

            }

            //driver.switchTo().alert().accept();
       }

    }

    private void createWork() throws InterruptedException {
        //Добавляем УРЛ
        text_area_name_site = (WebElement) (js.executeScript("return document.querySelector(\"#Text_AddUrl_MyUrl\");", text_area_name_site));
        text_area_name_site.sendKeys(URL);

        //Добавляем спарсенные данные
        btn_diverted_site = (WebElement) (js.executeScript("return document.querySelector(\"#A_AddUrl_SetMyUrls\").click();", btn_diverted_site));
        Thread.sleep(2000);

        //Считаем количество процентов.
        countRefs();

        //Вставляем ссылки в поля
        addHrefsToDelivered();
        Thread.sleep(2000);

        //Импорт настроек.
        btn_import_site_setting = (WebElement) (js.executeScript("return document.querySelector(\"#A_AddUrl_SetImport\");", btn_import_site_setting));
        clickButton(btn_import_site_setting);

        //Вставляем код
        text_area_setting = (WebElement) (js.executeScript("return document.querySelector(\"#Textarea_ImportSet\");", text_area_setting));
        text_area_setting.sendKeys(CODE);
        Thread.sleep(2000);

        //Сохраняем настройки
        btn_import_settings = (WebElement) (js.executeScript("return document.querySelector(\"#Btn_SetUrl_SaveImport\");", btn_import_settings));
        clickButton(btn_import_settings);
        Thread.sleep(2000);

        //Выбираем регион
        btn_add_region = (WebElement) (js.executeScript("return document.querySelector(\"#A_AddUrl_SetArea\").click();", btn_add_region));
        Thread.sleep(2000);

        radio_btn_Customized_region = (WebElement) (js.executeScript("return document.querySelector(\"#Rd_Area_Self\")", radio_btn_Customized_region));
        Thread.sleep(2000);
        clickButton(radio_btn_Customized_region);

        check_button_Ck_Area_Self_America = (WebElement) (js.executeScript("return document.querySelector(\"#Ck_Area_Self_America\")", check_button_Ck_Area_Self_America));
        Thread.sleep(2000);
        clickButton(check_button_Ck_Area_Self_America);

        btn_Btn_SetUrl_SaveArea = (WebElement) (js.executeScript("return document.querySelector(\"#Btn_SetUrl_SaveArea\")", btn_Btn_SetUrl_SaveArea));
        clickButton(btn_Btn_SetUrl_SaveArea);
        Thread.sleep(2000);
        driver.switchTo().alert().accept();

        btn_OK = (WebElement) (js.executeScript("return document.querySelector(\"#Btn_AddUrl_Ok\").click();", btn_OK));
        Thread.sleep(2000);
    }

    public void findAllBtnAddSite() throws InterruptedException {
        Thread.sleep(5000);
        listBtnsAddSite = new ArrayList<>();
        int count = 1;
        while (true) {

            btn_add_site = (WebElement) (js.executeScript("return document.querySelector(\"#Div_Client_List > table:nth-child(" + count + ") > thead > tr:nth-child(2) > td > table > tbody > tr > td:nth-child(13) > input[type=button]\")", btn_add_site));
            if (btn_add_site != null) {
                listBtnsAddSite.add(btn_add_site);
                count ++;
            }
            else
                break;
        }
    }

    public void FindAllButtonsChange()
    {
        mapAlreadySites = new HashMap<>();
        for (int i = 0; i < listBtnsAddSite.size(); i++) {
            List<WebElement> listAlreadySites = new ArrayList<>();
            for (int j = 2; j < 13; j++) {
                btn_change = (WebElement) (js.executeScript("return document.querySelector(\"#Div_Client_List > table:nth-child("+(i+1)+") > tbody > tr:nth-child("+j+") > td:nth-child(2) > table > tbody > tr > td.Click_Href > a\")",btn_change));
                if(btn_change!=null)
                {
                    listAlreadySites.add(btn_change);
                }
            }
            mapAlreadySites.put(i, listAlreadySites);
        }
    }

    private void clickButton(WebElement button) throws InterruptedException {
        button.click();
        Thread.sleep(500);
    }

    private void countRefs()
    {
        while (true)
        {
            if(!listRefs.isEmpty())
            {
                break;
            }
        }
        int count = 100;
        int num_refs = listRefs.size();
        if(num_refs>=100)
        {
            procent = 1;
            count_refs = 100;
            remains = 0;
        }
        else
        {
            procent = Math.round((float)(count/num_refs));
            remains = count - (procent*listRefs.size());
            count_refs = num_refs;
        }
    }

    private void addHrefsToDelivered() throws InterruptedException {
        for (int i = 0; i < count_refs; i++) {
            text_area_Text_SetUrl_MyUrls = (WebElement) (js.executeScript("return document.querySelector(\"#Text_SetUrl_MyUrls\");", text_area_Text_SetUrl_MyUrls));
            text_area_Text_SetUrl_MyUrls.clear();
            text_area_Text_SetUrl_MyUrls.sendKeys(listRefs.get(i));
            Thread.sleep(500);
            text_area_Text_SetUrl_MyUrlsPercent = (WebElement) (js.executeScript("return document.querySelector(\"#Text_SetUrl_MyUrlsPercent\");", text_area_Text_SetUrl_MyUrlsPercent));
            if(remains>0) {
                text_area_Text_SetUrl_MyUrlsPercent.clear();
                Thread.sleep(250);
                text_area_Text_SetUrl_MyUrlsPercent.sendKeys(String.valueOf(procent+1));
                remains--;
            }
            else
            {
                text_area_Text_SetUrl_MyUrlsPercent.clear();
                Thread.sleep(250);
                text_area_Text_SetUrl_MyUrlsPercent.sendKeys(String.valueOf(procent));
            }
            Thread.sleep(500);
            btn_Btn_SetUrl_AddUrls = (WebElement) (js.executeScript("return document.querySelector(\"#Btn_SetUrl_AddUrls\").click();", btn_Btn_SetUrl_AddUrls));
            Thread.sleep(500);
        }
        btn_Btn_SetUrl_SaveUrls = (WebElement) (js.executeScript("return document.querySelector(\"#Btn_SetUrl_SaveUrls\")", btn_Btn_SetUrl_SaveUrls));
        clickButton(btn_Btn_SetUrl_SaveUrls);
        driver.switchTo().alert().accept();
    }
}
