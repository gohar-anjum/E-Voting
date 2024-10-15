package acpbeforemid;

import acpbeforemid.Functions.ageNotValid;
import acpbeforemid.Functions.passwordStrength;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Register {

    Scanner scan = new Scanner(System.in);
    Functions functions = new Functions();
    int choice, key;

    public void voterRegister() {

        Voter[] voterList = null;
        String voterName, pass1, pass2;
        int vAge;

        try (FileInputStream fileInputStream = new FileInputStream("Voter.txt");
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            voterList = (Voter[]) objectInputStream.readObject();
        } catch (Exception ex) {
            System.out.println("No previous voter data found. Starting fresh.");
        }

        try (FileOutputStream fileOutputStream = new FileOutputStream("Voter.txt");
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {

            System.out.println("Enter Voter Name: ");
            voterName = scan.next();
            System.out.println("Enter Voter Age: ");
            vAge = scan.nextInt();

            try {
                functions.checkVoterAge(vAge);

                System.out.println("Enter password: ");
                pass1 = scan.next();
                System.out.println("Re-enter password: ");
                pass2 = scan.next();

                if (pass1.equals(pass2)) {
                    try {
                        functions.checkPasswordStrength(pass1);

                        int id = (voterList == null || voterList.length == 0) ? 1000 : voterList[voterList.length - 1].getVoterId() + 1;

                        Voter VOTER = new Voter(voterName, vAge, pass1);
                        VOTER.setVoterId(id);

                        Voter[] newVoterList = new Voter[(voterList == null ? 0 : voterList.length) + 1];
                        if (voterList != null) {
                            System.arraycopy(voterList, 0, newVoterList, 0, voterList.length);
                        }
                        newVoterList[newVoterList.length - 1] = VOTER;

                        objectOutputStream.writeObject(newVoterList);

                        System.out.println("Voter registered successfully!");
                        System.out.println(VOTER);

                    } catch (passwordStrength ex) {
                        System.out.println(ex.getMessage());
                    }
                } else {
                    System.out.println("Passwords do not match!");
                }

            } catch (ageNotValid ex) {
                System.out.println(ex.getMessage());
            }
        } catch (IOException e) {
            System.out.println("Error writing voter data: " + e.getMessage());
        }
    }

    public void nomineeRegister() {

        Nominee[] nomineeList = null;
        String nomineeName;
        int nomineeAge;

        try (FileInputStream fileInputStream = new FileInputStream("Nominee.txt");
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            nomineeList = (Nominee[]) objectInputStream.readObject();
        } catch (Exception ex) {
            System.out.println("No previous nominee data found. Starting fresh.");
        }

        try (FileOutputStream fileOutputStream = new FileOutputStream("Nominee.txt");
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {

            System.out.println("Enter Nominee Name: ");
            nomineeName = scan.next();
            System.out.println("Enter Nominee Age: ");
            nomineeAge = scan.nextInt();

            Nominee nominee = new Nominee();
            nominee.setNomName(nomineeName);
            nominee.setNomAge(nomineeAge);

            try {
                functions.checkNomineeAge(nomineeAge);

                System.out.println("Available positions:");
                functions.displayNomineeMenu();
                choice = scan.nextInt();

                switch (choice) {
                    case 1 -> nominee.setNomPosition("President");
                    case 2 -> nominee.setNomPosition("Prime Minister");
                    case 3 -> nominee.setNomPosition("Chief Minister");
                    default -> {
                        System.out.println("Invalid choice. Please select a valid position.");
                        return;
                    }
                }

                int id = (nomineeList == null || nomineeList.length == 0) ? 10000 : nomineeList[nomineeList.length - 1].getNomId() + 1;
                nominee.setNomId(id);

                Nominee[] newNomineeList = new Nominee[(nomineeList == null ? 0 : nomineeList.length) + 1];
                if (nomineeList != null) {
                    System.arraycopy(nomineeList, 0, newNomineeList, 0, nomineeList.length);
                }
                newNomineeList[newNomineeList.length - 1] = nominee;

                objectOutputStream.writeObject(newNomineeList);

                System.out.println("Nominee registered successfully!");
                System.out.println(nominee);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (IOException e) {
            System.out.println("Error writing nominee data: " + e.getMessage());
        }
    }
}
