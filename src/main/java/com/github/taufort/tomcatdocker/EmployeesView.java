package com.github.taufort.tomcatdocker;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean
@SessionScoped
public class EmployeesView {

	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	private List<Employee> employees = new ArrayList<>(10);

	public EmployeesView() {
	}

	@PostConstruct
	public void populateEmployees() {
		for (int i = 1; i <= 10; i++) {
			Employee emp = new Employee();
			emp.setEmployeeId(String.valueOf(i));
			emp.setEmployeeName("Employee#" + i);
			employees.add(emp);
			LOGGER.info("Just added emloyee '{}'", emp.getEmployeeName());
		}
	}

	public List<Employee> getEmployees() {
		LOGGER.info("Rendering emloyees");
		return employees;
	}
}