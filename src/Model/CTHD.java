package Model;

/**
 *
 * @author Tran_Hang
 */
public class CTHD {

    private String  MaHoaDon, MaCTHĐ, TenDV;
    private double Gia, thanhTien, tongTien;
    private int SoLuong, soDienMoi, soDienCu;

    public CTHD() {
    }

    public CTHD(String MaHoaDon, String MaCTHĐ, String TenDV, double Gia, double thanhTien, int SoLuong, int soDienMoi, int soDienCu) {
        this.MaHoaDon = MaHoaDon;
        this.MaCTHĐ = MaCTHĐ;
        this.TenDV = TenDV;
        this.Gia = Gia;
        this.thanhTien = thanhTien;
        this.SoLuong = SoLuong;
        this.soDienMoi = soDienMoi;
        this.soDienCu = soDienCu;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    
    public int getSoDienMoi() {
        return soDienMoi;
    }

    public void setSoDienMoi(int soDienMoi) {
        this.soDienMoi = soDienMoi;
    }

    public int getSoDienCu() {
        return soDienCu;
    }

    public void setSoDienCu(int soDienCu) {
        this.soDienCu = soDienCu;
    }

    public double getThanhTien() {
        return this.Gia * this.SoLuong;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }

    public String getMaHoaDon() {
        return MaHoaDon;
    }

    public void setMaHoaDon(String MaHoaDon) {
        this.MaHoaDon = MaHoaDon;
    }

    public String getMaCTHĐ() {
        return MaCTHĐ;
    }

    public void setMaCTHĐ(String MaCTHĐ) {
        this.MaCTHĐ = MaCTHĐ;
    }

    public String getTenDV() {
        return TenDV;
    }

    public void setTenDV(String TenDV) {
        this.TenDV = TenDV;
    }

    public double getGia() {
        return Gia;
    }

    public void setGia(double Gia) {
        this.Gia = Gia;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

}
