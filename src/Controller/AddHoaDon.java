package Controller;

import DAO.HoaDonDAO;
import Helper.DateHelper;
import Helper.DialogHelper;
import Model.HoaDon;
import Model.HopDong;
import View.HoaDonInternalFrame;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

/**
 *
 * @author Tran_Hang
 */
public class AddHoaDon extends javax.swing.JInternalFrame {

    HoaDonDAO dao = new HoaDonDAO();
    List<HopDong> listHD = new ArrayList<>();
    JDesktopPane dp;

    public AddHoaDon(JDesktopPane dp) {
        initComponents();

        this.dp = dp;
        this.fillComboBoxHD();

    }

    public void fillComboBoxHD() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboPT.getModel();
        model.removeAllElements();
        listHD = dao.getHopDong();
        for (HopDong hd : listHD) {
            model.addElement(hd.getMaHopDong() + " - " + hd.getMaPT());
        }

    }

    public HoaDon getModelHD() {
        String maHopDong = cboPT.getSelectedItem().toString().substring(0, cboPT.getSelectedItem().toString().indexOf(" "));
        HoaDon model = new HoaDon();

        model.setMaHD(txtHoaDon.getText());
        model.setNamThang(DateHelper.toDate(txtThang.getText(), "MM-yyyy"));
        model.setTinhTrang(rdoDtt.isSelected());
        model.setMaHopDong(maHopDong);
        model.setTongTien(0);
        return model;
    }

    public void insertHD() {
        if (this.check()) {
            if (this.checkID(txtHoaDon.getText())) {
                HoaDon model = this.getModelHD();
                try {
                    dao.insert(model);
                    DialogHelper.alert(this, "Thêm mới thành công");

                    HoaDonInternalFrame hd = new HoaDonInternalFrame();
                    for (JInternalFrame fcon : dp.getAllFrames()) {
                        fcon.dispose();
                    }
                    dp.add(hd);
                    hd.setSize(dp.getWidth() + 5, dp.getHeight() + 35);
                    hd.setLocation(-1, -25);
                    hd.setVisible(true);

                    this.dispose();
                } catch (Exception e) {
                    DialogHelper.alert(this, "Thêm mới thất bại");
                }
            }
        }
    }

    private boolean checkID(String maHD) {
        List<HoaDon> list = dao.selectAll();
        for (HoaDon hd : list) {
            if (maHD.equalsIgnoreCase(hd.getMaHD())) {
                DialogHelper.alert(this, "Đã tồn tại mã hóa đơn này");
                txtHoaDon.requestFocus();
                return false;
            }
        }
        return true;

    }

    private boolean check() {
        if (txtHoaDon.getText().length() == 0 || txtThang.getText().length() == 0) {
            DialogHelper.alert(this, "Không được để trống dữ liệu");
            return false;
        }

        SimpleDateFormat dg = new SimpleDateFormat();
        dg.applyPattern("MM-yyyy");

        try {
            Date ngay = dg.parse(txtThang.getText());
        } catch (Exception e) {

            DialogHelper.alert(this, "Không đúng định dạng tháng");
            return false;
        }

        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        txtHoaDon = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtThang = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cboPT = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        rdoDtt = new javax.swing.JRadioButton();
        rdoCtt = new javax.swing.JRadioButton();

        setClosable(true);
        setIconifiable(true);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 0, 51));
        jLabel5.setText("THÊM MỚI HÓA ĐƠN");

        jLabel1.setText("Mã hóa đơn");

        btnThem.setBackground(new java.awt.Color(0, 153, 255));
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        jLabel2.setText("Tháng-Năm");

        jLabel3.setText("Hợp đồng - Phòng trọ");

        jLabel4.setText("Tình trạng");

        buttonGroup1.add(rdoDtt);
        rdoDtt.setText("Đã thanh toán");
        rdoDtt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoDttActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoCtt);
        rdoCtt.setSelected(true);
        rdoCtt.setText("Chưa thanh toán");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 209, Short.MAX_VALUE)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(cboPT, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtThang, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(rdoDtt)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rdoCtt))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cboPT, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtThang, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoDtt)
                    .addComponent(rdoCtt))
                .addGap(18, 18, 18)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        this.insertHD();

    }//GEN-LAST:event_btnThemActionPerformed

    private void rdoDttActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoDttActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoDttActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnThem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cboPT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JRadioButton rdoCtt;
    private javax.swing.JRadioButton rdoDtt;
    private javax.swing.JTextField txtHoaDon;
    private javax.swing.JTextField txtThang;
    // End of variables declaration//GEN-END:variables
}
