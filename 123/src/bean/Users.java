package bean;

public class Users {
	// 用户唯一id
	private String id;
	// 用户借书权限（能借几本）
	private int num_right;
	// 用户时间权限（能借多久）（单位：天）
	private int data_right;
	// 目前已借本数
	private int borrow_now;
	// 用户总罚款数
	private float fine;

	public Users() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getNum_right() {
		return num_right;
	}

	public void setNum_right(int num_right) {
		this.num_right = num_right;
	}

	public int getData_right() {
		return data_right;
	}

	public void setData_right(int data_right) {
		this.data_right = data_right;
	}

	public int getBorrow_now() {
		return borrow_now;
	}

	public void setBorrow_now(int borrow_now) {
		this.borrow_now = borrow_now;
	}

	public float getFine() {
		return fine;
	}

	public void setFine(float fine) {
		this.fine = fine;
	}

}
