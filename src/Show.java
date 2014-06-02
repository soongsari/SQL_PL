import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.TextComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.ExpandVetoException;
import javax.swing.tree.MutableTreeNode;


public class Show implements ActionListener{

	///////////////////////���� ����///////////////////////
	DefaultTableModel model;
	JTable tmptable = new JTable();
	JTable dbtable;
	JScrollPane scroll = new JScrollPane(dbtable);   //���̺� ����
	
	DefaultTreeModel tree_model;
	DefaultMutableTreeNode root,subroot;
	JTree tree;   //Ʈ������
	JScrollPane t_scroll = new JScrollPane(tree);
    JTree subtree = new JTree(subroot);

	JTabbedPane tab = new JTabbedPane();
	TextArea query = new TextArea(3, 135);  //������
	JButton run = new JButton("RUN");     //run ��ư

	Connection conn; //db ���� Ŀ�ؼ�

	JFrame frame1 = new JFrame("DB_PROJECT");

	JPanel p2 = new JPanel();
	JPanel p3 = new JPanel();
	Component test = new JPanel();
	
	String root_name; //root �̸�

	////////////////////////////////////////////////////////
	
	public Show(String v1, String v2, String v3, String v4, String v5) {  //main Ŀ�ٶ� â ���� ������

		root_name = v5;
		
		try {
			DBConn db = new DBConn(v1, v2, v3, v4, v5);
			conn = db.dbConn();
		}

		catch (Exception e1) {
			System.out.println("frame ���� " + e1.getMessage());
		}
	

		// TODO Auto-generated method stub
		frame1.setLayout(new BorderLayout());
		frame1.add(show(), BorderLayout.CENTER);
		frame1.add(description(), BorderLayout.EAST);
		frame1.add(input(), BorderLayout.SOUTH);
		frame1.add(database(), BorderLayout.WEST);
		frame1.add(button(), BorderLayout.NORTH);
		
		frame1.setSize(1000, 650);
		frame1.setVisible(true);

	}

	public void addtable(Vector<Object> list, Vector<Object> vec) {// SQL�� ��������� �� ���̺� ����� �Լ�
																	
		model = new DefaultTableModel(vec, list);

		dbtable = new JTable(model);
		

		model.fireTableDataChanged();
		scroll = new JScrollPane(dbtable);
	}

	public void update(int res) {    //insert,update,delete ���� �� ó���Ǵ� �Լ�
	
		JLabel jl = new JLabel(res + "�� ����Ǿ����ϴ�.");
		p2.add(jl);
	}

	public Component show() {  //run ��ư ������ �� ������ ���̺� ȭ��
		
		JTabbedPane tp = new JTabbedPane();
		
		String tabs[] = { "SQL Data" };
		p2.setLayout(new GridLayout(1,1));
		p2.add(scroll);
		tp.addTab(tabs[0], p2);
		
		return tp;
	}

	public Component button() {  //run ��ư �ִ� ȭ��

		JPanel p = new JPanel();
		p.add(run);
		run.addActionListener(this);
		return p;

	}

	@Override
	public void actionPerformed(ActionEvent e) {  
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;

		if (e.getSource() == run) {
		
			ResultSet rs = null;
		
			try {

				String sql = query.getText();
				String sq = query.getText().substring(0, 6);

				if (sq.equals("SELECT") || sq.equals("select")) {

					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();

					ResultSetMetaData rsmd = rs.getMetaData();
					Vector<Object> list = new Vector<Object>();

					for (int i = 1; i <= rsmd.getColumnCount(); i++) {

						list.add(rsmd.getColumnName(i));  //�÷��̸� ����
					}

					Vector<Object> dataVector = new Vector<Object>(1, 1);
					while (rs.next()) {
						Vector<Object> rowVector = new Vector<Object>(1, 1);
						for (int i = 1; i <= rsmd.getColumnCount(); i++) {
							switch (rsmd.getColumnType(i)) {
							case Types.INTEGER:
							case Types.NUMERIC:
								rowVector.addElement(rs.getInt(i));
								break;
							case Types.FLOAT:
								rowVector.addElement(rs.getFloat(i));
								break;
							case Types.DOUBLE:
								rowVector.addElement(rs.getDouble(i));
								break;
							case Types.DATE:
								rowVector.addElement(rs.getDate(i));
								break;
							case Types.CHAR:
							default:
								rowVector.addElement(rs.getString(i));
								break;
							}
						}
						dataVector.addElement(rowVector);  //������ �����͸� ���� ��ü ������
					}
					
					addtable(list,dataVector);
					p2.removeAll();
					p2.add(scroll);  //�갡 �ֿ� ����		
					p2.updateUI();  //ȭ�� ����
					
				} else {  //insert,update,delete ���� ��
					pstmt = conn.prepareStatement(sql);
					int res = pstmt.executeUpdate();
					p2.removeAll();
					update(res);
					p2.updateUI();
				}
			} catch (Exception e1) {
				System.out.println("action_listener ���� " + e1.getMessage());
			}

		}

	}

