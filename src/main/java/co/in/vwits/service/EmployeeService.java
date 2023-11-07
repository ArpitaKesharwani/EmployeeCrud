package co.in.vwits.service;

import co.in.vwits.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

	List<Employee> findAll();

	void save(Employee e);

	Optional<Employee> findByEmpID(int rollno);

	void deleteByEmpId(int empId);

	void updateByEmpId(int empId, double modifiedSalary);

	List<Employee> findAllOrderBySalary();

	List<Employee> findAllOrderByName();

}
