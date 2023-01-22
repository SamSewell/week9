package projects;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import projects.entity.Project;
import projects.exception.DbException;
import projects.service.ProjectService;

public class ProjectsApp {
	
	private List<String> operations = List.of(
			"1. Add a project"
			
			);
	private Scanner scanner = new Scanner(System.in);
			

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new ProjectsApp().processUserSelections();
		
		
		
	}
			// takes the users selection and then allows the switch statement to make our choice.
	private void processUserSelections() {
		boolean done = false;
		
		while(!done) {
			try {
				int selection = getUserSelection();
				
				switch(selection) {
				case -1:
					done = exitMenu();
					break;
				case 1:
					createProject();
					break;	
					
					default:
						System.out.println("\n" + selection + " is not a valid selection. Please try again.");
				}
			}
			catch(Exception e) {
				System.out.println("\n Error: " + e + " Please Try Again.");
			}
		}
		
		
	}
	
	
	// here we create the project and get it ready to upload to the database.
	private void createProject() {
		String projectName = getStringInput("Enter the project name");
		BigDecimal estimatedHours = getDecimalInput("Enter the estimated hours: ");
		BigDecimal actualHours = getDecimalInput("Enter the Actual hours for the project: ");
		Integer difficulty = getIntInput("Enter the project difficulty(1 - 5)");
		String notes = getStringInput("Enter any notes for the project");
		
		Project project = new Project();
		
		project.setProjectName(projectName);
		project.setEstimatedHours(estimatedHours);
		project.setActualHours(actualHours);
		project.setDifficulty(difficulty);
		project.setNotes(notes);
		
		Project dbProject = ProjectService.appProject(project);
		System.out.println("You have Successfully created project: " + dbProject);
		
		
	}
	
	
	private BigDecimal getDecimalInput(String prompt) {
		String input = getStringInput(prompt);
		
		if(Objects.isNull(input)) {
			return null;
		}
		try {
			return new BigDecimal(input).setScale(2);
		}
		catch(NumberFormatException e) {
			throw new DbException(input + " is not a valid decimal number");
		}
		
	}
	
	
	
		// method gets user selection from the menu
	private int getUserSelection() {
		printOperations();
		Integer input = getIntInput("Enter a menu Selection please");
		
		return Objects.isNull(input) ? -1 : input;
	}
	
	
		// creates the menus operations available to the user
	private void printOperations() {
		System.out.println("\n These are the available selections. Press the Enter key to quit");
		
		operations.forEach(line -> System.out.println(" " + line));
	}
	
	
	// this handles the string input and converts it to a int and then checks to make sure it is a valid int.
	private Integer getIntInput(String prompt) {
		String input = getStringInput(prompt);
		
		if(Objects.isNull(input)) {
			return null;
		}
		
		try {
			return Integer.valueOf(input);
		}
		catch(NumberFormatException e) {
			throw new DbException(input + " is not a valid number.");
		}
		
		
	
		
												}
	//gets the string from the menu
	private String getStringInput(String prompt) {
		
		System.out.println(prompt + ": ");
		String input = scanner.nextLine();
		
		return input.isBlank() ? null : input.trim();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private boolean exitMenu() {
		System.out.println("Exiting the menu.");
		return true;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
