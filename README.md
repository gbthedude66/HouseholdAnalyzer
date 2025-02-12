# Expeditors Challenge

### Requirements:
Any `OpenJDK 18` distribution as that's what it's been tested on. The latest version of `Maven` if you plan on fiddling with the source code and building the jar.

### Assumptions
I treated the street addresses as normalized addresses, so characters like "." "," " ", "  ", don't change the actual address. Essentially comparing the normalized street addresses.
I assumed that if the street address was the same but the city or state were different that the addresses are different. Case also doesn't matter for any part of the address.
I also assumed that it would make most sense to ingest the input from a file so that you don't need to rebuild the jar everytime you make an adjustment.

### How to run?
Navigate to the projects base directory and run:
```
java -jar target/HouseholdAnalyzer-1.0-SNAPSHOT.jar
```

### How to make modifications and build your own jar?
After making any changes run the below to create a new jar:
```
mvn clean package
```

### Suggestions/Improvements?
Please open a pull request if you have any suggestions/improvements.
