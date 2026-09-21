/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InputUtil {
    public static int bacaInt(Scanner scanner, String pesan) {
        while (true) {
            try {
                System.out.print(pesan);
                int nilai = scanner.nextInt();
                scanner.nextLine();

                if (nilai <= 0) {
                    System.out.println("------------------------------------");
                    System.out.println("[Input harus lebih dari 0 -__-!]");
                    System.out.println("------------------------------------");
                    continue;
                }

                return nilai;

            } catch (InputMismatchException e) {
                System.out.println("------------------------------------");
                System.out.println("[Input harus berupa angka -__-!]");
                System.out.println("------------------------------------");
                scanner.nextLine();
            }
        }
    }

    public static String bacaString(Scanner scanner, String pesan) {
        while (true) {
            System.out.print(pesan);
            String nilai = scanner.nextLine();

            if (!nilai.trim().isEmpty()) {
                return nilai;
            }

            System.out.println("------------------------------------");
            System.out.println("[Input tidak boleh kosong -__-!]");
            System.out.println("------------------------------------");
        }
    }

    public static void tekanEnter(Scanner scanner) {
        System.out.println();
        System.out.print("Tekan Enter untuk kembali ke menu...");
        scanner.nextLine();
    }
}
