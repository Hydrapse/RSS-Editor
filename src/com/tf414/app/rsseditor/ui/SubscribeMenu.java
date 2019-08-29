package com.tf414.app.rsseditor.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SubscribeMenu extends JFrame {

	private JPanel contentPane;
	public int macStatusBarHeight = 22;

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
		init();//初始化窗口
	}
	
	private void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//设置窗口大小、位置
		GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment(); 
		Rectangle rect=ge.getMaximumWindowBounds(); 
		int width = (int)(rect.width * 0.35); 
		int height = (int)(width * 0.66) + macStatusBarHeight;
		int x = (rect.width - width) / 2;
		int y = (rect.height - height) / 2;
		
		setBounds(x, y, 500, 352);//实际交付状态用下方语句
//		setBounds(x, y, width, height); 
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JButton btnNewButton = new JButton("New button");
		contentPane.add(btnNewButton, BorderLayout.NORTH);
	}

}
