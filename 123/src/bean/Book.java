package bean;

import java.sql.Date;

public class Book {
private String id;
//书的总数
private int num;
//现借出数
private int borrow_num;
//借出总数
private int borrow_sum;
private String name,author,pub,describe,sort;
private Date RECORD;
public Book() {
	
}
public String getId() {
	return id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getAuthor() {
	return author;
}
public void setAuthor(String author) {
	this.author = author;
}
public String getPub() {
	return pub;
}
public void setPub(String pub) {
	this.pub = pub;
}
public String getDescribe() {
	return describe;
}
public void setDescribe(String describe) {
	this.describe = describe;
}
public String getSort() {
	return sort;
}
public void setSort(String sort) {
	this.sort = sort;
}
public Date getRECORD() {
	return RECORD;
}
public void setRECORD(Date rECORD) {
	RECORD = rECORD;
}
public void setId(String id) {
	this.id = id;
}
public int getNum() {
	return num;
}
public void setNum(int num) {
	this.num = num;
}
public int getBorrow_num() {
	return borrow_num;
}
public void setBorrow_num(int borrow_num) {
	this.borrow_num = borrow_num;
}
public int getBorrow_sum() {
	return borrow_sum;
}
public void setBorrow_sum(int borrow_sum) {
	this.borrow_sum = borrow_sum;
}
}
