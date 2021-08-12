package Model;

import java.util.Date;

/**
 *
 * @author Tran_Hang
 */
public class KhachHang {

    private String MaKH, TenKH, QueQuan, sđt, cnmd, maHD;
    private Date NgaySinh;
    private boolean GT, VaiTro;

    public KhachHang() {
    }

    public KhachHang(String MaKH, String TenKH, String QueQuan, String sđt, String cnmd, String maHD, Date NgaySinh, boolean GT, boolean VaiTro) {
        this.MaKH = MaKH;
        this.TenKH = TenKH;
        this.QueQuan = QueQuan;
        this.sđt = sđt;
        this.cnmd = cnmd;
        this.maHD = maHD;
        this.NgaySinh = NgaySinh;
        this.GT = GT;
        this.VaiTro = VaiTro;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String MaKH) {
        this.MaKH = MaKH;
    }

    public String getTenKH() {
        return TenKH;
    }

    public void setTenKH(String TenKH) {
        this.TenKH = TenKH;
    }

    public String getQueQuan() {
        return QueQuan;
    }

    public void setQueQuan(String QueQuan) {
        this.QueQuan = QueQuan;
    }

    public String getSđt() {
        return sđt;
    }

    public void setSđt(String sđt) {
        this.sđt = sđt;
    }

    public String getCnmd() {
        return cnmd;
    }

    public void setCnmd(String cnmd) {
        this.cnmd = cnmd;
    }

    public Date getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(Date NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public boolean isGT() {
        return GT;
    }

    public void setGT(boolean GT) {
        this.GT = GT;
    }

    public boolean isVaiTro() {
        return VaiTro;
    }

    public void setVaiTro(boolean VaiTro) {
        this.VaiTro = VaiTro;
    }
    
    
}
