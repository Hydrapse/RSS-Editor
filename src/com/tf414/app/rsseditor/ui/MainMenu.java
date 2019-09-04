package com.tf414.app.rsseditor.ui;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.tree.TreeSelectionModel;

import com.tf414.app.rsseditor.data.REDatabase;
import com.tf414.app.rsseditor.data.RSSChannel;
import com.tf414.app.rsseditor.data.RSSItem;
import com.tf414.app.rsseditor.data.RSSLabel;
import com.tf414.app.rsseditor.util.AutoadaptWindowSize;
import com.tf414.app.rsseditor.util.FriTreeNode;
import com.tf414.app.rsseditor.util.FriTreeRender;
import com.tf414.app.rsseditor.util.ImageAdaptive;
import com.tf414.app.rsseditor.util.NoneFrame;
import com.tf414.app.rsseditor.util.Tree;
import com.tf414.app.rsseditor.util.TreeLeftListener;


public class MainMenu {
	
	private static List<RSSLabel> labelList = null;
	
	private REDatabase database = null;
	
	private NoneFrame topWindow = null;	//主窗口
	
	private JPanel top =null;
	
	private JPanel menu = null;			//菜单栏
	
	private JPanel body = null;
	
	private JScrollPane itemList = null;
	
	private JScrollPane channelList = null;	//频道栏
	
	private JButton setting=null;
	
	private JButton refresh=null;
	
	private JButton tolikeMenu=null;

	private JButton SubscribeMenu = null;  //
	
	private JButton keepQuiet = null;   //静音
	
	private JButton search = null;	//搜索
	
	private JButton expendOrShrink = null; //扩展或收缩
	
	private JTextField searchTextField = null; //搜索文本框
	
	private List<RSSChannel> channels = null;
	
	private Tree channelTree = null;
	
	private Tree likeItemTree = null;
	
	public MainMenu() throws SQLException 
	{
		this.topWindow = new NoneFrame(AutoadaptWindowSize.getMainMenuGeometry()[2],AutoadaptWindowSize.getMainMenuGeometry()[3]);
		
		this.menu = new JPanel();
		
		this.initMenu();

		this.addMenuButtonListener();
		
		this.initChannelList();
		
		this.initLikeItemTree();
		
		this.initBody();
		
		this.setWindowSizeAndLocation();
		
		JLabel blank = new JLabel();
		blank.setBounds(0, 0, topWindow.getWidth(),topWindow.getHeight());
		blank.setIcon(ImageAdaptive.createAutoAdjustIcon("./icon/bg.PNG",false));
		blank.setBackground(null);
		this.topWindow.add(blank);
		
//		this.topWindow.setUndecorated(true);
		this.topWindow.setBackground(null);
		this.topWindow.setResizable(false);
		this.topWindow.setVisible(true);
		this.topWindow.setLocationRelativeTo(null);	


	}

	public void setWindowSizeAndLocation() {
		
		int[] lengthConfig = AutoadaptWindowSize.getMainMenuGeometry();
		
//		this.topWindow.setBounds(0, 0, lengthConfig[2],lengthConfig[3]);
		
		this.topWindow.setLayout(null);
		
		this.menu.setBounds(0, 0, lengthConfig[2],(int)(lengthConfig[2]*0.26));

		this.body.setBounds(0,(int)(lengthConfig[2]*0.26),lengthConfig[2],lengthConfig[3]-(int)(lengthConfig[2]*0.26));


		this.topWindow.add(menu);
		this.topWindow.add(body);
		
	}
	
