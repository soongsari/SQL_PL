import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login implements ActionListener {
	JFrame frame;
	JPanel panel;
	JLabel label1, label2, label3, label4, label5;
	JTextField field1, field2, field3, field4, field5;
	JButton button1, button2;
	public String v1,v2,v3,v4,v5;
	


	public Component connectwindow() {
		frame = new JFrame("Connect to Database");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel();
		panel.setSize(300, 300);
		label1 = new JLabel("Server Host: ");
		field1 = new JTextField(20);
		label2 = new JLabel("Port: ");
		field2 = new JTextField(10);
		label3 = new JLabel("Username: ");
		field3 = new JTextField(20);
		label4 = new JLabel("Password: ");
		field4 = new JTextField(20);
		label5 = new JLabel("Schema: ");
		field5 = new JTextField(20);
		button1 = new JButton("OK");
		button2 = new JButton("Cancel");

		//////////////////////////////
		field1.setText("localhost");
		field2.setText("3306");
		field5.setText("university");
		////////////////////////////////
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(label1);
		panel.add(field1);
		panel.add(label2);
		panel.add(field2);

		panel.add(label3);
		panel.add(field3);
		panel.add(label4);
		panel.add(field4);
		panel.add(label5);
		panel.add(field5);

		panel.add(button1);
		panel.add(button2);
		button1.addActionListener(this);

		
		frame.getContentPane().add(panel);
		frame.setSize(300, 300);
		frame.setVisible(true);
		return panel;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		Connection conn = null;
		if (e.getSource() == button1) {
			v1=field1.getText();
			v2=field2.getText();
			v3=field3.getText();
			v4=field4.getText();
			v5=field5.getText();

			Show show = new Show(v1,v2,v3,v4,v5);
			
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Login().connectwindow();
	}

}
