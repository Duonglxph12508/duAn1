package Bean;

import DAO.CTHDDao;
import DAO.HoaDonDAO;
import DAO.HopDongDAO;
import Helper.DateHelper;
import Helper.DialogHelper;
import Helper.JDBCHelper;
import Model.DichVu;
import Model.HoaDon;
import Model.CTHD;
import Model.HopDong;
import View.HoaDonInternalFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Tran_Hang
 */
public class CTHDFrame extends javax.swing.JInternalFrame {

    HoaDonDAO dao = new HoaDonDAO();
    HopDongDAO HĐao = new HopDongDAO();
    CTHDDao cthdDao = new CTHDDao();
    DefaultTableModel model;
    DefaultComboBoxModel model2 = new DefaultComboBoxModel();
    DefaultComboBoxModel model3 = new DefaultComboBoxModel();

    List<DichVu> listDV = new ArrayList<>();
    List<CTHD> list = new ArrayList<>();
    List<HopDong> listHD = new ArrayList<>();
    int index = -1;
    JDesktopPane dp;

    public CTHDFrame(JDesktopPane dp) {
        initComponents();

        this.dp = dp;
        this.fillComboBoxHD();
        lblTienPhong.setText(cthdDao.getGiaPhong(ListMaPT.get(0)) + "");

        model3 = (DefaultComboBoxModel) cboDVDaThue.getModel();
        model3.removeAllElements();
        listDV = dao.getTenDV(ListMaHopDong.get(0));
        for (DichVu dv : listDV) {
            model3.addElement(dv.getTenDV());
        }

        if (cboDVDaThue.getSelectedItem().equals("Điện")) {
            txtSoDienCu.setEnabled(true);
            txtSoDienMoi.setEnabled(true);
            lblSDC.setText("Số điện cũ:");
            lblSDM.setText("Số điện mới:");

            lblTen.setText(cboDVDaThue.getSelectedItem() + "");
            lblSL.setText(0 + "");
            double gia = this.dao.getGia((String) cboDVDaThue.getSelectedItem());
            lblGia.setText(gia + "");
            lblCongcu.setText("Đồng/Số");

        } else if (cboDVDaThue.getSelectedItem().equals("Nước")) {
            txtSoDienCu.setEnabled(false);
            txtSoDienMoi.setEnabled(false);
            lblSDC.setText("");
            lblSDM.setText("");
            txtSoDienCu.setText("");
            txtSoDienMoi.setText("");

            lblTen.setText(cboDVDaThue.getSelectedItem() + "");
            String maPT = ListMaPT.get(cboPT.getSelectedIndex());
            int SL = this.HĐao.soLuongNguoi(maPT);
            lblSL.setText(SL + "");
            double gia = this.dao.getGia((String) cboDVDaThue.getSelectedItem());
            lblGia.setText(gia + "");
            lblCongcu.setText("Đồng/Người");

        } else {
            txtSoDienCu.setEnabled(false);
            txtSoDienMoi.setEnabled(false);
            lblSDC.setText("");
            lblSDM.setText("");
            txtSoDienCu.setText("");
            txtSoDienMoi.setText("");

            lblTen.setText(cboDVDaThue.getSelectedItem() + "");
            lblSL.setText(1 + "");
            double gia = this.dao.getGia((String) cboDVDaThue.getSelectedItem());
            lblGia.setText(gia + "");
            lblCongcu.setText("Đồng");

        }
    }

    ArrayList<String> ListMaHopDong = new ArrayList<>();
    ArrayList<String> ListMaPT = new ArrayList<>();

    public void fillComboBoxHD() {
        try {
            String query = "SELECT MaHopDong, MaPT FROM dbo.HopDong";
            ResultSet rs = JDBCHelper.excuteQuery(query);
            while (rs.next()) {
                model2.addElement(rs.getString(1) + " - " + rs.getString(2) + " ");
                ListMaHopDong.add(rs.getString(1));
                ListMaPT.add(rs.getString(2));
            }
            cboPT.setModel(model2);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Lỗi dữ liệu");
        }

    }

