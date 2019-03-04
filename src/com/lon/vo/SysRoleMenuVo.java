package com.lon.vo;

public class SysRoleMenuVo {

	private int id;
	private String rname; //角色名称
	private String mname; //菜单名称
	
	
	public SysRoleMenuVo() {
		super();
	}
	public SysRoleMenuVo(int id, String rname, String mname) {
		super();
		this.id = id;
		this.rname = rname;
		this.mname = mname;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	@Override
	public String toString() {
		return "SysRoleMenuVo [id=" + id + ", rname=" + rname + ", mname=" + mname + "]";
	}
	
	
}
