/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
import java.util.ArrayList;
import java.util.Scanner;
import model.Penerima;
import model.PenerimaIndividu;
import model.PenerimaLembaga;
import util.InputUtil;

public class PenerimaService {
        private final ArrayList<Penerima> dataPenerima = new ArrayList<>();

    public PenerimaService() {
        dataPenerima.add(new PenerimaLembaga(1, "Rumah Singgah Harapan", "Rumah Singgah", "Putri Ayu"));
        dataPenerima.add(new PenerimaLembaga(2, "Panti Asuhan Kasih Ibu", "Panti Asuhan", "Siti Rahma"));
        dataPenerima.add(new PenerimaLembaga(3, "Panti Asuhan Cinta Harapan", "Rumah Singgah", "Budi Santoso"));
        dataPenerima.add(new PenerimaIndividu(4,"Orang yang membutuhkan"));
    }

    public void tableData() {
        System.out.println("------------------------------------");
        System.out.println("============= PENERIMA =============");
        System.out.println("------------------------------------");

        for (Penerima p : dataPenerima) {
            System.out.println("ID Penerima: " + p.getIdPenerima());
            System.out.println("Jenis Penerima: " + p.getJenisPenerima());

            if (p instanceof PenerimaIndividu individu) {
                System.out.println("Deskripsi Penerima: " + individu.getDeskripsiPenerima());
            } else if (p instanceof PenerimaLembaga lembaga) {
                System.out.println("Nama Lembaga: " + lembaga.getNamaLembaga());
                System.out.println("Jenis Lembaga: " + lembaga.getJenisLembaga());
                System.out.println("Nama Pengelola: " + lembaga.getNamaPengelola());
            }

            System.out.println("------------------------------------");
        }
    }

    public void dataPenerima(Scanner scanner) {
        boolean berjalanPenerima = true;

        while (berjalanPenerima) {

            tableData();

            System.out.println("[1] Tambah");
            System.out.println("[2] Update");
            System.out.println("[3] Hapus");
            System.out.println("[4] Keluar");
            int pilihanPenerima = InputUtil.bacaInt(scanner, ">> ");

            switch (pilihanPenerima) {

                case 1 -> tambahPenerima(scanner);

                case 2 -> updatePenerima(scanner);

                case 3 -> hapusPenerima(scanner);

                case 4 -> berjalanPenerima = false;

                default -> {
                    System.out.println("------------------------------------");
                    System.out.println("Mohon maaf, pilihan tidak valid! T-T");
                    System.out.println("------------------------------------");
                    InputUtil.tekanEnter(scanner);
                }
            }
        }
    }

    private void tambahPenerima(Scanner scanner) {
        int idPenerima = InputUtil.bacaInt(scanner, "ID Penerima: ");

        if (cariPenerima(idPenerima) != null) {
            System.out.println("------------------------------------");
            System.out.println("[ID Penerima sudah dipakai -__-!]");
            System.out.println("------------------------------------");
            InputUtil.tekanEnter(scanner);
            return;
        }

        System.out.println("------------------------------------");
        System.out.println("[1] Penerima Individu");
        System.out.println("[2] Penerima Lembaga");
        System.out.println("------------------------------------");
        int pilihanJenis = InputUtil.bacaInt(scanner, "Jenis Penerima: ");
        System.out.println("------------------------------------");

        if (pilihanJenis == 1) {

            String deskripsiPenerima = InputUtil.bacaString(scanner, "Deskripsi Penerima: ");

            dataPenerima.add(new PenerimaIndividu(
                    idPenerima,
                    deskripsiPenerima
            ));

            System.out.println("------------------------------------");
            System.out.println("[Yay! Data berhasil ditambahkan ^^]");
            System.out.println("------------------------------------");

        } else if (pilihanJenis == 2) {

            String namaLembaga = InputUtil.bacaString(scanner, "Nama Lembaga: ");
            String jenisLembaga = InputUtil.bacaString(scanner, "Jenis Lembaga: ");
            String namaPengelola = InputUtil.bacaString(scanner, "Nama Pengelola: ");

            dataPenerima.add(new PenerimaLembaga(
                    idPenerima,
                    namaLembaga,
                    jenisLembaga,
                    namaPengelola
            ));

            System.out.println("------------------------------------");
            System.out.println("[Yay! Data berhasil ditambahkan ^^]");
            System.out.println("------------------------------------");

        } else {

            System.out.println("------------------------------------");
            System.out.println("[Jenis Penerima tidak valid -__-!]");
            System.out.println("------------------------------------");
        }

        InputUtil.tekanEnter(scanner);
    }

