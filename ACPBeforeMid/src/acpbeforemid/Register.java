package acpbeforemid;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Register {
    Scanner scan = new Scanner(System.in);
    Functions functions = new Functions();

    public void voterRegister() {
        Voter[] voterList = null;
        String voterName, pass1, pass2;
        int vAge;

        // Read existing voter data
        try (FileInputStream fileInputStream = new FileInputStream("Voter.txt");
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            voterList = (Voter[]) objectInputStream.readObject();
        } catch (Exception ex) {
            System.out.println("No previous voter data found. Starting fresh.");
        }

        // Register new voter
        try (FileOutputStream fileOutputStream = new FileOutputStream("Voter.txt");
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {

            System.out.println("Enter Voter Name:");
            voterName = scan.nextLine();
            System.out.println("Enter Voter Age:");
            vAge = scan.nextInt();
            scan.nextLine(); // Consume newline left-over

            System.out.println("Enter Password:");
            pass1 = scan.nextLine();
            System.out.println("Re-enter Password:");
            pass2 = scan.nextLine();

            if (pass1.equals(pass2)) {
                Voter voter = new Voter(voterName, vAge, pass1);
                voter.setVoterId(voterList == null ? 1 : voterList.length + 1); // Set voter ID

                if (voterList == null) {
                    voterList = new Voter[1];
                    voterList[0] = voter;
                } else {
                    Voter[] temp = new Voter[voterList.length + 1];
                    for (int i = 0; i < voterList.length; i++) {
                        temp[i] = voterList[i];
                    }
                    temp[voterList.length] = voter;
                    voterList = temp;
                }

                objectOutputStream.writeObject(voterList);
                System.out.println("Voter registered successfully!");
                System.out.println("Voter Info: " + voter.toString()); // Display voter info
            } else {
                System.out.println("Passwords do not match. Registration failed.");
            }
        } catch (IOException ex) {
            System.out.println("Error writing to file: " + ex.getMessage());
        }
    }

    public void nomineeRegister() {
    Nominee[] nomineeList = null;
    String nomName;
    int nomAge, nomPositionOption;
    String[] positions = {"President", "Prime Minister", "Chief Minister"};  // Predefined positions

    // Read existing nominee data
    try (FileInputStream fileInputStream = new FileInputStream("Nominee.txt");
         ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
        nomineeList = (Nominee[]) objectInputStream.readObject();
    } catch (Exception ex) {
        System.out.println("No previous nominee data found. Starting fresh.");
    }

    // Register new nominee
    try (FileOutputStream fileOutputStream = new FileOutputStream("Nominee.txt");
         ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {

        System.out.println("Enter Nominee Name:");
        nomName = scan.nextLine();
        
        // Display the list of available positions
        System.out.println("Select Nominee Position:");
        for (int i = 0; i < positions.length; i++) {
            System.out.println((i + 1) + ". " + positions[i]);
        }
        
        nomPositionOption = scan.nextInt();
        scan.nextLine(); // Consume newline left-over
        
        // Check for valid input
        if (nomPositionOption < 1 || nomPositionOption > positions.length) {
            System.out.println("Invalid position option. Registration failed.");
            return;
        }
        
        String nomPosition = positions[nomPositionOption - 1];
        
        System.out.println("Enter Nominee Age:");
        nomAge = scan.nextInt();
        scan.nextLine(); // Consume newline left-over

        Nominee nominee = new Nominee(nomineeList == null ? 1 : nomineeList.length + 1, nomName, nomPosition, nomAge);

        if (nomineeList == null) {
            nomineeList = new Nominee[1];
            nomineeList[0] = nominee;
        } else {
            Nominee[] temp = new Nominee[nomineeList.length + 1];
            for (int i = 0; i < nomineeList.length; i++) {
                temp[i] = nomineeList[i];
            }
            temp[nomineeList.length] = nominee;
            nomineeList = temp;
        }

        objectOutputStream.writeObject(nomineeList);
        System.out.println("Nominee registered successfully!");
    } catch (IOException ex) {
        System.out.println("Error writing to file: " + ex.getMessage());
    }
    }

}