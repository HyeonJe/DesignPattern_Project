package org.jsoup.AddedPattern;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DocViewer {
    Document document=null;
    JFrame frame = null;
    JPanel frame_panel = null;
    JPanel title_panel = null;
    JPanel radio_panel = null;
    ArrayList<Document> doc_list = null;
    JTextArea contents_area = null;
    JList<String> tag_area = null;
    JLabel url_label = null;
    JLabel title_label = null;

    public DocViewer(Document doc){
        frame = new JFrame();
        frame_panel = new JPanel();
        frame.setSize(840, 630);
        set_DocViewer(doc);
    }

    public DocViewer(ArrayList<Document> doc_list){
        frame = new JFrame();
        frame_panel = new JPanel();
        radio_panel = new JPanel();
        frame.setSize(840, 630);

        this.doc_list = doc_list;
        JRadioButton[] radio = new JRadioButton[doc_list.size()];

        ButtonGroup group = new ButtonGroup();
        for(int i =0; i<doc_list.size();i++){
            radio[i] = new JRadioButton(doc_list.get(i).title());
            group.add(radio[i]);
            radio_panel.add(radio[i]);
            RadioListener radiolistener = new RadioListener();
            radio[i].addActionListener(radiolistener);
        }

        radio[0].setSelected(true);
        frame_panel.add(radio_panel);
        frame.add(radio_panel, BorderLayout.SOUTH);
        set_DocViewer(doc_list.get(0));

    }

    void set_DocViewer(Document doc){
        document = doc;
        title_panel = new JPanel();
        title_panel.setLayout(new GridLayout(2,1));

        url_label = new JLabel("URL : "+doc.baseUri());
        url_label.setHorizontalAlignment(JLabel.CENTER);
        title_label = new JLabel("TITLE : "+doc.title());
        title_label.setHorizontalAlignment(JLabel.CENTER);
        title_panel.add(url_label);
        title_panel.add(title_label);
        frame_panel.setLayout(new BorderLayout());
        frame_panel.add(title_panel);
        title_panel.setBackground(new Color(255, 255, 255));

        HashMap<String, String>contents = new HashMap<String, String>();
        Elements elems = document.getAllElements();
        for (Element e : elems) {
            if (isGoodText(e.text())) {
                contents.put(e.tagName(), e.text());
            }
        }
        Set<String> tagSet = contents.keySet();
        ArrayList<String> tagarraylist = new ArrayList<String>(tagSet);
        String[] taglist = new String[tagarraylist.size()];
        for(int i = 0; i<tagarraylist.size(); i++) {
            taglist[i] = tagarraylist.get(i);
        }

        tag_area = new JList<String>(taglist);
        TagSelectionListener tagselectionlistener = new TagSelectionListener();
        tag_area.addListSelectionListener(tagselectionlistener);
        frame.add(title_panel, BorderLayout.NORTH);
        JScrollPane tag_area_scroll = new JScrollPane(tag_area);
        tag_area_scroll.setPreferredSize(new Dimension(150, 560));
        frame.add(tag_area_scroll, BorderLayout.WEST);
        contents_area = new JTextArea();
        contents_area.setAlignmentX(JTextArea.LEFT_ALIGNMENT);
        JScrollPane contents_area_scroll = new JScrollPane(contents_area);
        contents_area_scroll.setPreferredSize(new Dimension(640, 540));
        frame.add(new JScrollPane(contents_area_scroll));
        frame.setVisible(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private class RadioListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String s = e.getActionCommand();
            if(e.getSource() instanceof JRadioButton){
                JRadioButton rb = (JRadioButton) e.getSource();
                if(rb.isSelected()){
                    for(int i = 0; i<doc_list.size();i++){
                        if(s.equals(doc_list.get(i).title())){
                            update_DocViewer(doc_list.get(i));
                        }
                    }
                }
            }

        }
    }

    private class TagSelectionListener implements ListSelectionListener {

        public void valueChanged(ListSelectionEvent arg0) {
            // TODO Auto-generated method stub
            String content = new String();
            Elements elems = document.getAllElements();
            int id = 1;
            for (Element e : elems) {
                if(e.tagName().equals(tag_area.getSelectedValue())) {
                    for(int i = 0; i<e.text().length(); i++) {
                        if(e.text().charAt(i) != ' ') {
                            content += Integer.toString(id++) + " : " + e.text() + "\n";
                            break;
                        }
                    }
                }
            }
            contents_area.setText(content);
        }
    }

    void update_DocViewer(Document doc){
        title_label.setText("TITLE : "+doc.title());
        url_label.setText("URL : "+doc.baseUri());
        document = doc;
        HashMap<String, String>contents = new HashMap<String, String>();
        Elements elems = document.getAllElements();
        for (Element e : elems) {
            if (isGoodText(e.text())) {
                contents.put(e.tagName(), e.text());
            }
        }
        Set<String> tagSet = contents.keySet();
        ArrayList<String> tagarraylist = new ArrayList<String>(tagSet);
        String[] taglist = new String[tagarraylist.size()];
        for(int i = 0; i<tagarraylist.size(); i++) {
            taglist[i] = tagarraylist.get(i);
        }

        tag_area.setListData(taglist);

    }

    private boolean isGoodText(String target) {
        for(int i = 0; i<target.length(); i++) {
            if(target.charAt(i) != ' ') {
                return true;
            }
        }
        return false;
    }


}