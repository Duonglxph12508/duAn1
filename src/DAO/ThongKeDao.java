/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.HopDong;
import Model.thongKe;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import Helper.JDBCHelper;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class ThongKeDao {

    public List<Object[]> getListOfArray(String sql, String[] cols, Object... args) {
        try {
            List<Object[]> list = new ArrayList<>();
            ResultSet rs = JDBCHelper.excuteQuery(sql, args);
            while (rs.next()) {
                Object[] vals = new Object[cols.length];
                for (int i = 0; i < cols.length; i++) {
                    vals[i] = rs.getObject(cols[i]);
                }
                list.add(vals);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public List<Integer> selectYearDT() {
        String sql = "select distinct year(ThangNam) from HoaDon";
        List<Integer> list = new ArrayList<>();
        ResultSet rs = JDBCHelper.excuteQuery(sql);
        try {
            while (rs.next()) {
                list.add(rs.getInt(1));
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<String> selectYearSL() {
        String sql = "select distinct NgayThue from HopDong";
        List<String> list = new ArrayList<>();
        ResultSet rs = JDBCHelper.excuteQuery(sql);
        try {
            while (rs.next()) {
                list.add(rs.getString(1));
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<Object[]> getDoanhThu(int nam) {
        String query = "{call sp_ThongKeDoanThu (?)}";
        String[] cols = {"TongTien", "doanhThuTB", "doanhthuMax", "doanhThuMin"};
        return this.getListOfArray(query, cols, nam);

    }

    public List<Object[]> getSoLuong(String ThangNam) {
        String query = "{call sp_thongKeSL (?)}";
        String[] cols = {"TongSL", "SLTB", "SLMax", "SLMin"};
        return this.getListOfArray(query, cols, ThangNam);

    }

    public String getSoPhong() {
        String query = "select * from tongPhong";
        String soLuong = null;
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.excuteQuery(query);
                while (rs.next()) {
                    soLuong = rs.getString("SoPhong");
                }
            } finally {
                rs.getStatement().getConnection().close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return soLuong;
    }

    public String getSoPhongTrong() {
        String query = "select * from phongTrong";
        String soLuong = null;
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.excuteQuery(query);
                while (rs.next()) {
                    soLuong = rs.getString("soPhongTrong");
                }
            } finally {
                rs.getStatement().getConnection().close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return soLuong;
    }

    public String getSoKhachHang() {
        String query = "select * from tongKhachHang";
        String soLuong = null;
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.excuteQuery(query);
                while (rs.next()) {
                    soLuong = rs.getString("tongKhachHang");
                }
            } finally {
                rs.getStatement().getConnection().close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return soLuong;
    }

    public List<thongKe> getDoanhThu() {
        String query = "{call sp_ThongKeDoanThu ()}";
        List list = new ArrayList();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.excuteQuery(query);
                while (rs.next()) {
                    thongKe tk = new thongKe();
                    tk.setDoanhThu(rs.getDouble("TongTien"));
                    tk.setDoanhThuTB(rs.getDouble("doanhThuTB"));

                    list.add(tk);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;

    }

    public List<String> getNam() {
        String query = "SELECT DISTINCT YEAR(NgayThue) FROM dbo.HopDong";
        List<String> list = new ArrayList();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.excuteQuery(query);
                while (rs.next()) {
                    String nam = rs.getString(1);

                    list.add(nam);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;

    }

    public List<String> getNamDT() {
        String query = "SELECT DISTINCT YEAR(ThangNam) FROM dbo.HoaDon";
        List<String> list = new ArrayList();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.excuteQuery(query);
                while (rs.next()) {
                    String nam = rs.getString(1);

                    list.add(nam);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;

    }

    public static double getThongKePT(String tinhTrang) {
        String query = "SELECT COUNT(MaPT)FROM dbo.PhongTro WHERE TinhTrang = ?";
        double sum = 0;

        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.excuteQuery(query, tinhTrang);
                while (rs.next()) {
                    sum = rs.getDouble(1);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sum;
    }

    public static double getThongKeKH(int nam, int thang) {
        String query = "SELECT COUNT(MaKH) \n"
                + "FROM dbo.KhachHang JOIN dbo.HopDong ON HopDong.MaHopDong = KhachHang.MaHopDong\n"
                + "WHERE YEAR(NgayThue) = ? AND MONTH(NgayThue) = ?";
        double sum = 0;

        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.excuteQuery(query, nam, thang);
                while (rs.next()) {
                    sum = rs.getDouble(1);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sum;
    }

    public static double getThongKeDT(int nam, int thang) {
        String query = "SELECT SUM(TongTien) \n"
                + "FROM dbo.HoaDon \n"
                + "WHERE YEAR(ThangNam) = ? AND MONTH(ThangNam) = ? AND TinhTrang = 1";
        double sum = 0;

        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.excuteQuery(query, nam, thang);
                while (rs.next()) {
                    sum = rs.getDouble(1);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sum;
    }
}
