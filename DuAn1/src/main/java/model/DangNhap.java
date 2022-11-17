/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "DangNhap")
public class DangNhap {

    @Id
    private String TenTaiKhoan;
    private String MatKhau;
    private String Quyen;
    @ManyToOne
    @JoinColumn(name = "IdNhanVien")
    private NhanVien IdNhanVien;

}