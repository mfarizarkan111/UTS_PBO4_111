/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uts_banking;

import java.util.Scanner;

/**
 *
 * @author Asus
 */
public class bilanganChecker {
    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);  
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String getValidNumber() {
        Scanner scanner = new Scanner(System.in);
        String input;
        boolean isValidNumber;

        do {
            System.out.print("Masukkan sebuah bilangan: ");
            input = scanner.nextLine();
            isValidNumber = isNumeric(input);

            if (!isValidNumber) {
                System.out.println("Input bukan bilangan. Silakan coba lagi.");
            }
        } while (!isValidNumber);

        return input;
    }
    
}
