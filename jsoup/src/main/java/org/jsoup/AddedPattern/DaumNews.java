package org.jsoup.AddedPattern;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DaumNews extends DocumentToData {

    String url;
    Document doc;
    Elements elements;

    public DaumNews() {
        this.url = "https://media.daum.net/";
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
        this.elements = this.doc.select("#cMain").select(".box_headline").select("li").select("a");
    }

    @Override
    void dealData() {
        // TODO Auto-generated method stub
        System.out.println("Daum Headline");

        for(Element el : elements) {
            System.out.println(el.text());
        }
        System.out.println("\n");
    }



}