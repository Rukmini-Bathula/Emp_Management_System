package com.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.demo.entities.Employee;
import com.demo.service.EmpService;

import jakarta.servlet.http.HttpSession;

@Controller
public class EmpController {

	
	@Autowired
	private EmpService service;
	
	@GetMapping("/index")
	public String home(Model m)
	{
		List<Employee> emp=service.getAllEmp();
		m.addAttribute("emp",emp);
		return "index";
	}
	@GetMapping("/addemp")
	public String addempForm()
      {
		return "add_emp";
		}
	@PostMapping("/register")
	public String empRegister(@ModelAttribute Employee e,HttpSession session)
	{
		System.out.println(e);
		
		service.addEmp(e);
		session.setAttribute("message","Employee added successfully");
		//session.removeAttribute("message");
		return "redirect:/index";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable int id,Model m)
	{
		Employee emp=service.getEmpById(id);
		m.addAttribute("emp",emp);
		return "edit";
	}
	@PostMapping("/update")
	public String updateEmp(@ModelAttribute Employee emp,HttpSession session)
	{
		service.addEmp(emp);
		session.setAttribute("message","Employee data updated successfully");
		return "redirect:/index";
	}
	@GetMapping("/delete/{id}")
	public String deleteEmp(@PathVariable int id,HttpSession session)
	{
		service.deleteEmp(id);
		session.setAttribute("message","Employee data deleted successfully");
		return "redirect:/index";
	}
}
