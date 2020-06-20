package bean;

import java.util.Date;

public class Records {
	//记录编号
	private int id;
//	借阅者编号
	private String U_id;
//	借阅书籍编号
	private String B_id;
//	借阅日期
	private Date date;
//	应还日期
	private Date back_date;
//	是否已经归还
	private boolean exit;
//	欠款
	private float return_money;
//	已换日期
	private Date already_back_date;
	public Records() {
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getU_id() {
		return U_id;
	}
	public void setU_id(String u_id) {
		U_id = u_id;
	}
	public String getB_id() {
		return B_id;
	}
	public void setB_id(String b_id) {
		B_id = b_id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getBack_date() {
		return back_date;
	}
	public void setBack_date(Date back_date) {
		this.back_date = back_date;
	}
	public boolean isExit() {
		return exit;
	}
	public void setExit(boolean exit) {
		this.exit = exit;
	}
	public float getReturn_money() {
		return return_money;
	}
	public void setReturn_money(float return_money) {
		this.return_money = return_money;
	}
	public Date getAlready_back_date() {
		return already_back_date;
	}
	public void setAlready_back_date(Date already_back_date) {
		this.already_back_date = already_back_date;
	}
	
}
