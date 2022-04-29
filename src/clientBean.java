
public class clientBean {
 private int no;
 private String company;
 private String position;
 private String name;
 private String depart;
 private String phone;
 private String email;
public int getNo() {
	return no;
}
public void setNo(int no) {
	this.no = no;
}
public String getCompany() {
	return company;
}
public void setCompany(String company) {
	this.company = company;
}
public String getPosition() {
	return position;
}
public void setPosition(String position) {
	this.position = position;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getDepart() {
	return depart;
}
public void setDepart(String depart) {
	this.depart = depart;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public clientBean(int no, String company, String position, String name, String depart, String phone, String email) {
	super();
	this.no = no;
	this.company = company;
	this.position = position;
	this.name = name;
	this.depart = depart;
	this.phone = phone;
	this.email = email;
}
public clientBean() {
	
}
}
