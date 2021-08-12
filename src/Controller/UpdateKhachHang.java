/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.KhachHangDAO;
import Helper.DialogHelper;
import Model.KhachHang;
import View.KhachHangInternalFrame;
import View.MainFrame;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

/**
 *
 * @author Tran_Hang
 */
public class UpdateKhachHang extends javax.swing.JInternalFrame {

    KhachHangDAO dao = new KhachHangDAO();
    JDesktopPane dp;

    public UpdateKhachHang(String maKH, JDesktopPane dp) {
        initComponents();

        this.dp = dp;

        txtMaNT.setEditable(false);
        txtMaNT.setBackground(new Color(200, 200, 200));

        KhachHang kh = dao.selectByID(maKH);

        txtMaNT.setText(kh.getMaKH());
        txtTenNT.setText(kh.getTenKH());
        rdoNam.setSelected(!kh.isGT());
        rdoNu.setSelected(kh.isGT());
        txtSDT.setText(kh.getSđt());
        txtNgaySinh.setText(Helper.DateHelper.toString(kh.getNgaySinh(), "dd-MM-yyyy"));
        txtCMND.setText(kh.getCnmd());
        txtQueQuan.setText(kh.getQueQuan());
        rdoNguoithue.setSelected(kh.isVaiTro());
        rdoNguoiO.setSelected(!kh.isVaiTro());
    }

    public KhachHang getModel() {
        KhachHang model = new KhachHang();

        model.setMaKH(txtMaNT.getText());
        model.setTenKH(txtTenNT.getText());
        model.setGT(rdoNu.isSelected());
        model.setSđt(txtSDT.getText());
        model.setNgaySinh(Helper.DateHelper.toDate(txtNgaySinh.getText(), "dd-MM-yyyy"));
        model.setCnmd(txtCMND.getText());
        model.setQueQuan(txtQueQuan.getText());
        model.setVaiTro(rdoNguoithue.isSelected());

        return model;
    }

    public void update() {
        if (this.check()) {
            KhachHang model = this.getModel();
            try {
                dao.update(model);
                DialogHelper.alert(this, "Sửa thành công");

                KhachHangInternalFrame kh = new KhachHangInternalFrame();
                for (JInternalFrame fcon : dp.getAllFrames()) {
                    fcon.dispose();
                }
                dp.add(kh);
                kh.setSize(dp.getWidth() + 5, dp.getHeight() + 35);
                kh.setLocation(-1, -25);
                kh.setVisible(true);

                this.dispose();
            } catch (Exception e) {
                DialogHelper.alert(this, "Sửa thất bại");
            }
        }
    }

    private boolean check() {
        if (txtTenNT.getText().length() == 0 || txtSDT.getText().length() == 0 || txtNgaySinh.getText().length() == 0 || txtQueQuan.getText().length() == 0
                || txtCMND.getText().length() == 0) {
            DialogHelper.alert(this, "Không được để trống dữ liệu");
            return false;
        }

        try {
            String dienthoai = "0\\d{9,10}";
            if (!txtSDT.getText().matches(dienthoai)) {
                DialogHelper.alert(this, "Mời bạn điền đúng số Điện thoại");
                txtSDT.requestFocus();
                return false;
            }
        } catch (Exception ex) {
            txtSDT.requestFocus();
            DialogHelper.alert(this, "SĐT phải là số");
            return false;
        }

        SimpleDateFormat dg = new SimpleDateFormat();
        dg.applyPattern("dd-MM-yyyy");

        try {
            Date ngay = dg.parse(txtNgaySinh.getText());
        } catch (Exception e) {
            txtNgaySinh.requestFocus();
            DialogHelper.alert(this, "Không đúng định dạng ngày");
            return false;
        }

        try {
            String CMND = "^\\d{9,12}\\z";
            if (!txtCMND.getText().matches(CMND)) {
                DialogHelper.alert(this, "Mời bạn điền đúng số CMND");
                txtCMND.requestFocus();
                return false;
            }
        } catch (Exception ex) {
            txtCMND.requestFocus();
            DialogHelper.alert(this, "CMND phải là số");
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        txtTenNT = new javax.swing.JTextField();
        btnCapNhta = new javax.swing.JButton();
        rdoNguoithue = new javax.swing.JRadioButton();
        txtQueQuan = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        rdoNguoiO = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        txtMaNT = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtNgaySinh = new javax.swing.JTextField();
        rdoNu = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtCMND = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);

        btnCapNhta.setBackground(new java.awt.Color(0, 153, 255));
        btnCapNhta.setText("Cập nhật");
        btnCapNhta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhtaActionPerformed(evt);
            }
        });

        buttonGroup2.add(rdoNguoithue);
        rdoNguoithue.setSelected(true);
        rdoNguoithue.setText("Chủ phòng");
        rdoNguoithue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoNguoithueActionPerformed(evt);
            }
        });

        jLabel3.setText("Giới tính");

        jLabel6.setText("Số điện thoại");

        jLabel1.setText("Mã khách hàng");

        buttonGroup2.add(rdoNguoiO);
        rdoNguoiO.setText("Người ở");

        jLabel8.setText("Ngày sinh");

        buttonGroup1.add(rdoNam);
        rdoNam.setSelected(true);
        rdoNam.setText("Nam");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 0, 51));
        jLabel7.setText("SỬA KHÁCH HÀNG");

        buttonGroup1.add(rdoNu);
        rdoNu.setText("Nữ");

        jLabel2.setText("Tên khách hàng");

        jLabel5.setText("Vai trò");

        jLabel4.setText("Quê quán");

        jLabel9.setText("CMND");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtQueQuan, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtMaNT, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(51, 51, 51)
                                .addComponent(rdoNam)
                                .addGap(18, 18, 18)
                                .addComponent(rdoNu))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTenNT))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addComponent(rdoNguoithue)
                                .addGap(18, 18, 18)
                                .addComponent(rdoNguoiO))
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(27, 27, 27)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSDT)
                                    .addComponent(txtNgaySinh)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(txtCMND))))
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(52, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCapNhta)
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(txtMaNT, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel2))
                    .addComponent(txtTenNT, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(rdoNam)
                    .addComponent(rdoNu))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtCMND, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtQueQuan, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(rdoNguoithue)
                    .addComponent(rdoNguoiO))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCapNhta, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCapNhtaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhtaActionPerformed
        this.update();
    }//GEN-LAST:event_btnCapNhtaActionPerformed

    private void rdoNguoithueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoNguoithueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoNguoithueActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhta;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNguoiO;
    private javax.swing.JRadioButton rdoNguoithue;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTextField txtCMND;
    private javax.swing.JTextField txtMaNT;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JTextField txtQueQuan;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenNT;
    // End of variables declaration//GEN-END:variables
}
