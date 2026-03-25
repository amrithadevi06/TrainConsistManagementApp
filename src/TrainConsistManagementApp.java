import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

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

        bogies.remove("AC Chair");

        System.out.println("\nAfter removing AC Chair:");
        System.out.println(bogies);

        boolean exists = bogies.contains("Sleeper");
        System.out.println("\nIs Sleeper present? " + exists);

        System.out.println("\nFinal bogie list:");
        System.out.println(bogies);

        // ================= UC3 =================

        System.out.println("\n=== UC3: Unique Bogie IDs ===");

        // Create HashSet
        Set<String> bogieIds = new HashSet<>();

        // Add IDs (with duplicates intentionally)
        bogieIds.add("BG101");
        bogieIds.add("BG102");
        bogieIds.add("BG103");
        bogieIds.add("BG101"); // duplicate
        bogieIds.add("BG102"); // duplicate

        // Display unique IDs
        System.out.println("Unique Bogie IDs:");
        System.out.println(bogieIds);
    }
}