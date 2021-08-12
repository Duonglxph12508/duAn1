
package Helper;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author Tran_Hang
 */
public class DialogHelper {
    // hiển thị thông báo cho người dùng
    public static void alert(Component parent, String message){
        JOptionPane.showMessageDialog(parent, message, "Hệ thống quản lý đào tạo", JOptionPane.INFORMATION_MESSAGE);
    }
    
    // hiển thị thông báo yêu cầu người dùng xác nhận
    public static boolean confirm(Component parent, String message){
        int result = JOptionPane.showConfirmDialog(parent, message, "Hệ thống quản lý đào tạo", JOptionPane.YES_NO_OPTION);
        return result == JOptionPane.YES_OPTION;
    }
    
    // hiển thị thông báo yêu cầu nhập dữ liệu
    public static String prompt(Component parent, String message){
       return JOptionPane.showInputDialog(parent, message, "Hệ thống quản lý đào tạo", JOptionPane.INFORMATION_MESSAGE);
    }
}
