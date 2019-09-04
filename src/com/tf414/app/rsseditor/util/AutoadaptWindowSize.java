package com.tf414.app.rsseditor.util;

import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

public final class AutoadaptWindowSize {
	
	private AutoadaptWindowSize() {
		throw new IllegalAccessError("Tools must not have instances.");
	}

	private static Rectangle rect = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();

	private final static int STATUS_BAR_HEIGHT = 22; // 不同操作系统的状态栏高度,Window下为38, Mac下为22
	private final static double WIDTH_PROPORTION = 1; // Windows下为1.1666,Mac下为1

	public static int[] getMainMenuGeometry() {
		int width = (int) (rect.width * 0.20833 / WIDTH_PROPORTION);
		int height = width * 2 + STATUS_BAR_HEIGHT;
		int x = (rect.width - width) / 2;
		int y = (rect.height - height) / 2;

		return new int[] { x, y, width, height };
	}

//	public static int[] getPreferencesGeometry() {
//		
//	}

	public static int[] getReadingWindowGeometry() {
		int width = (int) (rect.width * 0.55555 / WIDTH_PROPORTION);
		int height = (int) (width * 0.7) + STATUS_BAR_HEIGHT;
		int x = (rect.width - width) / 2;
		int y = (rect.height - height) / 2;

		return new int[] { x, y, width, height };
	}

	public static int[] getSubscribeMenuGeometry() {
		int width = (int) (rect.width * 0.35 / WIDTH_PROPORTION);
		int height = (int) (width * 0.66) + STATUS_BAR_HEIGHT;
		int x = (rect.width - width) / 2;
		int y = (rect.height - height) / 2;

		return new int[] { x, y, width, height };
		
	}
	
	public static int[] getPreferencesGeometry() {
		int width = (int) (rect.width / 3.76 / WIDTH_PROPORTION);
		int height = (int) (width * 1.1) + STATUS_BAR_HEIGHT;
		int x = (rect.width - width) / 2;
		int y = (rect.height - height) / 2;

		return new int[] { x, y, width, height };
		
	}

}
