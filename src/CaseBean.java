
public class CaseBean {
	private int no;
	private String court;
	private String caseno;
	private String debtor;
	private int money;
	private String day;
	private String client;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getCourt() {
		return court;
	}
	public void setCourt(String court) {
		this.court = court;
	}
	public String getCaseno() {
		return caseno;
	}
	public void setCaseno(String caseno) {
		this.caseno = caseno;
	}
	public String getDebtor() {
		return debtor;
	}
	public void setDebtor(String debtor) {
		this.debtor = debtor;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public CaseBean(int no, String court, String caseno, String debtor, int money, String day, String client) {
		super();
		this.no = no;
		this.court = court;
		this.caseno = caseno;
		this.debtor = debtor;
		this.money = money;
		this.day = day;
		this.client = client;
	}
	public CaseBean() {
		
	}
}
