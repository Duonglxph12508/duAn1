/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.HoaDonDAO;
import Helper.DateHelper;
import Helper.DialogHelper;
import Model.HoaDon;
import View.HoaDonInternalFrame;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

/**
 *
 * @author Tran_Hang
 */
public class UpdateHoaDon extends javax.swing.JInternalFrame {
    
    HoaDonDAO dao = new HoaDonDAO();
    JDesktopPane dp;
    
    public UpdateHoaDon(String maHD, JDesktopPane dp) {
        initComponents();
        
        this.dp = dp;
        
        txtMaHD.setEditable(false);
        txtMaHD.setBackground(new Color(200, 200, 200));
        
        HoaDon hd = dao.selectByID(maHD);
        this.setModel(hd); 
        
    }
    
    public void setModel(HoaDon hd) {
        txtMaHD.setText(hd.getMaHD());
        txtThang.setText(DateHelper.toString(hd.getNamThang(), "MM-yyyy"));
        rdoCtt.setSelected(!hd.isTinhTrang());
        rdoDtt.setSelected(hd.isTinhTrang());
    }
    
    public HoaDon getModel() {
        HoaDon model = new HoaDon();
        
        model.setMaHD(txtMaHD.getText());
        model.setNamThang(DateHelper.toDate(txtThang.getText(), "MM-yyyy"));
        model.setTinhTrang(rdoDtt.isSelected());
        
        return model;
    }
    
    public void update() {
        if (this.check()) {
            HoaDon model = this.getModel();
            try {
                dao.update(model);
                DialogHelper.alert(this, "Update thành công");
                
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
                DialogHelper.alert(this, "Update thất bại");
            }
            
        }
    }
    
    private boolean check() {
        if (txtThang.getText().length() == 0) {
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
        txtMaHD = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtThang = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        rdoDtt = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        rdoCtt = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);

        btnThem.setBackground(new java.awt.Color(0, 153, 255));
        btnThem.setText("Cập nhật");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        jLabel2.setText("Tháng-Năm");

        jLabel4.setText("Tình trạng");

        buttonGroup1.add(rdoDtt);
        rdoDtt.setText("Đã thanh toán");
        rdoDtt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoDttActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 0, 51));
        jLabel5.setText("SỬA HÓA ĐƠN");

        buttonGroup1.add(rdoCtt);
        rdoCtt.setSelected(true);
        rdoCtt.setText("Chưa thanh toán");

        jLabel1.setText("Mã hóa đơn");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtThang, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2)
                                .addComponent(jLabel4)))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(41, 41, 41)
                            .addComponent(rdoDtt)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(rdoCtt))))
                .addContainerGap(38, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnThem))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(23, 23, 23))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        this.update();

    }//GEN-LAST:event_btnThemActionPerformed

    private void rdoDttActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoDttActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoDttActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnThem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JRadioButton rdoCtt;
    private javax.swing.JRadioButton rdoDtt;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JTextField txtThang;
    // End of variables declaration//GEN-END:variables
}
