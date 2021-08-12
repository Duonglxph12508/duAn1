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
import View.HopDongInternalFrame;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

/**
 *
 * @author Tran_Hang
 */
public class UpdateHopDong extends javax.swing.JInternalFrame {

    HopDongDAO hdDao = new HopDongDAO();
    PhongTroDAO ptDao = new PhongTroDAO();
    JDesktopPane dp;

    public UpdateHopDong(String maHD, JDesktopPane dp) {
        initComponents();

        this.dp = dp;

        this.fillForm(maHD);
    }

    public void fillForm(String maHD) {
        txtMaHopDong.setEditable(false);
        txtMaHopDong.setBackground(new Color(200, 200, 200));
        txtSoNguoi.setEditable(false);
        txtSoNguoi.setBackground(new Color(200, 200, 200));

        HopDong hd = hdDao.selectByID(maHD);
        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");

        txtMaHopDong.setText(maHD);
        txtSoNguoi.setText(hdDao.getSLNguoi(maHD)+"");
        txtNgayThue.setText(date.format(hd.getNgayThue()).toString());
        txtNgayHetHan.setText(date.format(hd.getNgayTra()).toString());
    }

    public HopDong getModel() {
        HopDong model = new HopDong();
        model.setMaHopDong(txtMaHopDong.getText());
        model.setSoNguoi(hdDao.getSLNguoi(txtMaHopDong.getText())); 
        model.setNgayThue(DateHelper.toDate(txtNgayThue.getText(), "dd/MM/yyyy"));
        model.setNgayTra(DateHelper.toDate(txtNgayHetHan.getText(), "dd/MM/yyyy"));
        return model;
    }

    public void update() {
        if (this.check()) {
            HopDong model = this.getModel();
            try {
                hdDao.update(model);
                DialogHelper.alert(this, "Sửa thành công");

                HopDongInternalFrame pt = new HopDongInternalFrame();
                for (JInternalFrame fcon : dp.getAllFrames()) {
                    fcon.dispose();
                }
                dp.add(pt);
                pt.setSize(dp.getWidth() + 5, dp.getHeight() + 35);
                pt.setLocation(-1, -25);
                pt.setVisible(true);

                this.dispose();
            } catch (Exception e) {
                DialogHelper.alert(this, "Sửa thất bại");
            }
        }
    }

    public boolean check() {
        if (txtMaHopDong.getText().length() == 0
                || txtSoNguoi.getText().length() == 0) {
            DialogHelper.alert(this, "Không được để trống dữ liệu");
            return false;
        }
        try {
            if (Integer.parseInt(txtSoNguoi.getText()) < 0) {
                txtSoNguoi.requestFocus();
                DialogHelper.alert(this, "Số lượng phải lớn hơn 0");
                return false;
            }

        } catch (Exception ex) {
            txtSoNguoi.requestFocus();
            DialogHelper.alert(this, "Số lượng phải là số");
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

        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtMaHopDong = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtNgayThue = new javax.swing.JTextField();
        txtSoNguoi = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtNgayHetHan = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 51));
        jLabel1.setText("SỬA HỢP ĐỒNG");

        jLabel4.setText("Mã hợp đồng");

        btnThem.setBackground(new java.awt.Color(0, 153, 255));
        btnThem.setText("Cập nhật");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        jLabel7.setText("Số lượng người ở");

        jLabel9.setText("Ngày hết hạn");

        jLabel6.setText("Ngày thuê");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtMaHopDong)
                        .addComponent(txtSoNguoi)
                        .addComponent(txtNgayThue)
                        .addComponent(txtNgayHetHan, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(68, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThem)
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMaHopDong, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSoNguoi, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNgayThue, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNgayHetHan, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        this.update();
    }//GEN-LAST:event_btnThemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnThem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField txtMaHopDong;
    private javax.swing.JTextField txtNgayHetHan;
    private javax.swing.JTextField txtNgayThue;
    private javax.swing.JTextField txtSoNguoi;
    // End of variables declaration//GEN-END:variables
}
