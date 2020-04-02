package com.zensar.aa_pmo.controller;

import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.zensar.aa_pmo.model.Employee;
import com.zensar.aa_pmo.service.RestService;

@RestController
@EnableWebMvc
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/AA")
public class Controller {

	@Autowired
	RestService service;

	@PostMapping(value = "/AddEmployee", consumes = { "application/xml", "application/json" }, produces = {
			"application/xml", "application/json" })
	public Employee addEmployee(@RequestBody Employee employee) {
		System.out.println(employee);
		boolean findEmployee = service.findEmployee(employee.getEmpId());
		boolean addEmployee;
		if (findEmployee == false) {
			addEmployee = service.addEmployee(employee);
			System.out.println("Employee is : " + employee);
			if (addEmployee = true) {
				System.out.println("Find Employee = false Employee Insertion Successful");
				System.out.println(addEmployee);
				return employee;
			} else {
				System.err.println("Find Employee = false Employee Insertion Failed");
				return employee;
			}
		} else {
			System.err.println("Find Employee = true Employee Insertion Failed");
			return null;
		}
	}

	@PutMapping(value = "/UpdateEmployee", consumes = { "application/xml", "application/json" }, produces = {
			"application/xml", "application/json" })
	public Employee updateEmployee(@RequestBody Employee employee) {
		System.out.println(employee);
		boolean findEmployee = service.findEmployee(employee.getEmpId());
		boolean updateEmployee;
		if (findEmployee == true) {
			updateEmployee = service.addEmployee(employee);
			System.out.println("Employee is : " + employee);
			if (updateEmployee = true) {
				System.out.println("Find Employee = true Employee Updation Successful");
				System.out.println(updateEmployee);
				return employee;
				// return "Employee Insertion Successful";
			} else {
				System.err.println("Find Employee = true Employee Updation Failed");
				return employee;
				// return "Employee Insertion Failed";
			}
		} else {
			System.err.println("Find Employee = false Employee Updation Failed");
			return null;
		}
	}

	@DeleteMapping(value = "/DeleteEmployee/{id}", consumes = { "application/xml", "application/json" }, produces = {
			"application/xml", "application/json" })
	public String deleteEmployee(@PathVariable("id") int id) {

		boolean findEmployee = service.findEmployee(id);
		boolean updateEmployee;
		try {
			if (findEmployee == true) {
				service.deleteEmployee(id);
				System.out.println("Employee is : " + id);
				System.out.println("Employee Deletion Successful");
				return "Employee Deletion Successful";
			} else {
				System.err.println("Find Employee = false Employee Deletion Failed");
				return "Employee Deletion Failed";
			}

		} catch (Exception e) {
			System.err.println("Exception occured while deleteing the employee");
			System.err.println(e);
			return "Exception : " + e;
		}

	}

	@GetMapping(value = "/RetrieveEmployee")
	public List<Employee> retriveEmployee() {
		List<Employee> retrieve = service.retrieve();
		System.out.println("Employee Retrieval Successful");
		return retrieve;
	}
}

/*
 * Testing JSON Format { "srNo":"0", "empId":"10000", "empName":"karan",
 * "empDesignation":"something", "empNumber":"9762866765",
 * "email":"karan.shah@zensar.com", "password":"kshah9271", "status":"Verified"
 * }
 */

/*
 * Testing XML Format <Employee> <empId>11100</empId> <empName>karan</empName>
 * <empDesignation>something</empDesignation> <empNumber>9762866765</empNumber>
 * <email>karan.shah@zensar.com</email> <password>kshah9271</password>
 * <status>Verified</status> </Employee>
 */