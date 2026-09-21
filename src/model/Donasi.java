/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class Donasi {
    private int idDonasi;
    private int idDonatur;
    private String namaMakanan;
    private int jumlahPorsi;
    private String statusKelayakan;

    public Donasi(
            int idDonasi,
            int idDonatur,
            String namaMakanan,
            int jumlahPorsi,
            String statusKelayakan) {

        this.idDonasi = idDonasi;
        this.idDonatur = idDonatur;
        this.namaMakanan = namaMakanan;
        this.jumlahPorsi = jumlahPorsi;
        this.statusKelayakan = statusKelayakan;
    }

    public int getIdDonasi() {
        return idDonasi;
    }

    public void setIdDonasi(int idDonasi) {
        this.idDonasi = idDonasi;
    }

    public int getIdDonatur() {
        return idDonatur;
    }

    public void setIdDonatur(int idDonatur) {
        this.idDonatur = idDonatur;
    }

    public String getNamaMakanan() {
        return namaMakanan;
    }

    public void setNamaMakanan(String namaMakanan) {
        this.namaMakanan = namaMakanan;
    }

    public int getJumlahPorsi() {
        return jumlahPorsi;
    }

    public void setJumlahPorsi(int jumlahPorsi) {
        this.jumlahPorsi = jumlahPorsi;
    }

    public String getStatusKelayakan() {
        return statusKelayakan;
    }

    public void setStatusKelayakan(String statusKelayakan) {
        this.statusKelayakan = statusKelayakan;
    }
}
