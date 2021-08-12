
package Model;

/**
 *
 * @author Tran_Hang
 */
public class NguoiDung {
    private String Username,pass,Fullname;
    private boolean  vaiTro;

    public NguoiDung() {
    }

    public NguoiDung(String Username, String Fullname, String pass, boolean vaiTro) {
        this.Username = Username;
        this.Fullname = Fullname;
        this.pass = pass;
        this.vaiTro = vaiTro;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String Fullname) {
        this.Fullname = Fullname;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public boolean isVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(boolean vaiTro) {
        this.vaiTro = vaiTro;
    }
    
}
