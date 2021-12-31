package com.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Employee;
import com.example.repository.EmployeeRepository;

import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;
@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api")
public class EmployeeController {
	@Autowired
	private EmployeeRepository employeeRepository;
@GetMapping("home")
public List<Employee>getEmployee(){
	
	return employeeRepository.findAll();
}
@PostMapping("add")
public Employee addEmployee( @RequestBody Employee employee) {
	return employeeRepository.save(employee);
}

@GetMapping("/getid/{id}")
public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) {
	Employee employee = employeeRepository.findById(id)
			.orElseThrow();
	System.out.println("its working");
	return ResponseEntity.ok(employee);
}
@PutMapping("update/{id}")
public ResponseEntity<Employee> updateEmployee(@PathVariable int id,@RequestBody Employee updateEmployee) {
	Employee employee=employeeRepository.findById(id).orElseThrow();
	employee.setFirstName(updateEmployee.getFirstName());
	employee.setLastName(updateEmployee.getLastName());
	employee.setEmail(updateEmployee.getEmail());
	Employee newEmployee= employeeRepository.save(employee);
	return ResponseEntity.ok(newEmployee);
	
}
@DeleteMapping("delete/{id}")
public ResponseEntity<Map<String,Boolean>> deleteEmployee(@PathVariable int id){
	Employee employee = employeeRepository.findById(id).orElseThrow();
	employeeRepository.delete(employee);
	Map<String, Boolean> response = new HashMap<>();
	response.put("deleted", Boolean.TRUE);
	return ResponseEntity.ok(response);
}

}