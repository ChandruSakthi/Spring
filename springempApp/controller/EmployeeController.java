package org.jsp.springempApp.controller;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;

import org.jsp.springempApp.EmployeeConfig;
import org.jsp.springempApp.dao.EmployeeDao;
import org.jsp.springempApp.dto.Employee;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class EmployeeController {
	static EmployeeDao dao;
	static Scanner input = new Scanner(System.in);
	static {
		ApplicationContext context = new AnnotationConfigApplicationContext(EmployeeConfig.class);
		dao = context.getBean(EmployeeDao.class);
	}

	public static void main(String[] args) {
		System.out.println("1.save Employee");
		System.out.println("2.Update Employee");
		System.out.println("3.Find Employee");
		System.out.println("4.Verify Employee by phone and password");
		System.out.println("5.Verify Employee by email and password");
		System.out.println("6.Verify Employee by Id and password");
		System.out.println("7.Find Employee by desg");
		System.out.println("8.Find Employee by Name");
		System.out.println("9.Find Employee by Salary");
		System.out.println("10.Filter By Salary");
		switch (input.nextInt()) {
		case 1: {
			save();
			break;
		}
		case 2: {
			update();
			break;
		}
		case 3: {
			findById();
			break;
		}
		case 4: {
			verifyByPhone();
			break;
		}
		case 5: {
			verifyByEmail();
			break;
		}
		case 6: {
			verifyById();
			break;
		}
		case 7: {
			findByDesg();
			break;
		}
		case 8: {
			findByName();
			break;
		}
		case 9: {
			findBySalary();
			break;
		}
		case 10: {
			FilterBySalary();
			break;
		}
		default: {
			System.err.println("Invalid Choice");
		}
		}
	}

	public static void save() {
		System.out.println("Enter the name,phone,email,salary,designation and password to register");
		Employee e = new Employee();
		e.setName(input.next());
		e.setPhone(input.nextLong());
		e.setEmail(input.next());
		e.setSalary(input.nextDouble());
		e.setDesig(input.next());
		e.setPassword(input.next());
		e = dao.saveEmployee(e);
		System.out.println("Employee saved with Id:" + e.getId());
	}

	public static void update() {
		System.out.println("Enter the Employee Id to update");
		int eid = input.nextInt();
		System.out.println("Enter the name,phone,email,salary,designation and password to Update");
		Employee e = new Employee();
		e.setId(eid);
		e.setName(input.next());
		e.setPhone(input.nextLong());
		e.setEmail(input.next());
		e.setSalary(input.nextDouble());
		e.setDesig(input.next());
		e.setPassword(input.next());
		e = dao.updateEmployee(e);
		if (e != null)
			System.out.println("Employee  with Id:" + e.getId() + " Updated");
		else
			System.err.println("Cannot Update Employee as entered Id is invalid");
	}

	public static void findById() {
		System.out.println("Enter the Employee Id to display details");
		int eid = input.nextInt();
		Employee e = dao.findById(eid);
		if (e != null) {
			System.out.println("Employee Id:" + e.getId());
			System.out.println("Employee Name:" + e.getName());
			System.out.println("Designation:" + e.getDesig());
			System.out.println("Phone Number:" + e.getPhone());
			System.out.println("Email Id:" + e.getEmail());
			System.out.println("Salary:" + e.getSalary());
		} else {
			System.err.println("You have entered an Invalid Employee Id");
		}
	}

	public static void verifyByPhone() {
		System.out.println("Enter Your Phone Number and Password to verify");
		long phone = input.nextLong();
		String password = input.next();
		Employee e = dao.verifyEmployee(phone, password);
		if (e != null) {
			System.out.println("verification succesfull");
			System.out.println("Employee Id:" + e.getId());
			System.out.println("Employee Name:" + e.getName());
			System.out.println("Designation:" + e.getDesig());
			System.out.println("Phone Number:" + e.getPhone());
			System.out.println("Email Id:" + e.getEmail());
			System.out.println("Salary:" + e.getSalary());
		} else {
			System.err.println("Invalid Phone Number or Password");
		}
	}

	public static void verifyByEmail() {
		System.out.println("Enter Your Email Id and password to verify");
		String email = input.next();
		String password = input.next();
		Employee e = dao.verifyEmployee(email, password);
		if (e != null) {
			System.out.println("verification succesfull");
			System.out.println("Employee Id:" + e.getId());
			System.out.println("Employee Name:" + e.getName());
			System.out.println("Designation:" + e.getDesig());
			System.out.println("Phone Number:" + e.getPhone());
			System.out.println("Email Id:" + e.getEmail());
			System.out.println("Salary:" + e.getSalary());
		} else {
			System.err.println("Invalid Email Id or password");
		}
	}

	public static void verifyById() {
		System.out.println("Enter Your Employee Id and password to verify");
		int id = input.nextInt();
		String password = input.next();
		Employee e = dao.verifyEmployee(id, password);
		if (e != null) {
			System.out.println("verification succesfull");
			System.out.println("Employee Id:" + e.getId());
			System.out.println("Employee Name:" + e.getName());
			System.out.println("Designation:" + e.getDesig());
			System.out.println("Phone Number:" + e.getPhone());
			System.out.println("Email Id:" + e.getEmail());
			System.out.println("Salary:" + e.getSalary());
		} else {
			System.err.println("Invalid Employee Id or password");
		}
	}

	public static void findByDesg() {
		System.out.println("Enter the designation to find Employees");
		String desg = input.next();
		List<Employee> emps = dao.findByDesg(desg);
		if (emps.size() > 0)
			for (Employee e : emps) {
				System.out.println("Employee Id:" + e.getId());
				System.out.println("Employee Name:" + e.getName());
				System.out.println("Designation:" + e.getDesig());
				System.out.println("Phone Number:" + e.getPhone());
				System.out.println("Email Id:" + e.getEmail());
				System.out.println("Salary:" + e.getSalary());
			}
		else
			System.err.println("No Employee with entered Designation");
	}

	public static void findByName() {
		System.out.println("Enter the designation to find Employees");
		String name = input.next();
		List<Employee> emps = dao.findByName(name);
		if (emps.size() > 0)
			for (Employee e : emps) {
				System.out.println("Employee Id:" + e.getId());
				System.out.println("Employee Name:" + e.getName());
				System.out.println("Designation:" + e.getDesig());
				System.out.println("Phone Number:" + e.getPhone());
				System.out.println("Email Id:" + e.getEmail());
				System.out.println("Salary:" + e.getSalary());
			}
		else
			System.err.println("No Employee with entered Name");
	}

	public static void findBySalary() {
		System.out.println("Enter the designation to find Employees");
		double salary = input.nextDouble();
		List<Employee> emps = dao.findBySalary(salary);
		if (emps.size() > 0)
			for (Employee e : emps) {
				System.out.println("Employee Id:" + e.getId());
				System.out.println("Employee Name:" + e.getName());
				System.out.println("Designation:" + e.getDesig());
				System.out.println("Phone Number:" + e.getPhone());
				System.out.println("Email Id:" + e.getEmail());
				System.out.println("Salary:" + e.getSalary());
			}
		else
			System.err.println("No Employee with entered Salary");
	}
	public static void FilterBySalary() {
		System.out.println("Enter The Minimum Salary:");
		double sal1=input.nextDouble();
		System.out.println("Enter The Maximum Salary:");
		double sal2=input.nextDouble();
		Employee e=dao.filterBySalary(sal1, sal2);
		if(e!=null) {
			System.out.println("Employee Id:" + e.getId());
			System.out.println("Employee Name:" + e.getName());
			System.out.println("Designation:" + e.getDesig());
			System.out.println("Phone Number:" + e.getPhone());
			System.out.println("Email Id:" + e.getEmail());
			System.out.println("Salary:" + e.getSalary());
		}
		else {
			System.err.println("Invalid Salaries...!!!");
		}
	}
}