    public HoaDon getModel() {
        String maHopDong = cboPT.getSelectedItem().toString().substring(0, cboPT.getSelectedItem().toString().indexOf(" "));
        HoaDon model = new HoaDon();

        model.setMaHD(txtHoaDon.getText());
        model.setNamThang(DateHelper.toDate(txtThang.getText(), "MM-yyyy"));
        model.setMaHopDong(maHopDong);
        model.setTongTien(this.cthdDao.getTongTien(txtHoaDon.getText()));
        return model;
    }

    public void insert() {
        if (this.check()) {
            if (this.checkID(txtHoaDon.getText())) {
                HoaDon model = this.getModel();
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

    public void fillTable() {
        model = (DefaultTableModel) tblDS.getModel();
        model.setRowCount(0);
        try {
            list = cthdDao.selectAllDV(txtHoaDon.getText());
            for (CTHD ct : list) {
                model.addRow(new Object[]{ct.getTenDV(), ct.getGia(), ct.getSoLuong(), ct.getThanhTien()});
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }

    public CTHD getModelPhong() {
        CTHD model = new CTHD();
        model.setMaHoaDon(txtHoaDon.getText());
        model.setGia(Double.parseDouble(lblTienPhong.getText()));
        model.setTenDV(lblPhong.getText());
        model.setSoLuong(1);

        return model;
    }

    public void insertPhong() {
        CTHD model = this.getModelPhong();
        try {
            if (txtHoaDon.getText().length() == 0) {
                DialogHelper.alert(this, "Chưa nhập hóa đơn");
                txtHoaDon.requestFocus();
            } else {
                cthdDao.insert(model);
                this.fillTable();
            }
        } catch (Exception e) {
        }
    }

    public CTHD getModelDien() {
        CTHD model = new CTHD();
        model.setMaHoaDon(txtHoaDon.getText());
        model.setGia(Double.parseDouble(lblGia.getText()));
        model.setTenDV(lblTen.getText());
        model.setSoLuong(Integer.parseInt(lblSL.getText()));
        model.setSoDienMoi(Integer.parseInt(txtSoDienMoi.getText()));
        model.setSoDienCu(Integer.parseInt(txtSoDienCu.getText()));

        return model;
    }

    public void insertDien() {
        CTHD model = this.getModelDien();
        try {
            if (txtHoaDon.getText().length() == 0) {
                DialogHelper.alert(this, "Chưa nhập hóa đơn");
                txtHoaDon.requestFocus();
                return;
            } else {
                cthdDao.insert(model);
                this.fillTable();
            }

        } catch (Exception e) {
        }
    }

    public CTHD getModelDV() {
        CTHD model = new CTHD();
        model.setMaHoaDon(txtHoaDon.getText());
        model.setGia(Double.parseDouble(lblGia.getText()));
        model.setTenDV(lblTen.getText());
        model.setSoLuong(Integer.parseInt(lblSL.getText()));

        return model;
    }

    public void insertDV() {
        CTHD model = this.getModelDV();
        try {
            if (txtHoaDon.getText().length() == 0) {
                DialogHelper.alert(this, "Chưa nhập hóa đơn");
                txtHoaDon.requestFocus();
            } else {
                cthdDao.insert(model);
                this.fillTable();
            }
        } catch (Exception e) {
        }
    }

    public boolean checkDien() {
        if (txtSoDienCu.getText().length() == 0 || txtSoDienMoi.getText().length() == 0) {
            DialogHelper.alert(this, "Chưa nhập số điện");
            return false;
        }

        try {
            if (Integer.parseInt(txtSoDienCu.getText()) < 0 || Integer.parseInt(txtSoDienMoi.getText()) < 0) {
                DialogHelper.alert(this, "Số điện phải lớn hơn 0");
                return false;
            }

        } catch (Exception ex) {
            DialogHelper.alert(this, "Số lượng phải là số");
            return false;
        }

        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        lblTen4 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtHoaDon = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cboPT = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        txtThang = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lblTienPhong = new javax.swing.JLabel();
        lblPhong = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        pnlTT = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblTen = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblGia = new javax.swing.JLabel();
        lblSDC = new javax.swing.JLabel();
        lblSDM = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblSL = new javax.swing.JLabel();
        txtSoDienCu = new javax.swing.JTextField();
        txtSoDienMoi = new javax.swing.JTextField();
        lblCongcu = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        cboDVDaThue = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDS = new javax.swing.JTable();
        btnThemHĐ = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnLuu = new javax.swing.JButton();

        jLabel5.setText("Số điện mới:");

        setClosable(true);
        setIconifiable(true);

        jLabel4.setText("Mã hóa đơn");

        jLabel7.setText("Hợp đồng - Phòng trọ");

        cboPT.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboPTItemStateChanged(evt);
            }
        });

        jLabel8.setText("Tháng-Năm");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chi tiết hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Giá phòng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        lblPhong.setText("Tiền phòng");

        jLabel3.setText(":");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(lblPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(lblTienPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(88, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTienPhong, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblPhong)
                        .addComponent(jLabel3)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pnlTT.setBackground(new java.awt.Color(204, 204, 204));
        pnlTT.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel1.setForeground(new java.awt.Color(204, 0, 0));
        jLabel1.setText("Tên dịch vụ:");

        jLabel2.setForeground(new java.awt.Color(204, 0, 0));
        jLabel2.setText("Giá dịch vụ:");

        lblSDC.setForeground(new java.awt.Color(204, 0, 0));
        lblSDC.setText("Số điện cũ:");

        lblSDM.setForeground(new java.awt.Color(204, 0, 0));
        lblSDM.setText("Số điện mới:");

        jLabel6.setForeground(new java.awt.Color(204, 0, 0));
        jLabel6.setText("Số lượng:");

        javax.swing.GroupLayout pnlTTLayout = new javax.swing.GroupLayout(pnlTT);
        pnlTT.setLayout(pnlTTLayout);
        pnlTTLayout.setHorizontalGroup(
            pnlTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTTLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTTLayout.createSequentialGroup()
                        .addGroup(pnlTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTen, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlTTLayout.createSequentialGroup()
                                .addComponent(lblGia, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addComponent(lblCongcu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(10, 10, 10)
                        .addGroup(pnlTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblSDC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblSDM, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtSoDienCu, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                            .addComponent(txtSoDienMoi)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTTLayout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblSL, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlTTLayout.setVerticalGroup(
            pnlTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTTLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTTLayout.createSequentialGroup()
                        .addGroup(pnlTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(lblTen, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtSoDienCu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtSoDienMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblSDM))
                            .addComponent(lblGia, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(lblCongcu, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblSDC))
                .addGap(17, 17, 17)
                .addGroup(pnlTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lblSL, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dịch vụ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        cboDVDaThue.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboDVDaThueItemStateChanged(evt);
            }
        });
        cboDVDaThue.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboDVDaThueMouseClicked(evt);
            }
        });
        cboDVDaThue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboDVDaThueActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cboDVDaThue, 0, 99, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cboDVDaThue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        tblDS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "TÊN DỊCH VỤ", "GIÁ ", "SỐ LƯỢNG", "THÀNH TIỀN"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
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

        btnThemHĐ.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnThemHĐ.setText("Thêm hóa đơn");
        btnThemHĐ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemHĐActionPerformed(evt);
            }
        });

        btnThem.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnThem.setText("Thêm tiền phòng");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnLuu.setBackground(new java.awt.Color(0, 51, 255));
        btnLuu.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnLuu.setText("Thêm dịch vụ");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pnlTT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnThem)
                            .addComponent(btnThemHĐ)
                            .addComponent(btnLuu)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 619, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pnlTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(btnLuu)
                        .addGap(18, 18, 18)
                        .addComponent(btnThemHĐ)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(561, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(cboPT, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtThang, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(46, 46, 46))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtThang, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboPT, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(120, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboDVDaThueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboDVDaThueMouseClicked

        if (cboDVDaThue.getSelectedItem().equals("Điện")) {
            txtSoDienCu.setEnabled(true);
            txtSoDienMoi.setEnabled(true);
            lblSDC.setText("Số điện cũ:");
            lblSDM.setText("Số điện mới:");

            lblTen.setText(cboDVDaThue.getSelectedItem() + "");
            lblSL.setText(0 + "");
            double gia = this.dao.getGia((String) cboDVDaThue.getSelectedItem());
            lblGia.setText(gia + "");
            lblCongcu.setText("Đồng/Số");

        } else if (cboDVDaThue.getSelectedItem().equals("Nước")) {
            txtSoDienCu.setEnabled(false);
            txtSoDienMoi.setEnabled(false);
            lblSDC.setText("");
            lblSDM.setText("");
            txtSoDienCu.setText("");
            txtSoDienMoi.setText("");

            lblTen.setText(cboDVDaThue.getSelectedItem() + "");
            String maPT = ListMaPT.get(cboPT.getSelectedIndex());
            int SL = this.HĐao.soLuongNguoi(maPT);
            lblSL.setText(SL + "");
            double gia = this.dao.getGia((String) cboDVDaThue.getSelectedItem());
            lblGia.setText(gia + "");
            lblCongcu.setText("Đồng/Người");

        } else {
            txtSoDienCu.setEnabled(false);
            txtSoDienMoi.setEnabled(false);
            lblSDC.setText("");
            lblSDM.setText("");
            txtSoDienCu.setText("");
            txtSoDienMoi.setText("");

            lblTen.setText(cboDVDaThue.getSelectedItem() + "");
            lblSL.setText(1 + "");
            double gia = this.dao.getGia((String) cboDVDaThue.getSelectedItem());
            lblGia.setText(gia + "");
            lblCongcu.setText("Đồng");

        }
    }//GEN-LAST:event_cboDVDaThueMouseClicked

    private void cboDVDaThueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboDVDaThueActionPerformed

    }//GEN-LAST:event_cboDVDaThueActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        if (cboDVDaThue.getSelectedItem().equals("Điện")) {
            if (checkDien()) {
                int SL = Integer.parseInt(txtSoDienMoi.getText()) - Integer.parseInt(txtSoDienCu.getText());
                lblSL.setText(SL + "");
                this.insertDien();
            }
        } else {
            this.insertDV();
        }
    }//GEN-LAST:event_btnLuuActionPerformed

