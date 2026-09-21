/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import java.util.Scanner;
import service.DonasiService;
import service.DonaturService;
import service.PenerimaService;
import service.PenyaluranService;
import util.InputUtil;

public class SistemPengelolaanFoodRedistributionController {
    private DonaturService donaturService;
    private DonasiService donasiService;
    private PenerimaService penerimaService;
    private PenyaluranService penyaluranService;

    public SistemPengelolaanFoodRedistributionController() {
        donaturService = new DonaturService();
        donasiService = new DonasiService();
        penerimaService = new PenerimaService();
        penyaluranService = new PenyaluranService();
    }

    public void mulai(Scanner scanner) {
        boolean berjalan = true;

        while (berjalan) {

            tampilkanMenu();

            int pilihan = InputUtil.bacaInt(scanner, ">> ");

            switch (pilihan) {

                case 1 -> donaturService.dataDonatur(scanner);

                case 2 -> donasiService.dataDonasi(scanner);

                case 3 -> penerimaService.dataPenerima(scanner);

                case 4 -> penyaluranService.dataPenyaluran(scanner);

                case 5 -> {
                    System.out.println("------------------------------------");
                    System.out.println("[Terima kasih sudah menggunakan");
                    System.out.println(" Food Redistribution System ^^]");
                    System.out.println("------------------------------------");
                    berjalan = false;
                }

                default -> {
                    System.out.println("------------------------------------");
                    System.out.println("Mohon maaf, pilihan tidak valid! T-T");
                    System.out.println("------------------------------------");
                    InputUtil.tekanEnter(scanner);
                }
            }
        }
    }

    private void tampilkanMenu() {
        System.out.println("========================================");
        System.out.println("       FOOD REDISTRIBUTION SYSTEM");
        System.out.println("========================================");
        System.out.println("[1] Donatur");
        System.out.println("[2] Donasi");
        System.out.println("[3] Penerima");
        System.out.println("[4] Penyaluran");
        System.out.println("[5] Keluar");
    }
}
