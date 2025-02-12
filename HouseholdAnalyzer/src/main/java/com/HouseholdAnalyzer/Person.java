package com.HouseholdAnalyzer;

public class Person {
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private int age;

    public Person(String firstName, String lastName, String address, String city, String state, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.age = age;
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getAddress() { return address; }
    public int getAge() { return age; }

    // Normalize address for comparison
    public String getNormalizedAddress() {
        StringBuilder sb = new StringBuilder();
        return sb.append(address.toLowerCase()
                .replaceAll("\\s+", " ")
                .replaceAll("\\.$", "")
                .trim()).append(city.toLowerCase()).append(state.toLowerCase()).toString();
    }

    public String getFullAddress() {
        return String.format("%s, %s, %s", address, city, state);
    }

    @Override
    public String toString() {
        return String.format("%s %s, %s, Age: %d", firstName, lastName, getFullAddress(), age);
    }
}
