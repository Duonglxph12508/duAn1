package View;

import Bean.ThueDichVu;
import Bean.XemCTHopDong;
import Bean.XemKhachHang;
import Controller.AddHopDong;
import Controller.UpdateHopDong;
import DAO.HopDongDAO;
import DAO.PhongTroDAO;
import Helper.DateHelper;
import Helper.DialogHelper;
import Model.HopDong;
import Model.PhongTro;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Tran_Hang
 */
public class HopDongInternalFrame extends javax.swing.JInternalFrame {

    HopDongDAO dao = new HopDongDAO();
    PhongTroDAO ptDao = new PhongTroDAO();

    List<HopDong> list = new ArrayList<>();
    int index = -1;
    DefaultTableModel model;

    public HopDongInternalFrame() {
        initComponents();

        this.fillTable();
    }

    public void fillTable() {
        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
        model = (DefaultTableModel) tblDS.getModel();
        model.setRowCount(0);
        try {
            list = dao.selectAll();
            for (HopDong hd : list) {
                model.addRow(new Object[]{hd.getMaHopDong(), hd.getMaPT(),
                    date.format(hd.getNgayThue()), date.format(hd.getNgayTra()), dao.getSLNguoi(hd.getMaHopDong())});
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }

    public PhongTro getModelPT() {
        this.index = tblDS.getSelectedRow();
        PhongTro model = new PhongTro();
        model.setMaPT(tblDS.getValueAt(this.index, 1).toString());
        model.setTinhTrang("Còn trống");

        return model;
    }

    public void delete() {
        if (DialogHelper.confirm(this, "Bạn thật sự muốn xóa hợp đồng này")) {
            try {
                this.index = tblDS.getSelectedRow();
                String maHopDong = tblDS.getValueAt(this.index, 0).toString();
                dao.delete(maHopDong);
                ptDao.updateTT(this.getModelPT());
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
        HopDong hd = dao.selectByPT(maPT);
        if (hd != null) {
            model.addRow(new Object[]{hd.getMaHopDong(), hd.getMaPT(), hd.getTenKH(),
                DateHelper.toString(hd.getNgayThue(), "dd-MM-yyyy"),
                DateHelper.toString(hd.getNgayTra(), "dd-MM-yyyy"), hd.getSoNguoi()});
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
        btnXemKH = new javax.swing.JButton();
        btnThueDV = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDS = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);

        lblDS.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblDS.setForeground(new java.awt.Color(255, 255, 255));
        lblDS.setText("DANH SÁCH HỢP ĐỒNG");
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
        txtTimKiem.setCaretColor(new java.awt.Color(255, 255, 255));
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

        btnXemKH.setBackground(new java.awt.Color(255, 102, 102));
        btnXemKH.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnXemKH.setForeground(new java.awt.Color(255, 255, 255));
        btnXemKH.setText("Người thuê");
        btnXemKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemKHActionPerformed(evt);
            }
        });

        btnThueDV.setBackground(new java.awt.Color(255, 102, 102));
        btnThueDV.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnThueDV.setForeground(new java.awt.Color(255, 255, 255));
        btnThueDV.setText("Thuê dịch vụ");
        btnThueDV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThueDVActionPerformed(evt);
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
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "MÃ HỢP ĐỒNG", "MÃ PHÒNG TRỌ", "NGÀY THUÊ", "NGÀY HẾT HẠN", "SỐ NGƯỜI"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDSMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDS);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addComponent(btnThem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addComponent(lblDS, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSua, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXemKH, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnThueDV)
                            .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lblDS, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnThem))
                    .addComponent(btnTimKiem, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThueDV, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXemKH, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jDesktopPane1.setLayer(lblDS, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(btnThem, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(txtTimKiem, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(btnTimKiem, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(btnXemKH, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(btnThueDV, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(btnSua, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(btnXoa, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jDesktopPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        AddHopDong hd = new AddHopDong(jDesktopPane1);
        jDesktopPane1.add(hd);
        hd.setVisible(true);
    }//GEN-LAST:event_btnThemActionPerformed

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed

    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void btnXemKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemKHActionPerformed
        this.index = tblDS.getSelectedRow();
        if (this.index != -1) {
            XemKhachHang kh = new XemKhachHang(tblDS.getValueAt(this.index, 0).toString(), jDesktopPane1);
            jDesktopPane1.add(kh);
            kh.setVisible(true);
        } else {
            DialogHelper.alert(this, "Bạn chưa chọn hợp đồng nào");
        }
    }//GEN-LAST:event_btnXemKHActionPerformed

    private void btnThueDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThueDVActionPerformed
        this.index = tblDS.getSelectedRow();
        if (this.index != -1) {
            ThueDichVu dv = new ThueDichVu(jDesktopPane1, tblDS.getValueAt(this.index, 0).toString());
            jDesktopPane1.add(dv);
            dv.setVisible(true);
        } else {
            DialogHelper.alert(this, "Bạn chưa chọn hợp đồng nào");
        }


    }//GEN-LAST:event_btnThueDVActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        this.index = tblDS.getSelectedRow();
        if (this.index != -1) {
            UpdateHopDong hd = new UpdateHopDong(tblDS.getValueAt(this.index, 0).toString(), jDesktopPane1);
            jDesktopPane1.add(hd);
            hd.setVisible(true);
        } else {
            DialogHelper.alert(this, "Bạn chưa chọn hợp đồng nào");
        }

    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        this.index = tblDS.getSelectedRow();
        if (this.index != -1) {
            this.delete();
        } else {
            DialogHelper.alert(this, "Bạn chưa chọn hợp đồng nào");
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void txtTimKiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimKiemMouseClicked
        txtTimKiem.setText("");
        txtTimKiem.setForeground(Color.BLACK);
    }//GEN-LAST:event_txtTimKiemMouseClicked

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        String maPT = txtTimKiem.getText();
        this.TimKiem(maPT);
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void lblDSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDSMouseClicked
        this.fillTable();
        txtTimKiem.setText("Tìm kiếm phòng trọ");
        txtTimKiem.setForeground(Color.BLACK);
    }//GEN-LAST:event_lblDSMouseClicked

    private void tblDSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDSMouseClicked
        if (evt.getClickCount() == 2) {
            this.index = tblDS.getSelectedRow();
            if (this.index >= 0) {
                XemCTHopDong cthd = new XemCTHopDong(tblDS.getValueAt(this.index, 1).toString());
                jDesktopPane1.add(cthd);
                cthd.setVisible(true);
            }
        }
    }//GEN-LAST:event_tblDSMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThueDV;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXemKH;
    private javax.swing.JButton btnXoa;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDS;
    private javax.swing.JTable tblDS;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
