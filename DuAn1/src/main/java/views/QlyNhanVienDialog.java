/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package views;

import domainmodel.DangNhap;
import domainmodel.NhanVien;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import repository.DangNhapRepository;
import repository.NhanVienRepository;
import service.impl.QlyDangNhap;
import service.impl.QlyNhanVienImpl;
import viewmodel.NhanVienModel;

/**
 *
 * @author Admin
 */
public class QlyNhanVienDialog extends javax.swing.JDialog {
    
    private ArrayList<NhanVienModel> getAll = new QlyNhanVienImpl().getAllStaff();
    private DefaultTableModel tbl = new DefaultTableModel();
    private DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
    
    public void fillCBB(ArrayList<NhanVienModel> getAll) {
        
        cbbMaNV.setModel(dcbm);
        
        for (NhanVienModel o : getAll) {
            cbbMaNV.addItem(o.getMaNhanVien());
        }
        
    }
    
    public void fillToTable(List<NhanVienModel> listsStaff) {
        
        tbl = (DefaultTableModel) tblQlyNhanVien.getModel();
        tbl.setRowCount(0);
        
        for (NhanVienModel o : listsStaff) {
            tbl.addRow(new Object[]{o.getHoTen(), o.getEmail(), o.getSDT(), o.getGioiTinh(), o.getTrangThai(), o.getImage(), o.getMaNhanVien()});
        }
        
    }
    
