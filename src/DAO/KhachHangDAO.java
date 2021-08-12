package DAO;

import Helper.JDBCHelper;
import Model.KhachHang;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tran_Hang
 */
public class KhachHangDAO extends MoSysDAO<KhachHang, String> {

    @Override
    public void insert(KhachHang model) {
        String query = "INSERT dbo.KhachHang( MaKH ,MaHopDong, TenKH , GioiTinh , QueQuan , SDT , CMND , NgaySinh , VaiTro ) "
                + "VALUES  (?,?,?,?,?,?,?,?,?)";
        JDBCHelper.excuteUpdate(query,
                model.getMaKH(), model.getMaHD(), model.getTenKH(), model.isGT(), model.getQueQuan(),
                model.getSđt(), model.getCnmd(), model.getNgaySinh(), model.isVaiTro());
    }

    @Override
    public void update(KhachHang model) {
        String query = "UPDATE dbo.KhachHang SET TenKH =? , GioiTinh=? , QueQuan=? , SDT=? , CMND=? , NgaySinh=? , VaiTro=? "
                + "WHERE MaKH = ?";
        JDBCHelper.excuteUpdate(query,
                model.getTenKH(), model.isGT(), model.getQueQuan(), model.getSđt(),
                model.getCnmd(), model.getNgaySinh(), model.isVaiTro(), model.getMaKH());
    }

    @Override
    public void delete(String id) {
        String query = "DELETE dbo.KhachHang WHERE MaKH =?";
        JDBCHelper.excuteUpdate(query, id);
    }

    @Override
    public KhachHang selectByID(String id) {
         String query = "select * from KhachHang where MaKH = ?";
        List<KhachHang> list = selectBySQL(query, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<KhachHang> selectAll() {
        String query = "select * from KhachHang";
        return selectBySQL(query);
    }

    @Override
    protected List<KhachHang> selectBySQL(String sql, Object... args) {
        List<KhachHang> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.excuteQuery(sql, args);
                while (rs.next()) {
                    KhachHang KH = readFromRS(rs);
                    list.add(KH);
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
    public KhachHang readFromRS(ResultSet rs) throws SQLException {
        KhachHang model = new KhachHang();
        model.setMaKH(rs.getString("MaKH"));
        model.setMaHD(rs.getString("MaHopDong"));
        model.setQueQuan(rs.getString("QueQuan"));
        model.setTenKH(rs.getString("TenKH"));
        model.setCnmd(rs.getString("CMND"));
        model.setSđt(rs.getString("SDT"));
        model.setNgaySinh(rs.getDate("NgaySinh"));
        model.setGT(rs.getBoolean("GioiTinh"));
        model.setVaiTro(rs.getBoolean("VaiTro"));

        return model;
    }

    public List<KhachHang> selectByTenKH(String id) {
        String query = "select * from KhachHang where TenKH LIKE ?";
        List<KhachHang> list = selectBySQL(query, "%" + id + "%");
        return list;
    }

    public List<KhachHang> khachHangTheoHD(String MaHD) {
        String query = "select * from KhachHang where MaHopDong = ?";

        List<KhachHang> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.excuteQuery(query, MaHD);
                while (rs.next()) {
                    KhachHang kh = new KhachHang();
                    kh.setMaHD(rs.getString("MaHopDong"));
                    kh.setMaKH(rs.getString("MaKH"));
                    kh.setTenKH(rs.getString("TenKH"));
                    kh.setGT(rs.getBoolean("GioiTinh"));
                    kh.setSđt(rs.getString("SDT"));
                    kh.setQueQuan(rs.getString("QueQuan"));
                    kh.setCnmd(rs.getString("CMND"));
                    kh.setNgaySinh(rs.getDate("NgaySinh"));
                    kh.setVaiTro(rs.getBoolean("VaiTro"));

                    list.add(kh);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
