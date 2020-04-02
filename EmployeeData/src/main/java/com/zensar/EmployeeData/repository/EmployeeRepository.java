package com.zensar.EmployeeData.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zensar.EmployeeData.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}