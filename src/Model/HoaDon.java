
package Model;

import java.util.Date;

/**
 *
 * @author Tran_Hang
 */
public class HoaDon {
    private String maHD, tenPT, tenKH, maHopDong;
    private Date NamThang;
    private boolean tinhTrang;
    private double TongTien;

    public HoaDon() {
    }

    public HoaDon(String maHD, String tenPT, String tenKH, String maHĐ, Date NamThang, boolean tinhTrang, double TongTien) {
        this.maHD = maHD;
        this.tenPT = tenPT;
        this.tenKH = tenKH;
        this.maHopDong = maHĐ;
        this.NamThang = NamThang;
        this.tinhTrang = tinhTrang;
        this.TongTien = TongTien;
    }

    public String getMaHopDong() {
        return maHopDong;
    }

    public void setMaHopDong(String maHĐ) {
        this.maHopDong = maHĐ;
    }

    public double getTongTien() {
        return TongTien;
    }

    public void setTongTien(double TongTien) {
        this.TongTien = TongTien;
    }
    
    public boolean isTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(boolean tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getTenPT() {
        return tenPT;
    }

    public void setTenPT(String tenPT) {
        this.tenPT = tenPT;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public Date getNamThang() {
        return NamThang;
    }

    public void setNamThang(Date NamThang) {
        this.NamThang = NamThang;
    }
    
}
