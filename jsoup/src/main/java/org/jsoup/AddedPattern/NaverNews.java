package org.jsoup.AddedPattern;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class NaverNews extends DocumentToData {

    String url;
    Document doc;
    Elements elements;

    public NaverNews( ) {
        this.url = "https://news.naver.com/";
        super.getData();
    }

    @Override
    void createDoc() {
        // TODO Auto-generated method stub
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    void fetchElements() {
        // TODO Auto-generated method stub
        this.elements = this.doc.select("#ranking_100").select("li").select("a");
    }

    @Override
    void dealData() {
        // TODO Auto-generated method stub
        System.out.println("Naver Headline");

        for(Element el : elements) {
            System.out.println(el.text());
        }
        System.out.println("\n");
    }

    public Document getDoc(){
        return this.doc;
    }

}
