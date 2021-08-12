/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.MoSysDAO;
import Helper.JDBCHelper;
import Model.NguoiDung;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public  class NguoiDungDAO extends MoSysDAO<NguoiDung, String>{

    @Override
    public void insert(NguoiDung model) {
        String query = "Insert into NguoiDung(Username, Pass, FullName, VaiTro ) VALUES(?,?,?,?)";
         JDBCHelper.excuteUpdate(query, model.getUsername(),
                 model.getPass(),model.getFullname(),model.isVaiTro());
    }

    @Override
    public void update(NguoiDung model) {
        String query = "Update NguoiDung set Pass = ?, FullName = ? , VaiTro = ? where Username=?";
        JDBCHelper.excuteUpdate(query,
                model.getPass(),model.getFullname(),model.isVaiTro(),model.getUsername());
    }

    @Override
    public void delete(String Username) {
         String query = "Delete NguoiDung where Username  = ?";
        JDBCHelper.excuteUpdate(query, Username);
    }

    @Override
    public NguoiDung selectByID(String Username) {
         String query = "Select * from NguoiDung where Username= ?";
        List<NguoiDung> list = selectBySQL(query, Username);
         return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<NguoiDung> selectAll() {
        String query = "Select * from NguoiDung";
        return selectBySQL(query); 
    }

    @Override
    protected List<NguoiDung> selectBySQL(String sql, Object... args) {
         List<NguoiDung> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.excuteQuery(sql, args);
                while (rs.next()) {
                    NguoiDung nv = readFromRS(rs);
                    list.add(nv);
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
    public NguoiDung readFromRS(ResultSet rs) throws SQLException {
        NguoiDung model = new NguoiDung();
        model.setUsername(rs.getString("Username"));
        model.setFullname(rs.getString("Fullname"));
        model.setPass(rs.getString("Pass"));
        model.setVaiTro(rs.getBoolean("VaiTro"));
        return model;
    }
    
}
