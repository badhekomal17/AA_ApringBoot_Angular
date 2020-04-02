package com.zensar.aa_pmo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zensar.aa_pmo.model.Employee;

@Service
public interface RestService {

	public boolean addEmployee(Employee employee);

	public boolean updateEmployee(Employee employee);

	public boolean deleteEmployee(int id);

	public List<Employee> retrieve();

	boolean findEmployee(int empId);
}
