package com.tf414.app.rsseditor.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.tree.DefaultTreeCellRenderer;

import com.tf414.app.rsseditor.util.AutoadaptWindowSize;
import com.tf414.app.rsseditor.util.ImageAdaptive;

public class Preferences {
	private JFrame topWindow = null;
	private JPanel imageFrame = null;
	private JPanel launch = null;
	private JPanel view = null;
	private JPanel control = null;
	private int[] lengthConfig=AutoadaptWindowSize.getPreferencesGeometry();

	public void init() {
		topWindow = new JFrame("Preferences");
		topWindow.setBounds(lengthConfig[0], lengthConfig[1], lengthConfig[2],lengthConfig[3]);
		topWindow.setLayout(null);
		initImageFrame();
		initLaunch();
		initView();
		initControl();
		
		
		
		topWindow.add(imageFrame);
		topWindow.add(launch);
		topWindow.add(view);
		topWindow.add(control);
		topWindow.setVisible(true);
	}
	
	public void initImageFrame() {
		imageFrame = new JPanel();
		imageFrame.setBounds(0, 0, lengthConfig[2], (int)(lengthConfig[2]/10));
		ImageIcon icon =new ImageIcon("./icon/setting.PNG");
		JLabel settingIcon = new JLabel(icon);
		imageFrame.add(settingIcon);
		imageFrame.setBackground(Color.white);
		
	}
	
	public void initLaunch() {
		launch = new JPanel();
		launch.setBounds(0,(int)(lengthConfig[2]/10), lengthConfig[2], (int)(3*lengthConfig[2]/10));	
		JLabel launchLabel = new JLabel("Launch");
		JLabel label1 = new JLabel("开机时：");
		JLabel label2 = new JLabel("默认打开方式：");
		JTextArea label3 = new JTextArea("当启动时，选择主菜单栏全部收缩，仅展现所订阅频道；选择全部展开，展现所有文章");
		JComboBox openWay = new JComboBox(); 
		JRadioButton autoOpenRssEditor = new JRadioButton("自动启动RSSEditor");
		
		launchLabel.setFont(new Font("Serif",Font.BOLD,26));
		launchLabel.setBounds((int)(lengthConfig[2]/25), (int)(lengthConfig[2]/25),(int)(lengthConfig[2]/5), (int)(lengthConfig[2]/10));
		
		label1.setFont(new Font("Serif",Font.PLAIN,12));
		label1.setBounds((int)(12*lengthConfig[2]/50),(int)(2*lengthConfig[2]/25),(int)(lengthConfig[2]/5),(int)(16*lengthConfig[2]/500));
		
		autoOpenRssEditor.setFont(new Font("Serif",Font.PLAIN,12));
		autoOpenRssEditor.setBackground(Color.white);
		autoOpenRssEditor.setContentAreaFilled(false);//设置按钮透明
		autoOpenRssEditor.setBorderPainted(false);//设置按钮边框
		autoOpenRssEditor.setBounds((int)(25*lengthConfig[2]/50), (int)(2*lengthConfig[2]/25), (int)(20*lengthConfig[2]/50), (int)(16*lengthConfig[2]/500));
		
		label2.setFont(new Font("Serif",Font.PLAIN,12));
		label2.setBounds((int)(12*lengthConfig[2]/50),(int)(74*lengthConfig[2]/500),(int)(2*lengthConfig[2]/5),(int)(16*lengthConfig[2]/500));
		

		openWay.setBounds((int)(25*lengthConfig[2]/50), (int)(74*lengthConfig[2]/500), (int)(2*lengthConfig[2]/5), (int)(20*lengthConfig[2]/500));
		openWay.addItem("全部展开");
		openWay.addItem("全部收缩");
		
		label3.setFont(new Font("Serif",Font.PLAIN,12));
		label3.setEditable(false);
		label3.setLineWrap(true);
		label3.setWrapStyleWord(true);
		label3.setBounds((int)(25*lengthConfig[2]/50), (int)(1*lengthConfig[2]/5), (int)(2*lengthConfig[2]/5), (int)(1*lengthConfig[2]/10));
		
		launch.setBackground(Color.white);
		launch.setLayout(null);
		launch.add(launchLabel);
		launch.add(label1);
		launch.add(label2);
		launch.add(openWay);
		launch.add(label3);
		launch.add(autoOpenRssEditor);
		
	}
	
