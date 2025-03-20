package hmi.model;

import java.util.Date;

public class Stock {
	private int id, price, qty, pid;
	private String pname;
	private Date date;
	public Stock(int id, int pid, int price, int qty, Date date) {
		super();
		this.id = id;
		this.price = price;
		this.qty = qty;
		this.pid = pid;
		this.date = date;
	}
	
	public Stock(int id, int pid,String pname, int price, int qty, Date date) {
		this.id = id;
		this.price = price;
		this.qty = qty;
		this.pid = pid;
		this.pname = pname;
		this.date = date;
	}
	public Stock(int pid,String pname, int price, int qty, Date date) {
		this.price = price;
		this.qty = qty;
		this.pid = pid;
		this.pname = pname;
		this.date = date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	
}