	public void initMenu() {
		
		ImageIcon settingImage = ImageAdaptive.createAutoAdjustIcon("./icon/setting.PNG",false);
		this.setting = new JButton(settingImage);
		this.setting.setPreferredSize(new Dimension(30,30));//设置按钮大小
		this.setting.setContentAreaFilled(false);//设置按钮透明
		this.setting.setBorderPainted(false);//设置按钮边框
		
		ImageIcon refreshImage = ImageAdaptive.createAutoAdjustIcon("./icon/refresh.PNG",false);
		this.refresh = new JButton(refreshImage);
		this.refresh.setPreferredSize(new Dimension(30,30));//设置按钮大小
		this.refresh.setContentAreaFilled(false);//设置按钮透明
		this.refresh.setBorderPainted(false);//设置按钮边框
		
		ImageIcon tolikeMenuImage = ImageAdaptive.createAutoAdjustIcon("./icon/mark.PNG",false);
		this.tolikeMenu = new JButton(tolikeMenuImage);
		this.tolikeMenu.setPreferredSize(new Dimension(30,30));//设置按钮大小
		this.tolikeMenu.setContentAreaFilled(false);//设置按钮透明
		this.tolikeMenu.setBorderPainted(false);//设置按钮边框


		ImageIcon SubscribeMenuImage = ImageAdaptive.createAutoAdjustIcon("./icon/sub.PNG",false);
		this.SubscribeMenu = new JButton(SubscribeMenuImage);
		this.SubscribeMenu.setPreferredSize(new Dimension(30,30));//设置按钮大小
		this.SubscribeMenu.setContentAreaFilled(false);//设置按钮透明
		this.SubscribeMenu.setBorderPainted(false);//设置按钮边框
		
		
		ImageIcon keepQuietImage = ImageAdaptive.createAutoAdjustIcon("./icon/quite.PNG",false);
		this.keepQuiet = new JButton(keepQuietImage);
		this.keepQuiet.setPreferredSize(new Dimension(30,30));//设置按钮大小
		this.keepQuiet.setContentAreaFilled(false);//设置按钮透明
		this.keepQuiet.setBorderPainted(false);//设置按钮边框
		
		
		ImageIcon searchImage = ImageAdaptive.createAutoAdjustIcon("./icon/search.PNG",false);
		this.search = new JButton(searchImage);
		this.search.setPreferredSize(new Dimension(30,30));//设置按钮大小
		this.search.setContentAreaFilled(false);//设置按钮透明
		this.search.setBorderPainted(false);//设置按钮边框
		
		ImageIcon expendOrShrinkImage = ImageAdaptive.createAutoAdjustIcon("./icon/expand.PNG",false);
		this.expendOrShrink = new JButton(expendOrShrinkImage);
		this.expendOrShrink.setPreferredSize(new Dimension(30,30));//设置按钮大小
		this.expendOrShrink.setContentAreaFilled(false);//设置按钮透明
		this.expendOrShrink.setBorderPainted(false);//设置按钮边框
		
		
		this.searchTextField=new JTextField();
		this.searchTextField.setPreferredSize(new Dimension(100,30));
		this.searchTextField.setFont(new Font("楷体",Font.BOLD,16));   //设置文本字体及字数限制
		this.searchTextField.setVisible(false);
		
		this.menu.add(setting);
		this.menu.add(refresh);
		this.menu.add(tolikeMenu);
		this.menu.add(SubscribeMenu);
		this.menu.add(keepQuiet);
		this.menu.add(search);
		this.menu.add(expendOrShrink);
		this.menu.add(searchTextField);
		this.menu.setLayout(new FlowLayout(FlowLayout.LEADING,20,20));
		this.menu.setBackground(new Color(141,191,216));
		this.menu.setPreferredSize(new Dimension(this.topWindow.getWidth(),10));
	
	}
	
	public void initBody() {
		body = new JPanel();
		
		body.setLayout(new CardLayout());
		
		body.add(channelList,"card1");
		
		body.add(itemList,"card2");
		
	}
	
