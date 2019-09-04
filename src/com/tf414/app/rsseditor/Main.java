package com.tf414.app.rsseditor;

import com.tf414.app.rsseditor.kernal.*;
import com.tf414.app.rsseditor.ui.MainMenu;

public class Main {
	private MainMenu mainMenu=null;
	
	public static void main(String[] args) throws Exception{
		System.out.println("Starting...");
		DOMReader.read("https://rsshub.app/005tv/zx/latest");
		System.out.println("Started complately123!");

	}
}
