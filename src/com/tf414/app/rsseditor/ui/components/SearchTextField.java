package com.tf414.app.rsseditor.ui.components;

import java.awt.Graphics;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

import com.tf414.app.rsseditor.util.ImageAdaptive;
 
public class SearchTextField extends JTextField {
    private ImageIcon icon;
 
    public SearchTextField() {
        //获取当前路径下的图片
        icon = ImageAdaptive.createAutoAdjustIcon("./photos/我妻由乃.jpg", true);
        Insets insets = new Insets(0, 20, 0, 0);
        //设置文本输入距左边20
        this.setMargin(insets);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        Insets insets = getInsets();
        super.paintComponent(g);
        int iconWidth = icon.getIconWidth();
        int iconHeight = icon.getIconHeight();
        int Height = this.getHeight();
        //在文本框中画上之前图片
        icon.paintIcon(this, g, (insets.left - iconWidth)/2, (Height - iconHeight)/2);
//        icon.paintIcon(this, g, 0, 0);
    }
}
