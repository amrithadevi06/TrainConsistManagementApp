import java.util.ArrayList;
import java.util.List;

public class TrainConsistManagementApp {

    public static void main(String[] args) {

        // UC1: Initialize Train
        System.out.println("=== Train Consist Management App ===");

        List<String> bogies = new ArrayList<>();

        System.out.println("Train consist initialized.");
        System.out.println("Initial bogie count: " + bogies.size());

        // UC2: Add Passenger Bogies
        bogies.add("Sleeper");
        bogies.add("AC Chair");
        bogies.add("First Class");

        System.out.println("\nBogies after addition:");
        System.out.println(bogies);

        // Remove one bogie
        bogies.remove("AC Chair");

        System.out.println("\nAfter removing AC Chair:");
        System.out.println(bogies);

        // Check existence
        boolean exists = bogies.contains("Sleeper");
        System.out.println("\nIs Sleeper present? " + exists);

        // Final list
        System.out.println("\nFinal bogie list:");
        System.out.println(bogies);
    }
}