	public void initView() {
		view= new JPanel();

		JLabel label1 = new JLabel("标签功能设置：");
		JLabel label2 = new JLabel("打开标签功能");
		JLabel label3 = new JLabel("窗口模式设置：");
		JComboBox windowSetting = new JComboBox();
		JTextArea label4 = new JTextArea("当选择智能工作模式时，可将阅读器主菜单附着于侧边栏，也可单独拉出；简介模式只能显示普通窗口");
		JToggleButton button =new JToggleButton();		
		view.setBounds(0,(int)(4*lengthConfig[2]/10), lengthConfig[2], (int)(3*lengthConfig[2]/10));
		
		label1.setFont(new Font("Serif",Font.PLAIN,12));
		label1.setBounds((int)(12*lengthConfig[2]/50),(int)(1*lengthConfig[2]/10),(int)(200*lengthConfig[2]/500),(int)(16*lengthConfig[2]/500));
		
		label2.setFont(new Font("Serif",Font.PLAIN,12));
		label2.setBounds((int)(25*lengthConfig[2]/50),(int)(1*lengthConfig[2]/10),(int)(200*lengthConfig[2]/500),(int)(16*lengthConfig[2]/500));
		
		
		button.setFont(new Font("Serif",Font.PLAIN,12));
		button.setBounds((int)(220*lengthConfig[2]/500),(int)(1*lengthConfig[2]/10),(int)(20*lengthConfig[2]/500),(int)(20*lengthConfig[2]/500));
		button.setBorderPainted(false);
		button.setSelectedIcon(ImageAdaptive.createAutoAdjustIcon("./icon/pressedIcon.PNG"));
		button.setIcon(ImageAdaptive.createAutoAdjustIcon("./icon/disabledIcon.PNG"));
		button.setBackground(Color.white);
	

		label3.setFont(new Font("Serif",Font.PLAIN,12));
		label3.setBounds((int)(12*lengthConfig[2]/50),(int)(77*lengthConfig[2]/500),(int)(200*lengthConfig[2]/500),(int)(16*lengthConfig[2]/500));
		
		windowSetting.setBounds((int)(25*lengthConfig[2]/50), (int)(74*lengthConfig[2]/500), (int)(2*lengthConfig[2]/5), (int)(20*lengthConfig[2]/500));
		windowSetting.addItem("智能工作模式");
		windowSetting.addItem("简介模式");

		label4.setFont(new Font("Serif",Font.PLAIN,12));
		label4.setEditable(false);
		label4.setLineWrap(true);
		label4.setWrapStyleWord(true);
		label4.setBounds((int)(25*lengthConfig[2]/50), (int)(1*lengthConfig[2]/5), (int)(2*lengthConfig[2]/5), (int)(1*lengthConfig[2]/10));
		
		
		JLabel viewLabel = new JLabel("View");
		viewLabel.setFont(new Font("Serif",Font.BOLD,26));
		viewLabel.setBounds(20, 20,100, 50);
		
		view.setLayout(null);
		view.add(viewLabel);
		view.add(label1);
		view.add(label2);
		view.add(label3);
		view.add(label4);
		view.add(button);
		view.add(windowSetting);
		
		view.setBackground(Color.white);
	}
	public void initControl() {
		control = new JPanel();
		JLabel label1=new JLabel("自动刷新时间：");
		JLabel label2=new JLabel("<html>选择浏览器<br>(当跳转链接时):</html>");
		JRadioButton button1=new JRadioButton("允许刷新旧文章");
		JRadioButton button2=new JRadioButton("允许跳转链接");
		JComboBox browserSetting = new JComboBox();
		JTextField refresh=new JTextField("五分钟");
		
		control.setBackground(Color.green);
		control.setBounds(0, (int)(7*lengthConfig[2]/10), lengthConfig[2],(int)(3*lengthConfig[2]/10)+32);

		label1.setFont(new Font("Serif",Font.PLAIN,12));
		label1.setBounds((int)(12*lengthConfig[2]/50),(int)(1*lengthConfig[2]/10),(int)(200*lengthConfig[2]/500),(int)(16*lengthConfig[2]/500));
		
		refresh.setFont(new Font("Serif",Font.PLAIN,12));
		refresh.setBounds((int)(25*lengthConfig[2]/50), (int)(1*lengthConfig[2]/10), (int)(20*lengthConfig[2]/50), (int)(16*lengthConfig[2]/500));
		
		
		button1.setBounds((int)(25*lengthConfig[2]/50), (int)(75*lengthConfig[2]/500), (int)(20*lengthConfig[2]/50), (int)(16*lengthConfig[2]/500));
		button1.setBackground(Color.white);
		
		label2.setFont(new Font("Serif",Font.PLAIN,12));
		label2.setBounds((int)(12*lengthConfig[2]/50),(int)(110*lengthConfig[2]/500),(int)(200*lengthConfig[2]/500),(int)(32*lengthConfig[2]/500));
		
		browserSetting.addItem("Safari");
		browserSetting.addItem("chrome");
		browserSetting.addItem("ie edge");
		browserSetting.setBounds((int)(25*lengthConfig[2]/50), (int)(110*lengthConfig[2]/500), (int)(2*lengthConfig[2]/5), (int)(20*lengthConfig[2]/500));
		
		button2.setBounds((int)(25*lengthConfig[2]/50), (int)(140*lengthConfig[2]/500), (int)(20*lengthConfig[2]/50), (int)(16*lengthConfig[2]/500));
		button2.setBackground(Color.white);
		
		
		JLabel controlLabel = new JLabel("Control");
		controlLabel.setFont(new Font("Serif",Font.BOLD,26));

		controlLabel.setBounds(20, 20,100, 50);
		control.setLayout(null);
		control.add(controlLabel);
		control.add(label1);
		control.add(label2);
		control.add(button1);
		control.add(button2);
		control.add(browserSetting);
		control.add(refresh);
		control.setBackground(Color.white);
	}
	
	
	
	public static void main(String[] args) {
		Preferences prederences = new Preferences();
		prederences.init();
	}
}
