import java.awt.Component;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;


public class MyRenderer2 extends DefaultTreeCellRenderer {
	Icon primary = new ImageIcon("image/image.gif");
	Icon varchar = new ImageIcon("image/dog.gif");
	Icon integer = new ImageIcon("image/heart.gif");
	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean selected, boolean expanded, boolean leaf, int row,
			boolean hasFocus) {
		// TODO Auto-generated method stub
		super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
		if(leaf && value.toString().contains("[P]"))
		{
			setIcon(primary);
		}
		else if(leaf && value.toString().contains("VARCHAR"))
		{
			setIcon(varchar);
		}/*
		else if(leaf && value.toString().contains("INT"))
		{
			setIcon(integer);
		}*/
		return this;
	}

}
