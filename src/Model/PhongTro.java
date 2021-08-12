
package Model;

/**
 *
 * @author Tran_Hang
 */
public class PhongTro {
    private String maPT, moTa, tinhTrang;
    private double gia;

    public PhongTro() {
    }

    public PhongTro(String maPT, String moTa, String tinhTrang, double gia) {
        this.maPT = maPT;
        this.moTa = moTa;
        this.tinhTrang = tinhTrang;
        this.gia = gia;
    }

    public String getMaPT() {
        return maPT;
    }

    public void setMaPT(String maPT) {
        this.maPT = maPT;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }
    
    
}
