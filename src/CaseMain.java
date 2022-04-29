import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CaseMain extends JFrame implements ActionListener,ItemListener{
	JTable casetable,clienttable;
	String order,way,searchcol;
	String[] casecol= {"no","법원","사건번호","당사자명","변제원금","발생일자","담당자"};
	String[] clientcol= {"no","사명","직급","성명","부서","전화번호","이메일"};
	String[] title= {"등록","수정","삭제","종료"};
	JButton[] buttons;
	JButton orderby,reset,searchbutton,calbutton,calreset;
	Object[][] caserow,clientrow;
	ArrayList<CaseBean> caselist;
	ArrayList<clientBean> clientlist;
	JScrollPane casescrollpane,clientscrollpane;
	CaseDao cdao=new CaseDao();
	JTextField tfNo,tfSearch,tfCourt,tfCaseno,tfMoney,tfDay,tfClient,tfDebtor,tfname,tfInterest,tfstartday,tfendday,tfMoney2,tfInterest2,tfsum;
	JRadioButton rbCourt,rbDebtor,rbMoney,rbClient,rbDay,rbAsc,rbDesc;
	Choice ch;
	Date now =new Date();
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	CaseMain(String title){
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		caselist=cdao.getAllCase();
		clientlist=cdao.getAllClient();
		fillcase();
		fillclient();
		compose();
		setLocation(400,200);
		setVisible(true);
		setSize(1000,600);

	}
	private void fillcase() {
		int cnt=0;
		caserow=new Object[caselist.size()][casecol.length];
		for(int i=0; i<caselist.size();i++) {
			CaseBean bean=caselist.get(i);
			caserow[i][cnt++]=bean.getNo();
			caserow[i][cnt++]=bean.getCourt();
			caserow[i][cnt++]=bean.getCaseno();
			caserow[i][cnt++]=bean.getDebtor();
			caserow[i][cnt++]=bean.getMoney();
			caserow[i][cnt++]=bean.getDay();
			caserow[i][cnt++]=bean.getClient();
			cnt=0;
		}
	}
	private void fillclient() {
		int cnt=0;
		clientrow=new Object[clientlist.size()][clientcol.length];
		for(int i =0; i<clientlist.size();i++) {
			clientBean bean=clientlist.get(i);
			clientrow[i][cnt++]=bean.getNo();
			clientrow[i][cnt++]=bean.getCompany();
			clientrow[i][cnt++]=bean.getPosition();
			clientrow[i][cnt++]=bean.getName();
			clientrow[i][cnt++]=bean.getDepart();
			clientrow[i][cnt++]=bean.getPhone();
			clientrow[i][cnt++]=bean.getEmail();
			cnt=0;
		}
	}
	private void compose() {
		Container contentPane=getContentPane();
		casetable=new JTable(caserow,casecol);
		casetable.addMouseListener(new MouseEventProc());
		casescrollpane=new JScrollPane(casetable);
		contentPane.setLayout(new GridLayout(2,2));
		contentPane.add(casescrollpane);
		casetable.setAutoCreateRowSorter(true);
		
		clienttable=new JTable(clientrow,clientcol);
		clientscrollpane=new JScrollPane(clienttable);
		contentPane.add("Center",clientscrollpane);
		clienttable.setAutoCreateRowSorter(true);
		
		JPanel panel=new JPanel();
		contentPane.add(panel);
		JLabel lbNo=new JLabel("no");
		JLabel lbCourt=new JLabel("법원명");
		JLabel lbCaseno=new JLabel("사건번호");
		JLabel lbDebtor=new JLabel("당사자명");
		JLabel lbMoney=new JLabel("변제원금");
		JLabel lbDay=new JLabel("발생일자");
		JLabel lbClient=new JLabel("담당자");
		//panel.setLayout(new FlowLayout());

		panel.setLayout(null);
		lbNo.setBounds(15,20,80,15);
		lbCourt.setBounds(15,40,80,15);
		lbCaseno.setBounds(15,60,80,15);
		lbDebtor.setBounds(15,80,80,15);
		lbMoney.setBounds(15,100,80,15);
		lbDay.setBounds(15,120,80,15);
		lbClient.setBounds(15,140,80,15);
		
		panel.add(lbNo);
		panel.add(lbCourt);
		panel.add(lbCaseno);
		panel.add(lbDebtor);
		panel.add(lbMoney);
		panel.add(lbDay);
		panel.add(lbClient);
		
		tfNo=new JTextField();
		tfCourt=new JTextField();
		tfCaseno=new JTextField();
		tfDebtor=new JTextField();
		tfMoney=new JTextField();
		tfDay=new JTextField();
		tfClient=new JTextField();
		
		tfNo.setBounds(115,20,160,15);
		tfCourt.setBounds(115,40,160,15);
		tfCaseno.setBounds(115,60,160,15);
		tfDebtor.setBounds(115,80,160,15);
		tfMoney.setBounds(115,100,160,15);
		tfDay.setBounds(115,120,160,15);
		tfClient.setBounds(115,140,160,15);
		tfNo.setEditable(false);
		panel.add(tfNo);
		panel.add(tfCourt);
		panel.add(tfCaseno);
		panel.add(tfDebtor);
		panel.add(tfMoney);
		panel.add(tfDay);
		panel.add(tfClient);
		
		buttons=new JButton[title.length];
		for(int i=0;i<title.length;i++) {
			buttons[i]=new JButton(title[i]);
			buttons[i].addActionListener(this);
			buttons[i].setBounds(30+(i*100),200,80,20);
			panel.add(buttons[i]);
		}
		//JPanel radioPanel =new JPanel();
		ButtonGroup group=new ButtonGroup();
		rbCourt=new JRadioButton("법원");
		rbDebtor=new JRadioButton("당사자명");
		rbMoney=new JRadioButton("변제원금");
		rbDay=new JRadioButton("발생일자");
		rbClient=new JRadioButton("담당자");
		group.add(rbCourt);
		group.add(rbDebtor);
		group.add(rbMoney);
		group.add(rbDay);
		group.add(rbClient);
		rbCourt.setBounds(300,10,100,20);
		rbDebtor.setBounds(300,30,100,20);
		rbMoney.setBounds(300,50,100,20);
		rbDay.setBounds(300,70,100,20);
		rbClient.setBounds(300,90,100,20);
		rbCourt.addItemListener(new MyItemListener());
		rbDebtor.addItemListener(new MyItemListener());
		rbMoney.addItemListener(new MyItemListener());
		rbDay.addItemListener(new MyItemListener());
		rbClient.addItemListener(new MyItemListener());
		panel.add(rbCourt);
		panel.add(rbDebtor);
		panel.add(rbMoney);
		panel.add(rbDay);
		panel.add(rbClient);
		ButtonGroup group2=new ButtonGroup();
		rbAsc=new JRadioButton("오름차");
		rbDesc=new JRadioButton("내림차");
		group2.add(rbAsc);
		group2.add(rbDesc);
		rbAsc.setBounds(400,35,100,20);
		rbDesc.setBounds(400,55,100,20);
		rbAsc.addItemListener(new MyItemListener());
		rbDesc.addItemListener(new MyItemListener());
		panel.add(rbAsc);
		panel.add(rbDesc);
		rbCourt.setSelected(true);
		rbAsc.setSelected(true);
		orderby=new JButton("전체정렬");
		reset=new JButton("초기화");
		orderby.addActionListener(this);
		reset.addActionListener(this);
		orderby.setBounds(290,130,100,20);
		reset.setBounds(390,130,100,20);
		panel.add(orderby);
		panel.add(reset);
		JPanel panel2=new JPanel();
		contentPane.add(panel2);
		JLabel search=new JLabel("사건에서 찾기");
		panel2.setLayout(null);
		search.setBounds(30,20,100,20);
		panel2.add(search);
		tfSearch=new JTextField();
		tfSearch.setBounds(190,50,200,25);
		panel2.add(tfSearch);
		String [] searchcol= {"법원명","당사자명","담당자"};
		ch=new Choice();
		for(int i=0;i<searchcol.length;i++) {
			ch.add(searchcol[i]);
		}
		ch.addItemListener(this);
		ch.setBounds(85,50,100,20);
		panel2.add(ch);
		searchbutton=new JButton("검색");
		searchbutton.addActionListener(this);
		searchbutton.setBounds(400,50,80,25);
		panel2.add(searchbutton);
		
		JLabel cal=new JLabel("이자계산하기");
		tfname=new JTextField();
		tfname.setBounds(120,85,100,20);
		tfname.setEditable(false);
		panel2.add(tfname);
		cal.setBounds(30,85,100,20);
		panel2.add(cal);
		JLabel lname=new JLabel("원금");
		lname.setBounds(85,110,80,20);
		panel2.add(lname);
		tfMoney2=new JTextField();
		tfMoney2.setBounds(165,110,100,20);
		panel2.add(tfMoney2);
		JLabel lInterest=new JLabel("이율(연%)");
		lInterest.setBounds(285,110,100,20);
		panel2.add(lInterest);
		tfInterest=new JTextField();
		tfInterest.setBounds(345,110,100,20);
		panel2.add(tfInterest);
		JLabel startday=new JLabel("시작일");
		JLabel endday=new JLabel("종료일");
		startday.setBounds(85,140,100,20);
		endday.setBounds(285,140,100,20);
		panel2.add(startday);
		panel2.add(endday);
		JLabel lInterest2=new JLabel("이자");
		lInterest2.setBounds(85,170,100,20);
		panel2.add(lInterest2);
		JLabel lsum=new JLabel("원리금");
		lsum.setBounds(85,200,100,20);
		panel2.add(lsum);
		tfInterest2=new JTextField();
		tfsum=new JTextField();
		tfInterest2.setBounds(165,170,100,20);
		tfsum.setBounds(165,200,100,20);
		panel2.add(tfInterest2);
		panel2.add(tfsum);		
		tfstartday=new JTextField();
		tfendday=new JTextField();
		tfstartday.setBounds(165,140,100,20);
		tfendday.setBounds(345,140,100,20);
		panel2.add(tfstartday);
		panel2.add(tfendday);
		calbutton=new JButton("계산");
		calbutton.addActionListener(this);
		calbutton.setBounds(340,180,100,25);
		panel2.add(calbutton);
		calreset=new JButton("계산초기화");
		calreset.addActionListener(this);
		calreset.setBounds(340,210,100,25);
		panel2.add(calreset);
	}
	class MouseEventProc extends MouseAdapter{
		public void mouseClicked(MouseEvent e) {
			int row=casetable.getSelectedRow();
			System.out.println(row);
			String no =casetable.getValueAt(row, 0).toString();
			String court=(String)casetable.getValueAt(row, 1);
			String caseno=(String)casetable.getValueAt(row, 2);
			String debtor=(String)casetable.getValueAt(row, 3);
			String money=casetable.getValueAt(row, 4).toString();
			String day=(String)casetable.getValueAt(row, 5);			
			String client =(String)casetable.getValueAt(row, 6);//아이디			
			tfNo.setText(no);
			tfCourt.setText(court);
			tfCaseno.setText(caseno);
			tfDebtor.setText(debtor);
			tfMoney.setText(money);
			tfDay.setText(day);
			tfClient.setText(client);
			clientlist=cdao.getClient(client);
			fillclient();
			clienttable=new JTable(clientrow,clientcol);
			clienttable.setAutoCreateRowSorter(true);
			clientscrollpane.setViewportView(clienttable);
			tfMoney2.setText(money);
			tfstartday.setText(day);
			tfname.setText(debtor);
			tfInterest.setText("5");
			tfendday.setText(sdf.format(now));
			tfsum.setText(null);
			tfInterest2.setText(null);
		}
	}
	public static void main(String[] args) {
		new login();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj=e.getSource();
		if (obj==buttons[0]) {
			insertData();
		} else if (obj==buttons[1]) {
			updateData();
		} else if (obj==buttons[2]) {
			deleteData();
		} else if (obj==buttons[3]) {
			System.exit(0);
		} else if (obj==orderby) {
			orderby();
		} else if (obj==reset) {
			getAllCase();
			getAllClient();
			clearTextField();
		} else if (obj==searchbutton) {
			searchData();
		} else if (obj==calbutton) {
			calculate();
		} else if (obj==calreset) {
			tfMoney2.setText(null);
			tfstartday.setText(null);
			tfendday.setText(null);
			tfInterest2.setText(null);
			tfInterest.setText(null);
			tfsum.setText(null);
			tfname.setText(null);
		}
		} 
	private void calculate() {
		String startday=tfstartday.getText();
		String endday=tfendday.getText();
		String Money=tfMoney2.getText();
		String interest=tfInterest.getText();
		int calMoney=Integer.parseInt(Money);
		int calinterest=Integer.parseInt(interest);
		try {
			Date date1 = sdf.parse(startday);
			Date date2 =sdf.parse(endday);
			long calDate = date2.getTime() - date1.getTime();
			long calDateDays = calDate / ( 24*60*60*1000);
	        calDateDays = Math.abs(calDateDays);
	        System.out.println(calDateDays);
	        //((원금*5%)/365)*일수
	       int caledinterest=(int) ((calMoney*(calinterest*0.01)/365)*calDateDays);
	       tfInterest2.setText( Integer.toString(caledinterest));
	       tfsum.setText(Integer.toString(caledinterest+calMoney));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	private void searchData() {
		String searchword=tfSearch.getText();
		System.out.println(searchword+searchcol);
		caselist=cdao.searchData(searchcol,searchword);
		fillcase();
		casetable=new JTable(caserow,casecol);
		casetable.setAutoCreateRowSorter(true);
		casetable.addMouseListener(new MouseEventProc());
		casescrollpane.setViewportView(casetable);
		tfSearch.setText(null);
	}
	private void orderby() {
		caselist=cdao.orderby(order,way);
		fillcase();
		casetable=new JTable(caserow,casecol);
		casetable.setAutoCreateRowSorter(true);
		casetable.addMouseListener(new MouseEventProc());
		casescrollpane.setViewportView(casetable);
	}
	private void deleteData() {
		String no =tfNo.getText();
		int No=Integer.parseInt(no);
		int cnt=cdao.deleteData(No);
		
		if (cnt==-1) System.out.println("sql 오류");
		else if (cnt==0) System.out.println("수정 실패");
		else {System.out.println(cnt);
			getAllCase();
			clearTextField();
			}
	}
	private void updateData() {
		String no=tfNo.getText();
		String court=tfCourt.getText();
		String caseno=tfCaseno.getText();
		String debtor=tfDebtor.getText();
		String money=tfMoney.getText();
		String day=tfDay.getText();
		String client=tfClient.getText();
		
		CaseBean cb=new CaseBean();
		cb.setNo(Integer.parseInt(no));
		cb.setCourt(court);
		cb.setCaseno(caseno);
		cb.setDebtor(debtor);
		cb.setMoney(Integer.parseInt(money));
		cb.setClient(client);
		cb.setDay(day);
		
		int cnt=cdao.updateData(cb);
		if (cnt==-1) System.out.println("sql 오류");
		else if (cnt==0) System.out.println("수정 실패");
		else {System.out.println(cnt);
			getAllCase();
			clearTextField();}
	}
	private void insertData() {
		String court=tfCourt.getText();
		String caseno=tfCaseno.getText();
		String debtor=tfDebtor.getText();
		String money=tfMoney.getText();
		String day=tfDay.getText();
		String client=tfClient.getText();
		
		CaseBean cb=new CaseBean();
		cb.setCourt(court);
		cb.setCaseno(caseno);
		cb.setDebtor(debtor);
		cb.setMoney(Integer.parseInt(money));
		cb.setClient(client);
		cb.setDay(day);
		
		int cnt=cdao.insertData(cb);
		if (cnt==-1) System.out.println("sql 오류");
		else if (cnt==0) System.out.println("추가 실패");
		else {System.out.println(cnt);
			getAllCase();
			clearTextField();}
		}
	private void clearTextField() {
		tfNo.setText(null);
		tfCourt.setText(null);
		tfCaseno.setText(null);
		tfDebtor.setText(null);
		tfMoney.setText(null);
		tfDay.setText(null);
		tfClient.setText(null);
		
	}
	private void getAllCase() {
		caselist=cdao.getAllCase();
		fillcase();
		casetable=new JTable(caserow,casecol);
		casetable.setAutoCreateRowSorter(true);
		casetable.addMouseListener(new MouseEventProc());
		casescrollpane.setViewportView(casetable);
	}
	private void getAllClient() {
		clientlist=cdao.getAllClient();
		fillclient();
		clienttable=new JTable(clientrow,clientcol);
		clienttable.setAutoCreateRowSorter(true);
		clientscrollpane.setViewportView(clienttable);
	}
	class MyItemListener implements ItemListener{
		public void itemStateChanged(ItemEvent e) {
			if (rbCourt.isSelected()) {
			order="court";
			}
			else if (rbDebtor.isSelected()) {
			order="debtor";
			}
			else if (rbMoney.isSelected()){
			order="money";
			}
			else if (rbDay.isSelected()) {
			order="day";
			}
			else if (rbClient.isSelected()){
			order="client";	
			}
			
			if (rbAsc.isSelected()) {
				way="asc";
			}
			else if (rbDesc.isSelected()) {
				way="desc";
			}
		}
		
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		System.out.println(ch.getSelectedItem());
		if (ch.getSelectedItem().equalsIgnoreCase("당사자명")){
			searchcol="debtor";
		}
		else if (ch.getSelectedItem().equalsIgnoreCase("법원명")) {
			searchcol="court";
		}
		else if (ch.getSelectedItem().equalsIgnoreCase("담당자")){
			searchcol="client";
		}
	}
}

