/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
import java.util.ArrayList;
import java.util.Scanner;
import model.Penyaluran;
import util.InputUtil;

public class PenyaluranService {
    private final ArrayList<Penyaluran> dataPenyaluran = new ArrayList<>();
    private final DonasiService donasiService;
    private final PenerimaService penerimaService;

    public PenyaluranService(DonasiService donasiService, PenerimaService penerimaService) {
        this.donasiService = donasiService;
        this.penerimaService = penerimaService;
        
        dataPenyaluran.add(new Penyaluran(1, 1, 1, "Penyaluran ke Rumah Singgah", "10-09-2026", 20, "Petugas Komunitas"));
        dataPenyaluran.add(new Penyaluran(2, 2, 2, "Penyaluran ke Panti Asuhan", "01-09-2026", 15, "Petugas Komunitas"));
        dataPenyaluran.add(new Penyaluran(3, 3, 3, "Penyaluran ke Panti Asuhan", "10-07-2026", 10, "Petugas Komunitas"));
        dataPenyaluran.add(new Penyaluran(4, 4, 4, "Pembagian Makanan untuk Warga", "11-11-2026", 10, "Relawan"));
    }

    public void tableData() {
        System.out.println("------------------------------------");
        System.out.println("=========== PENYALURAN =============");
        System.out.println("------------------------------------");

        for (Penyaluran p : dataPenyaluran) {
            System.out.println("ID Penyaluran: " + p.getIdPenyaluran());
            System.out.println("ID Donasi: " + p.getIdDonasi());
            System.out.println("ID Penerima: " + p.getIdPenerima());
            System.out.println("Nama Kegiatan: " + p.getNamaKegiatan());
            System.out.println("Tanggal Penyaluran: " + p.getTanggalPenyaluran());
            System.out.println("Jumlah Porsi: " + p.getJumlahPorsi());
            System.out.println("Petugas: " + p.getPetugas());
            System.out.println("------------------------------------");
        }
    }

    public void dataPenyaluran(Scanner scanner) {
        boolean berjalanPenyaluran = true;

        while (berjalanPenyaluran) {

            tableData();

            System.out.println("[1] Tambah");
            System.out.println("[2] Update");
            System.out.println("[3] Hapus");
            System.out.println("[4] Keluar");
            int pilihanPenyaluran = InputUtil.bacaInt(scanner, ">> ");

            switch (pilihanPenyaluran) {

                case 1 -> tambahPenyaluran(scanner);

                case 2 -> updatePenyaluran(scanner);

                case 3 -> hapusPenyaluran(scanner);

                case 4 -> berjalanPenyaluran = false;

                default -> {
                    System.out.println("------------------------------------");
                    System.out.println("Mohon maaf, pilihan tidak valid! T-T");
                    System.out.println("------------------------------------");
                    InputUtil.tekanEnter(scanner);
                }
            }
        }
    }

    private void tambahPenyaluran(Scanner scanner) {
        int idPenyaluran = InputUtil.bacaInt(scanner, "ID Penyaluran: ");

        if (cariPenyaluran(idPenyaluran) != null) {
            System.out.println("------------------------------------");
            System.out.println("[ID Penyaluran sudah dipakai -__-!]");
            System.out.println("------------------------------------");
            InputUtil.tekanEnter(scanner);
            return;
        }
        
        int idDonasi;
        while (true) {
            idDonasi = InputUtil.bacaInt(scanner, "ID Donasi: ");

            if (donasiService.cariDonasi(idDonasi) != null) {
                break;
            }

            System.out.println("---------------------------------------");
            System.out.println("[      ID Donasi tidak ada -__-!      ]");
            System.out.println("[Silakan masukkan ID Donasi yang valid]");
            System.out.println("---------------------------------------");
        }

        
        int idPenerima;
        while (true) {
            idPenerima = InputUtil.bacaInt(scanner, "ID Penerima: ");

            if (penerimaService.cariPenerima(idPenerima) != null) {
                break;
            }
            
            System.out.println("-------------------------------------------");
            System.out.println("[       ID Penerima tidak ada -__-!        ]");
            System.out.println("[  Silakan masukkan ID Penerima yang valid ]");
            System.out.println("-------------------------------------------");
        }
        
        String namaKegiatan = InputUtil.bacaString(scanner, "Nama Kegiatan: ");
        String tanggalPenyaluran = InputUtil.bacaString(scanner, "Tanggal Penyaluran: ");
        int jumlahPorsi = InputUtil.bacaInt(scanner, "Jumlah Porsi: ");
        String petugas = InputUtil.bacaString(scanner, "Petugas: ");
        
        dataPenyaluran.add(new Penyaluran(
                idPenyaluran,
                idDonasi,
                idPenerima,
                namaKegiatan,
                tanggalPenyaluran,
                jumlahPorsi,
                petugas
        ));

        System.out.println("------------------------------------");
        System.out.println("[Yay! Data berhasil ditambahkan ^^]");
        System.out.println("------------------------------------");

        InputUtil.tekanEnter(scanner);
    }

