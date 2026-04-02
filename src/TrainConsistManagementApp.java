import java.util.*;
import java.util.regex.*;
import java.util.stream.*;

// ================= Bogie Class =================
class Bogie {
    String name;
    int capacity;

    Bogie(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return name + " -> " + capacity;
    }
}

// ================= Goods Bogie Class =================
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
        System.out.println("Train initialized. Count: " + bogies.size());

        // ================= UC2 =================
        bogies.add("Sleeper");
        bogies.add("AC Chair");
        bogies.add("First Class");

        System.out.println("\nBogies: " + bogies);
        bogies.remove("AC Chair");
        System.out.println("After removal: " + bogies);
        System.out.println("Contains Sleeper? " + bogies.contains("Sleeper"));

        // ================= UC3 =================
        Set<String> ids = new HashSet<>();
        ids.add("BG101");
        ids.add("BG102");
        ids.add("BG101");

        System.out.println("\nUnique IDs: " + ids);

        // ================= UC4 =================
        LinkedList<String> train = new LinkedList<>();
        train.add("Engine");
        train.add("Sleeper");
        train.add("AC");
        train.add("Cargo");
        train.add("Guard");

        train.add(2, "Pantry");
        train.removeFirst();
        train.removeLast();

        System.out.println("\nOrdered Train: " + train);

        // ================= UC5 =================
        LinkedHashSet<String> formation = new LinkedHashSet<>();
        formation.add("Engine");
        formation.add("Sleeper");
        formation.add("Cargo");
        formation.add("Guard");
        formation.add("Sleeper");

        System.out.println("\nFormation (ordered unique): " + formation);

        // ================= UC6 =================
        Map<String, Integer> capacityMap = new HashMap<>();
        capacityMap.put("Sleeper", 72);
        capacityMap.put("AC Chair", 54);
        capacityMap.put("First Class", 24);

        System.out.println("\nCapacities:");
        for (Map.Entry<String, Integer> e : capacityMap.entrySet()) {
            System.out.println(e.getKey() + " -> " + e.getValue());
        }

        // ================= UC7 =================
        List<Bogie> bogieList = new ArrayList<>();
        bogieList.add(new Bogie("Sleeper", 72));
        bogieList.add(new Bogie("AC Chair", 54));
        bogieList.add(new Bogie("First Class", 24));

        bogieList.sort(Comparator.comparingInt(b -> b.capacity));

        System.out.println("\nSorted Bogies:");
        bogieList.forEach(System.out::println);

        // ================= UC8 =================
        List<Bogie> filtered = bogieList.stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());

        System.out.println("\nFiltered (>60):");
        filtered.forEach(System.out::println);

        // ================= UC9 =================
        Map<String, List<Bogie>> grouped =
                bogieList.stream().collect(Collectors.groupingBy(b -> b.name));

        System.out.println("\nGrouped Bogies:");
        grouped.forEach((k, v) -> System.out.println(k + " -> " + v));

        // ================= UC10 =================
        int total = bogieList.stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);

        System.out.println("\nTotal Capacity: " + total);

        // ================= UC11 =================
        String trainId = "TRN-1234";
        String cargoCode = "PET-AB";

        Pattern p1 = Pattern.compile("TRN-\\d{4}");
        Pattern p2 = Pattern.compile("PET-[A-Z]{2}");

        System.out.println("\nTrain ID valid? " + p1.matcher(trainId).matches());
        System.out.println("Cargo Code valid? " + p2.matcher(cargoCode).matches());

        // ================= UC12 =================
        List<GoodsBogie> goods = Arrays.asList(
                new GoodsBogie("Cylindrical", "Petroleum"),
                new GoodsBogie("Box", "Coal"),
                new GoodsBogie("Cylindrical", "Petroleum")
        );

        boolean safe = goods.stream()
                .allMatch(g -> g.type.equals("Cylindrical") ?
                        g.cargo.equals("Petroleum") : true);

        System.out.println("\nSafety compliant? " + safe);

        // ================= UC13 =================
        List<Bogie> bigList = new ArrayList<>();

        for (int i = 0; i < 10000; i++) {
            bigList.add(new Bogie("Sleeper", i % 100));
        }

        // Loop timing
        long start1 = System.nanoTime();
        List<Bogie> loopResult = new ArrayList<>();

        for (Bogie b : bigList) {
            if (b.capacity > 60) {
                loopResult.add(b);
            }
        }
        long end1 = System.nanoTime();

        // Stream timing
        long start2 = System.nanoTime();
        List<Bogie> streamResult = bigList.stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());
        long end2 = System.nanoTime();

        System.out.println("\nLoop Time: " + (end1 - start1));
        System.out.println("Stream Time: " + (end2 - start2));
    }
}