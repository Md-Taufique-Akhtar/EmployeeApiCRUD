package com.Scalive.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Scalive.Entity.Emp;
import com.Scalive.Service.EmpService;
import com.Scalive.dto.EmpUpdateDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/emp")
public class EmpController {
	private EmpService serv;
	@Autowired
	public EmpController(EmpService serv) {
		this.serv=serv;
	}
	@PostMapping("/add")
	public String addEmp(@Valid @RequestBody Emp emp) {
		return serv.addEmp(emp);
	}
	
	//filtring with the @pathvariable for single record
	@GetMapping("/{empId}")
	public Emp getEmp(@PathVariable("empId") Integer empId) {
		return serv.getEmp(empId);
	}
	@PutMapping("/updated/{empId}")
	public String updateEmp(@Valid @RequestBody EmpUpdateDTO  empDTO,@PathVariable("empId") Integer empId) {
		return serv.updateEmp(empDTO, empId);
	}
	
	@DeleteMapping("/delete/{empId}")
	public String deleteEmp( @PathVariable("empId") Integer empId) {
	    return serv.deleteEmp(empId);
	}
}
