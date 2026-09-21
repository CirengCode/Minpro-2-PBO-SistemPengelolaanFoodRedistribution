/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;
import controller.SistemPengelolaanFoodRedistributionController;
import java.util.Scanner;

public class SistemPengelolaanFoodRedistributionMain {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        SistemPengelolaanFoodRedistributionController controller =
                new SistemPengelolaanFoodRedistributionController();

        controller.mulai(scanner);

        scanner.close();
    }
    
}
