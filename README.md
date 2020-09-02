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
