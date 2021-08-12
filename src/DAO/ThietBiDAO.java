package DAO;

import DAO.MoSysDAO;
import Helper.JDBCHelper;
import Model.ThietBi;
import Model.TrangBi;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tran_Hang
 */
public class ThietBiDAO extends MoSysDAO<ThietBi, String> {

    @Override
    public void insert(ThietBi model) {
        String query = "INSERT dbo.ThietBi( MaTB, TenTB,MoTa ) VALUES  (?,?,?)";
        JDBCHelper.excuteUpdate(query,
                model.getMaTB(), model.getTenTB(), model.getMoTa());
    }

    @Override
    public void update(ThietBi model) {
        String query = "UPDATE dbo.ThietBi SET  TenTB = ?, MoTa = ? WHERE MaTB = ?";
        JDBCHelper.excuteUpdate(query,
                model.getTenTB(), model.getMoTa(), model.getMaTB());
    }

    @Override
    public void delete(String id) {
        String query = "DELETE dbo.ThietBi WHERE MaTB =?";
        JDBCHelper.excuteUpdate(query, id);
    }

    @Override
    public ThietBi selectByID(String id) {
        String query = "select * from ThietBi where MaTB = ?";
        List<ThietBi> list = selectBySQL(query, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<ThietBi> selectAll() {
        String query = "select * from ThietBi";
        return selectBySQL(query);
    }

    @Override
    protected List<ThietBi> selectBySQL(String sql, Object... args) {
        List<ThietBi> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.excuteQuery(sql, args);
                while (rs.next()) {
                    ThietBi tb = readFromRS(rs);
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

    @Override
    public ThietBi readFromRS(ResultSet rs) throws SQLException {
        ThietBi model = new ThietBi();
        model.setMaTB(rs.getString("MaTB"));
        model.setTenTB(rs.getString("TenTB"));
        model.setMoTa(rs.getString("MoTa"));

        return model;
    }

   public List<ThietBi> getTenTB(){
       String query = "select TenTB from ThietBi";
       
       List<ThietBi> listTB = new ArrayList<>();
       
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.excuteQuery(query);
                while (rs.next()) {
                    ThietBi tb = new ThietBi();
                    tb.setTenTB(rs.getString("TenTB"));

                    listTB.add(tb);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listTB;
   }
   
   public void insertTB(TrangBi model) {
        String query = "INSERT dbo.TrangBi( MaTB, MaPT ) VALUES  (?,?)";
        JDBCHelper.excuteUpdate(query,
                 model.getMaTB(), model.getMaPT());
    }
   
   public void deleteTB(String maPT, String maTB) {
        String query = "DELETE dbo.TrangBi WHERE MaTB =? and MaPT = ?";
        JDBCHelper.excuteUpdate(query, maTB, maPT);
    }
   

}