	public void initChannelList() throws SQLException {
//		数据库
		REDatabase.init();
		database = REDatabase.getInstance();
		List<HashMap> channels = database.selectAllChannel();

		FriTreeNode rootNode = new FriTreeNode("root",0);
		for(int i=0 ; i<channels.size();++i) {
			HashMap channel=channels.get(i);
			 FriTreeNode channelNode = new FriTreeNode((String)(channel.get("name")),(int)(channel.get("channelID")));
			 
			 List<HashMap> items = database.selectChannelItems((int)(channel.get("channelID")));

			 for (int j=0 ; j<items.size() ; ++j) {
				 HashMap item = items.get(j);
				 FriTreeNode itemNode = new FriTreeNode((String)(item.get("title")),(int)(channel.get("channelID")),ImageAdaptive.createAutoAdjustIcon((String)(channel.get("logoPath"))));
				 channelNode.addchild(itemNode);
			 }
			 rootNode.addchild(channelNode);
		}

		channelTree = new Tree(rootNode);
		channelTree.setBackground(Color.white);
		channelTree.setRootVisible(false);
		channelTree.setCellRenderer(new FriTreeRender());

		
		channelTree.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 24));
		channelTree.setRowHeight(100);
		channelTree.setToggleClickCount(1);
		channelTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		channelTree.putClientProperty("JTree.lineStyle", "Horizontal");
		
		TreeLeftListener listener = new TreeLeftListener(channelTree,topWindow);
		this.channelTree.addTreeSelectionListener(listener);
		this.channelList = new JScrollPane(channelTree);
		channelList.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); 
	}
	
	public void initLikeItemTree() throws SQLException {
		database = REDatabase.getInstance();
		List<HashMap> like = database.selectHasRead();


		FriTreeNode rootNode = new FriTreeNode("root",0);
		for(int i=0 ; i<like.size();++i) {
			 HashMap channel = like.get(i);
			 
			 FriTreeNode channelNode = new FriTreeNode((String)(channel.get("name")),(int)(channel.get("channelID")));
		
			 List<HashMap> items = database.selectChannelItems((int)channel.get("channelID"));
			 
			 for (int j=0 ; j<items.size() ; ++j) {
				 HashMap item = items.get(j);
				 FriTreeNode itemNode = new FriTreeNode((String)(item.get("title")),(int)(channel.get("channelID")),ImageAdaptive.createAutoAdjustIcon((String)(channel.get("logoPath"))));
				 channelNode.addchild(itemNode);
			 }
			 rootNode.addchild(channelNode);
		}

		likeItemTree = new Tree(rootNode);
		likeItemTree.setBackground(Color.white);
		likeItemTree.setCellRenderer(new FriTreeRender());
		likeItemTree.setRootVisible(false);

		
		likeItemTree.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 24));
		likeItemTree.setRowHeight(100);
		likeItemTree.setToggleClickCount(1);
		likeItemTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		likeItemTree.putClientProperty("JTree.lineStyle", "Horizontal");
		
		TreeLeftListener listener = new TreeLeftListener(likeItemTree,topWindow);
		this.likeItemTree.addTreeSelectionListener(listener);
		this.itemList=new JScrollPane(likeItemTree);
		itemList.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); 
	}
	
	public void setLabelList(List<RSSLabel> labelList) {
		this.labelList=labelList;
	}
	
	public void initLabelListWindow() {
		FriTreeNode rootNode = new FriTreeNode("root",0);
		for(int i=0 ; i<labelList.size();++i) {
			RSSLabel tempLabel = labelList.get(i);
			for(int j=0 ; j <tempLabel.getChannelList().size() ; ++j) {
				RSSChannel tempChannel = tempLabel.getChannelList().get(i);
				FriTreeNode channelNode = new FriTreeNode(tempChannel.getName(),tempChannel.getId());
				for(int m=0 ; m<tempChannel.getItems().size() ; ++m) {
					RSSItem tempItem = tempChannel.getItems().get(i);
					FriTreeNode ItemNode = new FriTreeNode(tempItem.getTitle(),tempChannel.getId(),ImageAdaptive.createAutoAdjustIcon(tempChannel.getLogoPath()));
					channelNode.addchild(ItemNode);
				}
				rootNode.addchild(channelNode);
			}
		}
		
		Tree selectTree = new Tree(rootNode);
		JScrollPane selectRSSList=new JScrollPane(selectTree);
		body.add(selectRSSList,"card3");
		CardLayout c = (CardLayout)(body.getLayout());
		c.show(body, "card3");
	}
	
	public void addMenuButtonListener() {
		
		this.tolikeMenu.addActionListener(toLikeMenu());
		
		this.setting.addActionListener(toSetting());
		
		this.search.addActionListener(isShowSearchTextField());
		
		this.expendOrShrink.addActionListener(isExpandTree());
		
	}
	
	public static void main(String[] args) throws SQLException 
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
					searchTextField.repaint();
			 	}
			 	else {
					searchTextField.setVisible(true);
			 	}
			}	
			
		};
		return isShow;
	}
	
	private ActionListener isExpandTree() {
		ActionListener isExpand = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
			 	if(channelTree.isSecondLevelExpand()) {
			 		channelTree.shrinkTree();
			 		
			 	}
			 	else {
			 		channelTree.expandTree();
			 		
			 	};
			 	if(likeItemTree.isSecondLevelExpand()) {
			 		likeItemTree.shrinkTree();
			 	}
			 	else {
			 		likeItemTree.expandTree();
			 	}
			}	
			
		};
		return isExpand;
	}
		
	private ActionListener toLikeMenu() {
		ActionListener toLike = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				
				CardLayout card=(CardLayout)(body.getLayout());
				card.next(body);
			}	
			
		};
		return toLike;
		
	}
	private ActionListener toSetting() {
		ActionListener toLike = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				
				new Preferences().init();
			}	
			
		};
		return toLike;
		
	}
}

