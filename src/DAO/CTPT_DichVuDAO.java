/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.CTPT_DichVu;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import Helper.JDBCHelper;
import Model.DichVu;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class CTPT_DichVuDAO extends MoSysDAO<CTPT_DichVu, String> {

    @Override
    public void insert(CTPT_DichVu model) {
        String query = "INSERT dbo.CTPT_DichVu ( MaHopDong, MaDV ) VALUES  ( ?,? )";
        JDBCHelper.excuteUpdate(query, model.getMaHopDong(), model.getMaDV());
    }

    @Override
    public void update(CTPT_DichVu model) {

    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CTPT_DichVu selectByID(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CTPT_DichVu> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CTPT_DichVu readFromRS(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected List<CTPT_DichVu> selectBySQL(String sql, Object... args) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<DichVu> getDV(String maHD) {
        String query = " SELECT TenDV, Gia\n"
                + "FROM dbo.HopDong JOIN dbo.CTPT_DichVu ON CTPT_DichVu.MaHopDong = HopDong.MaHopDong\n"
                + "JOIN dbo.DichVu ON DichVu.MaDV = CTPT_DichVu.MaDV\n"
                + "WHERE CTPT_DichVu.MaHopDong = ?";

        List<DichVu> ListtenDV = new ArrayList<>();

        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.excuteQuery(query, maHD);
                while (rs.next()) {
                    DichVu dv = new DichVu();
                    dv.setTenDV(rs.getString("TenDV"));
                    dv.setGia(rs.getDouble("Gia"));

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

    public void deleteDV(String maHD, String maDV) {
        String query = "DELETE dbo.CTPT_DichVu WHERE MaHopDong =? AND MaDV =?";
        JDBCHelper.excuteUpdate(query, maHD, maDV);
    }

    public String selectByTenDV(String tenDV) {
        String query = "SELECT MaDV FROM dbo.DichVu WHERE TenDV =?";

        String maDV = null;

        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.excuteQuery(query, tenDV);
                while (rs.next()) {
                    CTPT_DichVu dv = new CTPT_DichVu();
                    dv.setMaDV(rs.getString("MaDV"));
                    maDV = dv.getMaDV();
                }
            } finally {
                rs.getStatement().getConnection().close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maDV;
    }

}
