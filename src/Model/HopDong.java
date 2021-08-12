
package Model;

import java.util.Date;

/**
 *
 * @author Tran_Hang
 */
public class HopDong {
   private String MaHopDong, tenKH, maPT, moTa;
    private Date NgayThue, NgayTra;
    private int SoNguoi;
    private double gia;

    public HopDong() {
    }

    public HopDong(String MaHopDong, String tenKH, String maPT, Date NgayThue, Date NgayTra, int SoNguoi, String moTa, double gia) {
        this.MaHopDong = MaHopDong;
        this.tenKH = tenKH;
        this.maPT = maPT;
        this.NgayThue = NgayThue;
        this.NgayTra = NgayTra;
        this.SoNguoi = SoNguoi;
        this.moTa = moTa;
        this.gia = gia;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }
    
    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getMaHopDong() {
        return MaHopDong;
    }

    public void setMaHopDong(String MaHopDong) {
        this.MaHopDong = MaHopDong;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getMaPT() {
        return maPT;
    }

    public void setMaPT(String maPT) {
        this.maPT = maPT;
    }

    public Date getNgayThue() {
        return NgayThue;
    }

    public void setNgayThue(Date NgayThue) {
        this.NgayThue = NgayThue;
    }

    public Date getNgayTra() {
        return NgayTra;
    }

    public void setNgayTra(Date NgayTra) {
        this.NgayTra = NgayTra;
    }

    public int getSoNguoi() {
        return SoNguoi;
    }

    public void setSoNguoi(int SoNguoi) {
        this.SoNguoi = SoNguoi;
    }
    
}
