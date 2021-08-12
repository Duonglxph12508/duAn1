/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.HopDongDAO;
import DAO.PhongTroDAO;
import Helper.DateHelper;
import Helper.DialogHelper;
import Model.HopDong;
import Model.PhongTro;
import View.HopDongInternalFrame;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

/**
 *
 * @author Tran_Hang
 */
public class AddHopDong extends javax.swing.JInternalFrame {

    HopDongDAO hdDao = new HopDongDAO();
    PhongTroDAO ptDao = new PhongTroDAO();
    JDesktopPane dp;

    public AddHopDong(JDesktopPane dp) {
        initComponents();

        this.fillToComboPT();
        this.dp = dp;
    }

    private void fillToComboPT() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboMaPT.getModel();
        model.removeAllElements();

        List<String> list = ptDao.selectMaPT();

        for (String maPT : list) {
            model.addElement(maPT);
        }
        cboMaPT.setSelectedIndex(0);
    }

    public HopDong getModel() {
        HopDong model = new HopDong();
        model.setMaHopDong(txtMaHopDong.getText());
        model.setMaPT((String) cboMaPT.getSelectedItem());
        model.setSoNguoi(0);
        model.setNgayThue(DateHelper.toDate(txtNgayThue.getText(), "dd/MM/yyyy"));
        model.setNgayTra(DateHelper.toDate(txtNgayHetHan.getText(), "dd/MM/yyyy"));
        model.setMoTa("");
        return model;
    }

    public PhongTro getModelPT() {
        PhongTro model = new PhongTro();
        model.setMaPT(cboMaPT.getSelectedItem().toString());
        model.setTinhTrang("Đã thuê");

        return model;
    }

    public void insert() {
        if (this.check()) {
            if (this.checkID(txtMaHopDong.getText())) {
//                if (this.checkMaPT()) {
                HopDong model = this.getModel();
                try {
                    hdDao.insert(model);
                    DialogHelper.alert(this, "Thêm mới thành công");

                    HopDongInternalFrame hd = new HopDongInternalFrame();
                    for (JInternalFrame fcon : dp.getAllFrames()) {
                        fcon.dispose();
                    }
                    dp.add(hd);
                    hd.setSize(dp.getWidth() + 5, dp.getHeight() + 35);
                    hd.setLocation(-1, -25);
                    hd.setVisible(true);

                    this.ptDao.updateTT(this.getModelPT());

                    this.dispose();
                } catch (Exception e) {
                    DialogHelper.alert(this, "Thêm mới thất bại");
                }
            }
        }
//        }
    }

    private boolean checkID(String MaHopDong) {
        List<HopDong> list = hdDao.selectAll();
        for (HopDong hd : list) {
            if (MaHopDong.equalsIgnoreCase(hd.getMaHopDong())) {
                DialogHelper.alert(this, "Đã tồn tại mã hợp đồng này");
                txtMaHopDong.requestFocus();
                return false;
            }
        }
        return true;

    }

//    private boolean checkMaPT() {
//        List<HopDong> list = hdDao.getMaPT();
//        for (HopDong hd : list) {
//            if (cboMaPT.getSelectedItem().toString().equalsIgnoreCase(hd.getMaPT())) {
//                DialogHelper.alert(this, "Đã có hợp đồng");
//                return false;
//            }
//        }
//        return true;
//
//    }
    public boolean check() {
        if (txtMaHopDong.getText().length() == 0) {
            DialogHelper.alert(this, "Không được để trống dữ liệu");
            return false;
        }

        SimpleDateFormat dateCheck = new SimpleDateFormat();
        dateCheck.applyPattern("dd/MM/yyyy");
        try {
            if (txtNgayThue.getText().length() == 0) {
                DialogHelper.alert(this, "Không được để trống ngày thuê");
                return false;
            } else {
                Date ngay = dateCheck.parse(txtNgayThue.getText());
            }
            if (txtNgayHetHan.getText().length() == 0) {
                DialogHelper.alert(this, "Không được để trống ngày trả");
                return false;
            } else {
                Date ngay = dateCheck.parse(txtNgayHetHan.getText());
            }
        } catch (Exception ex) {
            DialogHelper.alert(this, "Không đúng định dạng ngày");
            return false;
        }

        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtNgayThue = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtNgayHetHan = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtMaHopDong = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cboMaPT = new javax.swing.JComboBox();

        setClosable(true);
        setIconifiable(true);

        jLabel9.setText("Ngày hết hạn");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 51));
        jLabel1.setText("THÊM MỚI HỢP ĐỒNG");

        jLabel4.setText("Mã hợp đồng");

        btnThem.setBackground(new java.awt.Color(0, 153, 255));
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        jLabel6.setText("Ngày thuê");

        jLabel2.setText("Phòng trọ");

        cboMaPT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMaPTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtMaHopDong)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cboMaPT, 0, 231, Short.MAX_VALUE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNgayThue)
                                .addComponent(txtNgayHetHan, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMaHopDong, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboMaPT)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNgayThue, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNgayHetHan, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        this.insert();

    }//GEN-LAST:event_btnThemActionPerformed

    private void cboMaPTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMaPTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboMaPTActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnThem;
    private javax.swing.JComboBox cboMaPT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField txtMaHopDong;
    private javax.swing.JTextField txtNgayHetHan;
    private javax.swing.JTextField txtNgayThue;
    // End of variables declaration//GEN-END:variables
}
