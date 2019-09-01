package com.tf414.app.rsseditor.ui.subscribemenu;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;

import com.tf414.app.rsseditor.model.RSSChannel;

//用来提供ListMode对象
public class ChannelListModel extends DefaultListModel<RSSChannel>{

	private static final long serialVersionUID = 1L;
	private List<RSSChannel> channelList = new ArrayList<RSSChannel>();
	
	public void addElement(RSSChannel channel){
		this.channelList.add(channel);
	}
	public int getSize(){
    	return channelList.size();
    }
    public RSSChannel getElementAt(int index){
		return channelList.get(index);
	}

}
