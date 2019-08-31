package com.tf414.app.rsseditor.ui.subscribemenu;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.SwingConstants;

import com.tf414.app.rsseditor.model.RSSChannel;

//RightJList的渲染器
public class ChannelCellRenderer extends DefaultListCellRenderer{
	
	private static final long serialVersionUID = 1L;
	
	private boolean flag = true;
	
	@Override
	public Component getListCellRendererComponent(JList<? extends Object> list,
	    	Object value, int index, boolean isSelected, boolean cellHasFocus){
		if(value instanceof RSSChannel){
			RSSChannel channel = (RSSChannel) value;
			try{
				//拿到频道的图标URL
				Icon icon = new ImageIcon(channel.getLogoPath());
				//设置图标大小, 为20x20(mac下）
//				icon.setImage(icon.getImage().getScaledInstance(SubscribeMenu.width/25, SubscribeMenu.width/25, Image.SCALE_DEFAULT));
				setIcon(icon);
				//a.这里可以用html来控制字体的颜色
				String text1 = "<html><body color=\"red\">"+channel.getName()+"</body></html>";
				//b.也可以通过设置前景色来改变字体颜色
//				setForeground(Color.blue);
				String text2 = channel.getName();
				setText(text2);
				//设置背景色
//				setBackground(Color.gray);
				//设置文本的水平和垂直位置:比如（右上）
				setVerticalTextPosition(SwingConstants.TOP);
				setHorizontalTextPosition(SwingConstants.RIGHT);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		flag = isSelected;
		if(isSelected){
			setBackground(Color.BLUE);
			if(index == 0){
				System.out.println("第一个被选择了，注意鼠标按下算一次，鼠标松开算一次，只有选择发生改变时才会触发，重复点击无效果");
//				if(flag) {
//					setBackground(Color.black);
//					flag = false;
//				}
//				else {
//					setBackground(Color.white);
//					flag = true;
//				}
			}
		}
		if(cellHasFocus){
			System.out.println("???");
//			if(flag)
//				setBackground(Color.gray);
		}
		return this;
    }
	//重绘List中选中部分的背景  
	@Override
    public void paintComponent(Graphics g) {  
		System.out.println(getIcon());
        Color bColor = getBackground();  
        Icon icon = getIcon();  
        g.setColor(bColor);  
        int offset = 0;  
        if (icon != null && getText() != null)  
            offset = (icon.getIconWidth() + getIconTextGap());  
        g.fillRect(offset, 0, getWidth() - 1 - offset, getHeight() - 1);  
        if (flag) {  
            g.setColor(Color.red);  
            g.draw3DRect(offset, 0, getWidth() - 1 - offset, getHeight() - 1, true);  
        }  
        super.paintComponent(g);  
    }  

}
