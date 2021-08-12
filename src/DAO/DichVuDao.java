/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.MoSysDAO;
import Helper.JDBCHelper;
import Model.DichVu;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class DichVuDao extends MoSysDAO<DichVu, String> {

    @Override
    public void insert(DichVu model) {
        String query = "INSERT dbo.DichVu(MaDV, TenDV, Gia) VALUES (?,?,?)";
        JDBCHelper.excuteUpdate(query,
                model.getMaDV(), model.getTenDV(), model.getGia());
    }

    @Override
    public void update(DichVu model) {
        String query = "UPDATE dbo.DichVu SET  TenDV = ? , Gia = ? WHERE MaDV = ?";
        JDBCHelper.excuteUpdate(query, model.getTenDV(), model.getGia(), model.getMaDV());
    }

    @Override
    public void delete(String id) {
        String query = "DELETE dbo.DichVu WHERE MaDV = ?";
        JDBCHelper.excuteUpdate(query, id);
    }

    @Override
    public DichVu selectByID(String id) {
        String query = "select * from DichVu where MaDV = ?";
        List<DichVu> list = selectBySQL(query, id);
        return list.size() > 0 ? list.get(0) : null;

    }

    @Override
    public List<DichVu> selectAll() {
        String query = "select * from DichVu ";
        return selectBySQL(query);

    }

    @Override
    protected List<DichVu> selectBySQL(String sql, Object... args) {
        List<DichVu> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.excuteQuery(sql, args);
                while (rs.next()) {
                    DichVu dv = readFromRS(rs);
                    list.add(dv);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }

    @Override
    public DichVu readFromRS(ResultSet rs) throws SQLException {
        DichVu model = new DichVu();
        model.setMaDV(rs.getString("MaDV"));
        model.setTenDV(rs.getString("TenDV"));
        model.setGia(Double.parseDouble(rs.getString("Gia")));
        return model;

    }


}
