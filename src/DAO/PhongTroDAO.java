package DAO;

import DAO.MoSysDAO;
import Helper.JDBCHelper;
import Model.HopDong;
import Model.PhongTro;
import Model.ThietBi;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tran_Hang
 */
public class PhongTroDAO extends MoSysDAO<PhongTro, String> {

    @Override
    public void insert(PhongTro model) {
        String query = "INSERT dbo.PhongTro( MaPT, Gia, TinhTrang, MoTa ) VALUES  (?,?,?,?)";
        JDBCHelper.excuteUpdate(query,
                model.getMaPT(), model.getGia(),
                model.getTinhTrang(), model.getMoTa());

    }

    @Override
    public void update(PhongTro model) {
        String query = "UPDATE dbo.PhongTro SET  Gia = ?, TinhTrang = ?, MoTa = ? WHERE MaPT = ?";
        JDBCHelper.excuteUpdate(query,
                model.getGia(), model.getTinhTrang(),
                model.getMoTa(), model.getMaPT());
    }

    @Override
    public void delete(String id) {
        String query = "DELETE dbo.PhongTro WHERE MaPT =?";
        JDBCHelper.excuteUpdate(query, id);
    }

    @Override
    public PhongTro selectByID(String id) {
        String query = "select * from PhongTro where MaPT = ?";
        List<PhongTro> list = selectBySQL(query, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<PhongTro> selectAll() {
        String query = "select * from PhongTro";
        return selectBySQL(query);
    }

    @Override
    protected List<PhongTro> selectBySQL(String sql, Object... args) {
        List<PhongTro> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.excuteQuery(sql, args);
                while (rs.next()) {
                    PhongTro pt = readFromRS(rs);
                    list.add(pt);
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
    public PhongTro readFromRS(ResultSet rs) throws SQLException {
        PhongTro model = new PhongTro();
        model.setMaPT(rs.getString("MaPT"));
        model.setGia(rs.getDouble("Gia"));
        model.setTinhTrang(rs.getString("TinhTrang"));
        model.setMoTa(rs.getString("MoTa"));

        return model;
    }

    public List<ThietBi> ThietBiTheoPhong(String maPT) {
        String query = "SELECT ThietBi.MaTB, TenTB\n"
                + "FROM dbo.ThietBi JOIN dbo.TrangBi ON TrangBi.MaTB = ThietBi.MaTB \n"
                + "JOIN dbo.PhongTro ON PhongTro.MaPT = TrangBi.MaPT\n"
                + "WHERE PhongTro.MaPT = ?";

        List<ThietBi> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.excuteQuery(query, maPT);
                while (rs.next()) {
                    ThietBi tb = new ThietBi();
                    tb.setMaTB(rs.getString("MaTB"));
                    tb.setTenTB(rs.getString("TenTB"));
                    list.add(tb);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<HopDong> HopDongTheoPhong(String maPT) {
        String query = "SELECT MaHopDong, HopDong.MaPT, NgayThue, NgayTra, SoNguoi\n"
                + "FROM dbo.PhongTro JOIN dbo.HopDong ON HopDong.MaPT = PhongTro.MaPT\n"
                + "WHERE PhongTro.MaPT = ?";

        List<HopDong> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.excuteQuery(query, maPT);
                while (rs.next()) {
                    HopDong hd = new HopDong();
                    hd.setMaHopDong(rs.getString("MaHopDong"));
                    hd.setTenKH(rs.getString("MaPT"));
                    hd.setNgayThue(rs.getDate("NgayThue"));
                    hd.setNgayTra(rs.getDate("NgayTra"));
                    hd.setSoNguoi(rs.getInt("SoNguoi"));
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

    public List<String> selectMaPT() {
        String sql = "select distinct MaPT from PhongTro where TinhTrang = N'Còn trống'";
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
    
    public void updateTT(PhongTro model) {
        String query = "UPDATE dbo.PhongTro SET TinhTrang = ? WHERE MaPT = ?";
        JDBCHelper.excuteUpdate(query,
              model.getTinhTrang(),model.getMaPT());
    }

}
