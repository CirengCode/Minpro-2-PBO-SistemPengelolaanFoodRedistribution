/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class Donatur {
    private int idDonatur;
    private String namaDonatur;

    public Donatur(int idDonatur, String namaDonatur) {
        this.idDonatur = idDonatur;
        this.namaDonatur = namaDonatur;
    }

    public int getIdDonatur() {
        return idDonatur;
    }

    public void setIdDonatur(int idDonatur) {
        this.idDonatur = idDonatur;
    }

    public String getNamaDonatur() {
        return namaDonatur;
    }

    public void setNamaDonatur(String namaDonatur) {
        this.namaDonatur = namaDonatur;
    }

    public String getJenisDonatur() {
        return "Donatur";
    }
}
