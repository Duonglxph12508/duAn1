package DAO;

import Helper.JDBCHelper;
import Model.CTHD;
import Model.HoaDon;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tran_Hang
 */
public class CTHDDao extends MoSysDAO<CTHD, Integer> {

    @Override
    public void insert(CTHD model) {
        String query = "INSERT dbo.CTHD ( MaHoaDon, TenDV, Gia, SoLuong,ThanhTien, SoDienMoi, SoDienCu ) VALUES  ( ?,?,?,?,?,?,? )";
        JDBCHelper.excuteUpdate(query,
                model.getMaHoaDon(), model.getTenDV(), model.getGia(),
                model.getSoLuong(), model.getThanhTien(), model.getSoDienMoi(), model.getSoDienCu());

    }

    @Override
    public void update(CTHD model) {
        String query = "UPDATE dbo.CTHD SET MaHoaDon = ?,  TenDV = ?, Gia = ?, SoLuong = ? WHERE MaCTHD = ?";
        JDBCHelper.excuteUpdate(query,
                model.getMaHoaDon(), model.getTenDV(),
                model.getGia(), model.getSoLuong(), model.getMaCTHĐ());
    }

    @Override
    public void delete(Integer id) {
        String query = "DELETE dbo.CTHĐ WHERE MaCTHD =?";
        JDBCHelper.excuteUpdate(query, id);
    }

    @Override
    public CTHD selectByID(Integer id) {
        String query = "select * from CTHD where MaCTHD = ?";
        List<CTHD> list = selectBySQL(query, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<CTHD> selectAll() {
        String query = "select * from CTHD where MaHoaDon = ?";
        return selectBySQL(query);
    }

    @Override
    protected List<CTHD> selectBySQL(String sql, Object... args) {
        List<CTHD> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.excuteQuery(sql, args);
                while (rs.next()) {
                    CTHD cthd = readFromRS(rs);
                    list.add(cthd);
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
    public CTHD readFromRS(ResultSet rs) throws SQLException {
        CTHD model = new CTHD();
        //    model.setMaCTHĐ(rs.getString("MaCTHĐ"));
        model.setGia(rs.getDouble("Gia"));
        model.setMaHoaDon(rs.getString("MaHoaDon"));
        model.setTenDV(rs.getString("TenDV"));
        model.setSoLuong(rs.getInt("SoLuong"));
        model.setSoDienMoi(rs.getInt("SoDienMoi"));
        model.setSoDienCu(rs.getInt("SoDienCu"));
        model.setThanhTien(rs.getDouble("ThanhTien"));

        return model;
    }

    public CTHD selectByTenDV(String tenDV) {
        CTHD dv = null;

        String query = "SELECT TenDV, Gia, SoLuong, SoDienMoi, SoDienCu\n"
                + "FROM dbo.CTHD\n"
                + "WHERE TenDV = ?";
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.excuteQuery(query, tenDV);
                while (rs.next()) {
                    CTHD ct = new CTHD();
                    ct.setTenDV(rs.getString("TenDV"));
                    ct.setGia(rs.getDouble("Gia"));
                    ct.setSoLuong(rs.getInt("SoLuong"));
                    ct.setSoDienCu(rs.getInt("SoDienCu"));
                    ct.setSoDienMoi(rs.getInt("SoDienMoi"));

                    dv = ct;
                }
            } finally {
                rs.getStatement().getConnection().close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dv;
    }

    public List<CTHD> selectAllDV(String maHD) {
        String query = "select * from CTHD where MaHoaDon = ?";
        return selectBySQL(query, maHD);
    }

    public double getGiaPhong(String maPT) {
//        String query = "SELECT Gia\n"
//                + "FROM dbo.HoaDon JOIN dbo.HopDong ON HopDong.MaHopDong = HoaDon.MaHopDong\n"
//                + "JOIN dbo.PhongTro ON PhongTro.MaPT = HopDong.MaPT\n"
//                + "WHERE HopDong.MaPT = ?";
        String query = "SELECT gia\n"
                + "FROM dbo.PhongTro WHERE MaPT = ?";
        double gia = 0;

        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.excuteQuery(query, maPT);
                while (rs.next()) {
                    CTHD ct = new CTHD();
                    ct.setGia(rs.getDouble("Gia"));

                    gia = ct.getGia();
                }
            } finally {
                rs.getStatement().getConnection().close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return gia;
    }

    public double getTongTien(String maHD) {
        String query = "SELECT SUM(ThanhTien)  AS N'TongTien'\n"
                + "FROM dbo.CTHD WHERE MaHoaDon = ?\n";

        double tong = 0;

        try {
            ResultSet rs = null; 
            try {
                rs = JDBCHelper.excuteQuery(query, maHD);
                while (rs.next()) {
                    CTHD hd = new CTHD();
                    hd.setTongTien(rs.getDouble("TongTien"));

                    tong = hd.getTongTien();
                }
            } finally {
                rs.getStatement().getConnection().close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tong;
    }

    public List<CTHD> selectDV(String MaHoaDon) {
        String query = "SELECT TenDV, Gia, SoDienMoi, SoDienCu, SoLuong, ThanhTien\n"
                + "FROM dbo.HoaDon JOIN dbo.CTHD ON MaHoaDon = MaHD\n"
                + "WHERE MaHD = ?";

        List<CTHD> listDV = new ArrayList<>();

        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.excuteQuery(query, MaHoaDon);
                while (rs.next()) {
                    CTHD hd = new CTHD();
                    hd.setTenDV(rs.getString("TenDV"));
                    hd.setGia(rs.getDouble("Gia"));
                    hd.setSoLuong(rs.getInt("SoLuong"));
                    hd.setThanhTien(rs.getDouble("ThanhTien"));
                    hd.setSoDienCu(rs.getInt("SoDienCu"));
                    hd.setSoDienMoi(rs.getInt("SoDienMoi"));
                    listDV.add(hd);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listDV;
    }

    public HoaDon selectTT(String MaHoaDon) {
        String query = "SELECT TenKH, ThangNam,MaPT, TongTien\n"
                + "FROM dbo.HoaDon JOIN dbo.HopDong ON HopDong.MaHopDong = HoaDon.MaHopDong\n"
                + "JOIN dbo.KhachHang ON KhachHang.MaHopDong = HopDong.MaHopDong\n"
                + "WHERE MaHD = ? AND VaiTro = 1";

        HoaDon hd = new HoaDon();

        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.excuteQuery(query, MaHoaDon);
                while (rs.next()) {

                    hd.setTenKH(rs.getString("TenKH"));
                    hd.setTongTien(rs.getDouble("TongTien"));
                    hd.setNamThang(rs.getDate("ThangNam"));
                    hd.setTenPT(rs.getString("MaPT"));

                }
            } finally {
                rs.getStatement().getConnection().close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hd;
    }
}