    private void updatePenyaluran(Scanner scanner) {
        int idPenyaluran = InputUtil.bacaInt(scanner,"ID Penyaluran: ");

        Penyaluran penyaluran = cariPenyaluran(idPenyaluran);

        if (penyaluran == null) {
            System.out.println("------------------------------------");
            System.out.println("[ID Penyaluran tidak ada -__-!]");
            System.out.println("------------------------------------");
            InputUtil.tekanEnter(scanner);
            return;
        }

        System.out.println("------------------------------------");
        System.out.println("Data yang akan di-update:");
        System.out.println("ID Donasi: " + penyaluran.getIdDonasi());
        System.out.println("ID Penerima: " + penyaluran.getIdPenerima());
        System.out.println("Nama Kegiatan: " + penyaluran.getNamaKegiatan());
        System.out.println("Tanggal Penyaluran: " + penyaluran.getTanggalPenyaluran());
        System.out.println("Jumlah Porsi: " + penyaluran.getJumlahPorsi());
        System.out.println("Petugas: " + penyaluran.getPetugas());
        System.out.println("------------------------------------");

        System.out.print("Yakin ingin meng-update data? (y/n): ");
        String konfirmasi = scanner.nextLine();

        if (konfirmasi.equalsIgnoreCase("y")) {

            int idDonasi;
            while (true) {
                idDonasi = InputUtil.bacaInt(scanner, "ID Donasi: ");

                if (donasiService.cariDonasi(idDonasi) != null) {
                    break;
                }

                System.out.println("---------------------------------------");
                System.out.println("[      ID Donasi tidak ada -__-!      ]");
                System.out.println("[Silakan masukkan ID Donasi yang valid]");
                System.out.println("---------------------------------------");
            }
            
            
            int idPenerima;
            while (true) {
                idPenerima = InputUtil.bacaInt(scanner, "ID Penerima: ");

                if (penerimaService.cariPenerima(idPenerima) != null) {
                    break;
                }

                System.out.println("-------------------------------------------");
                System.out.println("[       ID Penerima tidak ada -__-!        ]");
                System.out.println("[  Silakan masukkan ID Penerima yang valid ]");
                System.out.println("-------------------------------------------");
            }
            
            String namaKegiatan = InputUtil.bacaString(scanner, "Nama Kegiatan: ");
            String tanggalPenyaluran = InputUtil.bacaString(scanner, "Tanggal Penyaluran: ");
            int jumlahPorsi = InputUtil.bacaInt(scanner, "Jumlah Porsi: ");
            String petugas = InputUtil.bacaString(scanner, "Petugas: ");

            penyaluran.setIdDonasi(idDonasi);
            penyaluran.setIdPenerima(idPenerima);
            penyaluran.setNamaKegiatan(namaKegiatan);
            penyaluran.setTanggalPenyaluran(tanggalPenyaluran);
            penyaluran.setJumlahPorsi(jumlahPorsi);
            penyaluran.setPetugas(petugas);

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

    private void hapusPenyaluran(Scanner scanner) {
        int idPenyaluran = InputUtil.bacaInt(scanner, "ID Penyaluran: ");

        Penyaluran penyaluran = cariPenyaluran(idPenyaluran);

        if (penyaluran == null) {
            System.out.println("------------------------------------");
            System.out.println("[ID Penyaluran tidak ada -__-!]");
            System.out.println("------------------------------------");
            InputUtil.tekanEnter(scanner);
            return;
        }

        System.out.println("------------------------------------");
        System.out.println("Data yang akan dihapus:");
        System.out.println("ID Donasi: " + penyaluran.getIdDonasi());
        System.out.println("ID Penerima: " + penyaluran.getIdPenerima());
        System.out.println("Nama Kegiatan: " + penyaluran.getNamaKegiatan());
        System.out.println("Tanggal Penyaluran: " + penyaluran.getTanggalPenyaluran());
        System.out.println("Jumlah Porsi: " + penyaluran.getJumlahPorsi());
        System.out.println("Petugas: " + penyaluran.getPetugas());
        System.out.println("------------------------------------");

        System.out.print("Yakin ingin menghapus data? (y/n): ");
        String konfirmasi = scanner.nextLine();

        if (konfirmasi.equalsIgnoreCase("y")) {

            dataPenyaluran.remove(penyaluran);

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

    private Penyaluran cariPenyaluran(int idPenyaluran) {
        for (Penyaluran p : dataPenyaluran) {
            if (p.getIdPenyaluran() == idPenyaluran) {
                return p;
            }
        }

        return null;
    }
}
