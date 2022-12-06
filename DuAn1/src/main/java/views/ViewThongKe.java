/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;
import domainmodel.HoaDon;
import domainmodel.KhachHang;
import java.awt.CardLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import service.impl.HoaDonServiceImpl;
import service.impl.KhachHangServiceImpl;
import service.impl.QuanLyThongKeServiceImpl;
import viewmodel.HoaDonModel;
import viewmodel.KhachHangModel;

/**
 *
 * @author vutuo
 */
public class ViewThongKe extends javax.swing.JFrame {

    /**
     * Creates new form TKe
     */
    private KhachHangServiceImpl impl = new KhachHangServiceImpl();
    private List<KhachHangModel> listKH = impl.getAll();
    private DefaultTableModel dtm = new DefaultTableModel();
    private DefaultTableModel dtm1 = new DefaultTableModel();
    private DefaultTableModel dtm2 = new DefaultTableModel();
    private DefaultTableModel dtm3 = new DefaultTableModel();
    private HoaDonServiceImpl donServiceImpl = new HoaDonServiceImpl();
    private List<HoaDonModel> listHD = donServiceImpl.getAllHoaDon();
    private QuanLyThongKeServiceImpl serviceImpl = new QuanLyThongKeServiceImpl();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private List<Object[]> listTK = serviceImpl.getList();
    private List<Object[]> listTKKH = serviceImpl.getListTK();
    private List<Object[]> listTKKHMD = serviceImpl.getListTKMD();
    private List<Object[]> listTKSP = serviceImpl.getListSP();
    private List<Object[]> listSPMD = serviceImpl.tkSPMD();

