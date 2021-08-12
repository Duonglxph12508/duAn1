
package Controller;

import Bean.XemKhachHang;
import DAO.KhachHangDAO;
import Helper.DialogHelper;
import Model.KhachHang;
import View.KhachHangInternalFrame;
import View.MainFrame;
import java.util.List;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

/**
 *
 * @author Tran_Hang
 */
public class AddKhachHang extends javax.swing.JInternalFrame {

    KhachHangDAO dao = new KhachHangDAO();
    JDesktopPane dp;
    String maHD;

    public AddKhachHang(JDesktopPane dp, String MaHD) {
        initComponents();
        
        this.dp = dp;
        this.maHD = MaHD;
    }

    public KhachHang getModel() {
        KhachHang model = new KhachHang();
        model.setMaKH(txtMaNT.getText());
        model.setMaHD(this.maHD); 
        model.setTenKH(txtTenNT.getText());
        model.setGT(rdoNu.isSelected());
        model.setSđt(txtSDT.getText());
        model.setNgaySinh(Helper.DateHelper.toDate(txtNgaySinh.getText(), "dd/MM/yyyy"));
        model.setCnmd(txtCMND.getText());
        model.setQueQuan(txtQueQuan.getText());
        model.setVaiTro(rdoNguoithue.isSelected());

        return model;
    }

    public void insert() {
        if (this.check()) {
            if (this.checkID(txtMaNT.getText())) {
                KhachHang model = this.getModel();
                try {
                    dao.insert(model);
                    DialogHelper.alert(this, "Thêm mới thành công");
                    
                    XemKhachHang kh = new XemKhachHang(maHD, dp); 
                    for (JInternalFrame fcon : dp.getAllFrames()) {
                        fcon.dispose();
                    }
                    dp.add(kh);
                    kh.setSize(dp.getWidth() + 5, dp.getHeight() + 35);
                    kh.setLocation(-1, -25);
                    kh.setVisible(true);
                    
                    this.dispose();
                } catch (Exception e) {
                    DialogHelper.alert(this, "Thêm mới thất bại");
                }
            }
        }
    }

    private boolean checkID(String maKH) {
        List<KhachHang> list = dao.selectAll();
        for (KhachHang kh : list) {
            if (maKH.equalsIgnoreCase(kh.getMaKH())) {
                DialogHelper.alert(this, "Đã tồn tại mã khách hàng này");
                txtMaNT.requestFocus();
                return false;
            }
        }
        return true;

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
                return false;
            }
        } catch (Exception ex) {
            txtSDT.requestFocus();
            DialogHelper.alert(this, "SĐT phải là số");
            return false;
        }
        try {
            String CMND = "0\\d{9,12}";
            if (!txtSDT.getText().matches(CMND)) {
                DialogHelper.alert(this, "Mời bạn điền đúng số CMND");
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
        rdoNguoiO = new javax.swing.JRadioButton();
        rdoNam = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        rdoNu = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        txtQueQuan = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtMaNT = new javax.swing.JTextField();
        txtNgaySinh = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtTenNT = new javax.swing.JTextField();
        rdoNguoithue = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        txtCMND = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);

        buttonGroup2.add(rdoNguoiO);
        rdoNguoiO.setText("Người ở");

        buttonGroup1.add(rdoNam);
        rdoNam.setSelected(true);
        rdoNam.setText("Nam");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 0, 51));
        jLabel7.setText("THÊM MỚI KHÁCH HÀNG");

        buttonGroup1.add(rdoNu);
        rdoNu.setText("Nữ");

        jLabel4.setText("Quê quán");

        btnThem.setBackground(new java.awt.Color(0, 153, 255));
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        jLabel6.setText("Số điện thoại");

        jLabel1.setText("Mã khách hàng");

        jLabel8.setText("Ngày sinh");

        jLabel2.setText("Tên khách hàng");

        jLabel5.setText("Vai trò");

        buttonGroup2.add(rdoNguoithue);
        rdoNguoithue.setSelected(true);
        rdoNguoithue.setText("Chủ phòng");
        rdoNguoithue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoNguoithueActionPerformed(evt);
            }
        });

        jLabel3.setText("Giới tính");

        jLabel9.setText("CMND");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 13, Short.MAX_VALUE)))
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(rdoNguoithue)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rdoNguoiO))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGap(4, 4, 4)
                                    .addComponent(txtMaNT, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(rdoNam)
                                    .addGap(18, 18, 18)
                                    .addComponent(rdoNu))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGap(5, 5, 5)
                                    .addComponent(txtTenNT))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGap(5, 5, 5)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtSDT)
                                        .addComponent(txtNgaySinh)
                                        .addComponent(txtCMND, javax.swing.GroupLayout.Alignment.TRAILING)))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGap(5, 5, 5)
                                    .addComponent(txtQueQuan, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(32, 32, 32))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(txtMaNT, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel2))
                    .addComponent(txtTenNT, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(rdoNam)
                    .addComponent(rdoNu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCMND, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtQueQuan, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(rdoNguoithue)
                    .addComponent(rdoNguoiO))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        this.insert();
    }//GEN-LAST:event_btnThemActionPerformed

    private void rdoNguoithueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoNguoithueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoNguoithueActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnThem;
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
