import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

	public class join extends JFrame implements ActionListener{
		JPanel panel;
	  	Label name,id,password,password2;
        TextField tfName,tfId;
        JPasswordField tfPw,tfPw2;
        JButton join,cancel;
        
		public join(){
			  	panel = new JPanel();
			  	name= new Label("이름");	
		        id = new Label("아이디");
		        password= new Label("패스워드");
		        password2= new Label("패스워드 확인");
		        add(name);
		        add(id);
		        add(password);
		        add(password2);
		        tfName = new TextField();
		        tfId = new TextField();
		        tfPw = new JPasswordField();
		        tfPw2 = new JPasswordField();
		        add(tfName);
		        add(tfId);
		        add(tfPw);
		        add(tfPw2);
		        join = new JButton("가입");
		        cancel = new JButton("취소");
		        add(join);
		        add(cancel);
		        name.setBounds(40, 30, 40, 40);
		        id.setBounds(40, 70, 40, 40);
		        password.setBounds(40,110,60,40);
		        password2.setBounds(40, 150, 80, 40);
		        tfName.setBounds(200, 30, 200, 30);
		        tfId.setBounds(200, 70, 200, 30);
		        tfPw.setBounds(200, 110, 200, 30);
		        tfPw2.setBounds(200, 150, 200, 30);
		        join.setBounds(125, 210, 80, 30);
		        cancel.setBounds(240, 210, 80, 30);
		        join.addActionListener(this);
		        cancel.addActionListener(this);
		    add(panel);
			setSize(500,300);
			setLocation(400,200);
			setTitle("회원가입");
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setVisible(true);
	       

		}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==join) {
		if(String.valueOf(tfPw.getPassword()).equals(String.valueOf(tfPw2.getPassword())))
			{
			BufferedWriter bos;
			try {
				bos = new BufferedWriter(new FileWriter("회원.txt",true));
				bos.write(tfName.getText()+"/");
				bos.write(tfId.getText()+"/");
				bos.write(String.valueOf(tfPw.getPassword())+"\r\n");
				bos.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "회원가입되었습니다.");
			dispose();
			}
		else {
			JOptionPane.showMessageDialog(null, "비밀번호를 확인하세요");
			}
		}
		if(e.getSource()==cancel) {
			dispose();
		}
		
	}
}

