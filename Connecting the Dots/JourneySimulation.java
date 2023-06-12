/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *Tak siap lagi ni
 * @author 123456
 */
public class JourneySimulation {
     public static void main(String[] args) {
        
    
    // Define the stations
        String[] stations = { "Station A", "Station B", "Station C" };

        // Start at the first station
        int currentStationIndex = 0;
        boolean journeyCompleted = false;

        Scanner scanner = new Scanner(System.in);

        // Main loop for the journey
        while (currentStationIndex >= 0 && currentStationIndex < stations.length) {
            String currentStation = stations[currentStationIndex];

            // Prompt Suzume to play the game at the current station
            System.out.println("Suzume is at " + currentStation);
            System.out.println("Play the game at " + currentStation + "! (Enter 'win' or 'lose')");
            String gameResult = scanner.nextLine();

            // Check if Suzume wins or loses the game
            if (gameResult.equalsIgnoreCase("win")) {
                System.out.println("Congratulations! Suzume wins the game at " + currentStation);
                currentStationIndex++; // Proceed to the next station
            } else if (gameResult.equalsIgnoreCase("lose")) {
                System.out.println("Oops! Suzume loses the game at " + currentStation);
                currentStationIndex--; // Fall back to the previous station
            } else {
                System.out.println("Invalid input. Please enter 'win' or 'lose'.");
                continue; // Restart the loop to prompt for valid input
            }

            // Check if Suzume has reached the end of the journey
            if (currentStationIndex >= stations.length) {
                journeyCompleted = true;
                System.out.println("Suzume's journey is complete!");
            } else if (currentStationIndex < 0) {
                System.out.println("Suzume's journey ends at the first station.");
            }
        }

        // Close the scanner
        scanner.close();

        // Final message
        if (!journeyCompleted) {
            System.out.println("Suzume's journey ends.");
        }
    }

}
