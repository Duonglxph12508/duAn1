
package Helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Tran_Hang
 */
public class DateHelper {
    static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
    
    // chuyển đổi từ String sang Date
    public static Date toDate(String date, String...pattern){
       Date ngay = null;
        try {
            if(date.length()>0){
                sdf.applyPattern(pattern[0]); 
            }
            if(date == null){
                ngay = DateHelper.now();
            }
            ngay = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
       return ngay;
    }
    
    // chuyển đổi từ Date sang String
    public static String toString(Date date, String...pattern){
        if(pattern.length>0){
            sdf.applyPattern(pattern[0]);
        }
        if(date == null){
            date = DateHelper.now();
        }
        return sdf.format(date);
    }
    
    // lấy thời gian hiện tại
    public static Date now(){
        return new Date();
    }
    
    
}
