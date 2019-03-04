package com.lon.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lon.dao.MenuDao;
import com.lon.entity.Menu;
import com.lon.util.DBUtils;

public class MenuDaoImpl implements MenuDao{

	@Override
	public void add(Menu menu) {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement("insert into hr_sys_menu(code,pcode,name,url,state,remark) values(?,?,?,?,?,?)");
			pst.setString(1, menu.getCode());
			pst.setString(2, menu.getPcode());
			pst.setString(3, menu.getName());
			pst.setString(4, menu.getUrl());
			pst.setInt(5, menu.getState());
			pst.setString(6, menu.getRemark());
			System.out.println("成功新增【"+pst.executeUpdate()+"】条记录");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, null);
		}
	}

	@Override
	public void addMore(List<Menu> list) {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement("insert into hr_sys_menu(code,pcode,name,url,state,remark) values(?,?,?,?,?,?)");
			for(int i=0;i<list.size();i++) {
				Menu menu = list.get(i);
				pst.setString(1, menu.getCode());
				pst.setString(2, menu.getPcode());
				pst.setString(3, menu.getName());
				pst.setString(4, menu.getUrl());
				pst.setInt(5, menu.getState());
				pst.setString(6, menu.getRemark());
				pst.addBatch();
				if(i%300==0) {
					pst.executeBatch();
					pst.clearBatch();
				}
			}
			pst.executeBatch();
			pst.clearBatch();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, null);
		}
	}

	@Override
	public void deleteById(int id) {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement("delete from hr_sys_menu where id=?");
			pst.setInt(1, id);
			System.out.println("成功删除【"+pst.executeUpdate()+"】条记录");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, null);
		}
	}

	@Override
	public void deleteMore(String ids) {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement("delete from hr_sys_menu where id in ("+ids+")");
			System.out.println("成功删除【"+pst.executeUpdate()+"】条记录");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, null);
		}
	}

	@Override
	public void update(Menu menu) {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement("update hr_sys_menu set code=?,pcode=?,name=?,url=?,state=?,remark=? where id=?");
			pst.setString(1, menu.getCode());
			pst.setString(2, menu.getPcode());
			pst.setString(3, menu.getName());
			pst.setString(4, menu.getUrl());
			pst.setInt(5, menu.getState());
			pst.setString(6, menu.getRemark());
			pst.setInt(7, menu.getId());
			System.out.println("成功更新【"+pst.executeUpdate()+"】条记录");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, null);
		}
	}

	@Override
	public Menu queryById(int id) {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Menu s = null;
		try {
			pst = con.prepareStatement("select * from hr_sys_menu where id=?");
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				s = new Menu();
				s.setId(rs.getInt("id"));
				s.setCode(rs.getString("code"));
				s.setPcode(rs.getString("pcode"));
				s.setUrl(rs.getString("url"));
				s.setName(rs.getString("name"));
				s.setState(rs.getInt("state"));
				s.setRemark(rs.getString("remark"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, rs);
		}
		return s;
	}

	@Override
	public List<Menu> queryAll() {
		List<Menu> list = new ArrayList<>();
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement("select * from hr_sys_menu order by id");
			rs = pst.executeQuery();
			while(rs.next()) {
				Menu s = new Menu();
				s.setId(rs.getInt("id"));
				s.setCode(rs.getString("code"));
				s.setPcode(rs.getString("pcode"));
				s.setUrl(rs.getString("url"));
				s.setName(rs.getString("name"));
				s.setState(rs.getInt("state"));
				s.setRemark(rs.getString("remark"));
				list.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, rs);
		}
		return list;
	}

	@Override
	public List<Menu> queryByPage(int currentPage, int pageSize) {
		List<Menu> list = new ArrayList<>();
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement("select * from hr_sys_menu order by id limit ?,?");
			pst.setInt(1, (currentPage-1)*pageSize);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while(rs.next()) {
				Menu s = new Menu();
				s.setId(rs.getInt("id"));
				s.setCode(rs.getString("code"));
				s.setPcode(rs.getString("pcode"));
				s.setUrl(rs.getString("url"));
				s.setName(rs.getString("name"));
				s.setState(rs.getInt("state"));
				s.setRemark(rs.getString("remark"));
				list.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, rs);
		}
		return list;
	}

	@Override
	public List<Menu> queryByPage(int currentPage, int pageSize, String condition) {
		List<Menu> list = new ArrayList<>();
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement("select * from hr_sys_menu "+condition+" order by id limit ?,?");
			pst.setInt(1, (currentPage-1)*pageSize);
			pst.setInt(2, pageSize);
			rs = pst.executeQuery();
			while(rs.next()) {
				Menu s = new Menu();
				s.setId(rs.getInt("id"));
				s.setCode(rs.getString("code"));
				s.setPcode(rs.getString("pcode"));
				s.setUrl(rs.getString("url"));
				s.setName(rs.getString("name"));
				s.setState(rs.getInt("state"));
				s.setRemark(rs.getString("remark"));
				list.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, rs);
		}
		return list;
	}

	@Override
	public int getTotals() {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement("select count(*) from hr_sys_menu");
			rs = pst.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, rs);
		}
		return 0;
	}

	@Override
	public int getTotals(String condition) {
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement("select count(*) from hr_sys_menu "+condition);
			rs = pst.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, rs);
		}
		return 0;
	}

	@Override
	public List<Menu> queryAllMenusByRids(String rids) {
		List<Menu> list = new ArrayList<>();
		Connection con = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql ="select m.* from hr_sys_role r,hr_sys_menu m,hr_sys_rolemenu rm where r.id=rm.rid and m.id=rm.mid and r.id in ("+rids+") order by r.id";
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()) {
				Menu s = new Menu();
				s.setId(rs.getInt("id"));
				s.setCode(rs.getString("code"));
				s.setPcode(rs.getString("pcode"));
				s.setUrl(rs.getString("url"));
				s.setName(rs.getString("name"));
				s.setState(rs.getInt("state"));
				s.setRemark(rs.getString("remark"));
				list.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.close(con, pst, rs);
		}
		return list;
	}

}
