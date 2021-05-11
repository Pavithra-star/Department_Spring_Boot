package com.example.deptcontroller;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.deptrao.DeptRepo;
import com.example.department.Department;

@Controller
public class DeptController {
	@Autowired
	private DeptRepo depts;

	@RequestMapping("/home")
	@ResponseBody
	public void hello() {
		System.out.println("WELCOME");
	}

	@RequestMapping("/deptlist")
	public String getDeptList(ModelMap model) {
		model.addAttribute("depts", depts.findAll());
		return "deptlist";
	}

	@RequestMapping("/adddept")
	public String addDept(ModelMap model) {
		Department d = new Department();
		model.addAttribute("dept", d);
		return "add_dept";
	}
	@RequestMapping("/deletedept/{id}")
	public String deleteDept(@PathVariable("id") int id, ModelMap model) {
		java.util.Optional<Department> dept = depts.findById(id);
		if (dept.isPresent()) {
			depts.delete(dept.get());
			return "redirect:/deptlist";
		} else {
			model.addAttribute("error", "Department Id Not Found");
			return "delete_dept";
		}
	}


	@RequestMapping(value = "/adddept", method = RequestMethod.POST)
	public String addDept(@Valid Department d, Errors errors, ModelMap model) {
		try {
			if (errors.getErrorCount() > 0)
				throw new RuntimeException(errors.toString());

			if (depts.findById(d.getId()).isPresent())
				throw new RuntimeException("DeptId exit");

			depts.save(d);
			return "redirect:/deptlist";
		} catch (Exception ex) {
			model.addAttribute("dept", d);
			model.addAttribute("message", ex.getMessage());
			return "add_dept";

		}
		
	}
}
