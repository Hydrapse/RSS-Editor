package com.tf414.app.rsseditor.util;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import com.tf414.app.rsseditor.data.REDatabase;
import com.tf414.app.rsseditor.data.RSSChannel;
import com.tf414.app.rsseditor.data.RSSItem;
import com.tf414.app.rsseditor.ui.ReadingWindow;

import sun.nio.ch.ChannelInputStream;

public class TreeLeftListener implements TreeSelectionListener {
	private JTree tree=null;
	private JFrame window=null;
	
	public TreeLeftListener(JTree tree,JFrame window){
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
            	
                String title=node.getTitle();
                int channelId=node.getChannelID();
//        		REDatabase database = REDatabase.getInstance();
//        		List<HashMap> items=null;
//				try {
//					items = database.selectChannelItems(channelId);
//				} catch (SQLException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
////				List<RSSItem> items = null;
////				try {
////					items = database.selectItemAll();
////				} catch (SQLException e1) {
////					// TODO Auto-generated catch block
////					e1.printStackTrace();
////				}
////				System.out.println(items.size());
//        		for (int i=0;i<items.size();++i) {
////        			RSSItem item = items.get(i);
////        			System.out.println(item.getTitle());
////        			System.out.println(item.getChannel().getId());
//        			HashMap item=items.get(i);
//        			
//        		}
            }
            else
            {
               
            }
        }
    }
	}


