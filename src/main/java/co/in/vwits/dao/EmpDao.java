package co.in.vwits.dao;

import co.in.vwits.model.Employee;

import java.util.List;
import java.util.Optional;
public interface EmpDao {
    List<Employee> findAll();
    void save(Employee e);
    Optional<Employee> findById(int empId);
    void deleteByEmpId(int empId);
    void updateByEmpId(int empId, double modifiedSalary);
}
