/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
import java.util.ArrayList;
import java.util.Scanner;
import model.Donasi;
import util.InputUtil;

public class DonasiService {
    private ArrayList<Donasi> dataDonasi = new ArrayList<>();

    public DonasiService() {
        dataDonasi.add(new Donasi(1, 1, "Muffin", 20, "Layak"));
        dataDonasi.add(new Donasi(2, 2, "Sapi Lada Hitam", 15, "Layak"));
        dataDonasi.add(new Donasi(3, 3, "Ayam Bistik", 10, "Layak"));
        dataDonasi.add(new Donasi(4, 4, "Nasi Ayam", 15, "Layak"));
    }

    public void tableData() {
        System.out.println("------------------------------------");
        System.out.println("============= DONASI ===============");
        System.out.println("------------------------------------");

        for (Donasi d : dataDonasi) {
            System.out.println("ID Donasi: " + d.getIdDonasi());
            System.out.println("ID Donatur: " + d.getIdDonatur());
            System.out.println("Nama Makanan: " + d.getNamaMakanan());
            System.out.println("Jumlah Porsi: " + d.getJumlahPorsi());
            System.out.println("Status Kelayakan: " + d.getStatusKelayakan());
            System.out.println("------------------------------------");
        }
    }

    public void dataDonasi(Scanner scanner) {
        boolean berjalanDonasi = true;

        while (berjalanDonasi) {

            tableData();

            System.out.println("[1] Tambah");
            System.out.println("[2] Update");
            System.out.println("[3] Hapus");
            System.out.println("[4] Keluar");
            int pilihanDonasi = InputUtil.bacaInt(scanner, ">> ");

            switch (pilihanDonasi) {

                case 1 -> tambahDonasi(scanner);

                case 2 -> updateDonasi(scanner);

                case 3 -> hapusDonasi(scanner);

                case 4 -> berjalanDonasi = false;

                default -> {
                    System.out.println("------------------------------------");
                    System.out.println("Mohon maaf, pilihan tidak valid! T-T");
                    System.out.println("------------------------------------");
                    InputUtil.tekanEnter(scanner);
                }
            }
        }
    }

    private void tambahDonasi(Scanner scanner) {
        int idDonasi = InputUtil.bacaInt(scanner, "ID Donasi: ");

        if (cariDonasi(idDonasi) != null) {
            System.out.println("------------------------------------");
            System.out.println("[ID Donasi sudah dipakai -__-!]");
            System.out.println("------------------------------------");
            InputUtil.tekanEnter(scanner);
            return;
        }

        int idDonatur = InputUtil.bacaInt(scanner, "ID Donatur: ");
        String namaMakanan = InputUtil.bacaString(scanner, "Nama Makanan: ");
        int jumlahPorsi = InputUtil.bacaInt(scanner, "Jumlah Porsi: ");
        String statusKelayakan = InputUtil.bacaString(scanner, "Status Kelayakan: ");

        dataDonasi.add(new Donasi(
                idDonasi,
                idDonatur,
                namaMakanan,
                jumlahPorsi,
                statusKelayakan
        ));

        System.out.println("------------------------------------");
        System.out.println("[Yay! Data berhasil ditambahkan ^^]");
        System.out.println("------------------------------------");

        InputUtil.tekanEnter(scanner);
    }

    private void updateDonasi(Scanner scanner) {
        int idDonasi = InputUtil.bacaInt(scanner, "ID Donasi: ");

        Donasi donasi = cariDonasi(idDonasi);

        if (donasi == null) {
            System.out.println("------------------------------------");
            System.out.println("[ID Donasi tidak ada -__-!]");
            System.out.println("------------------------------------");
            InputUtil.tekanEnter(scanner);
            return;
        }

        System.out.println("------------------------------------");
        System.out.println("Data yang akan di-update:");
        System.out.println("ID Donatur: " + donasi.getIdDonatur());
        System.out.println("Nama Makanan: " + donasi.getNamaMakanan());
        System.out.println("Jumlah Porsi: " + donasi.getJumlahPorsi());
        System.out.println("Status Kelayakan: " + donasi.getStatusKelayakan());
        System.out.println("------------------------------------");

        System.out.print("Yakin ingin meng-update data? (y/n): ");
        String konfirmasi = scanner.nextLine();

        if (konfirmasi.equalsIgnoreCase("y")) {

            int idDonatur = InputUtil.bacaInt(scanner, "ID Donatur: ");
            String namaMakanan = InputUtil.bacaString(scanner, "Nama Makanan: ");
            int jumlahPorsi = InputUtil.bacaInt(scanner, "Jumlah Porsi: ");
            String statusKelayakan = InputUtil.bacaString(scanner, "Status Kelayakan: ");

            donasi.setIdDonatur(idDonatur);
            donasi.setNamaMakanan(namaMakanan);
            donasi.setJumlahPorsi(jumlahPorsi);
            donasi.setStatusKelayakan(statusKelayakan);

            System.out.println("------------------------------------");
            System.out.println("[Yay! Data berhasil di-Update ^^]");
            System.out.println("------------------------------------");

        } else {

            System.out.println("------------------------------------");
            System.out.println("[Update dibatalkan ^^]");
            System.out.println("------------------------------------");
        }

        InputUtil.tekanEnter(scanner);
    }

    private void hapusDonasi(Scanner scanner) {
        int idDonasi = InputUtil.bacaInt(scanner, "ID Donasi: ");

        Donasi donasi = cariDonasi(idDonasi);

        if (donasi == null) {
            System.out.println("------------------------------------");
            System.out.println("[ID Donasi tidak ada -__-!]");
            System.out.println("------------------------------------");
            InputUtil.tekanEnter(scanner);
            return;
        }

        System.out.println("------------------------------------");
        System.out.println("Data yang akan dihapus:");
        System.out.println("ID Donatur: " + donasi.getIdDonatur());
        System.out.println("Nama Makanan: " + donasi.getNamaMakanan());
        System.out.println("Jumlah Porsi: " + donasi.getJumlahPorsi());
        System.out.println(
                "Status Kelayakan: " + donasi.getStatusKelayakan()
        );
        System.out.println("------------------------------------");

        System.out.print("Yakin ingin menghapus data? (y/n): ");
        String konfirmasi = scanner.nextLine();

        if (konfirmasi.equalsIgnoreCase("y")) {

            dataDonasi.remove(donasi);

            System.out.println("------------------------------------");
            System.out.println("[Yay! Data berhasil di-Hapus ^^]");
            System.out.println("------------------------------------");

        } else {

            System.out.println("------------------------------------");
            System.out.println("[Hapus dibatalkan ^^]");
            System.out.println("------------------------------------");
        }

        InputUtil.tekanEnter(scanner);
    }

    private Donasi cariDonasi(int idDonasi) {
        for (Donasi d : dataDonasi) {
            if (d.getIdDonasi() == idDonasi) {
                return d;
            }
        }

        return null;
    }
}
