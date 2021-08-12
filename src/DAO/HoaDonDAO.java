package DAO;

import Helper.JDBCHelper;
import Model.DichVu;
import Model.HoaDon;
import Model.HopDong;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tran_Hang
 */
public class HoaDonDAO extends MoSysDAO<HoaDon, String> {

    @Override
    public void insert(HoaDon model) {
        String query = "INSERT dbo.HoaDon ( MaHD ,MaHopDong, ThangNam ,TongTien , TinhTrang)VALUES  (?,?,?,?,?)";
        JDBCHelper.excuteUpdate(query,
                model.getMaHD(), model.getMaHopDong(), model.getNamThang(),
                model.getTongTien(), model.isTinhTrang());

    }

    @Override
    public void update(HoaDon model) {
        String query = "UPDATE dbo.HoaDon SET TinhTrang = ? WHERE MaHD = ?";
        JDBCHelper.excuteUpdate(query,
                model.isTinhTrang(), model.getMaHD());
    }

    @Override
    public void delete(String id) {
         String query =  "DELETE dbo.HoaDon WHERE MaHD = ? ";
        JDBCHelper.excuteUpdate(query, id);
    }
    
    public void deleteCTHD(String id) {
         String query = "DELETE dbo.CTHD WHERE MaHoaDon =?";
        JDBCHelper.excuteUpdate(query, id);
    }

    @Override
    public HoaDon selectByID(String maPT) {
        return null;

    }

    @Override
    public List<HoaDon> selectAll() {
        String query = "SELECT MaHD, MaPT,TenKH, ThangNam, TongTien, TinhTrang\n"
                + "FROM dbo.HoaDon JOIN dbo.HopDong ON HopDong.MaHopDong = HoaDon.MaHopDong\n"
                + "JOIN dbo.KhachHang ON HopDong.MaHopDong = dbo.KhachHang.MaHopDong\n"
                + "WHERE VaiTro = 1";

        List<HoaDon> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.excuteQuery(query);
                while (rs.next()) {
                    HoaDon hd = new HoaDon();
                    hd.setMaHD(rs.getString("MaHD"));
                    hd.setTenPT(rs.getString("MaPT"));
                    hd.setTenKH(rs.getString("TenKH"));
                    hd.setNamThang(rs.getDate("ThangNam"));
                    hd.setTongTien(rs.getDouble("TongTien"));
                    hd.setTinhTrang(rs.getBoolean("TinhTrang"));

                    list.add(hd);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    protected List<HoaDon> selectBySQL(String sql, Object... args) {
        List<HoaDon> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.excuteQuery(sql, args);
                while (rs.next()) {
                    HoaDon hd = readFromRS(rs);
                    list.add(hd);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public HoaDon readFromRS(ResultSet rs) throws SQLException {
        HoaDon model = new HoaDon();
        model.setMaHD(rs.getString("MaHD"));
        model.setNamThang(rs.getDate("ThangNam"));
        model.setTinhTrang(rs.getBoolean("TinhTrang"));
        model.setTongTien(rs.getDouble("TongTien"));

        return model;
    }

    public List<HoaDon> selectTimkiem(String maPT) {
        String query = "SELECT MaHD, MaPT,TenKH, ThangNam, TongTien, TinhTrang\n"
                + "FROM dbo.HoaDon JOIN dbo.HopDong ON HopDong.MaHopDong = HoaDon.MaHopDong\n"
                + "JOIN dbo.KhachHang ON HopDong.MaHopDong = dbo.KhachHang.MaHopDong\n"
                + "WHERE VaiTro = 1 and MaPT = ? ";

        List<HoaDon> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.excuteQuery(query, maPT);
                while (rs.next()) {
                    HoaDon hd = new HoaDon();
                    hd.setMaHD(rs.getString("MaHD"));
                    hd.setTenPT(rs.getString("MaPT"));
                    hd.setTenKH(rs.getString("TenKH"));
                    hd.setNamThang(rs.getDate("ThangNam"));
                    hd.setTongTien(rs.getDouble("TongTien"));
                    hd.setTinhTrang(rs.getBoolean("TinhTrang"));

                    list.add(hd);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<DichVu> getTenDV(String maHopDong) {
        String query = "SELECT TenDV\n"
                + "FROM dbo.HopDong JOIN dbo.CTPT_DichVu ON CTPT_DichVu.MaHopDong = HopDong.MaHopDong\n"
                + "JOIN dbo.DichVu ON DichVu.MaDV = CTPT_DichVu.MaDV\n"
                + "WHERE CTPT_DichVu.MaHopDong = ?";

        List<DichVu> ListtenDV = new ArrayList<>();

        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.excuteQuery(query, maHopDong);
                while (rs.next()) {
                    DichVu dv = new DichVu();
                    dv.setTenDV(rs.getString("TenDV"));

                    ListtenDV.add(dv);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ListtenDV;

    }

    public double getGia(String TenDV) {
        String query = "SELECT Gia FROM dbo.DichVu WHERE TenDV = ?";

        double gia = 0;

        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.excuteQuery(query, TenDV);
                while (rs.next()) {
                    DichVu dv = new DichVu();
                    dv.setGia(rs.getDouble("Gia"));

                    gia = dv.getGia();
                }
            } finally {
                rs.getStatement().getConnection().close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return gia;
    }

    public List<HopDong> getHopDong() {
        String query = "SELECT MaHopDong, MaPT FROM dbo.HopDong";

        List<HopDong> List = new ArrayList<>();

        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.excuteQuery(query);
                while (rs.next()) {
                    HopDong hd = new HopDong();
                    hd.setMaHopDong(rs.getString("MaHopDong"));
                    hd.setMaPT(rs.getString("MaPT"));

                    List.add(hd);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return List;
    }

    public void updateTT(HoaDon model) {
        String query = "UPDATE dbo.HoaDon SET TongTien = ? WHERE MaHD = ?";
        JDBCHelper.excuteUpdate(query,
                model.getTongTien(), model.getMaHD());
    }
}
