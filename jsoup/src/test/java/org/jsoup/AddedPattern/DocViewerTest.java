package org.jsoup.AddedPattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class DocViewerTest {
    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect("http://en.wikipedia.org/").get();
        NaverNews naver = new NaverNews();
        DaumNews daum = new DaumNews();
        ArrayList<Document> array = new ArrayList<Document>();
        array.add(doc);
        array.add(naver.getDoc());
        array.add(daum.getDoc());

        DocViewer doc_view = new DocViewer(array);
        //doc_view.makeView();

        //Doc_Viewer view = new Doc_Viewer(naver.getDoc());

    }
}