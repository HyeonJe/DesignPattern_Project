package org.jsoup.examples;

import org.jsoup.AddedPattern.DaumNews;
import org.jsoup.AddedPattern.DocViewer;
import org.jsoup.AddedPattern.NaverNews;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * A simple example, used on the jsoup website.
 */
public class Wikipedia {
    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect("http://en.wikipedia.org/").get();
        log(doc.title());

        Elements newsHeadlines = doc.select("#mp-itn b a");
        for (Element headline : newsHeadlines) {
            log("%s\n\t%s", headline.attr("title"), headline.absUrl("href"));
        }
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

    private static void log(String msg, String... vals) {
        System.out.println(String.format(msg, vals));
    }
}
