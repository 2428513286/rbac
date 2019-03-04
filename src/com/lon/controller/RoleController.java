package com.lon.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lon.dao.MenuDao;
import com.lon.dao.RoleDao;
import com.lon.dao.SysRoleMenuDao;
import com.lon.dao.SysUserRoleDao;
import com.lon.dao.impl.MenuDaoImpl;
import com.lon.dao.impl.RoleDaoImpl;
import com.lon.dao.impl.SysRoleMenuDaoImpl;
import com.lon.dao.impl.SysUserRoleDaoImpl;
import com.lon.entity.Menu;
import com.lon.entity.Role;
import com.lon.entity.SysRoleMenu;
import com.lon.entity.SysUserRole;

@Controller
@RequestMapping("/role")
public class RoleController {

	private HttpServletRequest request;
	private HttpServletResponse response;
	@RequestMapping("/add")
	public void add(Role role) {
		JSONObject jo = new JSONObject();
		PrintWriter out = null;
		try {
			out = response.getWriter();
			RoleDao dao = new RoleDaoImpl();
			dao.add(role);
			jo.put("state", 0);
			jo.put("msg", "成功新增记录");
		}catch(Exception e) {
			jo.put("state", -1);
			jo.put("msg", "新增记录失败："+e.getMessage());
		}finally {
			String str = JSON.toJSONString(jo);
			System.out.println(str);
			out.write(str);
			out.flush();
			out.close();
		}
	}
	@RequestMapping("/deleteMore")
	public void deleteMore(String ids) {
		JSONObject jo = new JSONObject();
		PrintWriter out = null;
		try {
			out = response.getWriter();
			RoleDao dao = new RoleDaoImpl();
			dao.deleteMore(ids);
			jo.put("state", 0);
			jo.put("msg", "成功删除记录");
		}catch(Exception e) {
			jo.put("state", -1);
			jo.put("msg", "删除记录失败："+e.getMessage());
		}finally {
			String str = JSON.toJSONString(jo);
			System.out.println(str);
			out.write(str);
			out.flush();
			out.close();
		}
	}
	@RequestMapping("/update")
	public void update(Role role) {
		JSONObject jo = new JSONObject();
		PrintWriter out = null;
		try {
			out = response.getWriter();
			RoleDao dao = new RoleDaoImpl();
			dao.update(role);
			jo.put("state", 0);
			jo.put("msg", "成功修改记录");
		}catch(Exception e) {
			jo.put("state", -1);
			jo.put("msg", "修改记录失败："+e.getMessage());
		}finally {
			String str = JSON.toJSONString(jo);
			System.out.println(str);
			out.write(str);
			out.flush();
			out.close();
		}
	}
	@RequestMapping("/queryByPage")
	public void queryByPage(String page) {
		
		String qname = request.getParameter("qname");
		String qusername = request.getParameter("qusername");
		String qsex = request.getParameter("qsex");
		String qbeginDate = request.getParameter("qbeginDate");
		String qendDate = request.getParameter("qendDate");
		//获取每页显示记录数
		String rows = request.getParameter("rows");
	
		String condition = " where 1=1 ";
		if(qname!=null&&!qname.equals("")&&!qname.equalsIgnoreCase("null")){
			condition += " and name like '%"+qname+"%'";
		}
		if(qusername!=null&&!qusername.equals("")&&!qname.equalsIgnoreCase("null")){
			condition += " and username like '%"+qusername+"%'";
		}
		if(qsex!=null&&!qsex.equals("")&&!qsex.equals("-1")&&!qname.equalsIgnoreCase("null")){
			condition += " and sex = "+qsex;
		}
		if(qbeginDate!=null&&!qbeginDate.equals("")) {
			condition += " and birthday >= '"+qbeginDate+"'";
		}
		if(qendDate!=null&&!qendDate.equals("")) {
			condition += " and birthday <= '"+qendDate+"'";
		}
		RoleDao dao = new RoleDaoImpl();
		//当前页
		int sp = 1;
		//总记录数
		int totals = dao.getTotals(condition);
		//每页记录数
		int pageSize = Integer.parseInt(rows);
		//总页数
		int pageCounts = totals/pageSize;
		if(totals%pageSize!=0){
			pageCounts++;
		}
		try{
			sp = Integer.parseInt(page);
		}catch(Exception e){
			sp = 1;
		}
		if(sp>pageCounts){
			sp = pageCounts;
		}
		if(sp<1){
			sp = 1;
		}
		List<Role> list = dao.queryByPage(sp, pageSize,condition);
		try {
			PrintWriter out = response.getWriter();
			JSONObject jo = new JSONObject();
			jo.put("total", totals);
			jo.put("rows", list);
			String json = JSON.toJSONString(jo);
			System.out.println(json);
			out.write(json);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping("/queryAll")
	public void queryAll() {
		RoleDao dao = new RoleDaoImpl();
		List<Role> list = dao.queryAll();
		try {
			PrintWriter out = response.getWriter();
			JSONObject jo = new JSONObject();
			jo.put("total", list.size());
			jo.put("rows", list);
			String json = JSON.toJSONString(jo);
			System.out.println(json);
			out.write(json);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/saveMenu")
	public void saveMenu(String rids,String mids) {
		SysRoleMenuDao RoleMenuDao = new SysRoleMenuDaoImpl();
		//删除角色已有的所有菜单
		RoleMenuDao.deleteRoleMenuByRids(rids);
		//添加菜单
		List<SysRoleMenu> list = new ArrayList<>();
		for(String rid:rids.split(",")) {
			for(String mid:mids.split(",")) {
				SysRoleMenu Role = new SysRoleMenu();
				Role.setRid(Integer.parseInt(rid));
				Role.setMid(Integer.parseInt(mid));
				list.add(Role);
			}
		}
		JSONObject jo = new JSONObject();
		PrintWriter out = null;
		try {
			out = response.getWriter();
			RoleMenuDao.addMore(list);
			jo.put("state", 0);
			jo.put("msg", "成功分配菜单");
		}catch(Exception e) {
			jo.put("state", -1);
			jo.put("msg", "分配菜单失败："+e.getMessage());
		}finally {
			String str = JSON.toJSONString(jo);
			System.out.println(str);
			out.write(str);
			out.flush();
			out.close();
		}
	}
	
	@RequestMapping("/getOwnerMenus")
	public void getOwnerMenus(String rids) {
		try {
			PrintWriter out = response.getWriter();
			MenuDao dao = new MenuDaoImpl();
			List<Menu> list = dao.queryAllMenusByRids(rids);
			String str = JSON.toJSONString(list);
			System.out.println(str);
			out.write(str);
			out.flush();
			out.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 处理参数为日期格式
	 * @param binder
	 */
	@InitBinder
    public void initBinder(ServletRequestDataBinder binder){
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }
	@ModelAttribute
	public void setRequestAndResponse(HttpServletRequest request,HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.response.setContentType("text/html;charset=utf-8");
	}
}
