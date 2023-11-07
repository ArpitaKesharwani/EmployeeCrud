package co.in.vwits.service;

import co.in.vwits.dao.EmpDao;
import co.in.vwits.dao.EmpDaoImpl;
import co.in.vwits.model.Employee;

import java.util.List;
import java.util.Optional;

public class EmployeeServiceImpl implements EmployeeService {
    public EmpDao emp = new EmpDaoImpl();
    @Override
    public List<Employee> findAll() {
        return emp.findAll();
    }

    @Override
    public void save(Employee e) {
        emp.save(e);
    }

    @Override
    public Optional<Employee> findByEmpID(int empId) {
        return emp.findById(empId);
    }

    @Override
    public void deleteByEmpId(int empId) {
        emp.deleteByEmpId(empId);
    }

    @Override
    public void updateByEmpId(int empId, double modifiedSalary) {
        emp.updateByEmpId(empId,modifiedSalary);
    }

    @Override
    public List<Employee> findAllOrderBySalary() {
        return  this.findAll().stream().sorted((x,y)->{
            return Double.compare(x.getSalary(),y.getSalary());
        }).toList();
    }

    @Override
    public List<Employee> findAllOrderByName() {
        return this.findAll().stream().sorted((x,y)->{
            return x.getName().compareTo(y.getName());
        }).toList();
    }
}
