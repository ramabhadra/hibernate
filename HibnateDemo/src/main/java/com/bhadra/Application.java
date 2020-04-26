package com.bhadra;

import com.bhadra.dao.EmployeeDao;
import com.bhadra.entity.Employee;

public class Application {
	public static void main(String[] args) {
		Employee emp = new Employee();
		emp.setName("Rama");
		emp.setAge(32);
		System.out.println("Hello World!");
		EmployeeDao dao =new EmployeeDao();
		dao.saveEmp(emp);
	}
}