    private void updatePenerima(Scanner scanner) {
        int idPenerima = InputUtil.bacaInt(scanner, "ID Penerima: ");

        Penerima penerima = cariPenerima(idPenerima);

        if (penerima == null) {
            System.out.println("------------------------------------");
            System.out.println("[ID Penerima tidak ada -__-!]");
            System.out.println("------------------------------------");
            InputUtil.tekanEnter(scanner);
            return;
        }

        System.out.println("------------------------------------");
        System.out.println("Data yang akan di-update:");
        System.out.println("Jenis Penerima: " + penerima.getJenisPenerima());

        if (penerima instanceof PenerimaIndividu individu) {
            System.out.println("Deskripsi Penerima: " + individu.getDeskripsiPenerima());

        } else if (penerima instanceof PenerimaLembaga lembaga) {
            System.out.println("Nama Lembaga: " + lembaga.getNamaLembaga());
            System.out.println("Jenis Lembaga: " + lembaga.getJenisLembaga());
            System.out.println("Nama Pengelola: " + lembaga.getNamaPengelola());
        }

        System.out.println("------------------------------------");

        System.out.print("Yakin ingin meng-update data? (y/n): ");
        String konfirmasi = scanner.nextLine();

        if (konfirmasi.equalsIgnoreCase("y")) {

            if (penerima instanceof PenerimaIndividu individu) {

                String deskripsiPenerima = InputUtil.bacaString(scanner, "Deskripsi Penerima: ");
               
                individu.setDeskripsiPenerima(deskripsiPenerima);

            } else if (penerima instanceof PenerimaLembaga lembaga) {

                String namaLembaga = InputUtil.bacaString(scanner, "Nama Lembaga: ");
                String jenisLembaga = InputUtil.bacaString(scanner, "Jenis Lembaga: ");
                String namaPengelola = InputUtil.bacaString(scanner, "Nama Pengelola: ");

                lembaga.setNamaLembaga(namaLembaga);
                lembaga.setJenisLembaga(jenisLembaga);
                lembaga.setNamaPengelola(namaPengelola);
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

    private void hapusPenerima(Scanner scanner) {
        int idPenerima = InputUtil.bacaInt(scanner, "ID Penerima: ");

        Penerima penerima = cariPenerima(idPenerima);

        if (penerima == null) {
            System.out.println("------------------------------------");
            System.out.println("[ID Penerima tidak ada -__-!]");
            System.out.println("------------------------------------");
            InputUtil.tekanEnter(scanner);
            return;
        }

        System.out.println("------------------------------------");
        System.out.println("Data yang akan dihapus:");
        System.out.println("Jenis Penerima: " + penerima.getJenisPenerima());

        if (penerima instanceof PenerimaIndividu individu) {
            System.out.println("Deskripsi Penerima: " + individu.getDeskripsiPenerima());

        } else if (penerima instanceof PenerimaLembaga lembaga) {
            System.out.println("Nama Lembaga: " + lembaga.getNamaLembaga());
            System.out.println("Jenis Lembaga: " + lembaga.getJenisLembaga());
            System.out.println("Nama Pengelola: " + lembaga.getNamaPengelola());
        }

        System.out.println("------------------------------------");
        System.out.print("Yakin ingin menghapus data? (y/n): ");
        String konfirmasi = scanner.nextLine();

        if (konfirmasi.equalsIgnoreCase("y")) {

            dataPenerima.remove(penerima);

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

    public Penerima cariPenerima(int idPenerima) {
        for (Penerima p : dataPenerima) {
            if (p.getIdPenerima() == idPenerima) {
                return p;
            }
        }

        return null;
    }
}
