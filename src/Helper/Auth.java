/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import Model.NguoiDung;

/**
 *
 * @author Tran_Hang
 */
public class Auth {
     // đối tượng này chứa thông tin người sử dụng sau khi đăng nhập
    public static NguoiDung user = null;
    
    // xóa thông tin người sử dụng khi có yêu cầu đăng xuất
    public  static void logoff(){
        Auth.user = null;
    }
    
    // kiểm tra xem đăng nhập hay chưa
    public static boolean authenticated(){
        return Auth.user != null;
    }
    
}
