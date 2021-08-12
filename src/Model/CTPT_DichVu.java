/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author admin
 */
public class CTPT_DichVu {
    int maCTPT;
    String maHopDong,maDV;

    public CTPT_DichVu() {
    }

    public CTPT_DichVu(int maCTPT, String maHopDong, String maDV) {
        this.maCTPT = maCTPT;
        this.maHopDong = maHopDong;
        this.maDV = maDV;
    }

    public void setMaCTPT(int maCTPT) {
        this.maCTPT = maCTPT;
    }

    public void setMaHopDong(String maHopDong) {
        this.maHopDong = maHopDong;
    }

    public void setMaDV(String maDV) {
        this.maDV = maDV;
    }

    public int getMaCTPT() {
        return maCTPT;
    }

    public String getMaHopDong() {
        return maHopDong;
    }

    public String getMaDV() {
        return maDV;
    }
    
}
