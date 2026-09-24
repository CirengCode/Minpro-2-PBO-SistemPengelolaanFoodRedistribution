/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.ArrayList;
import java.util.Scanner;
import model.Donatur;
import model.DonaturIndividu;
import model.DonaturInstansi;
import util.InputUtil;

public class DonaturService {
    private final ArrayList<Donatur> dataDonatur = new ArrayList<>();

    public DonaturService() {
        dataDonatur.add(new DonaturInstansi(1, "Budi Santoso", "Hotel Sejahtera", "Hotel"));
        dataDonatur.add(new DonaturInstansi(2, "Siti Rahma", "Restoran Makmur", "Restoran"));
        dataDonatur.add(new DonaturInstansi(3, "Andi Wijaya", "Dapur Berkah", "Usaha Kuliner"));
        dataDonatur.add(new DonaturIndividu(4, "Aulia Fatmawati", "Acara Syukuran"));
    }

    public void tableData() {
        System.out.println("------------------------------------");
        System.out.println("============= DONATUR ==============");
        System.out.println("------------------------------------");

        for (Donatur d : dataDonatur) {
            System.out.println("ID Donatur: " + d.getIdDonatur());
            System.out.println("Nama Donatur: " + d.getNamaDonatur());
            System.out.println("Jenis Donatur: " + d.getJenisDonatur());

            if (d instanceof DonaturIndividu individu) {
                System.out.println("Jenis Kegiatan: " + individu.getJenisKegiatan());
            } else if (d instanceof DonaturInstansi instansi) {
                System.out.println("Nama Instansi: " + instansi.getNamaInstansi());
                System.out.println("Jenis Instansi: " + instansi.getJenisInstansi());
            }
            System.out.println("------------------------------------");
        }
    }

    public void dataDonatur(Scanner scanner) {
        boolean berjalanDonatur = true;

        while (berjalanDonatur) {

            tableData();

            System.out.println("[1] Tambah");
            System.out.println("[2] Update");
            System.out.println("[3] Hapus");
            System.out.println("[4] Keluar");
            int pilihanDonatur = InputUtil.bacaInt(scanner, ">> ");

            switch (pilihanDonatur) {

                case 1 -> tambahDonatur(scanner);

                case 2 -> updateDonatur(scanner);

                case 3 -> hapusDonatur(scanner);

                case 4 -> berjalanDonatur = false;

                default -> {
                    System.out.println("------------------------------------");
                    System.out.println("Mohon maaf, pilihan tidak valid! T-T");
                    System.out.println("------------------------------------");
                    InputUtil.tekanEnter(scanner);
                }
            }
        }
    }

    private void tambahDonatur(Scanner scanner) {
        int idDonatur = InputUtil.bacaInt(scanner, "ID Donatur: ");

        if (cariDonatur(idDonatur) != null) {
            System.out.println("------------------------------------");
            System.out.println("[ID Donatur sudah dipakai -__-!]");
            System.out.println("------------------------------------");
            InputUtil.tekanEnter(scanner);
            return;
        }

        String namaDonatur = InputUtil.bacaString(scanner, "Nama Donatur: ");

        System.out.println("------------------------------------");
        System.out.println("[1] Donatur Individu");
        System.out.println("[2] Donatur Instansi");
        System.out.println("------------------------------------");
        int pilihanJenis = InputUtil.bacaInt(scanner, "Jenis Donatur: ");
        System.out.println("------------------------------------");

        if (pilihanJenis == 1) {

            String jenisKegiatan = InputUtil.bacaString(scanner,"Jenis Kegiatan: ");

            dataDonatur.add(new DonaturIndividu(
                    idDonatur, 
                    namaDonatur, 
                    jenisKegiatan
            ));

            System.out.println("------------------------------------");
            System.out.println("[Yay! Data berhasil ditambahkan ^^]");
            System.out.println("------------------------------------");

        } else if (pilihanJenis == 2) {

            String namaInstansi = InputUtil.bacaString(scanner, "Nama Instansi: ");
            String jenisInstansi = InputUtil.bacaString(scanner, "Jenis Instansi: ");

            dataDonatur.add(new DonaturInstansi(
                    idDonatur, 
                    namaDonatur, 
                    namaInstansi,
                    jenisInstansi
            ));

            System.out.println("------------------------------------");
            System.out.println("[Yay! Data berhasil ditambahkan ^^]");
            System.out.println("------------------------------------");

        } else {

            System.out.println("------------------------------------");
            System.out.println("[Jenis Donatur tidak valid -__-!]");
            System.out.println("------------------------------------");
        }

        InputUtil.tekanEnter(scanner);
    }

