package com.zensar.aa_pmo.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zensar.aa_pmo.model.Employee;
import com.zensar.aa_pmo.repository.RestRepository;

@Service
public class ServiceImpl implements RestService {

	@Autowired
	RestRepository repository;

	@Override
	public boolean addEmployee(Employee employee) {

		Employee save = repository.save(employee);
		if (save != null) {
			System.out.println("Into Insert-Service Successful");
			return true;
		} else {
			System.out.println("Into Insert-Service Failed");
			return false;
		}
	}

	@Override
	public boolean updateEmployee(Employee employee) {
		repository.deleteById(employee.getEmpId());
		Employee save = repository.save(employee);
		if (save != null) {
			System.out.println("Into Update-Service Successful");
			return true;
		} else {
			System.out.println("Into Update-Service Failed");
			return false;
		}
	}

	@Override
	public boolean findEmployee(int empId) {
		// int employeeId = Integer.parseInt(empId);
		System.out.println("EmpId : " + empId);
		Optional<Employee> find = repository.findById(empId);
		System.out.println("Find : " + find);
		Optional<Employee> xyz = Optional.empty();
		if (find == xyz) {
			System.out.println("Into Find-Service Failed");
			return false;
		} else if (find.get().getEmpId() > 0) {
			System.out.println("Into Find-Service Successful");
			return true;
		} else {
			System.out.println("Into Find-Service Successful");
			return true;
		}
	}

	@Override
	public boolean deleteEmployee(int id) {
		try {
			repository.deleteById(id);
			System.out.println("Into Delete-Service Successful");
			return true;
		}
		/*	
		 * if (save != null) return true; else return false;
		 */
		catch (Exception e) {
			System.out.println("EXCEPTION OCCURED");
			System.out.println("Into Delete-Service Failed");
			return false;
		}
	}

	@Override
	public List<Employee> retrieve() {
		List<Employee> findAll = (List<Employee>) repository.findAll();
		return findAll;
	}

}
