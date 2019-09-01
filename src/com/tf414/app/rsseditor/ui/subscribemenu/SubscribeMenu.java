package com.tf414.app.rsseditor.ui.subscribemenu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.tf414.app.rsseditor.data.RSSChannel;
import com.tf414.app.rsseditor.data.RSSLabel;
import com.tf414.app.rsseditor.util.AutoadaptWindowSize;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;

public class SubscribeMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	//窗口的长宽
	static int width;
	static int height;
	
	private JPanel contentPane;
	private JList<RSSLabel> labelList;
	private JPanel leftPanel;
	private JPanel buttonPanel;
	
	private JPanel rightPanel;
	private JList<RSSChannel> channelList;
	private ChannelListModel chlistModel;

	//仅供测试
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SubscribeMenu frame = new SubscribeMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SubscribeMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		int[] geo = AutoadaptWindowSize.getSubscribeMenuGeometry();
		setBounds(geo[0], geo[1], geo[2], geo[3]);
		width = geo[2];
		height = geo[3];
		
		initContentPane();//初始化窗口
	}
	
	private void initContentPane() {
		//最底层面板
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(height/11, width/20, height/22, width/20));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(width/20, 0));
//		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		
		//装载左面板
		leftPanel = new JPanel();
		leftPanel.setPreferredSize(new Dimension((int)(width*0.3),0));
		contentPane.add(leftPanel, BorderLayout.WEST);
		initLeftPanel();

		//装载右面板
		rightPanel = new JPanel();
		contentPane.add(rightPanel, BorderLayout.CENTER);
		initRightPanel();

	}
	
	private void initLeftPanel() {
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		leftPanel.setBorder(new EmptyBorder(height/15, 0, 0, 0));
		
		//装载标签面板
		labelList = new JList<RSSLabel>();
		JScrollPane scrollPane = new JScrollPane(labelList);
//		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new Dimension(0, (int)(height*0.65)));
		leftPanel.add(scrollPane);
	
		//装载按钮面板
		buttonPanel = new JPanel();
		leftPanel.add(buttonPanel);
		initButtonPanel();
	
	}
	
	private void initButtonPanel() {
		buttonPanel.setLayout(new BorderLayout(width/50, 0));
		buttonPanel.setBorder(new EmptyBorder(height/35, height/35, 0, (int)(height/4.4)));
		
		JButton btnNewButton = new JButton("+");
		btnNewButton.setPreferredSize(new Dimension(width/20, width/20));
		JButton btnNewButton_2 = new JButton("-");
		btnNewButton_2.setPreferredSize(new Dimension(width/20, width/20));
		
		buttonPanel.add(btnNewButton, BorderLayout.WEST);
		buttonPanel.add(btnNewButton_2, BorderLayout.EAST);
	}
	
	private void initRightPanel() {
		rightPanel.setLayout(new BorderLayout(0, 0));
		chlistModel = new ChannelListModel();
		//设置模型
		channelList = new JList<RSSChannel>(chlistModel);
		//设置自定义渲染器
		channelList.setCellRenderer(new ChannelCellRenderer());
		channelList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//一次只能选一个
		JScrollPane scrollPane = new JScrollPane(channelList);
		rightPanel.add(scrollPane, BorderLayout.CENTER);
		
		RSSChannel test1 = new RSSChannel("百度", "www.baidu.com", "logoPath/example.jpg");
		RSSChannel test2 = new RSSChannel("百度2", "www.baidu.com", "logoPath/example.jpg"); 

		chlistModel.addElement(test1);
		chlistModel.addElement(test2);
	}
}
