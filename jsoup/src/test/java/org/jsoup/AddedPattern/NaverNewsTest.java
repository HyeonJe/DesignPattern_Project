package org.jsoup.AddedPattern;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NaverNewsTest {
    NaverNews naver;
    @Before
    public void initailize(){
        naver = new NaverNews();
    }
    @Test
    public void createDoc() {
    }

    @Test
    public void fetchElements() {
    }

    @Test
    public void dealData() {
    }

    @Test
    public void getDoc() {
        assertEquals(new NaverNews().getDoc(), naver.getDoc());
    }
}