package acp.before_mid;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;


@SuppressWarnings("unused")
public class Functions {
	Scanner scan = new Scanner(System.in);
	Nominee nominee = null;
	
	@SuppressWarnings("serial")
	public class ageNotValid extends Exception{
		public ageNotValid(String message) {
			super(message);
		}		
	}//ageNotValid exception ends
	
	public int checkVoterAge(int age) throws ageNotValid {
		if(age < 18) {
			throw new ageNotValid("Voter age shouldn't be less than 18.");
		}
		return 1;
	}
	
	public int checkNomineeAge(int age) throws ageNotValid {
		if(age < 35) {
			throw new ageNotValid("Nominee age shouldn't be less than 35.");			
		}
		return 1;
	}
	
	@SuppressWarnings("serial")
	public class passwordStrength extends Exception{
		public passwordStrength(String message) {
			System.out.println(message);
		}
	}//passwordStrength exception ends
	
	public int checkPasswordStrength(String Password) throws passwordStrength{
		if(Password.length() < 8) {
			throw new passwordStrength("Password must contain atleast 8 characters.");
		}
		return 1;		
	}
	
	@SuppressWarnings("serial")
	public class voterRegister extends Exception{
		
		public voterRegister(String message) {
			System.out.println(message);
		}
	}
	
	public boolean voterRegistration(Voter voter) throws voterRegister{
		
		if(voter == null) {
			throw new voterRegister("Voter not registered!");
		}
		return true;
	}
	
	public void displayNomineeMenu() {
		
		System.out.println("Choose accordingly\n1. President\n2. Prime Minister\n3. Chief Minister");
	
	}
	
	public void displayLoginMenu() {
		
		System.out.println("1 . Cast your vote. ");
		System.out.println("2 . List of Nominees. ");
		System.out.println("3 . Go back to main menu. ");
		System.out.println("Select any option: ");
	
	}
	
	@SuppressWarnings("resource")
	public void displayNominee() throws IOException, Throwable {
		
		FileInputStream fileInputStream1 = new FileInputStream("Nominee.txt");
		ObjectInputStream objectInputStream1 = new ObjectInputStream(fileInputStream1);
		Nominee nominee = (Nominee) objectInputStream1.readObject();
		
		if(nominee == null) {
			System.out.println("No nominees registered yet!");
		}
		else {
			System.out.println(nominee);
			while((nominee = (Nominee) objectInputStream1.readObject()) != null) {
				System.out.println(nominee);
			}
		}
	}
	
	public void displayMainMenu() {
		
		System.out.println("Welcome to Online Poling System!\n");
		System.out.println("Enter 1 for login.");
		System.out.println("Enter 2 for registering. ");
		System.out.println("Enter 3 to exit!");
	
	}
	
	public void displayRegisterMenu() {
		
		System.out.println("1. Register as Voter");
		System.out.println("2. Register as Nominee");
		System.out.println("3. Go back to main menu");
		System.out.println("Select any option: ");
	
	}	
	
}
	
	
	