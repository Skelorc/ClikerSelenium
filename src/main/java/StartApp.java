import cliker.WebCliker;
import utils.Pars;
import work_with_elements.WorkWithAddSite;

import java.io.FileNotFoundException;
import java.io.IOException;

import static utils.Const.START_PAGE;

public class StartApp {

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        WebCliker webCliker = new WebCliker("c:/Program Files/Google/Chrome/Application/chromedriver.exe","http://vip.ipts.com");
        WorkWithAddSite workWithAddSite = new WorkWithAddSite();
        new Thread(() -> {
            Pars p = new Pars();
            try {
                workWithAddSite.listRefs = p.takeListOfRefs();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        webCliker.start_page(START_PAGE);
        webCliker.start_page(START_PAGE);
        webCliker.start_page(START_PAGE);
        workWithAddSite.findAllBtnAddSite();
        workWithAddSite.FindAllButtonsChange();
        workWithAddSite.click_add_site();
    }
}
