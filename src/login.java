import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;

public class login extends JFrame implements ActionListener{
	JFrame frame = new JFrame();
	JButton loginButton = new JButton("로그인");
	JButton joinButton = new JButton("회원가입");
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
			frame.setTitle("로그인창");
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
				bos = new BufferedReader(new FileReader("회원.txt"));
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
				JOptionPane.showMessageDialog(null, "로그인이 실패하였습니다.");
				userIDField.setText("");
				userPasswordField.setText("");
				}
				else if(cnt!=0) {
					JOptionPane.showMessageDialog(null, "로그인이 되었습니다");
					frame.dispose();
					new CaseMain("사건관리 프로그램");
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

