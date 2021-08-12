/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import DAO.PhongTroDAO;
import DAO.ThietBiDAO;
import Helper.DialogHelper;
import Helper.JDBCHelper;
import Model.ThietBi;
import Model.TrangBi;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Tran_Hang
 */
public class XemThietBi extends javax.swing.JInternalFrame {

    PhongTroDAO dao = new PhongTroDAO();
    ThietBiDAO daoTB = new ThietBiDAO();
    DefaultTableModel model;
    String maPT;
    int index = -1;
    DefaultComboBoxModel model2 = new DefaultComboBoxModel();

    public XemThietBi(String maPT) {
        initComponents();

        this.fillComboBox();
        this.maPT = maPT;
        this.fillTable();

    }

    ArrayList<String> MaTB = new ArrayList<>();
    List<ThietBi> listTen = new ArrayList<>();
    
    public void fillComboBox() {
        try {
            String sql = "select MaTB, TenTB from ThietBi";
            ResultSet rs = JDBCHelper.excuteQuery(sql);
            while (rs.next()) {
                model2.addElement(rs.getString(2));
                MaTB.add(rs.getString(1));
            }
            cboTenTB.setModel(model2);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Lỗi dữ liệu");
        }
    }

    public void fillTable() {
        model = (DefaultTableModel) tblDanhSach.getModel();
        model.setRowCount(0);

        List<ThietBi> list = dao.ThietBiTheoPhong(this.maPT);

        for (ThietBi tb : list) {
            model.addRow(new Object[]{tb.getMaTB(), tb.getTenTB()});
        }
    }
    
    public TrangBi getModel() {
        TrangBi model = new TrangBi();
        model.setMaPT(this.maPT);
        model.setMaTB(this.MaTB.get(cboTenTB.getSelectedIndex())); 

        return model;

    }
    
     public void insert() {

        TrangBi model = this.getModel();

        try {
            daoTB.insertTB(model);
            DialogHelper.alert(this, "Thêm mới thành công");

            this.fillTable();

        } catch (Exception e) {
            DialogHelper.alert(this, "Thêm mới thất bại");
        }

    }

    public void delete() {

        if (DialogHelper.confirm(this, "Bạn thật sự muốn xóa thiết bị này")) {
            try {
                this.index = tblDanhSach.getSelectedRow();
                String tenTB = tblDanhSach.getValueAt(this.index, 1).toString();
                String maTB = tblDanhSach.getValueAt(this.index, 0).toString();
                daoTB.deleteTB( maPT, maTB);

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

        jScrollPane1 = new javax.swing.JScrollPane();
        tblDanhSach = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        cboTenTB = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);

        tblDanhSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Mã thiết bị", "Tên thiết bị"
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

        jLabel2.setText("Tên thiết bị");

        cboTenTB.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboTenTBItemStateChanged(evt);
            }
        });
        cboTenTB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTenTBActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Add.png"))); // NOI18N
        jButton1.setText("Thêm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/delete.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnXoa, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(cboTenTB, 0, 374, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(44, 44, 44)
                    .addComponent(cboTenTB, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(235, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboTenTBItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboTenTBItemStateChanged

    }//GEN-LAST:event_cboTenTBItemStateChanged

    private void cboTenTBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTenTBActionPerformed

    }//GEN-LAST:event_cboTenTBActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.insert();
    }//GEN-LAST:event_jButton1ActionPerformed

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
    private javax.swing.JComboBox cboTenTB;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblDanhSach;
    // End of variables declaration//GEN-END:variables
}
