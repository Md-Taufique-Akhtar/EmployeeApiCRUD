package com.Scalive.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EmpUpdateDTO {
	@Size(min=1, message="Ename can not be empty")
	private String ename;
	
	@Min(value=10000, message="Atleast 10000")
	private Double sal;
}
