package com.tf414.app.rsseditor.ui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.ScrollPaneLayout;

import org.omg.CORBA.PRIVATE_MEMBER;

import com.tf414.app.rsseditor.data.RSSChannel;
import com.tf414.app.rsseditor.data.RSSItem;
import com.tf414.app.rsseditor.kernal.RSSController;
import com.tf414.app.rsseditor.util.AutoadaptWindowSize;
import com.tf414.app.rsseditor.util.ImageAdaptive;
import com.tf414.app.rsseditor.util.TimeConv;

import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class ReadingWindow extends JFrame{

	private JFrame frame;
	private JPanel contentPane;
    private Point pressedPoint; 
    int thisItem = -1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RSSChannel testChannel = new RSSChannel("OK");
					testChannel.setLastBuildDate(TimeConv.strToDate(new String("Tue, 03 Sep 2019 16:10:45 GMT")));
					RSSItem testItem = new RSSItem(testChannel, "isOkkkkkkkkkkkkkkkkkkkk");
					testItem.setDateCreated(TimeConv.strToDate(new String("Tue, 03 Sep 2019 16:10:45 GMT")));
					testItem.setDescription("hello");
					testItem.setChannel(testChannel);
					ReadingWindow window = new ReadingWindow(testItem);
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public ReadingWindow(RSSItem infoItem) throws IOException {
		String channelName =null;
		RSSChannel pchannel =null;
		List<RSSItem> itemList =null;
//		try {
//			channelName = infoItem.getChannel().getName();
//			pchannel = RSSController.getInstance().getRSSChannel(channelName);
//			itemList = pchannel.getItems();
//		}
//		catch (Exception e){
//			System.out.print("error on take itemList from Db");
//		}
//		
//		for(int i = 0 ; i < itemList.size()-1 ; ++i) {
//			if(itemList.get(i).getTitle().equals(infoItem.getTitle())) {
//				thisItem = i;
//			}
//		}
		
		showTheInfo(infoItem,itemList);
		initialize();
		setUndecorated(true); 
		setDefaultCloseOperation(EXIT_ON_CLOSE);     
//        setBounds(100, 100, 800, 560);  

  
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	public void showTheInfo(RSSItem infoItem,List<RSSItem> itemList) throws IOException {
		int[] geo = AutoadaptWindowSize.getReadingWindowGeometry();
		int dx = geo[0];
		int dy = geo[1];
		int dh = geo[2];
		int dw = geo[3];
		setBounds(dx, dy, dh, dw); 
        contentPane = new JPanel();     
        contentPane.setBorder(null);     
        contentPane.setLayout(new BorderLayout(0, 0));     
        setContentPane(contentPane); 
        
        //topPanel on titleBar useset geo
        JPanel topPanel = new JPanel();    
        topPanel.setBackground(new Color(167,174,217));
        int width = this.getWidth();
        Dimension barSize = new Dimension(width,32);
        topPanel.setPreferredSize(barSize);
        contentPane.add(topPanel,BorderLayout.NORTH); 
        topPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0)); 
        
        //add continer on right
        JPanel liconPanel = new JPanel();
        liconPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        liconPanel.setPreferredSize(new Dimension((width/2),32));
        liconPanel.setBackground(new Color(0,0,0,0));
        topPanel.add(liconPanel);
        
        //add like
        JPanel likePanel = new JPanel();
        likePanel.setLayout(new FlowLayout(FlowLayout.LEFT,30,0));
        likePanel.setPreferredSize(new Dimension(62,32));
        likePanel.setBackground(new Color(0,0,0,0));
        liconPanel.add(likePanel);
        JButton buttonLike = new JButton("");
        buttonLike.setPreferredSize(new Dimension(32,32));
        ImageIcon likeIcon;
        if(infoItem.getChannel().isLike()) {
        	likeIcon = ImageAdaptive.createAutoAdjustIcon("./icon/5-05.png", false);
        }
        else {
        	likeIcon = ImageAdaptive.createAutoAdjustIcon("./icon/4-04.png", false);
        }
        buttonLike.setIcon(likeIcon);
        buttonLike.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//like
				ImageIcon likeIcon2;
				if(infoItem.getChannel().isLike()) {
					//db
					//end db
					infoItem.getChannel().setLike(false);
					likeIcon2 = ImageAdaptive.createAutoAdjustIcon("./icon/4-04.png", false);
					buttonLike.setIcon(likeIcon2);
				}
				else {
					//db
					//end db
					infoItem.getChannel().setLike(true);
					likeIcon2 = ImageAdaptive.createAutoAdjustIcon("./icon/5-05.png", false);
					buttonLike.setIcon(likeIcon2);
					
				}
				
			}
		});
        buttonLike.setFocusPainted(false);
        buttonLike.setBorderPainted(false);
        buttonLike.setContentAreaFilled(false);
        likePanel.add(buttonLike);
        
        
        //add back
        JPanel backPanel = new JPanel();
        backPanel.setLayout(new FlowLayout(FlowLayout.LEFT,90,0));
        backPanel.setPreferredSize(new Dimension(130,32));
        backPanel.setBackground(new Color(0,0,0,0));
        liconPanel.add(backPanel);
        JButton buttonBack = new JButton("");
        buttonBack.setPreferredSize(new Dimension(32,32));
