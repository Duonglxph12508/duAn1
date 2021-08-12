package View;

import Bean.CTHDFrame;
import Bean.XemCTHoaDon;
import Controller.AddHoaDon;
import Controller.UpdateHoaDon;
import DAO.HoaDonDAO;
import Helper.DateHelper;
import Helper.DialogHelper;
import Helper.JDBCHelper;
import Model.HoaDon;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Tran_Hang
 */
public class HoaDonInternalFrame extends javax.swing.JInternalFrame {

    public static Connection conn;
    HoaDonDAO dao = new HoaDonDAO();
    List<HoaDon> list = new ArrayList<>();
    int index = -1;
    DefaultTableModel model;

    public HoaDonInternalFrame() {
        initComponents();
        this.fillTable();
    }

    public void fillTable() {
        model = (DefaultTableModel) tblDS.getModel();
        model.setRowCount(0);
        try {
            list = dao.selectAll();
            for (HoaDon hd : list) {
                model.addRow(new Object[]{hd.getMaHD(), hd.getTenPT(), hd.getTenKH(),
                    DateHelper.toString(hd.getNamThang(), "MM-yyyy"), hd.getTongTien(),
                    hd.isTinhTrang() ? "Đã thanh toán" : "Chưa thanh toán"});
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }

    public void delete() {
        if (DialogHelper.confirm(this, "Bạn thật sự muốn xóa hóa đơn này")) {
            try {
                this.index = tblDS.getSelectedRow();
                String maHD = tblDS.getValueAt(this.index, 0).toString();
                dao.delete(maHD);
                dao.deleteCTHD(maHD);

                this.fillTable();
                DialogHelper.alert(this, "Xóa thành công");
            } catch (Exception e) {
                DialogHelper.alert(this, "Xóa thất bại");
            }
        }

    }

    public void update() {
        this.index = tblDS.getSelectedRow();
        HoaDon model = new HoaDon();
        model.setTinhTrang(true);
        model.setMaHD(tblDS.getValueAt(this.index, 0).toString());
        try {
            dao.update(model);
            this.fillTable();

        } catch (Exception e) {
        }

    }

    public void TimKiem(String maPT) {

        model = (DefaultTableModel) tblDS.getModel();
        tblDS.removeAll();
        model.setRowCount(0);

        List<HoaDon> list = new ArrayList<>();
        list = dao.selectTimkiem(maPT);

        for (HoaDon hd : list) {
            model.addRow(new Object[]{hd.getMaHD(), hd.getTenPT(), hd.getTenKH(), hd.getNamThang(),
                hd.getTongTien(), hd.isTinhTrang() ? "Đã thanh toán" : "Chưa thanh toán"});
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
        btnThanhToan = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDS = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);

        lblDS.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblDS.setForeground(new java.awt.Color(255, 255, 255));
        lblDS.setText("DANH SÁCH CÁC PHÒNG CẦN THANH TOÁN");
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
        txtTimKiem.setText("Tìm kiếm ");
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

        btnThanhToan.setBackground(new java.awt.Color(255, 102, 102));
        btnThanhToan.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnThanhToan.setForeground(new java.awt.Color(255, 255, 255));
        btnThanhToan.setText("Thanh toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
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
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "MÃ HÓA ĐƠN", "PHÒNG TRỌ", "TÊN KHÁCH HÀNG", "THÁNG - NĂM", "TỔNG TIỀN", "TÌNH TRẠNG"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 154, Short.MAX_VALUE)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addComponent(lblDS, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnThanhToan)
                        .addGap(18, 18, 18)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(lblDS, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnThem)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnTimKiem))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        jDesktopPane1.setLayer(lblDS, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(btnThem, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(txtTimKiem, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(btnTimKiem, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(btnThanhToan, javax.swing.JLayeredPane.DEFAULT_LAYER);
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

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed

        CTHDFrame hd = new CTHDFrame(jDesktopPane1);
        jDesktopPane1.add(hd);
        hd.setVisible(true);

    }//GEN-LAST:event_btnThemActionPerformed

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed

    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        this.index = tblDS.getSelectedRow();
        if (this.index != -1) {
            this.update();
        } else {
            DialogHelper.alert(this, "Bạn chưa chọn hóa đơn nào");
        }
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        this.index = tblDS.getSelectedRow();
        if (this.index != -1) {
            this.delete();
        } else {
            DialogHelper.alert(this, "Bạn chưa chọn hóa đơn nào");
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void lblDSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDSMouseClicked
        this.fillTable();
        txtTimKiem.setText("Tìm kiếm phòng trọ");
        txtTimKiem.setForeground(Color.BLACK);
    }//GEN-LAST:event_lblDSMouseClicked

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        String maPT = txtTimKiem.getText();
        this.TimKiem(maPT);
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void txtTimKiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimKiemMouseClicked
        txtTimKiem.setText("");
        txtTimKiem.setForeground(Color.BLACK);
    }//GEN-LAST:event_txtTimKiemMouseClicked

    private void tblDSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDSMouseClicked
        if (evt.getClickCount() == 1) {
            this.index = tblDS.getSelectedRow();
            if (this.index >= 0) {
                String tinhTrang = tblDS.getValueAt(this.index, 5).toString();
                if (tinhTrang.equals("Đã thanh toán")) {
                    btnThanhToan.setEnabled(false);
                } else {
                    btnThanhToan.setEnabled(true);
                }
            }
        } else if (evt.getClickCount() == 2) {
            String maHD = tblDS.getValueAt(this.index, 0).toString();
            XemCTHoaDon hd = new XemCTHoaDon(maHD);
            jDesktopPane1.add(hd);
            hd.setVisible(true);
            
        }
    }//GEN-LAST:event_tblDSMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDS;
    private javax.swing.JTable tblDS;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
