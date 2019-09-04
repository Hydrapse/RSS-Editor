package com.tf414.app.rsseditor.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

public class FriTreeNode  implements TreeNode{

//	private String ID;//该节点的ID号
	
	private ImageIcon img=null;//节点存放图片
	
	private String title=null;//第一行文字（显示名字）
	
	private Date date=null;
	
	private int channelID=0;

	private ArrayList<TreeNode> children=null;//孩子节点
	
	private TreeNode parent;//父亲节点
	
	static public final Enumeration<TreeNode> EMPTY_ENUMERATION
    = Collections.emptyEnumeration();
	
	public FriTreeNode(String name,int channelID) {	
		this.title=name;
		this.channelID=channelID;
	}
	
	public FriTreeNode(String name,int channelID,ImageIcon img,Date date) {	
		this.title=name;
		this.img=img;
		this.channelID=channelID;
		this.date = date==null?new Date():date;
	}
	public FriTreeNode(String name,int channelID,ImageIcon img) {	
		this.title=name;
		this.img=img;
		this.channelID=channelID;
	}
	
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	
	/**
	 * @param parent the parent to set
	 */
	public void setParent(FriTreeNode parent) {
		this.parent = parent;
	}

	/**
	 * @return the img
	 */
	public ImageIcon getImg() {
		return img;
	}

	/**
	 * @param img the img to set
	 */
	public void setImg(ImageIcon img) {
		this.img = img;
	}

	public int getChannelID() {
		return this.channelID;
	}
	public void setChannelID(int id) {
		this.channelID=id;
	}
	public Date getDate() {
		if(date==null) {
			return null;
		}else{
			return date;
		}
		
	}
	public void setDate(Date date) {
		this.date=date;
	}
	
	public void addchild(FriTreeNode aChild){
		
		if(children==null){
			children=new ArrayList<TreeNode>();
		}
		children.add(aChild);
		aChild.parent=this;
	}
	/***
	 * 判断是否为根节点
	 * @return
	 */
	public boolean isroot(){
		
		return (getParent()==null);
			
	}
	
	@Override
	public TreeNode getChildAt(int childIndex) {
		if (children == null) {
            throw new ArrayIndexOutOfBoundsException("node has no children");
        }
		return children.get(childIndex) ;
	}

	@Override
	public int getChildCount() {
		if (children == null) {
            return -1 ;
        }
		return children.size();
	}

	@Override
	public TreeNode getParent() {
		
		return parent;
	}	
	@Override
	public int getIndex(TreeNode aChild) {
		
		if (aChild == null) {
            throw new IllegalArgumentException("argument is null");
        }

        if (!isNodeChild(aChild)) {
            return -1;
        }
        return children.indexOf(aChild); 	
	}

	@Override
	public boolean getAllowsChildren() {
		
		return true;
	}

	/**
	 * 判断是否是叶子节点
	 */
	@Override
	public boolean isLeaf() {
		
		return (getChildCount() ==-1)&&(getParent()!=null);
		
	}
	
//	public Enumeration<E> elements() {
//    return new Enumeration<E>() {
//        int count = 0;
//
//        public boolean hasMoreElements() {
//            return count < elementCount;
//        }
//
//        public E nextElement() {
//            synchronized (Vector.this) {
//                if (count < elementCount) {
//                    return elementData(count++);
//                }
//            }
//            throw new NoSuchElementException("Vector Enumeration");
//        }
//    };
//}
	
//	return new Enumeration<E>() {
//        int count = 0;
//
//        public boolean hasMoreElements() {
//            return count < elementCount;
//        }
//
//        public E nextElement() {
//            synchronized (Vector.this) {
//                if (count < elementCount) {
//                    return elementData(count++);
//                }
//            }
//            throw new NoSuchElementException("Vector Enumeration");
//        }
//    };

//	@SuppressWarnings("rawtypes")
//	@Override
//	public Enumeration children() {
//		if (children == null) {
//            return EMPTY_ENUMERATION;
//        } else {
//        	Iterator<TreeNode> iter = children.iterator();
//        	for(TreeNode node : children) {
//        		return node;
//        	}
//        }
//    
//	}
	@SuppressWarnings("rawtypes")
	@Override
	public Enumeration<TreeNode> children() {
		if(children !=null) {
			Vector<TreeNode> childrenV = new Vector<TreeNode>();
		Enumeration<TreeNode> e ;
		for (int i=0 ; i<children.size() ; ++i) {
			childrenV.add(children.get(i));
		}
		e=childrenV.elements();
		return e;
		}
		else {
			return null;
		}
		
	}

	 public boolean isNodeChild(TreeNode aNode) {
	        boolean retval;

	        if (aNode == null) {
	            retval = false;
	        } else {
	            if (getChildCount() == 0) {
	                retval = false;
	            } else {
	                retval = (aNode.getParent() == this);
	            }
	        }

	        return retval;
	    }
	 public TreeNode[] getPath() {
	        return getPathToRoot(this, 0);
	    } 
	 protected TreeNode[] getPathToRoot(TreeNode aNode, int depth) {
	        TreeNode[]              retNodes;

	        /* Check for null, in case someone passed in a null node, or
	           they passed in an element that isn't rooted at root. */
	        if(aNode == null) {
	            if(depth == 0)
	                return null;
	            else
	                retNodes = new TreeNode[depth];
	        }
	        else {
	            depth++;
	            retNodes = getPathToRoot(aNode.getParent(), depth);
	            retNodes[retNodes.length - depth] = aNode;
	        }
	        return retNodes;
	    }
}