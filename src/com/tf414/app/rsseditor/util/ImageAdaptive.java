package com.tf414.app.rsseditor.util;

import java.awt.Dimension;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.ImageIcon;

public final class ImageAdaptive {

	private ImageAdaptive() {
		throw new IllegalAccessError("Tools must not have instances.");
	}
	
	static ImageIcon icon = null;

	/**
	 * 创建一个可以自适应组件大小的ImageIcon对象
	 * 
	 * @param image       从<code> Image </code>对象来创建ImageIcon
	 * @param constrained 是否等比例缩放 。当为<code> true </code>时，可通过
	 *                    {@link javax.swing.JComponent#setAlignmentX(float)}和
	 *                    {@link javax.swing.JComponent#setAlignmentY(float)}方法设置组件对齐方式。
	 * @date 2019-08-20
	 */
	public static ImageIcon createAutoAdjustIcon(Image image, boolean constrained) {
		icon = new ImageIcon(image) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 5246128578559343596L;

			@Override
			public synchronized void paintIcon(java.awt.Component cmp, Graphics g, int x, int y) {
				// 初始化参数
				Point startPoint = new Point(0, 0);// 默认绘制起点
				Dimension cmpSize = cmp.getSize();// 获取组件大小
				Dimension imgSize = new Dimension(getIconWidth(), getIconHeight());// 获取图像大小

				// 计算绘制起点和区域
				if (constrained) {// 等比例缩放
					// 计算图像宽高比例
					double ratio = 1.0 * imgSize.width / imgSize.height;
					// 计算等比例缩放后的区域大小
					imgSize.width = (int) Math.min(cmpSize.width, ratio * cmpSize.height);
					imgSize.height = (int) (imgSize.width / ratio);
					// 计算绘制起点
					startPoint.x = (int) (cmp.getAlignmentX() * (cmpSize.width - imgSize.width));
					startPoint.y = (int) (cmp.getAlignmentY() * (cmpSize.height - imgSize.height));
				} else {// 完全填充
					imgSize = cmpSize;
				}

				// 根据起点和区域大小进行绘制
				if (getImageObserver() == null) {
					g.drawImage(getImage(), startPoint.x, startPoint.y, imgSize.width, imgSize.height, cmp);
				} else {
					g.drawImage(getImage(), startPoint.x, startPoint.y, imgSize.width, imgSize.height,
							getImageObserver());
				}
			};
		};
		return icon;
	}

	/**
	 * 创建一个可以自适应组件大小的Icon对象
	 * 
	 * @param filename    指定文件名或者路径的字符串
	 * @param constrained 是否等比例缩放。当为<code> true </code>时，可通过
	 *                    {@link javax.swing.JComponent#setAlignmentX(float)}和
	 *                    {@link javax.swing.JComponent#setAlignmentY(float)}方法设置组件对齐方式。
	 * @date 2019-08-20
	 */
	public static ImageIcon createAutoAdjustIcon(String imgurl, boolean constrained) {
		if (imgurl.toString().contains("http://") || imgurl.toString().contains("https://")) {
			URL url = null;
			try {
				url = new URL(imgurl);
			} catch (Exception e) {
				e.toString();
			}
			return createAutoAdjustIcon(url, constrained);
		} else {
			return createAutoAdjustIcon(new ImageIcon(imgurl).getImage(), constrained);
		}

	}

	/**
	 * 创建一个可以自适应组件大小的ImageIcon对象
	 * 
	 * @param url         从指定的<code> URL </code>对象来创建ImageIcon
	 * @param constrained 是否等比例缩放 。当为<code> true </code>时，可通过
	 *                    {@link javax.swing.JComponent#setAlignmentX(float)}和
	 *                    {@link javax.swing.JComponent#setAlignmentY(float)}方法设置组件对齐方式。
	 * @date 2019-08-20
	 */
	@SuppressWarnings("finally")
	public static ImageIcon createAutoAdjustIcon(URL url, boolean constrained) {
		if (url.toString().contains("http://") || url.toString().contains("https://")) {
			File file = new File("./photos");
			String files[];
			files = file.list();
			int num = files.length;
			try {
				URLConnection connection = url.openConnection();
				InputStream stream = connection.getInputStream();
				FileOutputStream image = new FileOutputStream("./photos/图片" + num + ".jpg");// 根本图片编号生成本地地址
				byte[] buf = new byte[1024 * 8];
				while (true) {// 读取图片字节
					int len = stream.read(buf);
					if (len == -1) {
						break;
					}
					image.write(buf, 0, len);// 存储到本地
				}
				image.close();
				Thread thread = Thread.currentThread();
				thread.interrupt(); // TODO To 港大：请使用thread.interrupt()安全的结束线程，而不是使用过时的stop()进行危险线程操作
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				return createAutoAdjustIcon(new ImageIcon("./photos/图片" + num + ".jpg").getImage(), constrained);
			}
		} else {
			return createAutoAdjustIcon(new ImageIcon(url).getImage(), constrained);
		}

	}

	public static String createAutoAdjustIcon(URL url) {
		synchronized (icon) {
			ImageIcon icon = createAutoAdjustIcon(url, false);
			return icon.getDescription();
		}
	}
}
