package com.google.ds.services;

//import tables.Employee;
import java.util.Date;
import java.util.List;



import com.google.ds.server.tables.Projects;
import com.google.ds.tables.Employee;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("query") 
public interface QueryService extends RemoteService {
	public String findServer(String name) throws IllegalArgumentException;
	public List<Employee> queryServer(String result) throws IllegalArgumentException;
	
	public List<Employee> listEmployee();
	public void addEmployee(Employee employee);
	public void removeEmployee(Employee employee);
	public void updateEmployee(Employee employee);
	
	// Projects Class
	//void addProject(Projects project);
	void addProject(String programTitle, String projectTitle, Date startDate, List<Date> returnVisitDate, String programadopted);
	void addProject(String programTitle, String projectTitle, Date startDate, String taCommunityDescription);
    
}
