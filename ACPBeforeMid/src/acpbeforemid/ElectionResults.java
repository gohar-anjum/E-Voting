package acpbeforemid;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class ElectionResults {

    // Method to count votes and display the leading candidate
    public void displayResults() {
        try (FileInputStream fileInputStream = new FileInputStream("Nominee.txt");
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

            Nominee[] nomineeList = (Nominee[]) objectInputStream.readObject();

            // Assuming the maximum number of positions is fixed
            final int MAX_POSITIONS = 3; // Example: 0 for President, 1 for PM, 2 for CM
            String[] positions = {"President", "Prime Minister", "Chief Minister"};
            int[] voteCounts = new int[MAX_POSITIONS];
            Nominee[] leadingNominees = new Nominee[MAX_POSITIONS];

            // Count votes based on the position
            for (Nominee nominee : nomineeList) {
                String position = nominee.getNomPosition();
                int index = -1;

                // Find the index of the position
                for (int i = 0; i < positions.length; i++) {
                    if (positions[i].equalsIgnoreCase(position)) {
                        index = i;
                        break;
                    }
                }

                // Increment the vote count if the position was found
                if (index != -1) {
                    voteCounts[index]++; // Increment vote count

                    // Determine if this nominee is the leading nominee for the position
                    if (leadingNominees[index] == null || voteCounts[index] > voteCounts[index]) {
                        leadingNominees[index] = nominee; // Set as leading nominee
                    }
                }
            }

            // Display results
            System.out.println("Election Results:");
            for (int i = 0; i < positions.length; i++) {
                if (leadingNominees[i] != null) {
                    System.out.println("Position: " + positions[i]);
                    System.out.println("Leading Nominee: " + leadingNominees[i].getNomName());
                    System.out.println("Votes: " + voteCounts[i]);
                    // You can define the winning condition based on your logic here
                    String winnerStatus = (voteCounts[i] > 0) ? "Winning" : "Not Winning"; // Replace with actual logic if needed
                    System.out.println("Status: " + winnerStatus);
                    System.out.println("------------------------------------");
                }
            }

        } catch (Exception e) {
            System.out.println("Error displaying election results: " + e.getMessage());
        }
    }
}
