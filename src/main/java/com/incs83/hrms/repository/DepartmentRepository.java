package com.incs83.hrms.repository;

import com.incs83.hrms.entity.Department;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepository extends CrudRepository<Department,String> {

}
