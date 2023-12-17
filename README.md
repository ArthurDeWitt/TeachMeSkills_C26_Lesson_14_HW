Repo for homework lesson 14
# DocumentValidator
Suppose there is a file with document numbers.
The document number is a string consisting of letters and numbers (no service characters).
Let this file contain each document number on a new line and no other information in the line, only the document number.
A valid document number must be 15 characters long and start with the sequence docnum (followed by any sequence of letters/digits) or soptgast (followed by any sequence of letters/digits).
Write a programme to read information from the input file - the path to the input file should be set through the console.
The programme should check document numbers for validity.
Valid docnum document numbers should be written to one report file.
Valid soptgast contract numbers should be written to another report file.
Errors, if they occur, should be written to the error_log.txt file
Logs should be written to the file execution_log.txt
Invalid document numbers should be written to another report file, 
but after the document numbers you should add information about why this document is invalid (wrong sequence of characters at the beginning/ there are service characters in the document name, etc.).