    private void updateDonatur(Scanner scanner) {
        int idDonatur = InputUtil.bacaInt(scanner, "ID Donatur: ");

        Donatur donatur = cariDonatur(idDonatur);

        if (donatur == null) {
            System.out.println("------------------------------------");
            System.out.println("[ID Donatur tidak ada -__-!]");
            System.out.println("------------------------------------");
            
            InputUtil.tekanEnter(scanner);
            return;
        }

        System.out.println("------------------------------------");
        System.out.println("Data yang akan di-update:");
        System.out.println("Nama Donatur: " + donatur.getNamaDonatur());
        System.out.println("Jenis Donatur: " + donatur.getJenisDonatur());

        if (donatur instanceof DonaturIndividu individu) {
            System.out.println("Jenis Kegiatan: " + individu.getJenisKegiatan());
        } else if (donatur instanceof DonaturInstansi instansi) {
            System.out.println("Nama Instansi: " + instansi.getNamaInstansi());
            System.out.println("Jenis Instansi: " + instansi.getJenisInstansi());
        }

        System.out.println("------------------------------------");
        System.out.print("Yakin ingin meng-update data? (y/n): ");
        String konfirmasi = scanner.nextLine();

        if (konfirmasi.equalsIgnoreCase("y")) {

            String namaDonatur = InputUtil.bacaString(scanner, "Nama Donatur: ");

            if (donatur instanceof DonaturIndividu individu) {

                String jenisKegiatan = InputUtil.bacaString(scanner, "Jenis Kegiatan: ");

                individu.setNamaDonatur(namaDonatur);
                individu.setJenisKegiatan(jenisKegiatan);

            } else if (donatur instanceof DonaturInstansi instansi) {

                String namaInstansi = InputUtil.bacaString(scanner, "Nama Instansi: ");
                String jenisInstansi = InputUtil.bacaString(scanner,"Jenis Instansi: ");

                instansi.setNamaDonatur(namaDonatur);
                instansi.setNamaInstansi(namaInstansi);
                instansi.setJenisInstansi(jenisInstansi);
            }

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

    private void hapusDonatur(Scanner scanner) {
        int idDonatur = InputUtil.bacaInt(scanner, "ID Donatur: ");

        Donatur donatur = cariDonatur(idDonatur);

        if (donatur == null) {
            System.out.println("------------------------------------");
            System.out.println("[ID Donatur tidak ada -__-!]");
            System.out.println("------------------------------------");
            InputUtil.tekanEnter(scanner);
            return;
        }

        System.out.println("------------------------------------");
        System.out.println("Data yang akan dihapus:");
        System.out.println("Nama Donatur: " + donatur.getNamaDonatur());
        System.out.println("Jenis Donatur: " + donatur.getJenisDonatur());

        if (donatur instanceof DonaturIndividu individu) {
            System.out.println("Jenis Kegiatan: " + individu.getJenisKegiatan());
        } else if (donatur instanceof DonaturInstansi instansi) {
            System.out.println("Nama Instansi: " + instansi.getNamaInstansi());
            System.out.println("Jenis Instansi: " + instansi.getJenisInstansi());
        }

        System.out.println("------------------------------------");

        System.out.print("Yakin ingin menghapus data? (y/n): ");
        String konfirmasi = scanner.nextLine();

        if (konfirmasi.equalsIgnoreCase("y")) {

            dataDonatur.remove(donatur);

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

    public Donatur cariDonatur(int idDonatur) {
        for (Donatur d : dataDonatur) {
            if (d.getIdDonatur() == idDonatur) {
                return d;
            }
        }

        return null;
    }
}
