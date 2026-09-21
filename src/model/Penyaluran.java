/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class Penyaluran {
    private int idPenyaluran;
    private int idDonasi;
    private int idPenerima;
    private String namaKegiatan;
    private String tanggalPenyaluran;
    private int jumlahPorsi;
    private String petugas;

    public Penyaluran(
            int idPenyaluran,
            int idDonasi,
            int idPenerima,
            String namaKegiatan,
            String tanggalPenyaluran,
            int jumlahPorsi,
            String petugas) {

        this.idPenyaluran = idPenyaluran;
        this.idDonasi = idDonasi;
        this.idPenerima = idPenerima;
        this.namaKegiatan = namaKegiatan;
        this.tanggalPenyaluran = tanggalPenyaluran;
        this.jumlahPorsi = jumlahPorsi;
        this.petugas = petugas;
    }

    public int getIdPenyaluran() {
        return idPenyaluran;
    }

    public void setIdPenyaluran(int idPenyaluran) {
        this.idPenyaluran = idPenyaluran;
    }

    public int getIdDonasi() {
        return idDonasi;
    }

    public void setIdDonasi(int idDonasi) {
        this.idDonasi = idDonasi;
    }

    public int getIdPenerima() {
        return idPenerima;
    }

    public void setIdPenerima(int idPenerima) {
        this.idPenerima = idPenerima;
    }

    public String getNamaKegiatan() {
        return namaKegiatan;
    }

    public void setNamaKegiatan(String namaKegiatan) {
        this.namaKegiatan = namaKegiatan;
    }

    public String getTanggalPenyaluran() {
        return tanggalPenyaluran;
    }

    public void setTanggalPenyaluran(String tanggalPenyaluran) {
        this.tanggalPenyaluran = tanggalPenyaluran;
    }

    public int getJumlahPorsi() {
        return jumlahPorsi;
    }

    public void setJumlahPorsi(int jumlahPorsi) {
        this.jumlahPorsi = jumlahPorsi;
    }

    public String getPetugas() {
        return petugas;
    }

    public void setPetugas(String petugas) {
        this.petugas = petugas;
    }
}