    public ViewThongKe() {
        initComponents();
        setLocationRelativeTo(null);
        jTable2.setModel(dtm);
        String headers[] = {"Ngày", "Số đơn thanh toán", "Số lượng sản phẩm", "Tổng tiền"};
        String headers1[] = {"Ngày", "Tên sản phẩm", "Số lượng sản phẩm đã mua", "Tổng hóa đơn", "Điểm tích lũy"};
        String headers2[] = {"Tên sản phẩm", "Số lượng sản phẩm đã bán"};
        String headers3[] = {"Ngày", "IDHD", "Tên sản phẩm", "Số lượng", "Giá"};
        dtm.setColumnIdentifiers(headers);
        jTable3.setModel(dtm1);
        jTable4.setModel(dtm2);
        jTable5.setModel(dtm3);
        dtm1.setColumnIdentifiers(headers1);
        dtm2.setColumnIdentifiers(headers2);
        dtm3.setColumnIdentifiers(headers3);
        // Ngày hôm nay
        String date = java.time.LocalDate.now().toString();
        Date date1 = null;
        try {
            date1 = sdf.parse(java.time.LocalDate.now().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        txtTien1.setText(tongDoanhThu(listHD, date) + " VNĐ");
        txtHDDB1.setText(soDonDaBan(listHD, date) + "");
        txtSPDB1.setText(serviceImpl.soLuong(date1).get(0) + "");

        // Ngày hôm qua
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        String hq = yesterday.toString();
        Date hq1 = null;
        try {
            hq1 = sdf.parse(hq);
        } catch (Exception e) {
            e.printStackTrace();
        }
        txtTien2.setText(tongDoanhThu(listHD, hq) + " VNĐ");
        txtHDDB2.setText(soDonDaBan(listHD, hq) + "");
        txtSPDB2.setText(serviceImpl.soLuong(hq1).get(0) + "");
        showData(listTK);
        showDataKH(listTKKHMD);
        showDataSP(listSPMD);
    }

    private void showData(List<Object[]> list) {
        dtm.setRowCount(0);
        for (Object[] objects : list) {
            dtm.addRow(objects);
        }
    }

    private void showDataKH(List<Object[]> list) {
        dtm1.setRowCount(0);
        for (Object[] objects : list) {
            dtm1.addRow(objects);
        }
    }

    private void showDataSP(List<Object[]> list) {
        dtm2.setRowCount(0);
        for (Object[] objects : list) {
            dtm2.addRow(objects);
        }
    }

    private void showDataDTCT(List<Object[]> list) {
        dtm3.setRowCount(0);
        for (Object[] objects : list) {
            dtm3.addRow(objects);
        }
    }

    private float tongDoanhThuNgay(List<HoaDonModel> list, Date ngayBD, Date ngayKT) {

        Date dateBD = ngayBD;
        Date dateKT = ngayKT;
        float sum = 0;

        for (HoaDonModel hoaDonModel : list) {
            Date ngay = hoaDonModel.getNgayTT();
            if (dateBD.compareTo(ngay) <= 0 && dateKT.compareTo(ngay) >= 0 && hoaDonModel.getTrangThai().equalsIgnoreCase("DTT")) {
                sum += hoaDonModel.getTongTien();
            }
        }
        return sum;
    }

    private int soDonDaBanNgay(List<HoaDonModel> list, Date ngayBD, Date ngayKT) {
        int count = 0;

        for (HoaDonModel hoaDonModel : list) {
            Date ngay = hoaDonModel.getNgayTT();
            if (ngayBD.compareTo(ngay) <= 0 && ngayKT.compareTo(ngay) >= 0 && hoaDonModel.getTrangThai().equalsIgnoreCase("DTT")) {
                count += 1;
            }
        }
        return count;
    }

    private int tongSanPham(List<HoaDonModel> list, Date ngayBD, Date ngayKT) {
        int count = 0;
        Date ngay = null;
        try {
            for (HoaDonModel hoaDonModel : list) {
                ngay = hoaDonModel.getNgayTT();
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        for (HoaDonModel hoaDonModel : list) {
            if (ngayBD.compareTo(ngay) <= 0 && ngayKT.compareTo(ngay) >= 0 && hoaDonModel.getTrangThai().equalsIgnoreCase("DTT")) {
                count += 1;
            }
        }
        return count;
    }

    private float tongDoanhThu(List<HoaDonModel> list, String date) {
        float sum = 0;
        for (HoaDonModel hoaDon : list) {
            if (date.equalsIgnoreCase(hoaDon.getNgayTT().toString()) && hoaDon.getTrangThai().equalsIgnoreCase("DTT")) {
                sum += hoaDon.getTongTien();
            }
        }
        return sum;
    }

    private int soDonDaBan(List<HoaDonModel> list, String date) {
        int count = 0;
        for (HoaDonModel hoaDonModel : list) {
            if (date.equalsIgnoreCase(hoaDonModel.getNgayTT().toString()) && hoaDonModel.getTrangThai().equalsIgnoreCase("DTT")) {
                count += 1;
            }
        }
        return count;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtTien0 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtHDDB0 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtSPDB0 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txtTien1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtHDDB1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtSPDB1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        txtTien2 = new javax.swing.JLabel();
        txtHDDB2 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtSPDB2 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        txtBatDau = new com.toedter.calendar.JDateChooser();
        txtKetThuc = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jLabel19 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(102, 51, 0));
        jPanel2.setPreferredSize(new java.awt.Dimension(1200, 678));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton2.setBackground(new java.awt.Color(51, 204, 0));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Xuất Excel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(154, 53, 115, 31));

        jButton3.setBackground(new java.awt.Color(51, 204, 0));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("In");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 53, 113, 31));

        jButton4.setBackground(new java.awt.Color(51, 204, 0));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Gửi Mail ");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 104, 109, 31));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("THỐNG KÊ KHÁCH HÀNG");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(957, 333, 230, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Doanh thu theo ngày");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(327, 104, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Doanh thu ngày hôm nay");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(498, 104, -1, -1));

        jPanel1.setBackground(new java.awt.Color(0, 204, 255));

        txtTien0.setBackground(new java.awt.Color(255, 255, 255));
        txtTien0.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtTien0.setForeground(new java.awt.Color(204, 0, 0));
        txtTien0.setText("0 VNĐ");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Tổng hóa đơn đã bán ");

        txtHDDB0.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        txtHDDB0.setForeground(new java.awt.Color(255, 255, 255));
        txtHDDB0.setText("0");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Tổng số sản phẩm đã bán ");

        txtSPDB0.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        txtSPDB0.setForeground(new java.awt.Color(255, 255, 255));
        txtSPDB0.setText("0");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(txtTien0, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(txtHDDB0)
                            .addGap(66, 66, 66))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(txtSPDB0)
                            .addGap(64, 64, 64))
                        .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel7)
                        .addGap(15, 15, 15)))
                .addGap(16, 16, 16))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(txtTien0)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtHDDB0)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtSPDB0)
                .addContainerGap())
        );

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 133, -1, -1));

        jPanel3.setBackground(new java.awt.Color(0, 204, 255));

        txtTien1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtTien1.setForeground(new java.awt.Color(204, 0, 0));
        txtTien1.setText("0 VNĐ");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Tổng hóa đơn đã bán");

        txtHDDB1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        txtHDDB1.setForeground(new java.awt.Color(255, 255, 255));
        txtHDDB1.setText("0");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Tổng số sản phẩm đã bán ");

        txtSPDB1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        txtSPDB1.setForeground(new java.awt.Color(255, 255, 255));
        txtSPDB1.setText("0");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addComponent(txtSPDB1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(28, 28, 28))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(txtHDDB1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(73, 73, 73))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(14, 14, 14))
                    .addComponent(txtTien1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(txtTien1)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtHDDB1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtSPDB1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(483, 133, -1, 197));

        jPanel4.setBackground(new java.awt.Color(0, 204, 255));

        txtTien2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtTien2.setForeground(new java.awt.Color(204, 0, 0));
        txtTien2.setText("0 VNĐ");

        txtHDDB2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        txtHDDB2.setForeground(new java.awt.Color(255, 255, 255));
        txtHDDB2.setText("0");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setText("Tổng số sản phẩm đã bán ");

        txtSPDB2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        txtSPDB2.setForeground(new java.awt.Color(255, 255, 255));
        txtSPDB2.setText("0");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setText("Tổng hóa đơn đã bán");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(txtSPDB2)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(15, 15, 15))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(txtHDDB2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(75, 75, 75))))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTien2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addContainerGap())))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(txtTien2)
                .addGap(18, 18, 18)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtHDDB2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSPDB2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(673, 133, -1, 197));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Doanh thu ngày hôm qua");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 104, -1, -1));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 410, 407, 222));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Thống kê doanh thu");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 371, 153, 26));

        txtBatDau.setDateFormatString("yyyy-MM-dd");
        jPanel2.add(txtBatDau, new org.netbeans.lib.awtextra.AbsoluteConstraints(81, 185, 188, 26));

        txtKetThuc.setDateFormatString("yyyy-MM-dd");
        jPanel2.add(txtKetThuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(84, 268, 188, 30));

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Từ ngày");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 185, 54, 26));

        jLabel17.setBackground(new java.awt.Color(255, 255, 255));
        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Đến ngày");
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 268, -1, 26));

        jButton5.setBackground(new java.awt.Color(51, 204, 0));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Search");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(154, 104, 115, 31));

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTable3);

        jPanel2.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(869, 410, 400, 222));

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Thống kê khách hàng");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(869, 371, -1, 26));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("THỐNG KÊ DOANH THU");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(444, 32, 219, -1));

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(jTable4);

        jPanel2.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(883, 136, 390, 187));

        jLabel16.setBackground(new java.awt.Color(255, 255, 255));
        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Sản phẩm được bán nhiều nhất");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(883, 100, -1, 26));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("THỐNG KÊ SẢN PHẨM");
        jPanel2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 50, 219, -1));

        jButton6.setBackground(new java.awt.Color(51, 204, 0));
        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("TOP");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 370, 115, 31));

        jButton7.setBackground(new java.awt.Color(51, 204, 0));
        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("TOP");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 90, 115, 31));

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable5MouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jTable5);

        jPanel2.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(419, 410, 432, 222));

        jLabel19.setBackground(new java.awt.Color(255, 255, 255));
        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Thống kê doanh thu chi tiết");
        jPanel2.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(425, 371, -1, 26));

        jButton8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 0, 0));
        jButton8.setText("X");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 20, 62, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(158, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 638, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        String username = "anvtph22489@fpt.edu.vn";
        String password = "evjzzlsbrvrflowx";
        String toEmail = JOptionPane.showInputDialog("Nhập email");
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            String subject = "BÁO CÁO DOANH THU";
            String date = java.time.LocalDate.now().toString();
            Date date1 = null;
            try {
                date1 = sdf.parse(java.time.LocalDate.now().toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            String jtext = "Tổng doanh thu hôm nay là : " + tongDoanhThu(listHD, date) + " VNĐ. \nSố đơn đã bán là: " + soDonDaBan(listHD, date) + ""
                    + "\n Tổng số sản phẩm đã bán là: " + serviceImpl.soLuong(date1).get(0).toString();

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(toEmail)
            );
            message.setSubject(subject);
            message.setText(jtext);
            Transport.send(message);
            JOptionPane.showMessageDialog(this, "Gửi thành công");
        } catch (MessagingException e) {
            JOptionPane.showMessageDialog(this, "Gửi thất bại");
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String path = "";
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int x = chooser.showSaveDialog(this);
        if (x == JFileChooser.APPROVE_OPTION) {
            path = chooser.getSelectedFile().getPath();
        }
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(path + "ThongKe.pdf"));

            document.open();

            //            document.add(new Paragraph("THỐNG KÊ DOANH THU"));
            PdfPTable table = new PdfPTable(4);
            table.addCell("Ngay");
            table.addCell("Tong so don");
            table.addCell("Tong san pham");
            table.addCell("Tong tien");

            for (int i = 0; i < jTable2.getRowCount(); i++) {

                String ngay = jTable2.getValueAt(i, 0).toString();
                String soDon = jTable2.getValueAt(i, 1).toString();
                String tongSP = jTable2.getValueAt(i, 2).toString();
                String tongTien = jTable2.getValueAt(i, 3).toString();

                table.addCell(ngay);
                table.addCell(soDon);
                table.addCell(tongSP);
                table.addCell(tongTien);
            }
            document.add(table);

            JOptionPane.showMessageDialog(this, "In thành công");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ViewThongKe.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(ViewThongKe.class.getName()).log(Level.SEVERE, null, ex);
        }
        document.close();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Thong_ke");
            XSSFRow row = null;
            Cell cell = null;
            row = sheet.createRow(0);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("Ngày");

            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Tổng số đơn");

            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Tổng sản phẩm");

            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Tổng tiền");

            for (int i = 0; i < listTK.size(); i++) {
                //                KhachHangModel model = listKH.get(i);
                Object[] model = listTK.get(i);
                row = sheet.createRow(1 + i);

                //                cell = row.createCell(0, CellType.NUMERIC);
                //                cell.setCellValue(i + 1);
                cell = row.createCell(0, CellType.STRING);
                cell.setCellValue(model[0].toString());

                cell = row.createCell(1, CellType.STRING);
                cell.setCellValue(model[1].toString());

                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue(model[2].toString());

                cell = row.createCell(3, CellType.STRING);
                cell.setCellValue(model[3].toString());

            }
            String path = "";
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int x = chooser.showSaveDialog(this);
            if (x == JFileChooser.APPROVE_OPTION) {
                path = chooser.getSelectedFile().getPath();
            }
            File f = new File(path + "\\ThongKe.xlsx");
            try {
                FileOutputStream fos = new FileOutputStream(f);
                workbook.write(fos);
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace(System.out);
                JOptionPane.showMessageDialog(null, e.getMessage());
            } catch (IOException ex) {
                ex.printStackTrace(System.out);
            }
            JOptionPane.showMessageDialog(this, "In thành công");
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
            JOptionPane.showMessageDialog(this, "Lỗi mở file");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        Date ngayBD = txtBatDau.getDate();
        Date ngayKT = txtKetThuc.getDate();

        txtTien0.setText(tongDoanhThuNgay(listHD, ngayBD, ngayKT) + " VNĐ");
        txtHDDB0.setText(soDonDaBanNgay(listHD, ngayBD, ngayKT) + "");
        txtSPDB0.setText(serviceImpl.soLuongNgay(ngayBD, ngayKT).get(0).toString());
        List<Object[]> listTKKHSearch = serviceImpl.getListTKKH(ngayBD, ngayKT);
        List<Object[]> listDT = serviceImpl.getListDT(ngayBD, ngayKT);
        List<Object[]> listTKSPNgay = serviceImpl.thongKeSPNgay(ngayBD, ngayKT);
        showDataKH(listTKKHSearch);
        showData(listDT);
        showDataSP(listTKSPNgay);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        int row = jTable2.getSelectedRow();
        Object listTest = listTK.get(row)[0];
        String ngayTT = listTest + "";
        Date date = null;
        try {
            date = sdf.parse(ngayTT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Object[]> list = serviceImpl.listThongKeCT(date);
        System.out.println(list.toString());
        showDataDTCT(list);
    }//GEN-LAST:event_jTable2MouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        showDataKH(listTKKH);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        showDataSP(listTKSP);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jTable5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable5MouseClicked

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton8ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewThongKe().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private com.toedter.calendar.JDateChooser txtBatDau;
    private javax.swing.JLabel txtHDDB0;
    private javax.swing.JLabel txtHDDB1;
    private javax.swing.JLabel txtHDDB2;
    private com.toedter.calendar.JDateChooser txtKetThuc;
    private javax.swing.JLabel txtSPDB0;
    private javax.swing.JLabel txtSPDB1;
    private javax.swing.JLabel txtSPDB2;
    private javax.swing.JLabel txtTien0;
    private javax.swing.JLabel txtTien1;
    private javax.swing.JLabel txtTien2;
    // End of variables declaration//GEN-END:variables
}
