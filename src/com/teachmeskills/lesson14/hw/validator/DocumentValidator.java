package com.teachmeskills.lesson14.hw.validator;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DocumentValidator {
    public static void main(String[] args) {
        // Пути к файлам
        String inputFilePath = "C:\\Projects\\TeachMeSkills_C26_Lesson_14_HW\\docnum\\docnum.txt";
        String validDocNumFilePath = "C:\\Projects\\TeachMeSkills_C26_Lesson_14_HW\\report\\docnum_report\\valid_docnum_report.txt";
        String validContractFilePath = "C:\\Projects\\TeachMeSkills_C26_Lesson_14_HW\\report\\contract_report\\valid_contract_report.txt";
        String invalidDocNumFilePath = "C:\\Projects\\TeachMeSkills_C26_Lesson_14_HW\\report\\doc_report\\invalid_doc_report.txt";
        String errorLogFilePath = "C:\\Projects\\TeachMeSkills_C26_Lesson_14_HW\\log\\error\\error_log.txt";
        String executionLogFilePath = "C:\\Projects\\TeachMeSkills_C26_Lesson_14_HW\\log\\execution\\execution_log.txt";

        try (BufferedWriter validDocNumWriter = new BufferedWriter(new FileWriter(validDocNumFilePath));
             BufferedWriter validContractWriter = new BufferedWriter(new FileWriter(validContractFilePath));
             BufferedWriter invalidDocNumWriter = new BufferedWriter(new FileWriter(invalidDocNumFilePath));
             BufferedWriter errorLogWriter = new BufferedWriter(new FileWriter(errorLogFilePath));
             BufferedWriter executionLogWriter = new BufferedWriter(new FileWriter(executionLogFilePath));
             BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {

            String line;
            while ((line = reader.readLine()) != null) {
                if (isValidDocNum(line)) {
                    if (line.startsWith("docnum")) {
                        validDocNumWriter.write(line);  // Запись валидного номера документа в файл valid_docnum_report.txt
                        validDocNumWriter.newLine();  // Добавление новой строки
                    } else if (line.startsWith("contract")) {
                        validContractWriter.write(line);  // Запись валидного контракта в файл valid_contract_report.txt
                        validContractWriter.newLine();  // Добавление новой строки
                    }
                } else {
                    invalidDocNumWriter.write(line + " - Invalid document number: ");
                    if (!line.matches("[A-Za-z0-9]+")) {
                        invalidDocNumWriter.write("Contains illegal characters: ");
                    }
                    if (line.length() != 15) {
                        invalidDocNumWriter.write("Wrong length ");
                    }
                    invalidDocNumWriter.newLine();  // Добавление новой строки
                }
            }

            executionLogWriter.write("The programme has successfully completed execution.");

        } catch (IOException e) {
            // Обработка ошибок и запись информации об ошибке в error_log.txt
            try (BufferedWriter errorLogWriter = new BufferedWriter(new FileWriter(errorLogFilePath))) {
                errorLogWriter.write("Error during file processing: " + e.getMessage());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    // Метод для проверки валидности номера документа
    public static boolean isValidDocNum(String docNum) {
        // Проверка на соответствие шаблону номера документа
        Pattern pattern = Pattern.compile("^(docnum|contract)[A-Za-z0-9]{12}$");
        Matcher matcher = pattern.matcher(docNum);
        return matcher.matches();  // Возвращение результата сопоставления
    }
}