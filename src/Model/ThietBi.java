
package Model;

/**
 *
 * @author Tran_Hang
 */
public class ThietBi {
    private String maTB, tenTB, moTa;

    public ThietBi() {
    }

    public ThietBi(String maTB, String tenTB, String moTa) {
        this.maTB = maTB;
        this.tenTB = tenTB;
        this.moTa = moTa;
    }

    public String getMaTB() {
        return maTB;
    }

    public void setMaTB(String maTB) {
        this.maTB = maTB;
    }

    public String getTenTB() {
        return tenTB;
    }

    public void setTenTB(String tenTB) {
        this.tenTB = tenTB;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
    
    
}
