package com.teachmeskills.lesson14.hw;
import java.io.*;
import java.util.regex.*;

public class DocumentValidator {

    public static void main(String[] args) {
        /* Получаем путь к входному файлу через консоль или прокидываем исключение об ошибке ввода пути.
        Применим BufferedReader для эффективного чтения данных из входного файла.
        Читаем построчно, потому что так удобней для обработки больших файлов без загрузки их целиком в память.
         */
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the input file path:");
        String inputFilePath;
        try {
            inputFilePath = consoleReader.readLine();
        } catch (IOException e) {
            System.out.println("Error reading input file path.");
            return;
        }

        File inputFile = new File(inputFilePath);
        try {
            // Создаем BufferedReader для чтения входного файла
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));

            // Создаем FileWriter для записи валидных номеров docnum
            FileWriter docNumWriter = new FileWriter("valid_docnum.txt");

            // Создаем FileWriter для записи валидных номеров контрактов
            FileWriter contractWriter = new FileWriter("valid_contract.txt");

            // Создаем FileWriter для записи ошибок
            FileWriter errorLogWriter = new FileWriter("error_log.txt");

            // Создаем FileWriter для записи логов выполнения
            FileWriter executionLogWriter = new FileWriter("execution_log.txt");

            // Переменная line, в которую считывается каждая строка из входного файла
            String line;

            while ((line = reader.readLine()) != null) {
                // Проверяем валидность номера документа и записываем соответствующие отчеты
                if (isValidDocNum(line)) {
                    docNumWriter.write(line + "\n");
                } else if (isValidContract(line)) {
                    contractWriter.write(line + "\n");
                } else {
                    errorLogWriter.write("Invalid document: " + line + " - Invalid format\n");
                }
            }

            // Закрываем все FileWriter
            docNumWriter.close();
            contractWriter.close();
            errorLogWriter.close();

            // Записываем сообщение о завершении процесса в execution_log.txt
            executionLogWriter.write("Validation process completed successfully.");
            executionLogWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Метод для проверки валидности номера docnum
    private static boolean isValidDocNum(String documentNumber) {
        Pattern pattern = Pattern.compile("^(docnum|contract)[a-zA-Z0-9]{13}$");
        Matcher matcher = pattern.matcher(documentNumber);
        return matcher.matches();
    }

    // Метод для проверки валидности номера контракта
    private static boolean isValidContract(String documentNumber) {
        Pattern pattern = Pattern.compile("^(contract|docnum)[a-zA-Z0-9]{13}$");
        Matcher matcher = pattern.matcher(documentNumber);
        return matcher.matches();
    }
}