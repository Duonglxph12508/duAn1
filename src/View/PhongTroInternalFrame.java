package View;

import Bean.XemHopDong;
import Bean.XemThietBi;
import Controller.AddPhongTro;
import Controller.UpdatePhongTro;
import DAO.PhongTroDAO;
import Helper.DateHelper;
import Helper.DialogHelper;
import Model.PhongTro;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Tran_Hang
 */
public class PhongTroInternalFrame extends javax.swing.JInternalFrame {

    PhongTroDAO dao = new PhongTroDAO();
    List<PhongTro> list = new ArrayList<>();
    int index = -1;
    DefaultTableModel model;

    public PhongTroInternalFrame() {
        initComponents();
        this.fillTable();
    }

    public void fillTable() {
        model = (DefaultTableModel) tblDS.getModel();
        model.setRowCount(0);
        try {
            list = dao.selectAll();
            for (PhongTro pt : list) {
                model.addRow(new Object[]{pt.getMaPT(), pt.getGia(), pt.getTinhTrang()});
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }

    public void delete() {
        if (DialogHelper.confirm(this, "Bạn thật sự muốn xóa phòng trọ này")) {
            try {
                this.index = tblDS.getSelectedRow();
                String maPT = tblDS.getValueAt(this.index, 0).toString();
                dao.delete(maPT);
                this.fillTable();
                DialogHelper.alert(this, "Xóa thành công");
            } catch (Exception e) {
                DialogHelper.alert(this, "Xóa thất bại");
            }
        }

    }

    public void TimKiem(String maPT) {

        model = (DefaultTableModel) tblDS.getModel();
        tblDS.removeAll();
        model.setRowCount(0);
        PhongTro pt = dao.selectByID(maPT);

        if (pt != null) {
            model.addRow(new Object[]{pt.getMaPT(), pt.getGia(), pt.getTinhTrang()});
        } else {
            DialogHelper.alert(this, "Không tìm thấy phòng trọ nào");
            this.fillTable();
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        lblDS = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        txtTimKiem = new javax.swing.JTextField();
        btnTimKiem = new javax.swing.JButton();
        btnXemTB = new javax.swing.JButton();
        btnXemHopDong = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDS = new javax.swing.JTable();

        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lblDS.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblDS.setForeground(new java.awt.Color(255, 255, 255));
        lblDS.setText("DANH SÁCH PHÒNG TRỌ");
        lblDS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDSMouseClicked(evt);
            }
        });

        btnThem.setBackground(new java.awt.Color(51, 153, 255));
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/check.png"))); // NOI18N
        btnThem.setText("Thêm mới");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        txtTimKiem.setBackground(new java.awt.Color(204, 204, 204));
        txtTimKiem.setForeground(new java.awt.Color(51, 51, 51));
        txtTimKiem.setText("Tìm kiếm phòng trọ");
        txtTimKiem.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        txtTimKiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTimKiemMouseClicked(evt);
            }
        });
        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });

        btnTimKiem.setBackground(new java.awt.Color(204, 204, 204));
        btnTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/magnifying-glass.png"))); // NOI18N
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        btnXemTB.setBackground(new java.awt.Color(255, 102, 102));
        btnXemTB.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnXemTB.setForeground(new java.awt.Color(255, 255, 255));
        btnXemTB.setText("Xem thiết bị");
        btnXemTB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemTBActionPerformed(evt);
            }
        });

        btnXemHopDong.setBackground(new java.awt.Color(255, 102, 102));
        btnXemHopDong.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnXemHopDong.setForeground(new java.awt.Color(255, 255, 255));
        btnXemHopDong.setText("Xem hợp đồng");
        btnXemHopDong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemHopDongActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(204, 204, 204));
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/update.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(204, 204, 204));
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/delete.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        tblDS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "MÃ PHÒNG", "GIÁ", "TÌNH TRẠNG"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblDS);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addComponent(lblDS, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addComponent(btnThem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSua, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXemTB, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnXemHopDong)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lblDS, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnThem)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnTimKiem))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXemHopDong, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXemTB, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jDesktopPane1.setLayer(lblDS, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(btnThem, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(txtTimKiem, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(btnTimKiem, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(btnXemTB, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(btnXemHopDong, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(btnSua, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(btnXoa, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblDSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDSMouseClicked
        this.fillTable();
        txtTimKiem.setText("Tìm kiếm phòng trọ");
        txtTimKiem.setForeground(Color.BLACK);
    }//GEN-LAST:event_lblDSMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        AddPhongTro pt = new AddPhongTro(jDesktopPane1);
        jDesktopPane1.add(pt);
        pt.setVisible(true);

    }//GEN-LAST:event_btnThemActionPerformed

    private void txtTimKiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimKiemMouseClicked
        txtTimKiem.setText("");
        txtTimKiem.setForeground(Color.BLACK);
    }//GEN-LAST:event_txtTimKiemMouseClicked

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed

    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        String maPT = txtTimKiem.getText();
        this.TimKiem(maPT);
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void btnXemTBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemTBActionPerformed
        this.index = tblDS.getSelectedRow();
        if (this.index != -1) {
            XemThietBi pt = new XemThietBi(tblDS.getValueAt(this.index, 0).toString());
            jDesktopPane1.add(pt);
            pt.setVisible(true);
        } else {
            DialogHelper.alert(this, "Bạn chưa chọn phòng trọ nào");
        }
    }//GEN-LAST:event_btnXemTBActionPerformed

    private void btnXemHopDongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemHopDongActionPerformed
        this.index = tblDS.getSelectedRow();
        if (this.index != -1) {
            XemHopDong pt = new XemHopDong(tblDS.getValueAt(this.index, 0).toString());
            jDesktopPane1.add(pt);
            pt.setVisible(true);
        } else {
            DialogHelper.alert(this, "Bạn chưa chọn phòng trọ nào");
        }
    }//GEN-LAST:event_btnXemHopDongActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        this.index = tblDS.getSelectedRow();
        if (this.index != -1) {
            UpdatePhongTro pt = new UpdatePhongTro(tblDS.getValueAt(this.index, 0).toString(), jDesktopPane1);
            jDesktopPane1.add(pt);
            pt.setVisible(true);
        } else {
            DialogHelper.alert(this, "Bạn chưa chọn phòng trọ nào");
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        this.index = tblDS.getSelectedRow();
        if (this.index != -1) {
            this.delete();
        } else {
            DialogHelper.alert(this, "Bạn chưa chọn phòng trọ nào");
        }
    }//GEN-LAST:event_btnXoaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXemHopDong;
    private javax.swing.JButton btnXemTB;
    private javax.swing.JButton btnXoa;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDS;
    private javax.swing.JTable tblDS;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
