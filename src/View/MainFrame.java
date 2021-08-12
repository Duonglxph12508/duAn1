
package View;

import Helper.Auth;
import Helper.DialogHelper;
import Helper.ShareHelper;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Tran_Hang
 */
public class MainFrame extends javax.swing.JFrame {

    boolean vaitro;
    String manv;

    public MainFrame() {
        initComponents();

        this.init();
        this.openPhongTro();
        setLocationRelativeTo(null);

        setIconImage(ShareHelper.app_icon);

    }

    void openLogin() {

        DangNhapDialog DN = new DangNhapDialog(this, true);
        DN.setVisible(true);
        this.vaitro = DN.vaitro;
        this.manv = DN.maNV;

    }

    void init() {
        setLocationRelativeTo(null);
        this.openLogin();
    }

    void openPhongTro() {
        if (Auth.authenticated()) {
            pnlKH.setBackground(new Color(211, 211, 211));
            pnlDV.setBackground(new Color(211, 211, 211));
            pnlHoaDon.setBackground(new Color(211, 211, 211));
            pnlHopDong.setBackground(new Color(211, 211, 211));
            pnlTB.setBackground(new Color(211, 211, 211));
            pnlPhongTro.setBackground(new Color(102, 179, 255));
            pnlTK.setBackground(new Color(211, 211, 211));

            PhongTroInternalFrame pt = new PhongTroInternalFrame();
            for (JInternalFrame fcon : jDesktopPane1.getAllFrames()) {
                fcon.dispose();
            }
            jDesktopPane1.add(pt);
            pt.setSize(jDesktopPane1.getWidth() + 5, jDesktopPane1.getHeight() + 35);
            pt.setLocation(-1, -25);
            pt.setVisible(true);
        } else {
            DialogHelper.alert(this, "Vui lòng đăng nhập");
        }
    }

    void openKhachHang() {
        if (Auth.authenticated()) {
            pnlKH.setBackground(new Color(102, 179, 255));
            pnlDV.setBackground(new Color(211, 211, 211));
            pnlHoaDon.setBackground(new Color(211, 211, 211));
            pnlHopDong.setBackground(new Color(211, 211, 211));
            pnlTB.setBackground(new Color(211, 211, 211));
            pnlPhongTro.setBackground(new Color(211, 211, 211));
            pnlTK.setBackground(new Color(211, 211, 211));

            KhachHangInternalFrame kh = new KhachHangInternalFrame();
            for (JInternalFrame fcon : jDesktopPane1.getAllFrames()) {
                fcon.dispose();
            }
            jDesktopPane1.add(kh);
            kh.setSize(jDesktopPane1.getWidth() + 3, jDesktopPane1.getHeight() + 35);
            kh.setLocation(-1, -25);
            kh.setVisible(true);
        } else {
            DialogHelper.alert(this, "Vui lòng đăng nhập");
        }
    }

    void openHoaDon() {
        if (Auth.authenticated()) {
            pnlKH.setBackground(new Color(211, 211, 211));
            pnlDV.setBackground(new Color(211, 211, 211));
            pnlHoaDon.setBackground(new Color(102, 179, 255));
            pnlHopDong.setBackground(new Color(211, 211, 211));
            pnlTB.setBackground(new Color(211, 211, 211));
            pnlPhongTro.setBackground(new Color(211, 211, 211));
            pnlTK.setBackground(new Color(211, 211, 211));

            HoaDonInternalFrame hd = new HoaDonInternalFrame();
            for (JInternalFrame fcon : jDesktopPane1.getAllFrames()) {
                fcon.dispose();
            }
            jDesktopPane1.add(hd);
            hd.setSize(jDesktopPane1.getWidth() + 5, jDesktopPane1.getHeight() + 35);
            hd.setLocation(-1, -25);
            hd.setVisible(true);
        } else {
            DialogHelper.alert(this, "Vui lòng đăng nhập");
        }
    }

