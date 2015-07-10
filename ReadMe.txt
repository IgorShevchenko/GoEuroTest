#################################################
#												
#	             GoEuro Test APP	            
#          ---------------------------          
#												
#################################################

Usage: java -jar GoEuroTest.jar <CITY_NAME>

An application takes a city and retrieves JSON from the locations WEB API (goeuro.com). 
The retrieved results are saved into the CSV file. In case no results are retrieved for the specified CITY_NAME, then an empty file with just headers is created.

Features: 
- Java 7;
- Dependencies: Gson, JUnit;
- External configuration file (optional);
- Enable/disable application;
- Retry count for querying the WEB API;
- Append/overwrite output file;
- Keep things simple;
- Tests;

List of improvements:
1. Substitute System.out.println() with Logger to track the execution;
2. Make output file follow some naming convention (e.g. city_name + timestamp);
3. Improve polymorphism/encapsulation by inheriting Location class from some parent class or by implementing GeoPosition as an inner class;
4. Finish CSV/JSON readers/writers;
