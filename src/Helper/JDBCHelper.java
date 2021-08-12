package Helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tran_Hang
 */
public class JDBCHelper {

    public static Connection conn;
    private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static String url = "jdbc:sqlserver://localhost:1433;databaseName=QLNT_DA1";
    private static String DB_URL = "jdbc:sqlserver://localhost;"
            + "databaseName=QLNT_DA1;";
    private static String user = "sa";
    private static String pass = "123456";

    // Nạp driver
    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public JDBCHelper() {

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(DB_URL, user, pass);
            System.out.println("connect successfully!");

        } catch (ClassNotFoundException ex) {
            System.out.println("Lỗi thiếu thư viện kết nối");
        } catch (SQLException ex) {
            System.out.println("Lỗi kết nối CSDL!");
        }
    }

    // Xây dựng PrepareStatement
    //    sql là câu lệnh sql có thể chứa tham số. nó có thể là một lời gọi thủ tục lưu trữ
    //    args là danh sách các giá trị được cung cấp cho các tham số trong câu lệnh sql
    public static PreparedStatement preparedStatement(String sql, Object... args) throws SQLException {
        conn = DriverManager.getConnection(url, user, pass);
        PreparedStatement ps = null;
        if (sql.trim().endsWith("{")) { // nhận vào thủ tục lưu
            ps = conn.prepareCall(sql);
        } else {
            ps = conn.prepareStatement(sql);
        }

        for (int i = 0; i < args.length; i++) {
            ps.setObject(i + 1, args[i]);
        }

        return ps;
    }

    // Thực hiện câu lệnh SQL thao tác (Insert, Update, Delete)
    //    sql là câu lệnh sql có thể chứa tham số. nó có thể là một lời gọi thủ tục lưu trữ
    //    args là danh sách các giá trị được cung cấp cho các tham số trong câu lệnh sql
    public static void excuteUpdate(String sql, Object... args) {
        try {
            PreparedStatement pstm = preparedStatement(sql, args);
            try {
                pstm.execute();
            } finally {
                pstm.getConnection().close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Thực hiện câu lệnh SQL truy vấn (Select)
    //    sql là câu lệnh sql có thể chứa tham số. nó có thể là một lời gọi thủ tục lưu trữ
    //    args là danh sách các giá trị được cung cấp cho các tham số trong câu lệnh sql
    public static ResultSet excuteQuery(String sql, Object... args) {
        ResultSet rs = null;
        try {
            PreparedStatement pstm = preparedStatement(sql, args);

            rs = pstm.executeQuery();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rs;
    }
}
