# UWI-Hospital

Java project that acts as a system for national identification for persons living in a country. 
UWI-Hospital provides the following functionality:

● Registration of citizens - including the generation of a unique identification for each person registered within the system.

● Update civil data (e.g. marriage, death etc.)

● Search a database of persons to locate matching data based on Identification #  or name.

# Breakdown

A compilation of 4 major objects completes the project:
 
1. SNIDApp administrates the generation, modification and retrivial of citizen's data from various files.

2. SNIDDb administrates the creation and/or modification of various citizen's files (e.g. Death Certificates).

3. TextUI implements a text-based menu interface to the application.

4. Gui implements a graphical-user interface with the sole purpose of locating citizen's information.

# How To Jar

Change to the project's directory and type the following commands:

1. javac -d .\bin\ -cp .\src\ .\src\snid\*.java .\src\app\*.java .\src\data\*.java .\src\ui\*.java .\src\launch\*.java .\src\gui\*.java

2. javadoc -d .\docs .\src\snid\*.java .\src\app\*.java .\src\data\*.java .\src\ui\*.java .\src\gui\*.java .\src\launch\*.java

3. jar -cvfm UWI-Hospital.jar Manifest.txt -C .\bin\ . .\src\ .\docs\ 

4. java -jar UWI-Hospital.jar (To run the jar file)

# What I Learned

● Exception handling given erronous data and logical errors.

● Gui creation and functionality; ActionListener, MouseListner, OnClicked etc.

● File handling given specific output.
