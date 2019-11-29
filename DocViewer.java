package dp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JList;

import org.jsoup.nodes.Document;

public class DocViewer {
	Document document=null;
	JFrame frame = null;
	JPanel frame_panel = null;
	JPanel title_panel = null;
	JPanel tag_list_panel = null;
	JPanel contents_list_panel = null;
	JTextArea contents_area = null;
	JScrollPane tag_scroll = null;
	JList<String> combo = null;
	DocViewer(Document doc){
		document = doc;
		frame = new JFrame();
		frame_panel = new JPanel();
		tag_list_panel = new JPanel();
		contents_list_panel = new JPanel();
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
		title_panel.setBackground(new Color(210, 210, 210));
		tag_list_panel.setBackground(new Color(200, 200, 200));
		String [] fruits= {"apple", "banana", "kiwi", "mango", "pear", "peach",
				"berry", "strawberry", "blackberry","apple", "banana", "kiwi", "mango", "pear", "peach",
				"berry", "strawberry", "blackberry","apple", "banana", "kiwi", "mango", "pear", "peach",
				"berry", "strawberry", "blackberry","apple", "banana", "kiwi", "mango", "pear", "peach",
				"berry", "strawberry", "blackberry","apple", "banana", "kiwi", "mango", "pear", "peach",
				"berry", "strawberry", "blackberry","apple", "banana", "kiwi", "mango", "pear", "peach",
				"berry", "strawberry", "blackberry"};
		combo = new JList<String>(fruits);
		contents_area = new JTextArea();
		
		contents_area.setAlignmentX(JTextArea.LEFT_ALIGNMENT);
		contents_area.setPreferredSize(new Dimension(640, 540));
		contents_area.append("test string1 123341341324\n");
		contents_area.append("test string2\n");
		contents_area.append("test string3\n");
		contents_list_panel.setBackground(new Color(180, 180, 180));
		contents_list_panel.add(contents_area);
		JScrollPane scroll = new JScrollPane(combo);
		scroll.setPreferredSize(new Dimension(150, 540));
		tag_list_panel.add(scroll);
		
		frame.add(title_panel, BorderLayout.NORTH);
		frame.add(tag_list_panel, BorderLayout.WEST);
		frame.add(contents_list_panel, BorderLayout.CENTER);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
