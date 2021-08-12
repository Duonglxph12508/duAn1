
package Bean;

import DAO.CTPT_DichVuDAO;
import DAO.DichVuDao;
import Helper.DialogHelper;
import Helper.JDBCHelper;
import Model.CTPT_DichVu;
import Model.DichVu;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDesktopPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Tran_Hang
 */
public class ThueDichVu extends javax.swing.JInternalFrame {

    DichVuDao dv = new DichVuDao();
    CTPT_DichVuDAO ctpt = new CTPT_DichVuDAO();
    JDesktopPane dp;
    String maHopDong;
    DefaultTableModel model;
    DefaultComboBoxModel model2 = new DefaultComboBoxModel();
    List<DichVu> list = new ArrayList<>();
    int index = -1;

    public ThueDichVu(JDesktopPane dp, String maHD) {
        initComponents();

        this.dp = dp;
        this.maHopDong = maHD;
        this.fillComboBox();
        this.fillTable();
        txtGia.setText(giaDV.get(0));
    }

    ArrayList<String> giaDV = new ArrayList<>();
    ArrayList<String> MaDV = new ArrayList<>();

    public void fillComboBox() {
        try {
            String sql = "SELECT MaDV, TenDV, Gia from DichVu";
            ResultSet rs = JDBCHelper.excuteQuery(sql);
            while (rs.next()) {
                model2.addElement(rs.getString(2));
                giaDV.add(rs.getString(3));
                MaDV.add(rs.getString(1));
            }
            cboTenDV.setModel(model2);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Lỗi dữ liệu");
        }
    }

    public void fillTable() {
        model = (DefaultTableModel) tblDanhSach.getModel();
        model.setRowCount(0);
        try {
            list = ctpt.getDV(this.maHopDong);
            for (DichVu dv : list) {
                model.addRow(new Object[]{dv.getTenDV(), dv.getGia()});
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }

    public CTPT_DichVu getModel() {
        CTPT_DichVu model = new CTPT_DichVu();
        model.setMaDV(this.MaDV.get(cboTenDV.getSelectedIndex()));
        model.setMaHopDong(this.maHopDong);

        return model;

    }

    public void insert() {

        CTPT_DichVu model = this.getModel();

        try {
            ctpt.insert(model);
            DialogHelper.alert(this, "Thêm mới thành công");

            this.fillTable();

        } catch (Exception e) {
            DialogHelper.alert(this, "Thêm mới thất bại");
        }

    }

    public void delete() {

        if (DialogHelper.confirm(this, "Bạn thật sự muốn xóa dịch vụ này")) {
            try {
                this.index = tblDanhSach.getSelectedRow();
                String tenDV = tblDanhSach.getValueAt(this.index, 0).toString();
                ctpt.deleteDV(maHopDong, ctpt.selectByTenDV(tenDV));

                this.fillTable();
                DialogHelper.alert(this, "Xóa thành công");
            } catch (Exception e) {
                DialogHelper.alert(this, "Xóa thất bại");
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnXoa = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        cboTenDV = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDanhSach = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtGia = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);

        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/delete.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        jLabel2.setText("Tên dịch vụ");

        cboTenDV.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboTenDVItemStateChanged(evt);
            }
        });
        cboTenDV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTenDVActionPerformed(evt);
            }
        });

        tblDanhSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Tên dịch vụ", "Giá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblDanhSach);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Add.png"))); // NOI18N
        jButton1.setText("Thêm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Giá");

        txtGia.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtGia)
                        .addComponent(btnXoa)
                        .addComponent(jButton1)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
                        .addComponent(cboTenDV, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboTenDV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnXoa)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.insert();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cboTenDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTenDVActionPerformed

    }//GEN-LAST:event_cboTenDVActionPerformed

    private void cboTenDVItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboTenDVItemStateChanged
        txtGia.setText(giaDV.get(cboTenDV.getSelectedIndex()));
    }//GEN-LAST:event_cboTenDVItemStateChanged

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        this.index = tblDanhSach.getSelectedRow();
        if (this.index != -1) {
            this.delete();
        } else {
            DialogHelper.alert(this, "Bạn chưa chọn dịch vụ nào");
        }
    }//GEN-LAST:event_btnXoaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox cboTenDV;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblDanhSach;
    private javax.swing.JTextField txtGia;
    // End of variables declaration//GEN-END:variables
}
