package com.company.yedam.emp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component
public class DeptDAO {
	Connection conn;
	PreparedStatement pstmt;
	
	//singletone
	private static DeptDAO instance;
	public static DeptDAO getInstance() {
		if(instance == null) {
			instance = new DeptDAO();
		}
		return instance;
	}
	//전체조회
	public ArrayList<DeptVO> selectAll(){
		ArrayList<DeptVO> deptList = new ArrayList<DeptVO>();
		DeptVO vo = null;
		
		try {
			conn = JdbcUtil.connect();
			String sql = "SELECT DEPARTMENT_ID,"
							+ " DEPARTMENT_NAME,"
							+ " MANAGER_ID,"
							+ " LOCATION_ID"
							+ " FROM DEPARTMENTS"
							+ "	ORDER BY DEPARTMENT_NAME";
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				vo = new DeptVO();
				vo.setDepartment_id(rs.getString(1));
				vo.setDepartment_name(rs.getString("DEPARTMENT_NAME"));
				vo.setManager_id(rs.getString("MANAGER_ID"));
				vo.setLocation_id(rs.getString("LOCATION_ID"));
				
				deptList.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.disconnect(conn);
		}
		return deptList;
		
	}
	
	//단건조회 
	public DeptVO selectOne(String id) {
		DeptVO vo = null;
		try {
			conn = JdbcUtil.connect();
			String sql = "SELECT DEPARTMENT_ID,"
					+ " DEPARTMENT_NAME,"
					+ " MANAGER_ID,"
					+ " LOCATION_ID"
					+ " FROM DEPARTMENTS"
					+ "	WHERE DEPARTMENT_ID = ? ";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				vo = new DeptVO();
				vo.setDepartment_id(rs.getString("DEPARTMENT_ID"));
				vo.setDepartment_name(rs.getString("DEPARTMENT_NAME"));
				vo.setManager_id(rs.getString("MANAGER_ID"));
				vo.setLocation_id(rs.getString("LOCATION_ID"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.disconnect(conn);
		}
		return vo;
	}
	//1건 입력
	public void insert(DeptVO vo ) {
		try {
			conn = JdbcUtil.connect();
			String sql = "INSERT INTO DEPARTMENTS (DEPARTMENT_ID,"
																								+ "DEPARTMENT_NAME,"
																								+ "MANAGER_ID,"
																								+ "LOCATION_ID)"
																								+ " VALUES(?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getDepartment_id());
			pstmt.setString(2, vo.getDepartment_name());
			pstmt.setString(3, vo.getManager_id());
			pstmt.setString(4, vo.getLocation_id());
			int r = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.disconnect(conn);
		}
	}
	//1건 수정
	public void update(DeptVO vo) {
		try {
			conn = JdbcUtil.connect();
			String sql ="UPDATE DEPARTMENTS "
													 + "SET DEPARTMENT_NAME = ?,"
																+ "MANAGER_ID=?,"
																+ "LOCATION_ID=? "
																+ "WHERE DEPARTMENT_ID=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getDepartment_name());
			pstmt.setString(2, vo.getManager_id());
			pstmt.setString(3, vo.getLocation_id());
			pstmt.setString(4, vo.getDepartment_id());
			
			int r = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.disconnect(conn);
		}
	}
	
}
