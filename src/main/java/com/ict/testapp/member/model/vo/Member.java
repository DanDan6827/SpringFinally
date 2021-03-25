package com.ict.testapp.member.model.vo;

import java.sql.Date;

//vo는 valueobject줄임말 용도는 데이터베이스 테이블의 한행의 정보-컬럼값들 을 저장할 용도
//dto:data transfer object == do : domain object == bean

//vo 작성규칙
// 1. 직렬화 처리
// 2. 모든 필드는 반드시 private이어야 한다(캡슐화)
// 3. 기본(default) 생성자와 매계변수 있는 생성자가 있어야한다.
// 4. 모든 필드에 대한 getter setter 가 필요하다
// 5. toString() 오버라이딩함
public class Member implements java.io.Serializable {
	private static final long serialVersionUID = 1000L;
	
	private int idx;
	private String id;
	private String pw;
	private String name;
	private int age;
	private String addr;
	private java.sql.Date reg;
	
	public Member() {}

	public Member(int idx, String id, String pw, String name, int age, String addr, Date reg) {
		super();
		this.idx = idx;
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.age = age;
		this.addr = addr;
		this.reg = reg;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public java.sql.Date getReg() {
		return reg;
	}

	public void setReg(java.sql.Date reg) {
		this.reg = reg;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Member [idx=" + idx + ", id=" + id + ", pw=" + pw + ", name=" + name + ", age=" + age + ", addr=" + addr
				+ ", reg=" + reg + "]";
	}
	
}
