package com.csvdatahandling.advanced_problem.encryptanddecryptCSVdata;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.util.Base64;

public class Main {
    private static final String ALGORITHM = "AES";
    private static SecretKey secretKey;

    public static void main(String[] args) throws Exception {
        // Generate a secret key
        secretKey = generateSecretKey();

        String csvFile = "employeeData.csv";
        String encryptedCsvFile = "employeeDataEncrypted.csv";
        String decryptedCsvFile = "employeeDataDecrypted.csv";

        // Encrypt and write CSV data
        encryptAndWriteCsv(csvFile, encryptedCsvFile);

        // Read and decrypt CSV data
        readAndDecryptCsv(encryptedCsvFile, decryptedCsvFile);
    }

    private static SecretKey generateSecretKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM);
        keyGen.init(128);
        return keyGen.generateKey();
    }

    private static String encrypt(String data) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    private static String decrypt(String encryptedData) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = Base64.getDecoder().decode(encryptedData);
        return new String(cipher.doFinal(decryptedBytes));
    }

    private static void encryptAndWriteCsv(String inputCsvFile, String outputCsvFile) {
        try (CSVReader csvReader = new CSVReader(new FileReader(inputCsvFile));
             CSVWriter csvWriter = new CSVWriter(new FileWriter(outputCsvFile))) {

            String[] header = csvReader.readNext();
            csvWriter.writeNext(header);

            String[] line;
            while ((line = csvReader.readNext()) != null) {
                line[3] = encrypt(line[3]); // Encrypt Salary
                csvWriter.writeNext(line);
            }

            System.out.println("CSV data encrypted and written successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void readAndDecryptCsv(String inputCsvFile, String outputCsvFile) {
        try (CSVReader csvReader = new CSVReader(new FileReader(inputCsvFile));
             CSVWriter csvWriter = new CSVWriter(new FileWriter(outputCsvFile))) {

            String[] header = csvReader.readNext();
            csvWriter.writeNext(header);

            String[] line;
            while ((line = csvReader.readNext()) != null) {
                line[3] = decrypt(line[3]); // Decrypt Salary
                csvWriter.writeNext(line);
            }

            System.out.println("CSV data decrypted and written successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}