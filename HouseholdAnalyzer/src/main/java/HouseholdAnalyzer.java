import java.io.*;
import java.util.*;

public class HouseholdAnalyzer {
    private static String[] parseCSVLine(String line) {
        List<String> result = new ArrayList<>();
        StringBuilder currentField = new StringBuilder();
        boolean inQuotes = false;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                result.add(currentField.toString());
                currentField = new StringBuilder();
            } else if (c == ',' && inQuotes) { //edge case where we need skip a ,
                continue;
            }
            else if (c != '"') {  // Skip the quotes
                currentField.append(c);
            }
        }
        result.add(currentField.toString());

        return result.toArray(new String[0]);
    }

    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();

        // Read from resources directory
        try (InputStream inputStream = HouseholdAnalyzer.class.getClassLoader().getResourceAsStream("input.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            if (inputStream == null) {
                System.err.println("Could not find input.txt in resources directory");
                System.exit(1);
            }

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;  // Skip empty lines

                try {
                    String[] parts = parseCSVLine(line);
                    people.add(new Person(
                            parts[0],
                            parts[1],
                            parts[2],
                            parts[3],
                            parts[4],
                            Integer.parseInt(parts[5])
                    ));
                } catch (NumberFormatException e) {
                    System.err.println("Error parsing age in line: " + line);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.err.println("Invalid number of fields in line: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            System.exit(1);
        }

        // Count households
        Map<String, Integer> householdCounts = new HashMap<>();
        Map<String, String> fullAddresses = new HashMap<>();

        for (Person person : people) {
            String normalizedAddr = person.getNormalizedAddress();
            householdCounts.put(normalizedAddr, householdCounts.getOrDefault(normalizedAddr, 0) + 1);
            fullAddresses.put(normalizedAddr, person.getFullAddress());
        }

        // Print household counts
        System.out.println("Households and number of occupants:");
        for (Map.Entry<String, Integer> entry : householdCounts.entrySet()) {
            String fullAddress = fullAddresses.get(entry.getKey());
            System.out.printf("%s: %d occupant(s)%n", fullAddress, entry.getValue());
        }

        // Filter and sort people over 18
        List<Person> adultsOnly = new ArrayList<>();
        for (Person person : people) {
            if (person.getAge() > 18) {
                adultsOnly.add(person);
            }
        }

        // Sort by lastName then firstName
        Collections.sort(adultsOnly, new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                int lastNameComparison = p1.getLastName().compareTo(p2.getLastName());
                if (lastNameComparison != 0) {
                    return lastNameComparison;
                }
                return p1.getFirstName().compareTo(p2.getFirstName());
            }
        });

        // Print sorted adults
        System.out.println("\nOccupants over 18 (sorted by Last Name, First Name):");
        for (Person person : adultsOnly) {
            System.out.println(person);
        }
    }
}