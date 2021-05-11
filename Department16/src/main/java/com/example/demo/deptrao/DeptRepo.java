package com.example.demo.deptrao;

 import org.springframework.data.repository.CrudRepository;

import com.example.department.Department;



public interface DeptRepo extends CrudRepository<Department, Integer> {

	

}