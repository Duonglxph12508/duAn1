/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Tran_Hang
 */
public class DichVu {
    private String MaDV, tenDV;
    private double gia;

    public DichVu() {
    }

    public DichVu(String MaDV, String tenDV, double gia) {
        this.MaDV = MaDV;
        this.tenDV = tenDV;
        this.gia = gia;
    }

    public String getMaDV() {
        return MaDV;
    }

    public void setMaDV(String MaDV) {
        this.MaDV = MaDV;
    }

    public String getTenDV() {
        return tenDV;
    }

    public void setTenDV(String tenDV) {
        this.tenDV = tenDV;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }
    
    
}
