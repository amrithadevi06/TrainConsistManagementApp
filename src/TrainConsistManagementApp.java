import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import java.util.LinkedList;
import java.util.LinkedHashSet;

public class TrainConsistManagementApp {

    public static void main(String[] args) {

        // ================= UC1 =================
        System.out.println("=== Train Consist Management App ===");

        List<String> bogies = new ArrayList<>();

        System.out.println("Train consist initialized.");
        System.out.println("Initial bogie count: " + bogies.size());

        // ================= UC2 =================
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

        Set<String> bogieIds = new HashSet<>();

        bogieIds.add("BG101");
        bogieIds.add("BG102");
        bogieIds.add("BG103");
        bogieIds.add("BG101"); // duplicate
        bogieIds.add("BG102"); // duplicate

        System.out.println("Unique Bogie IDs:");
        System.out.println(bogieIds);

        // ================= UC4 =================
        System.out.println("\n=== UC4: Maintain Ordered Train Consist ===");

        LinkedList<String> train = new LinkedList<>();

        train.add("Engine");
        train.add("Sleeper");
        train.add("AC");
        train.add("Cargo");
        train.add("Guard");

        System.out.println("Initial Train:");
        System.out.println(train);

        train.add(2, "Pantry");

        System.out.println("\nAfter adding Pantry at position 2:");
        System.out.println(train);

        train.removeFirst();
        train.removeLast();

        System.out.println("\nAfter removing first and last bogie:");
        System.out.println(train);

        // ================= UC5 =================
        System.out.println("\n=== UC5: Preserve Insertion Order of Bogies ===");

        LinkedHashSet<String> formation = new LinkedHashSet<>();

        formation.add("Engine");
        formation.add("Sleeper");
        formation.add("Cargo");
        formation.add("Guard");

        formation.add("Sleeper"); // duplicate

        System.out.println("Train Formation:");
        System.out.println(formation);
    }
}