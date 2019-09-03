package com.tf414.app.rsseditor.util;
import java.util.Enumeration;

import javax.swing.JTree;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

public class Tree extends JTree {
	
	private boolean secondNodeExpand=false;
	public Tree(TreeNode node) {
		super(node);
	}
	
	public void expandTree() {
		TreeNode node = (TreeNode)this.getModel().getRoot();
		expandOrShrink(new TreePath(node),true);
		secondNodeExpand=true;
	}
	
	private void expandOrShrink(TreePath parent,boolean expand) {

		TreeNode node = (TreeNode) parent.getLastPathComponent();
		
        if (node.getChildCount() > 0) {

            for (Enumeration e = node.children(); e.hasMoreElements();) {
                TreeNode n = (TreeNode) e.nextElement();
                TreePath path = parent.pathByAddingChild(n);
                expandOrShrink(path, expand);
            }
        }
        if (expand) {
            this.expandPath(parent);
        } else {
            this.collapsePath(parent);
        }
	}
	
	public void shrinkNode(TreeNode aNode) {
	    if (aNode.isLeaf()) {
	      return;
	    }
	    if(aNode instanceof FriTreeNode ) {
	    	TreePath path=new TreePath(((FriTreeNode)aNode).getPath());
	    this.collapsePath(path);
	    }
	    
	  }
	
	public void shrinkTree() {
		TreeNode rootNode = (TreeNode)this.getModel().getRoot();
		for(int i=0 ; i<rootNode.getChildCount() ; ++i) {
			TreeNode node = (TreeNode)rootNode.getChildAt(i);
			shrinkNode(node);
		}
		secondNodeExpand=false;
	}
	
	public boolean isSecondLevelExpand() {
		return this.secondNodeExpand;
	}
}
