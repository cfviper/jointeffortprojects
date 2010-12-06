package com.google.ds.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;

import com.google.ds.services.QueryService;
import com.google.ds.services.QueryServiceAsync;
import com.google.ds.shared.FieldVerifier;
import com.google.ds.shared.Global;
import com.google.ds.tables.Employee;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.DecoratedPopupPanel;
import com.google.gwt.user.client.ui.DecoratedTabPanel;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormSubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormSubmitEvent;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import common.PMF;


public class AdminDashboard {
	private final static QueryServiceAsync queryService = GWT.create(QueryService.class);
	final static MenuBar menuBar = new MenuBar(false);
	final static Label statsLabel = new Label();
	final static DeckPanel deckPanel = new DeckPanel();
	final static FormPanel formNewProgram = new FormPanel();
	static ArrayList<String> formAnswers = new ArrayList<String>();
	static List<Date> visitList = null;
	static ArrayList<Date> alVisitList = new ArrayList<Date>();
	static int theCount;

	public static DockLayoutPanel createAdmindDashboard() {
		DockLayoutPanel dockLayoutPanel = new DockLayoutPanel(Unit.EM);

		MenuBar mbMenuBar = createMenuBar(menuBar);

		statsLabel.setText(queryCountProjects() + " Active projects");

		DecoratedTabPanel decoratedTabPanel = new DecoratedTabPanel();
		decoratedTabPanel.add(newProgramQuestions(), "Overview");
		decoratedTabPanel.add(new DockLayoutPanel(Unit.EM), "522");
		decoratedTabPanel.setAnimationEnabled(true);
		decoratedTabPanel.selectTab(0);

		deckPanel.add(statsLabel);
		deckPanel.add(decoratedTabPanel);
		deckPanel.showWidget(0);

		dockLayoutPanel.addNorth(mbMenuBar, 1.7);
		dockLayoutPanel.add(deckPanel);
		return dockLayoutPanel;
	}

	private static MenuBar createMenuBar(MenuBar menuBar) {

		MenuBar file = new MenuBar(true);
		file.addItem("New Program", new Command() {
			public void execute() {
				deckPanel.showWidget(1);
			}
		});
		file.addItem("New Project", new Command() {
			public void execute() {
			}
		});
		file.addSeparator();
		file.addItem("Open Program", new Command() {
			public void execute() {
			}
		});
		file.addItem("Open Project", new Command() {
			public void execute() {
			}
		});

		MenuBar attachFile = new MenuBar(true);
		// add commands here

		menuBar.addItem("Program Entry", file);
		menuBar.addItem("Attach File", attachFile);
		return menuBar;
	}

	private static FormPanel newProgramQuestions() {
		VerticalPanel formHolder = new VerticalPanel();
		
		
		formHolder.add(new Label("Program Name"));
		final TextBox tbProgramName = new TextBox();
		tbProgramName.setName("tbProgramName");
		formHolder.add(tbProgramName);

		formHolder.add(new Label("Initial Project"));
		final TextBox tbStarterProject = new TextBox();
		tbStarterProject.setName("tbStarterProject");
		formHolder.add(tbStarterProject);

		formHolder.add(new Label("startDate"));
		final DateBox tbStartDate = new DateBox();
		tbStartDate.setValue(new Date());
		formHolder.add(tbStartDate);

		formHolder.add(new Label("returnVisitDate"));
		final DateBox tbReturnVisitDate = new DateBox();
		tbReturnVisitDate.setValue(new Date());
		formHolder.add(tbReturnVisitDate);

		formHolder.add(new Label("programAdopted"));
		final TextBox tbProgramAdopted = new TextBox();
		tbProgramAdopted.setName("tbProgramAdopted");
		formHolder.add(tbProgramAdopted);

		
		
		Button submitButton = new Button("Submit", new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				alVisitList.add(tbReturnVisitDate.getValue());  // This should be moved to update program, and should query selected project for insert
				saveForm(tbProgramName.getText(), tbStarterProject.getText(),tbStartDate.getValue(), alVisitList  , tbProgramAdopted.getText() );
			}
		});
		submitButton.ensureDebugId("cwBasicButton-normal");
		formHolder.add(submitButton);

		formNewProgram.add(formHolder);

		return formNewProgram;
	}

	private static int queryCountProjects() {
		queryService.queryServer("Smith", new AsyncCallback<List<Employee>>() {
			int countCurrentProjects = 0;

			@Override
			public void onFailure(Throwable caught) {
				final DecoratedPopupPanel errorPopup = new DecoratedPopupPanel(true);
				errorPopup.add(new HTML("x " + caught.getLocalizedMessage() + " x"));
				statsLabel.setText("failed");
			}

			public void onSuccess(List<Employee> result) {
				countCurrentProjects = 0;
				for (Employee employee : result) {
					employee.getFirstName();
					countCurrentProjects++;
					statsLabel.setText("Active projects:: " + countCurrentProjects);
					statsLabel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
				}

				theCount = countCurrentProjects;
			}

		});

		return theCount;
	}

	private static void saveForm(String programTitle, String projectTitle, Date startDate, List<Date> returnVisitDate, String programadopted) throws IllegalArgumentException{
		//formAnswers.add(programTitle, projectTitle,startDate, returnVisitDate, programadopted);
		
		queryService.addProject( programTitle, projectTitle,startDate, returnVisitDate, programadopted, new AsyncCallback<Void>(){

			
			public void onFailure(Throwable caught) {
				final DecoratedPopupPanel simplePopup = new DecoratedPopupPanel(true);
				simplePopup.setWidget(new HTML("x "+caught.getLocalizedMessage()+" x"));
				Window.alert("Fail");
			}

			
			public void onSuccess(Void result) {
				Window.alert("Submitted successfully");
				
			}
		});
		
	}
}