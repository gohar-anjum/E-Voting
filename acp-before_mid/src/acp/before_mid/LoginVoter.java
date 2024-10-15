package acp.before_mid;

import acp.before_mid.Functions.voterRegister;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class LoginVoter {
		
	@SuppressWarnings("unchecked")
	public void login() throws IOException, ClassNotFoundException {
		
		int voterId, choice, key, vote;
		String voterPass;
		boolean check = false;
		Functions functions =  new Functions();
		FileInputStream fileInputStream;
		ObjectInputStream objectInputStream;
				 
		Scanner scan = new Scanner(System.in);
		FileInputStream fileInputStream1 = new FileInputStream("Nominee.txt");
		ObjectInputStream objectInputStream1 = new ObjectInputStream(fileInputStream1);
		ArrayList<Nominee> nomineeList = new ArrayList<Nominee>();
		nomineeList = (ArrayList<Nominee>)objectInputStream1.readObject();
		//System.out.println(nomineeList);
		
		try {
			
			fileInputStream = new FileInputStream("Voter.txt");
			objectInputStream = new ObjectInputStream(fileInputStream);
			System.out.print("Enter Voter Id : ");
			voterId = scan.nextInt();
			System.out.println("Enter Password : ");
			voterPass = scan.next();
			Voter voter1 = null;
			List<Voter> voterList = (ArrayList<Voter>)objectInputStream.readObject();
			
			for(Voter voter : voterList) {
				if(voter.getVoterId() == voterId) {
					voter1 = voter;
					break;
				}
			}
			
			
			
			try {
				functions.voterRegistration(voter1);
				if(voter1.getPassword().equals(voterPass)) {
					
					do {
						functions.displayLoginMenu();
						choice = scan.nextInt();
						
						switch(choice) {
						
							case 1:
								
								functions.displayNomineeMenu();
								key = scan.nextInt();
								System.out.println("Enter respective id : ");
								vote = scan.nextInt();
								
								
								for(Nominee nominee: nomineeList) {
									
									if(nominee.getNomId() == vote) {
										
										switch(key) {
											case 1:
												if((nominee.getNomPosition().equals("President"))){
													
													if(voter1.getPresiVote() == 0) {
														voter1.setPresiVote(vote);
														System.out.println("Vote casted!");
													}
													else{
														System.out.println("Vote casted for Presient already.");
													}
												}
												else {
													System.out.println("Wrong President Id entered!");
												}
												break;
												
											case 2:
												if((nominee.getNomPosition().equals("Prime Minister"))){
													if(voter1.getPmVote() == 0) {
														voter1.setPmVote(vote);
														System.out.println("Vote casted!");
													}
													else {
														System.out.println("Vote casted for Prime Minister already.");
													}
												}
												else {
													System.out.println("Wrong Prime Minister Id entered!");
												}
												break;
											
											case 3:
												if((nominee.getNomPosition().equals("Chief Minister"))){
													if(voter1.getCmVote() == 0) {
														voter1.setCmVote(vote);
														System.out.println("Vote casted!");
													}
													else {
														System.out.println("Vote casted for Chief Minister already.");
													}
												}
												else {
													System.out.println("Wrong Chief Minister Id entered!");
												}
												break;
										
										}//switch close
																				
										
										
										break;
										
									}//if close
									
									else {
										//System.out.println("Wrong nominee id entered!");
									}
								}							
								break;
								
							case 2:
								for(Nominee nominee : nomineeList)
									System.out.println(nominee);							
								
								break;
								
							case 3:
								break;							
						
						}//login-switch
						
					}while(choice < 3);
					
				}
				else {
					System.out.println("Incorrect password!");
				}				
				
				
				
			}//sub-try
			catch(voterRegister ex) {}
			
			
			
			
			
			
			
			
			objectInputStream.close();
			fileInputStream.close();
			objectInputStream1.close();
			fileInputStream1.close();
			
			
		}//main-try
		catch (Exception e) {
			System.out.println("No voters registered yet!");
		} 
		
		/*FileOutputStream fileOutputStream = new FileOutputStream("Voter.txt");
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
		for(Voter voter : voterList) {
		if(voter.getVoterId() == voterId) {
			voter = voter1;
			objectOutputStream.writeObject(voterList);
			break;
			}
		}
		objectOutputStream.close();
		fileOutputStream.close();*/
		
	}

}
