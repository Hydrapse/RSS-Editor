package com.tf414.app.rsseditor.util;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import com.tf414.app.rsseditor.util.ImageAdaptive;

public class NoneFrame extends JFrame {
 
	private static final long serialVersionUID = 1L;
	Point pressedPoint;
	private JButton closebutton=null;
	private JButton backButton=null;
 	public NoneFrame(int width,int height) {

		this.setUndecorated(true);// 取消窗体修饰效果
		this.setBounds(0, 0, width, height);
		/**
		 * 窗体鼠标移动事件
		 */
		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) { //鼠标按下事件
				pressedPoint = e.getPoint(); //记录鼠标坐标
			}
		});
		this.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) { // 鼠标拖拽事件
				Point point = e.getPoint();// 获取当前坐标
				Point locationPoint = getLocation();// 获取窗体坐标
				int x = locationPoint.x + point.x - pressedPoint.x;// 计算移动后的新坐标
				int y = locationPoint.y + point.y - pressedPoint.y;
				setLocation(x, y);// 改变窗体位置
			}
		});
		
		this.closebutton = new JButton(ImageAdaptive.createAutoAdjustIcon("./icon/close.PNG",false));
		this.closebutton.setContentAreaFilled(false);//设置按钮透明
		this.closebutton.setBorderPainted(false);//设置按钮边框
		this.closebutton.addActionListener(new ActionListener() 
			{// 设置按钮关闭动作事件处理
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
		this.add(closebutton);
		this.closebutton.setBounds(width-25, 0, 25, 25);
	}

}