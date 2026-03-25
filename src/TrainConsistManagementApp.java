import java.util.ArrayList;
import java.util.List;

public class TrainConsistManagementApp {

    public static void main(String[] args) {

        // Welcome Message
        System.out.println("=== Train Consist Management App ===");

        // Initialize Train Consist (Empty List)
        List<String> bogies = new ArrayList<>();

        // Display Initial Bogie Count
        System.out.println("Train consist initialized.");
        System.out.println("Initial bogie count: " + bogies.size());
    }
}