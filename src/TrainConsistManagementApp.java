import java.util.*;
import java.util.regex.*;
import java.util.stream.*;
import java.util.Comparator;

// ================= Custom Exception (UC14) =================
class InvalidCapacityException extends Exception {
    public InvalidCapacityException(String message) {
        super(message);
    }
}

// ================= Bogie Class =================
class Bogie {
    String name;
    int capacity;

    Bogie(String name, int capacity) throws InvalidCapacityException {
        if (capacity <= 0) {
            throw new InvalidCapacityException("Capacity must be greater than zero");
        }
        this.name = name;
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return name + " -> " + capacity;
    }
}

// ================= Goods Bogie =================
class GoodsBogie {
    String type;
    String cargo;

    GoodsBogie(String type, String cargo) {
        this.type = type;
        this.cargo = cargo;
    }
}

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
        bogieIds.add("BG101");
        bogieIds.add("BG102");

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

        System.out.println("\nAfter adding Pantry:");
        System.out.println(train);

        train.removeFirst();
        train.removeLast();

        System.out.println("\nAfter removing first & last:");
        System.out.println(train);

        // ================= UC5 =================
        System.out.println("\n=== UC5: Preserve Insertion Order ===");

        LinkedHashSet<String> formation = new LinkedHashSet<>();
        formation.add("Engine");
        formation.add("Sleeper");
        formation.add("Cargo");
        formation.add("Guard");
        formation.add("Sleeper");

        System.out.println(formation);

        // ================= UC6 =================
        System.out.println("\n=== UC6: Map Bogie to Capacity ===");

        Map<String, Integer> capacityMap = new HashMap<>();
        capacityMap.put("Sleeper", 72);
        capacityMap.put("AC Chair", 54);
        capacityMap.put("First Class", 24);

        for (Map.Entry<String, Integer> entry : capacityMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // ================= UC7 =================
        System.out.println("\n=== UC7: Sort Bogies by Capacity ===");

        List<Bogie> bogieList = new ArrayList<>();

        try {
            bogieList.add(new Bogie("Sleeper", 72));
            bogieList.add(new Bogie("AC Chair", 54));
            bogieList.add(new Bogie("First Class", 24));
        } catch (InvalidCapacityException e) {
            System.out.println(e.getMessage());
        }

        bogieList.sort(Comparator.comparingInt(b -> b.capacity));

        System.out.println("Sorted Bogies (by capacity):");
        for (Bogie b : bogieList) {
            System.out.println(b);
        }

        // ================= UC8 =================
        System.out.println("\n=== UC8: Filter Bogies (>60) ===");

        List<Bogie> filtered = bogieList.stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());

        filtered.forEach(System.out::println);

        // ================= UC9 =================
        System.out.println("\n=== UC9: Group Bogies ===");

        Map<String, List<Bogie>> grouped =
                bogieList.stream().collect(Collectors.groupingBy(b -> b.name));

        grouped.forEach((k, v) -> System.out.println(k + " -> " + v));

        // ================= UC10 =================
        System.out.println("\n=== UC10: Total Capacity ===");

        int total = bogieList.stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);

        System.out.println("Total Capacity: " + total);

        // ================= UC11 =================
        System.out.println("\n=== UC11: Regex Validation ===");

        Pattern p1 = Pattern.compile("TRN-\\d{4}");
        Pattern p2 = Pattern.compile("PET-[A-Z]{2}");

        System.out.println(p1.matcher("TRN-1234").matches());
        System.out.println(p2.matcher("PET-AB").matches());

        // ================= UC12 =================
        System.out.println("\n=== UC12: Safety Check ===");

        List<GoodsBogie> goods = Arrays.asList(
                new GoodsBogie("Cylindrical", "Petroleum"),
                new GoodsBogie("Box", "Coal"),
                new GoodsBogie("Cylindrical", "Petroleum")
        );

        boolean safe = goods.stream()
                .allMatch(g -> g.type.equals("Cylindrical") ?
                        g.cargo.equals("Petroleum") : true);

        System.out.println("Safe? " + safe);

        // ================= UC13 =================
        System.out.println("\n=== UC13: Performance ===");

        List<Bogie> bigList = new ArrayList<>();

        try {
            for (int i = 1; i <= 10000; i++) {
                bigList.add(new Bogie("Sleeper", i));
            }
        } catch (InvalidCapacityException e) {
            System.out.println(e.getMessage());
        }

        long start1 = System.nanoTime();
        List<Bogie> loop = new ArrayList<>();

        for (Bogie b : bigList) {
            if (b.capacity > 60) loop.add(b);
        }
        long end1 = System.nanoTime();

        long start2 = System.nanoTime();
        List<Bogie> stream = bigList.stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());
        long end2 = System.nanoTime();

        System.out.println("Loop Time: " + (end1 - start1));
        System.out.println("Stream Time: " + (end2 - start2));

        // ================= UC14 =================
        System.out.println("\n=== UC14: Exception Handling ===");

        try {
            Bogie invalid = new Bogie("Invalid", 0);
            System.out.println(invalid);
        } catch (InvalidCapacityException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}