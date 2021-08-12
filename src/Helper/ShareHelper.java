
package Helper;


import java.awt.Image;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;

/**
 *
 * @author Tran_Hang
 */
public class ShareHelper {
    
    // ảnh biểu tượng của ứng dụng, xuất hiện trên mọi cửa sổ
   public static final Image app_icon;
    
    static {
        // tải ảnh biểu tượng ứng dụng
        String file = "/Icon/house.png";
        app_icon = new ImageIcon(ShareHelper.class.getResource(file)).getImage();
    }
    
   

}
