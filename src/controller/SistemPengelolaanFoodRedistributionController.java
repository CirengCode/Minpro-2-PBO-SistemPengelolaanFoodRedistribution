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
        donasiService = new DonasiService(donaturService);
        penerimaService = new PenerimaService();
        penyaluranService = new PenyaluranService(donasiService, penerimaService);
    }

    public void mulai(Scanner scanner) {
        boolean berjalan = true;

        while (berjalan) {

            tampilkanMenu();

            int pilihan = InputUtil.bacaInt(scanner, ">> ");

            switch (pilihan) {

                case 1 -> menuAdmin(scanner);

                case 2 -> menuPetugas(scanner);

                case 3 -> {
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
        System.out.println("       ⋆⭒˚.⋆ SELAMAT DATANG DI ⋆⭒˚.⋆     ");
        System.out.println("        FOOD REDISTRIBUTION SYSTEM      ");
        System.out.println("========================================");
        System.out.println("[1] Admin");
        System.out.println("[2] Petugas");
        System.out.println("[3] Keluar");
    }
    
    private void menuAdmin(Scanner scanner) {
        boolean berjalanAdmin = true;

        while (berjalanAdmin) {

            System.out.println();
            System.out.println("========================================");
            System.out.println("               MENU ADMIN               ");
            System.out.println("========================================");
            System.out.println("[1] Donatur");
            System.out.println("[2] Donasi");
            System.out.println("[3] Penerima");
            System.out.println("[4] Penyaluran");
            System.out.println("[5] Kembali");

            int pilihanAdmin = InputUtil.bacaInt(scanner, ">> ");

            switch (pilihanAdmin) {

                case 1 -> donaturService.dataDonatur(scanner);

                case 2 -> donasiService.dataDonasi(scanner);

                case 3 -> penerimaService.dataPenerima(scanner);

                case 4 -> penyaluranService.dataPenyaluran(scanner);

                case 5 -> berjalanAdmin = false;

                default -> {
                    System.out.println("------------------------------------");
                    System.out.println("Mohon maaf, pilihan tidak valid! T-T");
                    System.out.println("------------------------------------");
                    InputUtil.tekanEnter(scanner);
                }
            }
        }
    }
    
    private void menuPetugas(Scanner scanner) {
        boolean berjalanPetugas = true;

        while (berjalanPetugas) {

            System.out.println();
            System.out.println("========================================");
            System.out.println("              MENU PETUGAS              ");
            System.out.println("========================================");
            System.out.println("[1] Lihat Data Donasi");
            System.out.println("[2] Lihat Data Penerima");
            System.out.println("[3] Lihat Data Penyaluran");
            System.out.println("[4] Kembali");

            int pilihanPetugas = InputUtil.bacaInt(scanner, ">> ");

            switch (pilihanPetugas) {

                case 1 -> {
                    donasiService.tableData();
                    InputUtil.tekanEnter(scanner);
                }

                case 2 -> {
                    penerimaService.tableData();
                    InputUtil.tekanEnter(scanner);
                }

                case 3 -> {
                    penyaluranService.tableData();
                    InputUtil.tekanEnter(scanner);
                }

                case 4 -> berjalanPetugas = false;

                default -> {
                    System.out.println("------------------------------------");
                    System.out.println("Mohon maaf, pilihan tidak valid! T-T");
                    System.out.println("------------------------------------");
                    InputUtil.tekanEnter(scanner);
                }
            }
        }
    }

}
