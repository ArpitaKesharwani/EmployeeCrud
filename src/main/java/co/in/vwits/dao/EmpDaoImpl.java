package co.in.vwits.dao;

import co.in.vwits.model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmpDaoImpl implements EmpDao {
    @Override
    public List<Employee> findAll() {
        List<Employee> students = new ArrayList<>();
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Employee", "root", "root");
             PreparedStatement pstmt = con.prepareStatement("SELECT * FROM empdata ");) {

            ResultSet rs = pstmt.executeQuery();// firing query-
            while (rs.next()) { // this method returns true if any
                // records are present
                Employee foundEmp = new Employee();
                foundEmp.setId(rs.getInt(1));
                foundEmp.setName(rs.getString(2));
                foundEmp.setSalary(rs.getDouble(3));
                students.add(foundEmp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public void save(Employee e) {

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Employee", "root", "root");
             PreparedStatement pstmt = con.prepareStatement("Insert into empdata (name,salary) values (?,?)",
                     Statement.RETURN_GENERATED_KEYS);) {

            pstmt.setString(1, e.getName());
            pstmt.setDouble(2, e.getSalary());
            int no = pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    e.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating Employee failed, no ID obtained.");
                }
            }

            System.out.println("Number of row added " + no);
            System.out.println(e);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Optional<Employee> findById(int empId) {
        Employee emp = null;
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Employee", "root", "root");
             PreparedStatement pstmt = con.prepareStatement("select * from empdata where id = ?");) {
            pstmt.setInt(1, empId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getDouble(3));
                    emp = new Employee(rs.getInt(1), rs.getString(2), rs.getDouble(3));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Optional.ofNullable(emp);
    }

    @Override
    public void deleteByEmpId(int empId) {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Employee", "root", "root");
             PreparedStatement pstmt = con.prepareStatement("delete from empdata where id = ?");) {
            pstmt.setInt(1, empId);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Record with ID " + empId + " deleted successfully.");
            } else {
                System.out.println("No record found with ID " + empId + ". Nothing deleted.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void updateByEmpId(int empId, double modifiedSalary) {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Employee", "root", "root");
             PreparedStatement pstmt = con.prepareStatement("UPDATE empdata SET salary = ? WHERE id = ?");) {
            pstmt.setDouble(1, modifiedSalary);
            pstmt.setInt(2, empId);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Record with ID " + empId + " updated successfully.");
            } else {
                System.out.println("No record found with ID " + empId + ". Nothing updated.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
