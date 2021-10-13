package utils;

import org.openqa.selenium.Cookie;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GetSettings {
    private Scanner sc;
    private Cookie cookie;


    public void getSettingsFromFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("settings.txt"));

        while(sc.hasNextLine())
        {
            String all_line = sc.nextLine();
            if(all_line.contains("cookie")) {
                String name = all_line.substring(all_line.indexOf("-")+1, all_line.indexOf("="));
                System.out.println(name);
                String value = all_line.substring(all_line.indexOf("=") + 1);
                System.out.println(value);
                cookie = new Cookie(name, value);
            }
            if(all_line.contains("start_page"))
            {
                Const.START_PAGE = all_line.substring(all_line.indexOf("-")+1);
            }
            if(all_line.contains("code"))
            {
                Const.CODE = all_line.substring(all_line.indexOf("-")+1);
            }
            if(all_line.contains("url"))
            {
                Const.URL = all_line.substring(all_line.indexOf("-")+1);
            }
            if (all_line.contains("site_for_pars"))
            {
                Const.SITE_FOR_PARSING = all_line.substring(all_line.indexOf("-")+1);
            }
            if(all_line.contains("class"))
            {
                Const.CLASS = all_line.substring(all_line.indexOf("-")+1);
            }
        }

        sc.close();

    }


    public Cookie getCookie() {
        return cookie;
    }

    public static void main(String[] args) throws FileNotFoundException {
        GetSettings gs = new GetSettings();
        gs.getSettingsFromFile();
        System.out.println(Const.START_PAGE);
        System.out.println(Const.CLASS);
        System.out.println(Const.URL);
        System.out.println(Const.CODE);
        System.out.println(Const.SITE_FOR_PARSING);
    }
}
