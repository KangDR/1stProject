import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;

public class login extends JFrame implements ActionListener{
	JFrame frame = new JFrame();
	JButton loginButton = new JButton("�α���");
	JButton joinButton = new JButton("ȸ������");
	JTextField userIDField = new JTextField();
	JPasswordField userPasswordField = new JPasswordField();
	JLabel userIDLabel = new JLabel("ID:");
	JLabel userPasswordLabel = new JLabel("password:");
	public login() {
			userIDLabel.setBounds(50,100,75,25);
			userPasswordLabel.setBounds(50,150,75,25);
			userIDField.setBounds(125,100,200,25);
			userPasswordField.setBounds(125,150,200,25);
			loginButton.setBounds(125,200,100,25);
			loginButton.addActionListener(this);
			joinButton.setBounds(225,200,100,25);
			joinButton.addActionListener(this);
			frame.add(userIDLabel);
			frame.add(userPasswordLabel);
			frame.add(userIDField);
			frame.add(userPasswordField);
			frame.add(loginButton);
			frame.add(joinButton);
			frame.setSize(420,420);
			frame.setLocation(400,200);
			frame.setLayout(null);
			frame.setTitle("�α���â");
			frame.setVisible(true);
	}
public void actionPerformed(ActionEvent e) {
		if(e.getSource()==joinButton) {
			userIDField.setText("");
			userPasswordField.setText("");
			new join();
		}
		if(e.getSource()==loginButton) {
			String userID = userIDField.getText();
			String password = String.valueOf(userPasswordField.getPassword());
			String s;
			int cnt=0;
			BufferedReader bos=null;
			try {
				bos = new BufferedReader(new FileReader("ȸ��.txt"));
			} catch (FileNotFoundException e2) {
				e2.printStackTrace();
			}
			try {
				while((s=bos.readLine())!=null){
					String [] array=s.split("/");
				if(userID.equals(array[1])&&password.equals(array[2]))
				cnt++;
				}
				if(cnt==0) {
				JOptionPane.showMessageDialog(null, "�α����� �����Ͽ����ϴ�.");
				userIDField.setText("");
				userPasswordField.setText("");
				}
				else if(cnt!=0) {
					JOptionPane.showMessageDialog(null, "�α����� �Ǿ����ϴ�");
					frame.dispose();
					new CaseMain("��ǰ��� ���α׷�");
				}
				bos.close();
				
			} catch (HeadlessException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}	
	}
}

