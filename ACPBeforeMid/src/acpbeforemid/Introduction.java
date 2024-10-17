package acpbeforemid;

import java.io.IOException;
import java.util.Scanner;

public class Introduction {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        int ch, key = 0;
        Functions functions = new Functions();
        LoginVoter login = new LoginVoter();
        Register register = new Register();
        ElectionResults electionResults = new ElectionResults(); // Added ElectionResults class reference
        Scanner scan = new Scanner(System.in);

        do {
            functions.displayMainMenu();
            ch = scan.nextInt();

            switch (ch) {
                case 1 -> login.login();
                case 2 -> {
                    functions.displayRegisterMenu();
                    key = scan.nextInt();

                    switch (key) {
                        case 1 -> register.voterRegister();
                        case 2 -> register.nomineeRegister();
                        case 3 -> {
                        }
                    }
                }
                case 3 -> {
                    electionResults.displayResults(); // Call the method to display results
                }
                case 4 -> System.out.println("Exiting system...");
                default -> System.out.println("Wrong selection made!");
            }
        } while (ch != 4); // Exit when choice is 4
    }
}
