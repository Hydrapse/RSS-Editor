package com.tf414.app.rsseditor.ui;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

import com.tf414.app.rsseditor.util.AutoadaptWindowSize;
import com.tf414.app.rsseditor.util.ImageAdaptive;
import com.tf414.app.rsseditor.util.SearchTextField;

public class MainMenu {
	
	private JFrame topWindow = null;	//主窗口
	
	private Container container = null; //主容器
	
	private JPanel menu = null;			//菜单栏
	
	private JPanel channelList = null;	//频道栏

	private JButton SubscribeMenu = null;  //
	
	private JButton keepQuiet = null;   //静音
	
	private JButton search = null;	//搜索
	
	private JButton expendOrShrink = null; //扩展或收缩
	
	JTextField searchTextField = null; //搜索文本框
	
	
	public MainMenu() 
	{
		this.topWindow = new JFrame();
		
		this.menu = new JPanel();
		
		this.channelList = new JPanel();
		
		this.initMenu();

		this.addMenuButtonListener();
		
		this.setWindowSizeAndLocation();
		
		this.channelList.add(new JLabel("hello world"));
		this.channelList.setBackground(Color.white);
		this.topWindow.setTitle("RSS");
		
//		this.topWindow.setResizable(false);
		this.topWindow.setVisible(true);
		this.topWindow.setLocationRelativeTo(null);	
	}

	public void setWindowSizeAndLocation() {
		
		int[] lengthConfig = AutoadaptWindowSize.getMainMenuGeometry();
		
		this.topWindow.setSize(lengthConfig[2], lengthConfig[3]);
		
		this.topWindow.setLayout(null);
		
		this.menu.setBounds(0, 0, lengthConfig[2],(int)(lengthConfig[2]*0.26));

		this.channelList.setBounds(0,(int)(lengthConfig[2]*0.26),lengthConfig[2],this.topWindow.getHeight()-(int)(lengthConfig[2]*0.26));

		this.topWindow.add(menu);
		this.topWindow.add(channelList);
		
	}
	
	public void initMenu() {
		//-------------菜单栏中按钮以及搜索文本框的初始化---------------------------------

		ImageIcon SubscribeMenuImage = ImageAdaptive.createAutoAdjustIcon("./photos/图片7.jpg",false);
		this.SubscribeMenu = new JButton(SubscribeMenuImage);
		this.SubscribeMenu.setPreferredSize(new Dimension(30,30));//设置按钮大小
//		this.SubscribeMenu.setContentAreaFilled(false);//设置按钮透明
//		this.SubscribeMenu.setBorderPainted(false);//设置按钮边框
		
		
		ImageIcon keepQuietImage = new ImageIcon(/*图片路径*/);
		this.keepQuiet = new JButton(keepQuietImage);
		this.keepQuiet.setPreferredSize(new Dimension(30,30));//设置按钮大小
//		this.keepQuiet.setContentAreaFilled(false);//设置按钮透明
//		this.keepQuiet.setBorderPainted(false);//设置按钮边框
		
		
		ImageIcon searchImage = new ImageIcon(/*图片路径*/);
		this.search = new JButton(searchImage);
		this.search.setPreferredSize(new Dimension(30,30));//设置按钮大小
//		this.search.setVisible(false);
//		this.search.setContentAreaFilled(false);//设置按钮透明
//		this.search.setBorderPainted(false);//设置按钮边框
		
		ImageIcon expendOrShrinkImage = new ImageIcon(/**/);
		this.expendOrShrink = new JButton(expendOrShrinkImage);
		this.expendOrShrink.setPreferredSize(new Dimension(30,30));//设置按钮大小
//		this.expendOrShrink.setContentAreaFilled(false);//设置按钮透明
//		this.expendOrShrink.setBorderPainted(false);//设置按钮边框
		
		
		this.searchTextField=new JTextField();
		this.searchTextField.setPreferredSize(new Dimension(100,30));
		this.searchTextField.setFont(new Font("楷体",Font.BOLD,16));   //设置文本字体及字数限制
		this.searchTextField.setVisible(false);
		
		this.menu.add(SubscribeMenu);
		this.menu.add(keepQuiet);
		this.menu.add(search);
		this.menu.add(expendOrShrink);
		this.menu.add(searchTextField);
		this.menu.setLayout(new FlowLayout(FlowLayout.LEADING,20,20));
		this.menu.setBackground(new Color(141,191,216));
		this.menu.setPreferredSize(new Dimension(this.topWindow.getWidth(),10));
	
	}
	
	public void initChannelList() {
		this.channelList = new JPanel();
//		MutableTreeNode root = new DefaultMutableTreeNode("百度");
	}
	
	public void addMenuButtonListener() {
		
//		this.SubscribeMenu.addActionListener();
		
//		this.keepQuiet.addActionListener();
		
		this.search.addActionListener(isShowSearchTextField());
		
//		this.expendOrShrink.addActionListener(isShowSearchTextField());
		
	}
	
	
	public static void main(String[] args) 
	{
		MainMenu window = new MainMenu();
	}
	
	
	
	
	//----------------事件监听--------------
	private ActionListener isShowSearchTextField() {
		ActionListener isShow = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
			 	if(searchTextField.isVisible()) {

					searchTextField.setVisible(false);

			 	}
			 	else {
					searchTextField.setVisible(true);
			 	}
			}	
			
		};
		return isShow;
	}
	
	
	
	//-----------------------------------
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


}

