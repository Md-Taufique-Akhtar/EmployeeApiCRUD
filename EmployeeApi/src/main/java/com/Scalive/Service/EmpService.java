package com.Scalive.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Scalive.Entity.Emp;
import com.Scalive.Exception.EmpAlreadyExistsException;
import com.Scalive.Exception.NoSuchEmpExistException;
import com.Scalive.Repository.EmpRepository;
import com.Scalive.dto.EmpUpdateDTO;

@Service
public class EmpService {
	private EmpRepository empRepo;
	@Autowired
	public EmpService(EmpRepository empRepo) {
		this.empRepo=empRepo;
	}
	
	//addEmp use for inserting the data
	public String addEmp(Emp emp) {
		Emp e=empRepo.findByEname(emp.getEname()).orElse(null);
		
		if(e !=null) {
			throw new EmpAlreadyExistsException("Emp with name "+emp.getEname()+" Already  exist");
		}
		empRepo.save(emp);
		return "Emp saved Successfully";
	}
	
	
	public Emp getEmp(Integer empId) {
		Emp e = empRepo.findById(empId).orElse(null);
		if(e==null) {
			throw new NoSuchEmpExistException("Emp with id "+empId+" does not exists");
		}
		return e;
	}


	//obj(ename, sal), id(will find is present or not) for update 
	public String updateEmp(EmpUpdateDTO empDTO, Integer empId) {
		Emp e=empRepo.findById(empId).orElse(null);
		if(e==null) {
			throw new NoSuchEmpExistException("Emp with id "+empId+" does not exists");
		}
		if(empDTO.getEname()== null && empDTO.getSal() == null) {
			throw new RuntimeException("Empty Object not Allow  for updation!");
		}
		if(empDTO.getEname()!=null) {
			e.setEname(empDTO.getEname());
		}
		if(empDTO.getSal()!=null) {
			e.setSal(empDTO.getSal());
		}
		empRepo.save(e);
		return "Updated Successully!";
	}
	
	public String deleteEmp(Integer empId) {
	    Emp e = empRepo.findById(empId).orElse(null);

	    if (e == null) {
	        throw new NoSuchEmpExistException("Emp with ID " + empId + " does not exist");
	    }

	    empRepo.delete(e);

	    return "Emp with ID " + empId + " deleted successfully!";
	}

}
