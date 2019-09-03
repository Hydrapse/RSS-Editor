package com.tf414.app.rsseditor.util;
import java.awt.Window;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

public class TreeListener implements TreeSelectionListener {
	private JTree tree=null;
	private JFrame window=null;
	
	public TreeListener(JTree tree,JFrame window){
		this.tree=tree;
		this.window=window;
	}	
	
	@Override
	public void valueChanged(TreeSelectionEvent e) {
		// TODO Auto-generated method stub

        if(e.getSource()==tree)
        {
            FriTreeNode node=(FriTreeNode)tree.getLastSelectedPathComponent();
            if(node.isLeaf())
            {
                String str=node.getTitle();
                
                System.out.println(str);
                window.setVisible(false);
            }
            else
            {
               
            }
        }
    }
	}


