package com.company.yedam.emp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

/*
 * VO :Value Object ==EmpDTO EmpDO EmpVO
 * DAO : Data Access Object
 * 
 */
@Component
public class EmpDAO {
	
	Connection conn;
	PreparedStatement pstmt;
	

	
	
	//전체조회 select * from employees
	public ArrayList<EmpVO> selectList(){
		ArrayList<EmpVO> list = new ArrayList<EmpVO>();
		EmpVO vo = null;
		try {
			conn = JdbcUtil.connect();
			String sql = "SELECT 	EMPLOYEE_ID,"
								+ " FIRST_NAME,"
								+ " LAST_NAME,"
								+ " EMAIL,"
								+ " PHONE_NUMBER,"
								+ " to_char(HIRE_DATE, 'yyyy-mm') as HIRE_DATE,"
								+ " JOB_ID,"
								+ " to_char(SALARY, 'FML999,999') as SALARY,"
								+ " COMMISSION_PCT,"
								+ " MANAGER_ID,"
								+ " DEPARTMENT_ID"
								+ " FROM EMPLOYEES "
								+ "	ORDER BY EMPLOYEE_ID";
			pstmt = conn.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				vo = new EmpVO();
				vo.setEmployee_id(rs.getString(1));
				vo.setFirst_name(rs.getString("FIRST_NAME"));
				vo.setLast_name(rs.getString("LAST_NAME"));
				vo.setEmail(rs.getString("EMAIL"));
				vo.setSalary(rs.getString("SALARY"));
				vo.setHire_date(rs.getString("HIRE_DATE"));
				
				list.add(vo);
				
				
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			JdbcUtil.disconnect(conn);
		}
		
		return list;
		
	}
	
	
	//단건조회 select * from employees where employee_id = ?
	public EmpVO selectOne(String id) {
		EmpVO vo = null;
		try {
			conn = JdbcUtil.connect();
			String sql = "SELECT 	EMPLOYEE_ID,"
								+ " FIRST_NAME,"
								+ " LAST_NAME,"
								+ " EMAIL,"
								+ " PHONE_NUMBER,"
								+ " HIRE_DATE,"
								+ " JOB_ID,"
								+ " SALARY,"
								+ " COMMISSION_PCT,"
								+ " MANAGER_ID,"
								+ " DEPARTMENT_ID"
								+ " FROM EMPLOYEES "
								+ "	WHERE EMPLOYEE_ID = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				vo = new EmpVO();
				vo.setEmployee_id(rs.getString(1));
				vo.setFirst_name(rs.getString("FIRST_NAME"));
				vo.setLast_name(rs.getString("LAST_NAME"));
				vo.setEmail(rs.getString("EMAIL"));
				vo.setPhone_number(rs.getString("PHONE_NUMBER"));
				vo.setHire_date(rs.getString("HIRE_DATE"));
				vo.setJob_id(rs.getString("JOB_ID"));
				vo.setDepartment_id(rs.getString("DEPARTMENT_ID"));
				vo.setManager_id(rs.getString("MANAGER_ID"));
				
				
				
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			JdbcUtil.disconnect(conn);
		}
		
		
		return vo;
	}
	//이메일조회
	public EmpVO selectOneByEmail(String id) {
		EmpVO vo = null;
		try {
			conn = JdbcUtil.connect();
			String sql = "SELECT 	EMPLOYEE_ID,"
								+ " FIRST_NAME,"
								+ " LAST_NAME,"
								+ " EMAIL,"
								+ " PHONE_NUMBER,"
								+ " HIRE_DATE,"
								+ " JOB_ID,"
								+ " SALARY,"
								+ " COMMISSION_PCT,"
								+ " MANAGER_ID,"
								+ " DEPARTMENT_ID"
								+ " FROM EMPLOYEES "
								+ "	WHERE EMAIL= ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				vo = new EmpVO();
				vo.setEmployee_id(rs.getString(1));
				vo.setFirst_name(rs.getString("FIRST_NAME"));
				vo.setLast_name(rs.getString("LAST_NAME"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			JdbcUtil.disconnect(conn);
		}
		
		
		return vo;
	}
	
	
	
	public void insert(EmpVO vo) {
		try {
		//1. connect
		conn = JdbcUtil.connect();
		//2. statement(실행할sql구문)
		String sql =  "INSERT INTO EMPLOYEES (EMPLOYEE_ID,"
							+ " LAST_NAME,"
							+ " EMAIL,"
							+ " HIRE_DATE,"
							+ " JOB_ID) "
							+ " VALUES(?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		//3. execute(구문 실행)
		pstmt.setString(1, vo.getEmployee_id());
		pstmt.setString(2, vo.getLast_name());
		pstmt.setString(3, vo.getEmail());
		pstmt.setString(4, vo.getHire_date());
		pstmt.setString(5, vo.getJob_id());
		int r = pstmt.executeUpdate();
		System.out.println(r + " 건이 등록됨");
		//4. resultset(select라면 조회결과처리)
		}catch(Exception e) {
			e.printStackTrace();
		}finally {	
			//5. close(연결해제)
			JdbcUtil.disconnect(conn);
		}

	}
	public void update(EmpVO vo) {
		try {
		//1. connect
			conn = JdbcUtil.connect();
		//2. statement(실행할sql구문)
			String sql ="UPDATE employees SET email = ?,"
																					+ " phone_number = ?"
																					+ " where employee_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
		//3. execute(구문 실행)
			pstmt.setString(1, vo.getEmail());
			pstmt.setString(2, vo.getPhone_number());
			pstmt.setString(3, vo.getEmployee_id());
			int r = pstmt.executeUpdate();
		//4. resultset(select라면 조회결과처리)
		}catch(Exception e) {
			e.printStackTrace();
		}finally {	
			//5. close(연결해제)
			JdbcUtil.disconnect(conn);
		}
		
	}
}
