package com.lon.vo;

public class SysUserRoleVo {

	private int id;
	private String uname; //用户帐号
	private String rname;  //角色名称
	
	
	
	public SysUserRoleVo() {
		super();
	}
	public SysUserRoleVo(int id, String uname, String rname) {
		super();
		this.id = id;
		this.uname = uname;
		this.rname = rname;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	@Override
	public String toString() {
		return "UserRoleVo [id=" + id + ", uname=" + uname + ", rname=" + rname + "]";
	}
	
	
	
}
