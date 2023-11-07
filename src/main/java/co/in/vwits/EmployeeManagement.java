package co.in.vwits;

import co.in.vwits.model.Employee;
import co.in.vwits.service.EmployeeService;
import co.in.vwits.service.EmployeeServiceImpl;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class EmployeeManagement {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        int option=1;
        Scanner sc = null;
        try {
            sc=new Scanner(System.in);
            EmployeeService emp = new EmployeeServiceImpl();

            do {
                System.out.println("Welcome to student management ");
                System.out.println("1 Find all records");
                System.out.println("2 Insert Records");
                System.out.println("3 Find Employee by EmpID.");
                System.out.println("4 Delete Employee by EmpID");
                System.out.println("5 Update Employee by EmpID");
                System.out.println("6 Employee list in accenting order by salary");
                System.out.println("7 Employee list in accenting order by name");
                System.out.println("-1 to Exit");
                System.out.println("Enter choice");

                option= sc.nextInt();

                switch(option) {
                    case 1:
                        List<Employee> students = emp.findAll();
                        System.out.println(students);
                        break;
                    case 2:
                        Employee e= new Employee();
                        System.out.println("Enter Name:");
                        String name = sc.next();
                        e.setName(name);
                        System.out.println("Enter Salary");
                        double salary = Double.parseDouble(sc.next());
                        e.setSalary(salary);
                        emp.save(e);
                        break;
                    case 3:
                        System.out.println("Enter the EmpID");
                        int empId=sc.nextInt();
                        Optional<Employee> foundEmployee;

                        try {

                            foundEmployee = emp.findByEmpID(empId);
                            System.out.println("Found Employee"+foundEmployee.get());
                        } catch (Exception ex) {
                            System.out.println("Employee Not Found");
                        }

                        break;
                    case 4:
                        System.out.println("Enter the EmpID");
                        try {
                            empId=sc.nextInt();
                            emp.deleteByEmpId(empId);
                        }
                        catch(InputMismatchException ex) {
                            System.out.println("Roll number must be an Integer value");
                            sc.nextLine();
                        }
                        break;

                    case 5:
                        double modifiedSalary;
                        System.out.println("Enter the EmpId");
                        empId=sc.nextInt();
                        System.out.println("Enter new Salary");
                        modifiedSalary=sc.nextDouble();
                        emp.updateByEmpId(empId,modifiedSalary);
                        break;

                    case 6:
                        students = emp.findAllOrderBySalary();
                        System.out.println(students);
                        break;

                    case 7:
                        students = emp.findAllOrderByName();
                        System.out.println(students);
                        break;

                    case -1:
                        System.out.println("--------------------Thank You---------------------");
                        break;

                }

            }while(option!=-1);
        }
        finally {
            sc.close();
        }
    }
}