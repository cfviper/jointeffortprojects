package com.google.ds.services;

import java.util.Date;
import java.util.List;



import com.google.ds.server.tables.Projects;
import com.google.ds.tables.Employee;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>QueryService</code>.
 */

public interface QueryServiceAsync {
	public void findServer(String input, AsyncCallback<String> callback) throws IllegalArgumentException;
	public  void queryServer(String input, AsyncCallback<List<Employee>> asyncCallback);
	
	public void listEmployee(AsyncCallback<List<Employee>> callback);
	public void addEmployee(Employee employee, AsyncCallback<Void> callback);
	public void removeEmployee(Employee employee, AsyncCallback<Void> callback);
	public void updateEmployee(Employee employee, AsyncCallback<Void> callback);
	
	// Projects Class
	//void addProject(Projects project, AsyncCallback<Void> callback);
	//public void queryServer(String input, AsyncCallback<Void> asyncCallback);
	public void addProject(String programTitle, String projectTitle, Date startDate, List<Date> returnVisitDate, String programadopted, AsyncCallback<Void> asyncCallback);
	public void addProject(String programTitle, String projectTitle, Date startDate, String taCommunityDescription, AsyncCallback<Void> asyncCallback);
	
}
