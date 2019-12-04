package dp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;
import javax.swing.JList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DocViewer {
	Document document=null;
	JFrame frame = null;
	JPanel frame_panel = null;
	JPanel title_panel = null;
	
	JTextArea contents_area = null;
	JList<String> tag_area = null;
	
	DocViewer(Document doc){
		document = doc;
		frame = new JFrame();
		frame_panel = new JPanel();
		frame.setSize(840, 630);
		title_panel = new JPanel();
		title_panel.setLayout(new GridLayout(2,1));
		JLabel url_label = new JLabel("URL : "+doc.baseUri());
		url_label.setHorizontalAlignment(JLabel.CENTER);
		JLabel title_label = new JLabel("TITLE : "+doc.title());
		title_label.setHorizontalAlignment(JLabel.CENTER);
		title_panel.add(url_label);
		title_panel.add(title_label);
		frame_panel.setLayout(new BorderLayout());
		frame_panel.add(title_panel);
		title_panel.setBackground(new Color(255, 255, 255));
		
		Elements elems = doc.getAllElements();
		ArrayList<String> tags = new ArrayList<String>();
		for (Element e : elems) {
			String target = e.tagName();
			boolean new_tag = true;
			for (String s : tags) {
				if(s == target) {
					new_tag = false;
				}
			}
			if(new_tag) {
				tags.add(target);
			}
		}
		String[] hi = new String[tags.size()];
		for(int i = 0; i<tags.size(); i++) {
			hi[i] = tags.get(i);
		}
		tag_area = new JList<String>(hi);
		

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
}
