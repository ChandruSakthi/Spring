package org.jsp.springempApp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.jsp.springempApp.dto.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDao {
	@Autowired
	private EntityManager manager;

	public Employee saveEmployee(Employee emp) {
		EntityTransaction transaction = manager.getTransaction();
		manager.persist(emp);
		transaction.begin();
		transaction.commit();
		return emp;
	}

	public Employee updateEmployee(Employee emp) {
		Employee dbEmp = manager.find(Employee.class, emp.getId());
		if (dbEmp != null) {
			EntityTransaction transaction = manager.getTransaction();
			dbEmp.setName(emp.getName());
			dbEmp.setDesig(emp.getDesig());
			dbEmp.setEmail(emp.getEmail());
			dbEmp.setPassword(emp.getPassword());
			dbEmp.setPhone(emp.getPhone());
			dbEmp.setSalary(emp.getSalary());
			transaction.begin();
			transaction.commit();
			return emp;
		}
		return null;
	}

	public Employee findById(int id) {
		return manager.find(Employee.class, id);
	}

	public Employee verifyEmployee(int id, String password) {
		Query q = manager.createQuery("select e from Employee e where e.Id=?1 and e.Password=?2");
		q.setParameter(1, id);
		q.setParameter(2, password);
		try {
			return (Employee) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Employee verifyEmployee(String email, String password) {
		Query q = manager.createQuery("select e from Employee e where e.Email=?1 and e.Password=?2");
		q.setParameter(1, email);
		q.setParameter(2, password);
		try {
			return (Employee) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Employee verifyEmployee(long phone, String password) {
		Query q = manager.createQuery("select e from Employee e where e.Phone=?1 and e.Password=?2");
		q.setParameter(1, phone);
		q.setParameter(2, password);
		try {
			return (Employee) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<Employee> findByDesg(String desg) {
		Query q = manager.createQuery("select e from Employee e where e.Desig=?1");
		q.setParameter(1, desg);
		return q.getResultList();
	}

	public List<Employee> findByName(String name) {
		Query q = manager.createQuery("select e from Employee e where e.Name=?1");
		q.setParameter(1, name);
		return q.getResultList();
	}

	public List<Employee> findBySalary(double salary) {
		Query q = manager.createQuery("select e from Employee e where e.Salary=?1");
		q.setParameter(1, salary);
		return q.getResultList();
	}

	public Employee filterBySalary(double min, double max) {
		Query q = manager.createQuery("select e from Employee e where Salary between ?1 and ?2");
		q.setParameter(1, min);
		q.setParameter(2, max);
		try {
			return (Employee) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
