Jake Degiovanni BT Code Test submission, 27th December 2016

Compile using command: 
    javac RouterPatchCheck.java

Run using command:
    java RouterPatchCheck filename.csv

Example:
    java RouterPatchCheck sample.csv

The code submitted here, to the best of my kowledge, answers the specification assigned to me and is a fully working submission. The code will print off any lines that are of the incorrect format in order for the user to be able to go back and fix them at a later date, it will then proceed to print off the routers to be patched immediately after.

Below I will outline the assumptions I made in order to answer the problem:
    - Unless the line is blank (no entries whatsoever, i.e. ,,,,) then the only potentially missing entry is the notes field
    - Entries are not guaranteed to be of the correct format, but, are guaranteed to not contain any extra ',' characters
    - The CSV file will always need to parsed using the ',' character
    - IP addresses will always be IPv4
    - There is no specific format for domains to be in, i.e. any string is acceptable 