    void openHopDong() {
        if (Auth.authenticated()) {
            pnlKH.setBackground(new Color(211, 211, 211));
            pnlDV.setBackground(new Color(211, 211, 211));
            pnlHoaDon.setBackground(new Color(211, 211, 211));
            pnlHopDong.setBackground(new Color(102, 179, 255));
            pnlTB.setBackground(new Color(211, 211, 211));
            pnlPhongTro.setBackground(new Color(211, 211, 211));
            pnlTK.setBackground(new Color(211, 211, 211));

            HopDongInternalFrame hd = new HopDongInternalFrame();
            for (JInternalFrame fcon : jDesktopPane1.getAllFrames()) {
                fcon.dispose();
            }
            jDesktopPane1.add(hd);
            hd.setSize(jDesktopPane1.getWidth() + 3, jDesktopPane1.getHeight() + 35);
            hd.setLocation(-1, -25);
            hd.setVisible(true);
        } else {
            DialogHelper.alert(this, "Vui lòng đăng nhập");
        }
    }

    void openThietBi() {
        if (Auth.authenticated()) {
            pnlKH.setBackground(new Color(211, 211, 211));
            pnlDV.setBackground(new Color(211, 211, 211));
            pnlHoaDon.setBackground(new Color(211, 211, 211));
            pnlHopDong.setBackground(new Color(211, 211, 211));
            pnlTB.setBackground(new Color(102, 179, 255));
            pnlPhongTro.setBackground(new Color(211, 211, 211));
            pnlTK.setBackground(new Color(211, 211, 211));

            ThietBiInternalFrame tb = new ThietBiInternalFrame();
            for (JInternalFrame fcon : jDesktopPane1.getAllFrames()) {
                fcon.dispose();
            }
            jDesktopPane1.add(tb);
            tb.setSize(jDesktopPane1.getWidth() + 5, jDesktopPane1.getHeight() + 35);
            tb.setLocation(-1, -25);
            tb.setVisible(true);
        } else {
            DialogHelper.alert(this, "Vui lòng đăng nhập");
        }
    }

    void openDichVu() {
        if (Auth.authenticated()) {
            pnlKH.setBackground(new Color(211, 211, 211));
            pnlDV.setBackground(new Color(102, 179, 255));
            pnlHoaDon.setBackground(new Color(211, 211, 211));
            pnlHopDong.setBackground(new Color(211, 211, 211));
            pnlTB.setBackground(new Color(211, 211, 211));
            pnlPhongTro.setBackground(new Color(211, 211, 211));
            pnlTK.setBackground(new Color(211, 211, 211));

            DichVuInternalFrame dv = new DichVuInternalFrame();
            for (JInternalFrame fcon : jDesktopPane1.getAllFrames()) {
                fcon.dispose();
            }
            jDesktopPane1.add(dv);
            dv.setSize(jDesktopPane1.getWidth() + 3, jDesktopPane1.getHeight() + 35);
            dv.setLocation(-1, -25);
            dv.setVisible(true);
        } else {
            DialogHelper.alert(this, "Vui lòng đăng nhập");
        }
    }

