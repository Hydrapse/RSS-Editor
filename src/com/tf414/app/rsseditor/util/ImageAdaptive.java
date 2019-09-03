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

public class ImageAdaptive {
		
		static ImageIcon icon = null;
		
		static String localImageUrl = "./icon";
		
		static int num = (new File(localImageUrl)).list().length;
		
		static String deafultImageUrlAndName = localImageUrl+"/picture"+num+".jpg";
		
	    /**创建一个可以自适应组件大小的ImageIcon对象
	     * @param image 从<code> Image </code>对象来创建ImageIcon
	     * @param constrained 是否等比例缩放 。当为<code> true </code>时，可通过
	     *      {@link javax.swing.JComponent#setAlignmentX(float)}和
	     *      {@link javax.swing.JComponent#setAlignmentY(float)}方法设置组件对齐方式。
	     * @date  2019-08-20 */
	    public static ImageIcon createAutoAdjustIcon(Image image, boolean constrained ) {
	        icon = new ImageIcon(image) {
	            @Override
	            public synchronized void paintIcon(java.awt.Component cmp, Graphics g, int x, int y) {
	                //初始化参数
	                Point startPoint = new Point(0, 0);//默认绘制起点
	                Dimension cmpSize = cmp.getSize();//获取组件大小
	                Dimension imgSize = new Dimension(getIconWidth(), getIconHeight());//获取图像大小
	                
	                //计算绘制起点和区域
	                if(constrained) {//等比例缩放
	                    //计算图像宽高比例
	                    double ratio = 1.0*imgSize.width/imgSize.height;
	                    //计算等比例缩放后的区域大小
	                    imgSize.width = (int) Math.min(cmpSize.width, ratio*cmpSize.height);
	                    imgSize.height = (int) (imgSize.width/ratio);
	                    //计算绘制起点
	                    startPoint.x = (int) 
	                            (cmp.getAlignmentX()*(cmpSize.width - imgSize.width));
	                    startPoint.y = (int) 
	                            (cmp.getAlignmentY()*(cmpSize.height - imgSize.height));
	                } else {//完全填充
	                    imgSize = cmpSize;
	                }
	                
	                //根据起点和区域大小进行绘制
	                if(getImageObserver() == null) {
	                    g.drawImage(getImage(), startPoint.x, startPoint.y,
	                            imgSize.width, imgSize.height, cmp);
	                 } else {
	                    g.drawImage(getImage(), startPoint.x, startPoint.y,
	                            imgSize.width, imgSize.height, getImageObserver());
	                 }
	            };
	        };
	        return icon;
	    }
	    
	    /**创建一个可以自适应组件大小的Icon对象
	     * @param imgurl 为图片的路径本地或网络皆可，
	     * @param imageName 当imgurl为网络路径时，会将图片储存至本地，而imageName为图片名字
	     * @param constrained 是否等比例缩放。
	     * @date  2019-09-01 */
	    public static ImageIcon createAutoAdjustIcon(String imgurl, boolean constrained,String imageName) {
	    	if(imgurl.toString().contains("http://") ||imgurl.toString().contains("https://") ) {
	    		URL url = null;
	    		try {
	    			url = new URL(imgurl);
	    		}
	    		catch(Exception e) {
	    				e.toString();
	    		}
	    		return createAutoAdjustIcon(url,constrained,imageName);
	    	}
	    	else {
	    		return createAutoAdjustIcon(new ImageIcon(imgurl).getImage(), constrained);
	    	}

	    }

	    /**创建一个可以自适应组件大小的Icon对象
	     * @param imgurl 为图片的路径本地或网络皆可，当imgurl为网络路径程序在存储图片时，会使用默认命名，即“photosX.jpg”X为photos下的文件数量
	     * @param constrained 是否等比例缩放。
	     * @date  2019-09-01 */
	    public static ImageIcon createAutoAdjustIcon(String imageUrl, boolean constrained) {
        	String image=deafultImageUrlAndName;
        	return createAutoAdjustIcon(imageUrl,constrained,image);
	    }
	    

	    /**创建一个可以自适应组件大小的Icon对象
	     * @param imgurl 为图片的路径本地或网络皆可，
	     * @param imageName 当imgurl为网络路径时，会将图片储存至本地，而imageName为图片名字
	     * @param constrained 是否等比例缩放。
	     * @date  2019-09-01 */
	    public static ImageIcon createAutoAdjustIcon(URL url, boolean constrained,String imageUrl) {
	    	if(url.toString().contains("http://") || url.toString().contains("https://")) {
		    	try {
					URLConnection connection = url.openConnection();
					InputStream stream = connection.getInputStream();
					FileOutputStream image = new FileOutputStream(localImageUrl+"/"+imageUrl);//根本图片编号生成本地地址	
					byte[] buf = new byte[1024*8];
		            while(true) {//读取图片字节	            	
		                int len = stream.read(buf);      
		                if(len == -1) {
		                	break;     
		                }
		                image.write(buf, 0, len);//存储到本地
		            }
		            image.close();
		            Thread thread =Thread.currentThread();
		            thread.stop();
		    	}
		    	catch (MalformedURLException e) {
		    		e.printStackTrace();
	    		} catch (IOException e) {
	    			e.printStackTrace();
	    		}
		    	finally {
		    			return createAutoAdjustIcon(new ImageIcon(localImageUrl+"/"+imageUrl).getImage(), constrained);
		    	}
	    	}
	    	else {
	    		return createAutoAdjustIcon(new ImageIcon(url).getImage(), constrained);
	    	}

	   }
	    /**创建一个可以自适应组件大小的Icon对象
	     * @param imageUrl 为图片的路径本地或网络皆可，当imageUrl为网络路径程序在存储图片时，会使用默认命名，即“photosX.jpg”X为photos下的文件数量
	     * @param constrained 是否等比例缩放。
	     * @date  2019-09-01 */
	    public static ImageIcon createAutoAdjustIcon(URL imageUrl, boolean constrained) {
	    	String image=deafultImageUrlAndName;
	    	return createAutoAdjustIcon(imageUrl,constrained,image);
	    }

	    
	    /**
	     * @param url 为网络路径
	     * @param imageName 为图片命名
	     * @return 
	     */
	    public static ImageIcon createAutoAdjustIcon(String url,String imageName) {
    		return createAutoAdjustIcon(url,false,imageName);
	    }
	    /**
	     * @param url 为网络路径
	     * @return 默认命名的ImageIcon
	     */
	    public static ImageIcon createAutoAdjustIcon(String url) {
	    	String imageName = deafultImageUrlAndName;
    		return createAutoAdjustIcon(url,true,imageName);
	    }

}