    public QlyNhanVienDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        fillToTable(getAll);
        fillCBB(new QlyNhanVienImpl().getAllStaff());
        showDetailIndex();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        txtEmail = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        rdoFemale = new javax.swing.JRadioButton();
        rdoMale = new javax.swing.JRadioButton();
        rdoOFF = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        rdoOn = new javax.swing.JRadioButton();
        txtNumberPhone = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        lblAnh = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtPass = new javax.swing.JPasswordField();
        jPanel2 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblQlyNhanVien = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        cbbPosition = new javax.swing.JComboBox<>();
        cbbMaNV = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        txtAccount = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(89, 146, 270, -1));

        jLabel6.setText("Giới tính");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 218, -1, -1));

        buttonGroup1.add(rdoFemale);
        rdoFemale.setText("Nữ");
        getContentPane().add(rdoFemale, new org.netbeans.lib.awtextra.AbsoluteConstraints(161, 214, -1, -1));

        buttonGroup1.add(rdoMale);
        rdoMale.setText("Nam");
        getContentPane().add(rdoMale, new org.netbeans.lib.awtextra.AbsoluteConstraints(89, 214, -1, -1));

        buttonGroup2.add(rdoOFF);
        rdoOFF.setText("OFF");
        getContentPane().add(rdoOFF, new org.netbeans.lib.awtextra.AbsoluteConstraints(161, 239, -1, -1));

        jLabel7.setText("Trạng Thái");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 248, -1, -1));

        buttonGroup2.add(rdoOn);
        rdoOn.setText("ON");
        rdoOn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoOnActionPerformed(evt);
            }
        });
        getContentPane().add(rdoOn, new org.netbeans.lib.awtextra.AbsoluteConstraints(89, 239, -1, -1));
        getContentPane().add(txtNumberPhone, new org.netbeans.lib.awtextra.AbsoluteConstraints(89, 181, 270, -1));

        jLabel3.setText("Họ Tên");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 106, -1, -1));
        getContentPane().add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(89, 103, 270, -1));

        jLabel4.setText("Email");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 149, -1, -1));

        jPanel1.setBackground(new java.awt.Color(255, 153, 51));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setForeground(new java.awt.Color(51, 102, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QlyNhanVien.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Quản Lý Nhân Viên");

        jButton3.setFont(new java.awt.Font("Sitka Text", 1, 12)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/timkiem.png"))); // NOI18N
        jButton3.setText("Search");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(37, 37, 37)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel5.setText("SDT");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 181, -1, -1));

        lblAnh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblAnh.setText("    Upload");
        lblAnh.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblAnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAnhMouseClicked(evt);
            }
        });
        getContentPane().add(lblAnh, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 100, 104, 122));

        jLabel8.setText("Account");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 290, -1, -1));

        jLabel9.setText("Pass");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 322, -1, -1));
        getContentPane().add(txtPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 320, 260, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton5.setFont(new java.awt.Font("Segoe UI Symbol", 1, 12)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/save.png"))); // NOI18N
        jButton5.setText("Create Account for Staff");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 310, 210, 30));

        jButton6.setFont(new java.awt.Font("Zilla Slab Medium", 0, 12)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/backPng.png"))); // NOI18N
        jButton6.setText("Back");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 140, 99, 30));

        jButton1.setFont(new java.awt.Font("SimSun-ExtB", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/update.png"))); // NOI18N
        jButton1.setText("Update");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 90, -1, -1));

        jButton2.setFont(new java.awt.Font("Segoe UI Symbol", 1, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/save.png"))); // NOI18N
        jButton2.setText("Create");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 40, 99, -1));

        tblQlyNhanVien.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tblQlyNhanVien.setForeground(new java.awt.Color(102, 102, 102));
        tblQlyNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Họ Tên", "Email", "SĐT", "Giới Tính", "Trạng Thái", "Image", "Mã NV"
            }
        ));
        tblQlyNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblQlyNhanVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblQlyNhanVien);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 644, 120));

        jLabel11.setText("Mã Nhân Viên");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, -1, -1));

        cbbPosition.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Staff" }));
        cbbPosition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbPositionActionPerformed(evt);
            }
        });
        jPanel2.add(cbbPosition, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 280, 90, -1));

        cbbMaNV.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel2.add(cbbMaNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 320, 90, -1));

        jLabel10.setText("Position");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, -1, -1));
        jPanel2.add(txtAccount, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 200, 260, -1));

        jLabel12.setText("Mã Nhân Viên");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 200, -1, -1));
        jPanel2.add(txtMaNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 200, 130, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 650, 480));

        jButton4.setFont(new java.awt.Font("Segoe UI Symbol", 1, 12)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/save.png"))); // NOI18N
        jButton4.setText("Create");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(393, 103, 99, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rdoOnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoOnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoOnActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        List<NhanVienModel> getAllSearch = new ArrayList<>();
        
        for (NhanVienModel o : getAll) {
            if (o.getHoTen().equalsIgnoreCase(txtSearch.getText())) {
                getAllSearch.add(o);
            }
        }
        
        if (getAllSearch.size() > 0) {
            fillToTable(getAllSearch);
            JOptionPane.showMessageDialog(this, "Đã tìm thấy");
        } else {
            JOptionPane.showMessageDialog(this, "Không tìm thấy");
        }

    }//GEN-LAST:event_jButton3ActionPerformed
    
    private String strHinhAnh = "";
    

    private void lblAnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAnhMouseClicked
        try {
            JFileChooser jfc = new JFileChooser("C:\\Users\\Admin\\Desktop\\DuAn1\\da1\\DuAn1\\src\\main\\resources\\");
            jfc.showOpenDialog(null);
            File file = jfc.getSelectedFile();
            Image img = ImageIO.read(file);
            strHinhAnh = file.getName();
            lblAnh.setText("");
            int width = lblAnh.getWidth();
            int height = lblAnh.getHeight();
            lblAnh.setIcon(new ImageIcon(img.getScaledInstance(width, height, 0)));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Bạn đã huỷ chọn ảnh");
        }
    }//GEN-LAST:event_lblAnhMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void cbbPositionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbPositionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbPositionActionPerformed
    
    public void showDetailIndex() {
        txtName.setText(new QlyNhanVienImpl().getAllStaff().get(1).getHoTen());
        txtEmail.setText(new QlyNhanVienImpl().getAllStaff().get(1).getEmail());
        txtNumberPhone.setText(new QlyNhanVienImpl().getAllStaff().get(1).getSDT());
        if (new QlyNhanVienImpl().getAllStaff().get(1).getImage() == null) {
            lblAnh.setText("null");
            lblAnh.setIcon(null);
        } else {
            lblAnh.setText("");
            ImageIcon imgIcon = new ImageIcon("C:\\Users\\Admin\\Desktop\\DuAn1\\da1\\DuAn1\\src\\main\\resources\\" + new QlyNhanVienImpl().getAllStaff().get(1).getImage());
            Image img = imgIcon.getImage();
            img.getScaledInstance(lblAnh.getWidth(), lblAnh.getHeight(), 0);
            lblAnh.setIcon(imgIcon);
        }
        if (new QlyNhanVienImpl().getAllStaff().get(1).getTrangThai().equalsIgnoreCase("ON")) {
            rdoOn.setSelected(true);
        } else {
            rdoOFF.setSelected(true);
        }
        if (new QlyNhanVienImpl().getAllStaff().get(1).getGioiTinh().trim().equalsIgnoreCase("Nam")) {
            rdoMale.setSelected(true);
        } else {
            rdoFemale.setSelected(true);
        }
    }
    

    private void tblQlyNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblQlyNhanVienMouseClicked
        
        txtName.setText(new QlyNhanVienImpl().getAllStaff().get(tblQlyNhanVien.getSelectedRow()).getHoTen());
        txtEmail.setText(new QlyNhanVienImpl().getAllStaff().get(tblQlyNhanVien.getSelectedRow()).getEmail());
        txtNumberPhone.setText(new QlyNhanVienImpl().getAllStaff().get(tblQlyNhanVien.getSelectedRow()).getSDT());
        if (new QlyNhanVienImpl().getAllStaff().get(tblQlyNhanVien.getSelectedRow()).getImage() == null) {
            lblAnh.setText("null");
            lblAnh.setIcon(null);
        } else {
            lblAnh.setText("");
            ImageIcon imgIcon = new ImageIcon("C:\\Users\\Admin\\Desktop\\DuAn1\\da1\\DuAn1\\src\\main\\resources\\" + new QlyNhanVienImpl().getAllStaff().get(tblQlyNhanVien.getSelectedRow()).getImage());
            Image img = imgIcon.getImage();
            img.getScaledInstance(lblAnh.getWidth(), lblAnh.getHeight(), 0);
            lblAnh.setIcon(imgIcon);
        }
        if (new QlyNhanVienImpl().getAllStaff().get(1).getTrangThai().equalsIgnoreCase("ON")) {
            rdoOn.setSelected(true);
        } else {
            rdoOFF.setSelected(true);
        }
        if (new QlyNhanVienImpl().getAllStaff().get(1).getGioiTinh().trim().equalsIgnoreCase("Nam")) {
            rdoMale.setSelected(true);
        } else {
            rdoFemale.setSelected(true);
        }
    }//GEN-LAST:event_tblQlyNhanVienMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        NhanVien staff = new NhanVien();
        staff.setEmail(txtEmail.getText());
        staff.setMaNhanVien(txtMaNV.getText());
        if (rdoMale.isSelected()) {
            staff.setGioiTinh("Nam");
        } else {
            staff.setGioiTinh("Nữ");
        }
        staff.setHoTen(txtName.getText());
        staff.setSDT(txtNumberPhone.getText());
        
        String hinh;
        if (strHinhAnh == null) {
            hinh = "null";
        } else {
            hinh = strHinhAnh;
            staff.setImage(strHinhAnh);
        }
        
        if (rdoOn.isSelected()) {
            staff.setTrangThai("ON");
        } else {
            staff.setTrangThai("OFF");
        }
        
        if (new QlyNhanVienImpl().Save(staff)) {
            
            JOptionPane.showMessageDialog(this, "Succes");
            getAll.add(new NhanVienModel(staff));
            fillToTable(new QlyNhanVienImpl().getAllStaff());
            cbbMaNV.removeAllItems();
            fillCBB(new QlyNhanVienImpl().getAllStaff());
            
        } else {
            JOptionPane.showMessageDialog(this, "Failed");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        NhanVien staff = new NhanVien();
        staff.setEmail(txtEmail.getText());
        if (rdoMale.isSelected()) {
            staff.setGioiTinh("Nam");
        } else {
            staff.setGioiTinh("Nữ");
        }
        staff.setHoTen(txtName.getText());
        staff.setSDT(txtNumberPhone.getText());
        staff.setMaNhanVien(txtMaNV.getText());
        String hinh;
        if (strHinhAnh == null) {
            hinh = "null";
        } else {
            hinh = strHinhAnh;
            staff.setImage(strHinhAnh);
        }
        
        if (rdoOn.isSelected()) {
            staff.setTrangThai("ON");
        } else {
            staff.setTrangThai("OFF");
        }
        
        if (new QlyNhanVienImpl().Update(UUID.fromString(new QlyNhanVienImpl().getAllStaff().get(tblQlyNhanVien.getSelectedRow()).getIdNhanVien() + ""), staff)) {
            JOptionPane.showMessageDialog(this, "Succes");
            fillToTable(new QlyNhanVienImpl().getAllStaff());
        } else {
            JOptionPane.showMessageDialog(this, "Failed");
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        fillToTable(new QlyNhanVienImpl().getAllStaff());
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        
        DangNhap log = new DangNhap();
        log.setTenTaiKhoan(txtAccount.getText());
        log.setMatKhau(txtPass.getText());
        log.setQuyen(cbbPosition.getSelectedItem() + "");
        log.setIdNhanVien((NhanVien) new NhanVienRepository().getNhanVienByMa(cbbMaNV.getSelectedItem() + "").get(0));
        //   System.out.println(new NhanVienRepository().getNhanVienByMa(cbbMaNV.getSelectedItem() + "").get(0));
        try {
            if (new DangNhapRepository().Save(log)) {
                JOptionPane.showMessageDialog(this, "Create account for staff successfully");
            } else {
                JOptionPane.showMessageDialog(this, "Create account for staff failed");
                
            }
        } catch (Exception e) {
        }
        ;
    }//GEN-LAST:event_jButton5ActionPerformed

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
            java.util.logging.Logger.getLogger(QlyNhanVienDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QlyNhanVienDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QlyNhanVienDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QlyNhanVienDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                QlyNhanVienDialog dialog = new QlyNhanVienDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cbbMaNV;
    private javax.swing.JComboBox<String> cbbPosition;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAnh;
    private javax.swing.JRadioButton rdoFemale;
    private javax.swing.JRadioButton rdoMale;
    private javax.swing.JRadioButton rdoOFF;
    private javax.swing.JRadioButton rdoOn;
    private javax.swing.JTable tblQlyNhanVien;
    private javax.swing.JTextField txtAccount;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtNumberPhone;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