    private void tblDSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDSMouseClicked

    }//GEN-LAST:event_tblDSMouseClicked

    private void btnThemHĐActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemHĐActionPerformed
        this.insert();
    }//GEN-LAST:event_btnThemHĐActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        this.insertPhong();
    }//GEN-LAST:event_btnThemActionPerformed

    private void cboPTItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboPTItemStateChanged
        lblTienPhong.setText(cthdDao.getGiaPhong(ListMaPT.get(cboPT.getSelectedIndex())) + "");
        model3 = (DefaultComboBoxModel) cboDVDaThue.getModel();
        model3.removeAllElements();
        listDV = dao.getTenDV(ListMaHopDong.get(cboPT.getSelectedIndex()));
        for (DichVu dv : listDV) {
            model3.addElement(dv.getTenDV());
        }
    }//GEN-LAST:event_cboPTItemStateChanged

    private void cboDVDaThueItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboDVDaThueItemStateChanged

    }//GEN-LAST:event_cboDVDaThueItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThemHĐ;
    private javax.swing.JComboBox cboDVDaThue;
    private javax.swing.JComboBox cboPT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCongcu;
    private javax.swing.JLabel lblGia;
    private javax.swing.JLabel lblPhong;
    private javax.swing.JLabel lblSDC;
    private javax.swing.JLabel lblSDM;
    private javax.swing.JLabel lblSL;
    private javax.swing.JLabel lblTen;
    private javax.swing.JLabel lblTen4;
    private javax.swing.JLabel lblTienPhong;
    private javax.swing.JPanel pnlTT;
    private javax.swing.JTable tblDS;
    private javax.swing.JTextField txtHoaDon;
    private javax.swing.JTextField txtSoDienCu;
    private javax.swing.JTextField txtSoDienMoi;
    private javax.swing.JTextField txtThang;
    // End of variables declaration//GEN-END:variables
}