    void openThongKe() {
        if (Auth.authenticated()) {
            pnlKH.setBackground(new Color(211, 211, 211));
            pnlDV.setBackground(new Color(211, 211, 211));
            pnlHoaDon.setBackground(new Color(211, 211, 211));
            pnlHopDong.setBackground(new Color(211, 211, 211));
            pnlTB.setBackground(new Color(211, 211, 211));
            pnlPhongTro.setBackground(new Color(211, 211, 211));
            pnlTK.setBackground(new Color(102, 179, 255));

            ThongKeInternalFrame tk = new ThongKeInternalFrame();
            for (JInternalFrame fcon : jDesktopPane1.getAllFrames()) {
                fcon.dispose();
            }
            jDesktopPane1.add(tk);
            tk.setSize(jDesktopPane1.getWidth() + 7, jDesktopPane1.getHeight() + 35);
            tk.setLocation(-1, -25);
            tk.setVisible(true);
        } else {
            DialogHelper.alert(this, "Vui lòng đăng nhập");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlHeader = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        pnlButton = new javax.swing.JPanel();
        pnlPhongTro = new javax.swing.JPanel();
        lblPhongTro = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        pnlKH = new javax.swing.JPanel();
        lblKH = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        pnlHopDong = new javax.swing.JPanel();
        lblHopDong = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        pnlDV = new javax.swing.JPanel();
        lblDV = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        pnlTB = new javax.swing.JPanel();
        lblTB = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        pnlHoaDon = new javax.swing.JPanel();
        lblHoaDon = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        pnlTK = new javax.swing.JPanel();
        lblTK = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        pnlFooter = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("QUẢN LÝ NHÀ TRỌ");

        pnlHeader.setBackground(new java.awt.Color(51, 153, 255));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/home.png"))); // NOI18N
        jLabel1.setText("  PHÒNG TRỌ VIỆT ");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel9.setBackground(new java.awt.Color(0, 153, 255));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/user.png"))); // NOI18N
        jLabel19.setText("Admin");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel19)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlHeaderLayout = new javax.swing.GroupLayout(pnlHeader);
        pnlHeader.setLayout(pnlHeaderLayout);
        pnlHeaderLayout.setHorizontalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(91, 91, 91))
        );
        pnlHeaderLayout.setVerticalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHeaderLayout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pnlPhongTro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlPhongTroMouseClicked(evt);
            }
        });

        lblPhongTro.setText("Phòng trọ");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/14.png"))); // NOI18N

        javax.swing.GroupLayout pnlPhongTroLayout = new javax.swing.GroupLayout(pnlPhongTro);
        pnlPhongTro.setLayout(pnlPhongTroLayout);
        pnlPhongTroLayout.setHorizontalGroup(
            pnlPhongTroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPhongTroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPhongTroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPhongTro)
                    .addGroup(pnlPhongTroLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlPhongTroLayout.setVerticalGroup(
            pnlPhongTroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPhongTroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblPhongTro)
                .addContainerGap())
        );

        pnlKH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlKHMouseClicked(evt);
            }
        });

        lblKH.setText("Khách hàng");

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/9.png"))); // NOI18N

        javax.swing.GroupLayout pnlKHLayout = new javax.swing.GroupLayout(pnlKH);
        pnlKH.setLayout(pnlKHLayout);
        pnlKHLayout.setHorizontalGroup(
            pnlKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKHLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblKH)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlKHLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(25, 25, 25))
        );
        pnlKHLayout.setVerticalGroup(
            pnlKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlKHLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblKH)
                .addContainerGap())
        );

        pnlHopDong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlHopDongMouseClicked(evt);
            }
        });

        lblHopDong.setText("Hợp đồng");

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/5.png"))); // NOI18N

        javax.swing.GroupLayout pnlHopDongLayout = new javax.swing.GroupLayout(pnlHopDong);
        pnlHopDong.setLayout(pnlHopDongLayout);
        pnlHopDongLayout.setHorizontalGroup(
            pnlHopDongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHopDongLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlHopDongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblHopDong)
                    .addGroup(pnlHopDongLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel7)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlHopDongLayout.setVerticalGroup(
            pnlHopDongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHopDongLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblHopDong)
                .addContainerGap())
        );

        pnlDV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlDVMouseClicked(evt);
            }
        });

        lblDV.setText("Dịch vụ");

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/24-hours.png"))); // NOI18N

        javax.swing.GroupLayout pnlDVLayout = new javax.swing.GroupLayout(pnlDV);
        pnlDV.setLayout(pnlDVLayout);
        pnlDVLayout.setHorizontalGroup(
            pnlDVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDVLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDV, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                    .addGroup(pnlDVLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel9)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlDVLayout.setVerticalGroup(
            pnlDVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDVLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDV)
                .addContainerGap())
        );

        pnlTB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlTBMouseClicked(evt);
            }
        });

        lblTB.setText("Thiết bị");

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/voltmeter.png"))); // NOI18N

        javax.swing.GroupLayout pnlTBLayout = new javax.swing.GroupLayout(pnlTB);
        pnlTB.setLayout(pnlTBLayout);
        pnlTBLayout.setHorizontalGroup(
            pnlTBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTBLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel11)
                .addContainerGap(18, Short.MAX_VALUE))
            .addGroup(pnlTBLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlTBLayout.setVerticalGroup(
            pnlTBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTBLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTB)
                .addContainerGap())
        );

        pnlHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlHoaDonMouseClicked(evt);
            }
        });

        lblHoaDon.setText("Hóa đơn");

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/3.png"))); // NOI18N

        javax.swing.GroupLayout pnlHoaDonLayout = new javax.swing.GroupLayout(pnlHoaDon);
        pnlHoaDon.setLayout(pnlHoaDonLayout);
        pnlHoaDonLayout.setHorizontalGroup(
            pnlHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHoaDonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblHoaDon)
                    .addGroup(pnlHoaDonLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel13)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlHoaDonLayout.setVerticalGroup(
            pnlHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHoaDonLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblHoaDon)
                .addContainerGap())
        );

        pnlTK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlTKMouseClicked(evt);
            }
        });

        lblTK.setText("Thống kê");

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/clock.png"))); // NOI18N

        javax.swing.GroupLayout pnlTKLayout = new javax.swing.GroupLayout(pnlTK);
        pnlTK.setLayout(pnlTKLayout);
        pnlTKLayout.setHorizontalGroup(
            pnlTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTKLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTK)
                    .addGroup(pnlTKLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel15)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlTKLayout.setVerticalGroup(
            pnlTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTKLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTK)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlButtonLayout = new javax.swing.GroupLayout(pnlButton);
        pnlButton.setLayout(pnlButtonLayout);
        pnlButtonLayout.setHorizontalGroup(
            pnlButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlButtonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlPhongTro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlHopDong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlDV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlTK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(181, Short.MAX_VALUE))
        );
        pnlButtonLayout.setVerticalGroup(
            pnlButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlButtonLayout.createSequentialGroup()
                .addGroup(pnlButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlPhongTro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlHopDong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlDV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlTK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 3, Short.MAX_VALUE))
        );

        jDesktopPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 419, Short.MAX_VALUE)
        );

        pnlFooter.setBackground(new java.awt.Color(102, 102, 102));

        jLabel23.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("@2020 Phòng Trọ");

        jLabel45.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(255, 255, 255));
        jLabel45.setText("Quản lý Phòng Trọ 2020");

        javax.swing.GroupLayout pnlFooterLayout = new javax.swing.GroupLayout(pnlFooter);
        pnlFooter.setLayout(pnlFooterLayout);
        pnlFooterLayout.setHorizontalGroup(
            pnlFooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFooterLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlFooterLayout.setVerticalGroup(
            pnlFooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFooterLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlFooter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jDesktopPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDesktopPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlFooter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pnlPhongTroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlPhongTroMouseClicked
        this.openPhongTro();
    }//GEN-LAST:event_pnlPhongTroMouseClicked

    private void pnlKHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlKHMouseClicked
        this.openKhachHang();
    }//GEN-LAST:event_pnlKHMouseClicked

    private void pnlHopDongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlHopDongMouseClicked
        this.openHopDong();
    }//GEN-LAST:event_pnlHopDongMouseClicked

    private void pnlDVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlDVMouseClicked
        this.openDichVu();
    }//GEN-LAST:event_pnlDVMouseClicked

    private void pnlTBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTBMouseClicked
        this.openThietBi();
    }//GEN-LAST:event_pnlTBMouseClicked

    private void pnlHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlHoaDonMouseClicked
        this.openHoaDon();
    }//GEN-LAST:event_pnlHoaDonMouseClicked

    private void pnlTKMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTKMouseClicked
        this.openThongKe();
    }//GEN-LAST:event_pnlTKMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel lblDV;
    private javax.swing.JLabel lblHoaDon;
    private javax.swing.JLabel lblHopDong;
    private javax.swing.JLabel lblKH;
    private javax.swing.JLabel lblPhongTro;
    private javax.swing.JLabel lblTB;
    private javax.swing.JLabel lblTK;
    private javax.swing.JPanel pnlButton;
    private javax.swing.JPanel pnlDV;
    private javax.swing.JPanel pnlFooter;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPanel pnlHoaDon;
    private javax.swing.JPanel pnlHopDong;
    private javax.swing.JPanel pnlKH;
    private javax.swing.JPanel pnlPhongTro;
    private javax.swing.JPanel pnlTB;
    private javax.swing.JPanel pnlTK;
    // End of variables declaration//GEN-END:variables
}
