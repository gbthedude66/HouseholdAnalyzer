# Expeditors Challenge

### Requirements:
Any `OpenJDK 18` distribution as that's what it's been tested on. The latest version of `Maven` if you plan on fiddling with the source code and building the jar.

### Assumptions
If the address contains an apartment there can either be a " " or a "," seperator between the street address and the apartment. The street address can end with or without a ".". Case doesn't matter. Essentially comparing the normalized addresses.
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
