package utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import static utils.Const.SITE_FOR_PARSING;

public class Pars {

    private ArrayList<Element> listElements;
    private ArrayList<String> listHrefs;

    public ArrayList<String> takeListOfRefs() throws IOException {
        Document document = Jsoup.connect(SITE_FOR_PARSING).proxy("192.168.0.254", 2121).get();
        Elements href = document.select("."+Const.CLASS);
        listElements = new ArrayList<>();
        listElements.addAll(href);
        createListHrefs();
        return listHrefs;
    }

    private void createListHrefs()
    {
        listHrefs = new ArrayList<>();
        listElements.forEach(x-> listHrefs.add(x.baseUri() + x.attributes().get("href")));
    }

}