	public Component input() {   //������ �Է� â

		JTabbedPane tp = new JTabbedPane();
		String tabs[] = { "SQL" };
		JPanel p1 = new JPanel();
		p1.add(query);

		tp.addTab(tabs[0], p1);

		return tp;

	}

	public Component description() {  //������ tree ȭ��

		JTabbedPane tp = new JTabbedPane();
		String tabs[] = { "Description" };

		p3.add(subtree);
		tp.addTab(tabs[0], p3);

		return tp;
	}

	public Component database() {  //���� Ʈ�� ���� Panel

		JTabbedPane tp = new JTabbedPane();
		String tabs[] = { "Database" };
		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout());
		t_scroll = new JScrollPane(database_tree());
		p1.add(t_scroll);
		tp.addTab(tabs[0], p1);

		return tp;

	}

	public Component database_tree() {  //���� Ʈ�� ����� �Լ�
		
		ResultSet rs = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		
		try {
			DatabaseMetaData data = conn.getMetaData();
			DatabaseMetaData data2 = conn.getMetaData();
			DatabaseMetaData data3 = conn.getMetaData();

			rs = data.getTables(null, null, "%", new String[] { "TABLE" });

			root = new DefaultMutableTreeNode(root_name); //root Ʈ�� ����

			while (rs.next()) {
				DefaultMutableTreeNode node;
				String tableName = rs.getString(3);
				node = new DefaultMutableTreeNode(tableName); //���̺� �̸��� ��� ����
				rs2 = data2.getColumns(null, null, tableName, null);
				rs3 = data3.getPrimaryKeys(null, null, tableName);
				
				while (rs2.next()) {
					int ck=0;
					DefaultMutableTreeNode node2;  //���̺� �÷��� ��� ����
					if (rs3.next()) {
	
						if (rs3.getString(4).equals(rs2.getString(4))) { //�����̸Ӹ� Ű �� ��

							node2 = new DefaultMutableTreeNode("[P]"+rs2.getString(4));
						//	node2 = new DefaultMutableTreeNode(new ImageIcon("image/heart.gif").getImage());
							
							node.add(node2);
							
							ck=1;
						}
						else
							ck=0;
					}

					if(ck==0){
					node2 = new DefaultMutableTreeNode(rs2.getString(4));
					node.add(node2);
					}
				}

				root.add(node);
			}

			Vector<Object> list = new Vector<Object>();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		tree = new JTree(root);
		tree.setCellRenderer(new MyRenderer2());
/*

		  DefaultTreeCellRenderer icon=new
				  DefaultTreeCellRenderer(); 
		  icon.setl
				  icon.setLeafIcon(new
				  ImageIcon("image/heart.gif"));
				  tree.setCellRenderer(icon);
				  tree.setEditable(true);
				  */
		
		tree.addTreeSelectionListener(new TreeSelectionListener() {
		    public void valueChanged(TreeSelectionEvent e) {
		        DefaultMutableTreeNode node = (DefaultMutableTreeNode)
		                           tree.getLastSelectedPathComponent();
		        DefaultMutableTreeNode type,value;
				type = new DefaultMutableTreeNode("Data Type");
				value = new DefaultMutableTreeNode("Default Value");

		    /* if nothing is selected */ 
		        if (node == null) return;

		        try {
					DatabaseMetaData data = conn.getMetaData();
					ResultSet rs = data.getColumns(null, null, node.getParent().toString(), null);
					
					String subroot_name = "'"+node.getPath()[0]+"','"+node.getPath()[1]+"','"+node.getPath()[2]+"'";
			        
					while(rs.next()){
						DefaultMutableTreeNode t_node;
						DefaultMutableTreeNode n_node;
						DefaultMutableTreeNode i_node;
						DefaultMutableTreeNode d_node;
					
						if(node.getPath()[2].toString().replace("[P]","").equals(rs.getString(4))){  //���� ���� �÷��̸��� ��
							String tp = rs.getString(6)+"("+rs.getString(7)+")";
							t_node = new DefaultMutableTreeNode(tp);  //Ÿ��,���� ����
							type.add(t_node);
							
							if(rs.getBoolean(18))  //null ����
								n_node = new DefaultMutableTreeNode("null ����");
							else
								n_node = new DefaultMutableTreeNode("null �Ұ���");
							
							type.add(n_node);
							
							if(rs.getBoolean(23))  //�ڵ� ���� ����
								i_node = new DefaultMutableTreeNode("Auto Increment");
							else
								i_node = new DefaultMutableTreeNode("Not Auto Increment");
							type.add(i_node);
							
							d_node = new DefaultMutableTreeNode(rs.getString("COLUMN_DEF")+""); //default value �� ����
							value.add(d_node);
							
						}
						
					}
					

			        subroot = new DefaultMutableTreeNode(subroot_name);
			        subroot.add(type);
			        subroot.add(value);
			      
			        tree_model = new DefaultTreeModel(subroot);
			        tree_model.reload();
			        
					subtree =  new JTree(tree_model);
					subtree.setCellRenderer(new MyRenderer2());
					subtree.setEditable(true);
		
					p3.removeAll();
			        p3.add(subtree);
			        p3.updateUI();
			        
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        
		    }
		});
		
		return tree;
	}
	
}