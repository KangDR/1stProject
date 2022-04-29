import java.util.ArrayList;
import java.sql.*;

public class CaseDao {
	Connection conn;
	String sql;
	PreparedStatement ps;
	ResultSet rs;
	int cnt=-1;
	CaseDao(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public void getConnection(){
		try {
			conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","jspid","jsppw");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public ArrayList<CaseBean> getAllCase(){
		getConnection();
		ArrayList<CaseBean> list=new ArrayList<CaseBean>();
		sql="select * from case";
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()) {
				int no=rs.getInt("no");
				String court=rs.getString("court");
				String caseno=rs.getString("caseno");
				String debtor=rs.getString("debtor");
				int money=rs.getInt("money");
				String day=String.valueOf(rs.getDate("day"));
				String client=rs.getString("client");
				
				CaseBean cb=new CaseBean();
				cb.setNo(no);
				cb.setCourt(court);
				cb.setCaseno(caseno);
				cb.setDebtor(debtor);
				cb.setMoney(money);
				cb.setDay(day);
				cb.setClient(client);
				list.add(cb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	public ArrayList<clientBean> getClient(String client){
		getConnection();
		ArrayList<clientBean> list=new ArrayList<clientBean>();
		sql="select * from client where name='"+client+"'";
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()) {
				int no=rs.getInt("no");
				String company=rs.getString("company");
				String position=rs.getString("position");
				String name=rs.getString("name");
				String depart=rs.getString("depart");
				String phone=rs.getString("phone");
				String email=rs.getString("email");
				
				clientBean cb=new clientBean();
				cb.setNo(no);
				cb.setCompany(company);
				cb.setPosition(position);
				cb.setName(name);
				cb.setDepart(depart);
				cb.setPhone(phone);
				cb.setEmail(email);
				list.add(cb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	public ArrayList<clientBean> getAllClient(){
		getConnection();
		ArrayList<clientBean> list=new ArrayList<clientBean>();
		sql="select * from client";
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()) {
				int no=rs.getInt("no");
				String company=rs.getString("company");
				String position=rs.getString("position");
				String name=rs.getString("name");
				String depart=rs.getString("depart");
				String phone=rs.getString("phone");
				String email=rs.getString("email");
				
				clientBean cb=new clientBean();
				cb.setNo(no);
				cb.setCompany(company);
				cb.setPosition(position);
				cb.setName(name);
				cb.setDepart(depart);
				cb.setPhone(phone);
				cb.setEmail(email);
				list.add(cb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	public int insertData(CaseBean cb) {
		getConnection();
		sql="insert into case values(cseq.nextval,?,?,?,?,?,?)";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, cb.getCourt());
			ps.setString(2, cb.getCaseno());
			ps.setString(3, cb.getDebtor());
			ps.setInt(4, cb.getMoney());
			ps.setString(5, cb.getDay());
			ps.setString(6, cb.getClient());
			cnt=ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return cnt;
	}
	public int updateData(CaseBean cb) {
		getConnection();
		sql="update case set court=?,caseno=?,debtor=?,money=?,day=?,client=? where no=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, cb.getCourt());
			ps.setString(2, cb.getCaseno());
			ps.setString(3, cb.getDebtor());
			ps.setInt(4, cb.getMoney());
			ps.setString(5, cb.getDay());
			ps.setString(6, cb.getClient());
			ps.setInt(7, cb.getNo());
			cnt=ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return cnt;
	}
	public int deleteData(int no) {
		getConnection();
		sql="delete from case where no=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no);
			cnt=ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return cnt;
		
		}
	public ArrayList<CaseBean> orderby(String order, String way) {
		getConnection();
		sql="select * from case order by "+order+" "+way;
		ArrayList<CaseBean> list=new ArrayList<CaseBean>();
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()) {
				int no=rs.getInt("no");
				String court=rs.getString("court");
				String caseno=rs.getString("caseno");
				String debtor=rs.getString("debtor");
				int money=rs.getInt("money");
				String day=String.valueOf(rs.getDate("day"));
				String client=rs.getString("client");
				
				CaseBean cb=new CaseBean();
				cb.setNo(no);
				cb.setCourt(court);
				cb.setCaseno(caseno);
				cb.setDebtor(debtor);
				cb.setMoney(money);
				cb.setDay(day);
				cb.setClient(client);
				list.add(cb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	public ArrayList<CaseBean> searchData(String searchcol, String searchword) {
		getConnection();
		ArrayList<CaseBean> list=new ArrayList<CaseBean>();
		sql="select * from case where "+searchcol+" like '%"+searchword+"%'";
		try {
			System.out.println(sql);
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()) {
				int no=rs.getInt("no");
				String court=rs.getString("court");
				String caseno=rs.getString("caseno");
				String debtor=rs.getString("debtor");
				int money=rs.getInt("money");
				String day=String.valueOf(rs.getDate("day"));
				String client=rs.getString("client");
				
				CaseBean cb=new CaseBean();
				cb.setNo(no);
				cb.setCourt(court);
				cb.setCaseno(caseno);
				cb.setDebtor(debtor);
				cb.setMoney(money);
				cb.setDay(day);
				cb.setClient(client);
				list.add(cb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
