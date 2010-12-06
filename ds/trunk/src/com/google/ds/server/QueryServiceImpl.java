package com.google.ds.server;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.appengine.api.datastore.Text;
import com.google.ds.services.QueryService;
import com.google.ds.tables.Employee;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import common.PMF;

/**
 * The server side implementation of the RPC service.
 */
//@SuppressWarnings("serial")
public class QueryServiceImpl extends RemoteServiceServlet implements QueryService { 
	PersistenceManager pm = PMF.get().getPersistenceManager();
	private static final long serialVersionUID = 1L;
	private PersistJDO persistDAO = new PersistJDO();
	

	public String findServer(String input)throws IllegalArgumentException{
		input = escapeHtml("worked");
		return input;
	}
	
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
	}
	
	
	//////////////////////////
	// Projects Class Methods
	/////////////////////////
//	@Override
//	public void addProject(Projects project) {
//		// TODO Auto-generated method stub
//		persistDAO.addProject(project);	
//	}
	
	public void addProject(String programTitle, String projectTitle, Date startDate, List<Date> returnVisitDate, String programadopted) {
		persistDAO.addProject(programTitle, projectTitle,startDate, returnVisitDate, programadopted);
	}
	
	
	/////////////////////////
	// Employee Class Methods
	/////////////////////////
	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> queryServer(String result) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
		String query = "select from "+ Employee.class.getName()+" where lastName =='Smith'"; 
		//List<Employee> entities =   (List<Employee>) pm.newQuery(query).execute();
		
		Query q = pm.newQuery(Employee.class, "lastName == Smith");
	    List<Employee> entities = (List<Employee>) q.execute();
		//return entities;  can't return "entities" since it's an object
	    return new ArrayList<Employee>( entities);
	}

	@Override
	public void addEmployee(Employee employee) {
		// TODO Auto-generated method stub
		persistDAO.addEmployee(employee);
	}

	@Override
	public void removeEmployee(Employee employee) {
		// TODO Auto-generated method stub
		persistDAO.removeEmployee(employee);
	}

	@Override
	public void updateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		persistDAO.updateEmployee(employee);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> listEmployee() {
        String query = "select from "+ Employee.class.getName()+" where lastName =='Smith'"; 
		List<Employee> listEmployee =  (List<Employee>) pm.newQuery(query).execute();
        return new ArrayList<Employee> (listEmployee);
	}

	@Override
	public void addProject(String programTitle, String projectTitle, Date startDate, String taCommunityDescription) {
		// TODO Auto-generated method stub
		
	}

	

	
	
}