//        File fBack = new File("./icon/back.jpg");
//        ImageIcon backIcon = new ImageIcon(fBack.getAbsolutePath());
        ImageIcon backIcon = ImageAdaptive.createAutoAdjustIcon("./icon/back.png", false);
        buttonBack.setIcon(backIcon);
        buttonBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//back
				if(thisItem<itemList.size() && thisItem>0) {
					--thisItem;
					try {
						showTheInfo(infoItem, itemList);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "There is not message on back ", "WORING", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
        buttonBack.setFocusPainted(false);
        buttonBack.setBorderPainted(false);
        buttonBack.setContentAreaFilled(false);
        backPanel.add(buttonBack);
        
        //add front
        JPanel frontPanel = new JPanel();
        frontPanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
        frontPanel.setPreferredSize(new Dimension(90,32));
        frontPanel.setBackground(new Color(0,0,0,0));
        liconPanel.add(frontPanel);
        JButton buttonFront = new JButton("");
        buttonFront.setPreferredSize(new Dimension(32,32));
        ImageIcon frontIcon = ImageAdaptive.createAutoAdjustIcon("./icon/front.png", false);
        buttonFront.setIcon(frontIcon);
        buttonFront.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//front
				if(thisItem<itemList.size()-1 && thisItem>=0) {
					--thisItem;
					try {
						showTheInfo(infoItem, itemList);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "There is not message on back ", "WORING", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
        buttonFront.setBorder(null);
        buttonFront.setBackground(null);
        buttonFront.setFocusPainted(false);
        buttonFront.setBorderPainted(false);
        buttonFront.setContentAreaFilled(false);
        frontPanel.add(buttonFront);
        
        
        //add bar on right
        JPanel riconPanel = new JPanel();
        riconPanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
        riconPanel.setPreferredSize(new Dimension((width/2),32));
        riconPanel.setBackground(new Color(0,0,0,0));
        topPanel.add(riconPanel);
        JPanel iconPanel = new JPanel();

        //add exit button
        iconPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        iconPanel.setPreferredSize(new Dimension((width/2),32));
        iconPanel.setBackground(new Color(0,0,0,0));
        riconPanel.add(iconPanel);
        JButton buttonExit = new JButton("");
        buttonExit.setPreferredSize(new Dimension(32,32));
        ImageIcon exitIcon = ImageAdaptive.createAutoAdjustIcon("./icon/exit.png", false);
        buttonExit.setIcon(exitIcon);
        buttonExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				//Exit
			}
		});
        buttonExit.setFocusPainted(false);
        buttonExit.setBorderPainted(false);
        buttonExit.setContentAreaFilled(false);
        File f1 = new File("./icon/exitOn.jpg");
//        buttonExit.setRolloverIcon(exitOnIcon);
        iconPanel.add(buttonExit);
        
        //Listener
        //use titleBar move window
        topPanel.addMouseMotionListener(new MouseMotionAdapter() {     
            @Override
            public void mouseDragged(MouseEvent e){  
            	//TODO move
            	Point point = e.getPoint();     
                Point locationPoint = getLocation();     
                            
                int x = locationPoint.x + point.x - pressedPoint.x;     
                int y = locationPoint.y + point.y - pressedPoint.y;     
                            
                setLocation(x, y); 
            }    
        });     
        topPanel.addMouseListener(new MouseAdapter() {     
            @Override
            public void mousePressed(MouseEvent e) {     
                // TODO Clik
            	pressedPoint = e.getPoint();  
            }     
        }); 
        
//the Main panel
        JPanel MainPanel = new JPanel();
        JScrollPane MainScroll = new JScrollPane();
        contentPane.add(MainScroll, BorderLayout.CENTER);
        MainScroll.setViewportView(MainPanel);
        MainPanel.setPreferredSize(new Dimension(width-60,dh-32));
        MainPanel.setBackground(new Color(242,242,242));
        
//        MainPanel.setLayout(new ScrollPaneLayout());
        
        //TitlePanel
        int TitlePanelHight = width/7+12;
        JPanel TitlePanel = new JPanel();
        TitlePanel.setPreferredSize(new Dimension(width-60,TitlePanelHight));
        TitlePanel.setBackground(new Color(242,242,242));
        MainPanel.add(TitlePanel);
        TitlePanel.setLayout(null);
        JLabel titleLabel = new JLabel(infoItem.getTitle());
        
        //Label Title
        titleLabel.setFont(new Font("title", 37, 37));
        titleLabel.setForeground(new Color(115,134,191));
        titleLabel.setBounds(53,35,600,50);
        TitlePanel.add(titleLabel);
        //Label Channel
        JLabel channelLabel = new JLabel(" "+infoItem.getChannel().getName());
        channelLabel.setOpaque(true);
        channelLabel.setBackground(new Color(115,133,198));
        channelLabel.setFont(new Font("title", 18, 18));
        channelLabel.setForeground(new Color(223,226,242));
        channelLabel.setBounds(53,115,85,30);
        TitlePanel.add(channelLabel);
        //Label date
        SimpleDateFormat turnDate = new SimpleDateFormat("yyyy/mm/dd");
        String date = turnDate.format(infoItem.getDateCreated());
        JLabel dateLabel = new JLabel(" "+date);
        dateLabel.setOpaque(true);
        dateLabel.setBackground(new Color(223,226,242));
        dateLabel.setFont(new Font("title", 16, 16));
        dateLabel.setForeground(new Color(115,133,198));
        dateLabel.setBounds(163,115,95,30);
        TitlePanel.add(dateLabel);
        
        //the artical info
        JPanel infoPanel = new JPanel();
        MainPanel.add(infoPanel);
        infoPanel.setLayout(new BorderLayout(0,0));
        infoPanel.setBackground(new Color(242,242,242));
        JEditorPane infoViewer = new JEditorPane();
        infoViewer.setPreferredSize(new Dimension(dh*5/6,dh*2));
        infoViewer.setBackground(new Color(242,242,242,0));
        infoPanel.add(infoViewer);
        infoViewer.setContentType("text/html");
        infoViewer.setText("<font sizefont size=\"6\">"+infoItem.getDescription()+"</font>");
        infoViewer.setEditable(false);
//        setAllInfo(infoItem) ;

//		Line 
        JSeparator s = new JSeparator(SwingConstants.CENTER);
        s.setBounds(45, TitlePanelHight-10, width/11*10, 50);
        TitlePanel.add(s);
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public boolean setAllInfo(RSSItem infoItem){
		try {
			
			return true;
		}
		catch(Exception e) {
			System.out.print("ReadingWindow catch info Woring");
			return false;
		}
	}
	
	
	
}


