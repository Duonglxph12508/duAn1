package DAO;

import Helper.JDBCHelper;
import Model.HopDong;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tran_Hang
 */
public class HopDongDAO extends MoSysDAO<HopDong, String> {

    @Override
    public void insert(HopDong model) {
        String query = "INSERT dbo.HopDong ( MaHopDong  , MaPT ,NgayThue ,NgayTra ,SoNguoi , MoTa)\n"
                + "VALUES  (?,?,?,?,?,?)";
        JDBCHelper.excuteUpdate(query,
                model.getMaHopDong(), model.getMaPT(),
                model.getNgayThue(), model.getNgayTra(),
                model.getSoNguoi(), model.getMoTa());
    }

    @Override
    public void update(HopDong model) {
        String query = "UPDATE HopDong set NgayThue = ?,NgayTra = ?,SoNguoi = ? WHERE MaHopDong = ? ";
        JDBCHelper.excuteUpdate(query,
                model.getNgayThue(), model.getNgayTra(), model.getSoNguoi(), model.getMaHopDong());
    }

    @Override
    public void delete(String id) {
        String query = "DELETE HopDong WHERE MaHopDong =?";
        JDBCHelper.excuteUpdate(query, id);
    }

    @Override
    public HopDong selectByID(String id) {
        String query = "SELECT MaHopDong, MaPT, SoNguoi, NgayThue, NgayTra\n"
                + "FROM dbo.HopDong WHERE MaHopDong = ?";
        List<HopDong> list = selectBySQL(query, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<HopDong> selectAll() {
        String query = "SELECT MaHopDong, MaPT, NgayThue, NgayTra, SoNguoi\n"
                + "FROM dbo.HopDong";
        return selectBySQL(query);
    }

    @Override
    protected List<HopDong> selectBySQL(String sql, Object... args) {
        List<HopDong> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.excuteQuery(sql, args);
                while (rs.next()) {
                    HopDong hd = readFromRS(rs);
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
    public HopDong readFromRS(ResultSet rs) throws SQLException {
        HopDong model = new HopDong();
        model.setMaHopDong(rs.getString("MaHopDong"));
        model.setMaPT(rs.getString("MaPT"));
        //  model.setTenKH(rs.getString("TenKH"));
        model.setNgayThue(rs.getDate("NgayThue"));
        model.setNgayTra(rs.getDate("NgayTra"));
        model.setSoNguoi(rs.getInt("SoNguoi"));

        return model;
    }

    public void updateMoTa(HopDong model) {
        String query = "UPDATE HopDong set MoTa = ? WHERE MaHopDong = ? ";
        JDBCHelper.excuteUpdate(query,
                model.getMoTa(), model.getMaHopDong());
    }
    
    public void updateSL(HopDong model) {
        String query = "UPDATE HopDong set SoNguoi = ? WHERE MaHopDong = ? ";
        JDBCHelper.excuteUpdate(query,
                model.getSoNguoi(), model.getMaHopDong());
    }

    public List<HopDong> getMaPT() {
        String query = "SELECT MaPT\n"
                + "FROM dbo.HopDong ";
        List<HopDong> list = new ArrayList<>();

        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.excuteQuery(query);
                while (rs.next()) {
                    HopDong hd = new HopDong();

                    hd.setMaPT(rs.getString("MaPT"));

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

    public int soLuongNguoi(String maPT) {
        String query = "SELECT SoNguoi\n"
                + "FROM dbo.PhongTro JOIN dbo.HopDong ON HopDong.MaPT = PhongTro.MaPT\n"
                + "WHERE HopDong.MaPT = ?";

        int soLuong = 0;

        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.excuteQuery(query, maPT);
                while (rs.next()) {
                    HopDong hd = new HopDong();
                    hd.setSoNguoi(rs.getInt("SoNguoi"));

                    soLuong = hd.getSoNguoi();
                }
            } finally {
                rs.getStatement().getConnection().close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return soLuong;
    }
    
      public int getSLNguoi(String maHD) {
 
        String query = "SELECT COUNT(MaKH) AS N'SoLuong'\n"
                + "FROM dbo.KhachHang\n"
                + "WHERE MaHopDong = ?";

        int soLuong = 0;

        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.excuteQuery(query, maHD);
                while (rs.next()) {
                    HopDong hd = new HopDong();
                    hd.setSoNguoi(rs.getInt("SoLuong"));

                    soLuong = hd.getSoNguoi();
                }
            } finally {
                rs.getStatement().getConnection().close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return soLuong;
    }

    public HopDong selectByID1(String id) {
        String query = "SELECT HopDong.MaHopDong, HopDong.MaPT,TenKH, NgayThue, NgayTra, SoNguoi, HopDong.MoTa, Gia\n"
                + "FROM dbo.HopDong JOIN dbo.KhachHang ON KhachHang.MaHopDong = HopDong.MaHopDong\n"
                + "JOIN dbo.PhongTro ON HopDong.MaPT = PhongTro.MaPT\n"
                + "WHERE VaiTro = 1 and HopDong.MaPT = ?";

        HopDong hd = new HopDong();

        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.excuteQuery(query, id);
                while (rs.next()) {

                    hd.setMaHopDong(rs.getString("MaHopDong"));
                    hd.setMaPT(rs.getString("MaPT"));
                    hd.setTenKH(rs.getString("TenKH"));
                    hd.setNgayThue(rs.getDate("NgayThue"));
                    hd.setNgayTra(rs.getDate("NgayTra"));
                    hd.setSoNguoi(rs.getInt("SoNguoi"));
                    hd.setMoTa(rs.getString("MoTa"));
                    hd.setGia(rs.getDouble("Gia")); 

                }
            } finally {
                rs.getStatement().getConnection().close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hd;
    }

    public HopDong selectByPT(String id) {
        String query = "SELECT MaHopDong, MaPT, SoNguoi, NgayThue, NgayTra\n"
                + "FROM dbo.HopDong WHERE MaPT = ?";
        List<HopDong> list = selectBySQL(query, id);
        return list.size() > 0 ? list.get(0) : null;
    }

}
