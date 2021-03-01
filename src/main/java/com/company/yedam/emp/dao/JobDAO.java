package com.company.yedam.emp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component
public class JobDAO {
	Connection conn;
	PreparedStatement pstmt;
	
	private static JobDAO instance;
	public static JobDAO getInstance() {
		if(instance == null) {
			instance = new JobDAO();
		}
		return instance;
	}
	public ArrayList<JobVO> selectAll(){
		ArrayList<JobVO> jobList = new ArrayList<JobVO>();
		JobVO vo = null;
		try {
			conn = JdbcUtil.connect();
			String sql = "SELECT JOB_ID,"
							 + " JOB_TITLE,"
							 + " MIN_SALARY,"
							 + " MAX_SALARY"
							 + " FROM JOBS "
							 + " ORDER BY JOB_TITLE";
			
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				vo = new JobVO();
				vo.setJob_id(rs.getString(1));
				vo.setJob_title(rs.getString("JOB_TITLE"));
				vo.setMin_salary(rs.getString("MIN_SALARY"));
				vo.setMax_salary(rs.getString("MAX_SALARY"));
				
				jobList.add(vo);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			JdbcUtil.disconnect(conn);
		}
		
		
		
		return jobList;
	}
}
