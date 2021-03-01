package com.company.yedam.emp.controller;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.yedam.HomeController;
import com.company.yedam.emp.dao.DeptDAO;
import com.company.yedam.emp.dao.DeptVO;
import com.company.yedam.emp.dao.EmpDAO;
import com.company.yedam.emp.dao.EmpVO;
import com.company.yedam.emp.dao.JobDAO;

@Controller //@Component  1. 컨테이너 빈으로 등록, 컨트롤러화
public class EmpController {
	Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Autowired EmpDAO empDAO;
	@Autowired JobDAO jobDAO;
	@Autowired DeptDAO deptDAO;
	
	//사원목록(emp)
	@RequestMapping("/empList")
	public String empList(HttpServletRequest request) {
		request.setAttribute("list", empDAO.selectList());
		return "emp/empList";
	}
	//부서목록(dept)
	@RequestMapping("/deptList")
	public String deptList(HttpServletRequest request) {
		request.setAttribute("deptList", deptDAO.selectAll());
		return "emp/deptList";
	}
	//사원등록품(emp)
	@GetMapping("/empInsert")
	public String empInsert(HttpServletRequest request) {
		
		request.setAttribute("jobList", jobDAO.selectAll());
		request.setAttribute("deptList", deptDAO.selectAll());
		return "emp/empInsert";
		
	}
	//부서등록폼(dept)
	@GetMapping("/deptInsert")
	public String deptInsert(HttpServletRequest request) {
		request.setAttribute("jobList", jobDAO.selectAll());
		request.setAttribute("list", empDAO.selectList());
		return"emp/deptInsert";
	}
	//사원등록처리(emp)
	@PostMapping("/empInsert")
	public String empInsertProc(EmpVO vo) {
		logger.debug(vo.toString());
		empDAO.insert(vo);
		return "redirect:empList";
	}
	//부서등록처리(dept)
	@PostMapping("/deptInsert")
	public String deptInsertProc(DeptVO vo) {
		logger.debug(vo.toString());
		deptDAO.insert(vo);
		return "redirect:deptList";
	}
	//사원수정폼(emp)
	@RequestMapping("/empUpdate")
	public String empUpdate(HttpServletRequest request) {
		String empid = request.getParameter("employee_id");
		request.setAttribute("empVO", empDAO.selectOne(empid));
		request.setAttribute("jobList", jobDAO.selectAll());
		request.setAttribute("deptList", deptDAO.selectAll());
		return "emp/empInsert";
	}
	//부서수정폼(dept)
	@RequestMapping("/deptUpdate")
	public String deptUpdate(HttpServletRequest request) {
		String deptid= request.getParameter("department_id");
		request.setAttribute("deptVO", deptDAO.selectOne(deptid));
		request.setAttribute("jobList", jobDAO.selectAll());
		request.setAttribute("list", empDAO.selectList());
		return "emp/deptInsert";
	}
	//사원수정처리(emp)
	@PostMapping("/empUpdate")
	public String empUpdateProc(EmpVO vo) {
		logger.debug(vo.toString());
		empDAO.update(vo);
		return "redirect:empList";
	}
	//부서수정처리(dept)
	@PostMapping("/deptUpdate")
	public String deptUpdateProc(DeptVO vo) {
		logger.debug(vo.toString());
		deptDAO.update(vo);
		return "redirect:deptList";
	}
	//이메일체크(emp)
	
	//사원검색(emp)
	@RequestMapping("/empSearch")
	public String empSearch(HttpServletRequest request) {
		request.setAttribute("list", empDAO.selectList());
		return "emp/empSearch";
	}
	
	
}
