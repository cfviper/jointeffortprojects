package com.google.ds.server.tables;


import com.google.appengine.api.datastore.Text;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.IdentityType;
//import java.text.DateFormat;
//import java.util.Calendar;



	
	@PersistenceCapable(identityType = IdentityType.APPLICATION)
	public class Projects implements Serializable{
	    
		private static final long serialVersionUID = 1L;
		
		@PrimaryKey
	    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	    private Long id;
	    //private Key key;
	    
	    @Persistent
	    private String programTitle;  //multiple projects can fall under a program

	    @Persistent
	    private String projectTitle;

	    @Persistent
	    private Date startDate;

	    @Persistent
	    List<Date> returnVisitDate; 
	    
	    @Persistent
	    private String programAdopted;
	    
	    @Persistent(defaultFetchGroup = "true") // what does defaultFetchGroup = "true" do ?
	    private Text communityDescription;
	    
	    public Projects(){};
	    
	    public Projects(String programTitle, String projectTitle, Date startDate, List<Date> returnVisitDate, String programAdopted) {
	        this.programTitle = programTitle;
	        this.projectTitle = projectTitle;
	        this.startDate = startDate;
	        this.returnVisitDate = returnVisitDate;
	        this.programAdopted = programAdopted;
	    }
	    public Projects(String programTitle, String projectTitle, Date startDate, Text communityDescription) {
	        this.programTitle = programTitle;
	        this.projectTitle = projectTitle;
	        this.startDate = startDate;
	        this.setCommunityDescription(communityDescription);
	    }
	    public Projects(String programTitle, String projectTitle) {
	        super();
	    	this.programTitle = programTitle;
	        this.projectTitle = projectTitle;
	    }
	    

	    // Accessors for the fields.  JDO doesn't use these, but your application does.

	    
	    public Long getId() {
	        return id;
	    }

	    public String getprogramTitle() {
	        return programTitle;
	    } 
	    public void setprogramTitle(String programTitle) {
	        this.programTitle = programTitle;
	    } 

	    public String getprojectTitle() {
	        return projectTitle;
	    } 
	    public void setprojectTitle(String projectTitle) {
	        this.projectTitle = projectTitle;
	    } 

	    public Date getstartDate() {
	        return startDate;
	    } 
	    public void setstartDate(Date startDate) {
	        this.startDate = startDate;
	    } 
	    
	    public List<Date> getReturnVisitDate() {
	        return returnVisitDate;
	    } 
	    public void setReturnVisitDate(List<Date> returnVisitDate) {
	        this.returnVisitDate = returnVisitDate;
	    }
	    
	    public String getProgramAdopted() {
	        return programAdopted;
	    } 
	    public void setProgramAdopted(String programAdopted) {
	        this.programAdopted = programAdopted;
	    }

		public void setCommunityDescription(Text communityDescription) {
			this.communityDescription = communityDescription;
		}

		public Text getCommunityDescription() {
			return communityDescription;
		} 
	}