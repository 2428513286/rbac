package com.lon.dao;

import java.util.List;

import com.lon.entity.SysRoleMenu;
import com.lon.vo.SysRoleMenuVo;

public interface SysRoleMenuDao {

	/**
	 * 新增
	 * @param student
	 */
	public void add(SysRoleMenu roleMenu);
	/**
	 * 批量新增
	 * @param list
	 */
	public void addMore(List<SysRoleMenu> list);
	/**
	 * 根据主键删除
	 * @param id
	 */
	public void deleteById(int id);
	/**
	 * 根据多个主键批量删除
	 * @param ids
	 */
	public void deleteMore(String ids);
	/**
	 * 更新
	 * @param student
	 */
	public void update(SysRoleMenu roleMenu);
	/**
	 * 根据主键查询记录
	 * @param id
	 * @return
	 */
	public SysRoleMenu queryById(int id);
	/**
	 * 查询所有记录
	 * @return
	 */
	public List<SysRoleMenu> queryAll();
	/**
	 * 分页查询记录
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public List<SysRoleMenu> queryByPage(int currentPage,int pageSize);
	public List<SysRoleMenuVo> rqueryByPage(int currentPage,int pageSize);
	/**
	 * 条件分页查询记录
	 * @param currentPage
	 * @param pageSize
	 * @param condition
	 * @return
	 */
	public List<SysRoleMenu> queryByPage(int currentPage,int pageSize,String condition);
	/**
	 * 查询总记录数
	 * @return
	 */
	public int getTotals();
	/**
	 * 根据条件查询总记录数
	 * @param condition
	 * @return
	 */
	public int getTotals(String condition);
	/**
	 * 根据角色编号删除菜单
	 * @param uids
	 */
	public void deleteRoleMenuByRids(String rids);
}
