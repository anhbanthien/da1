/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainmodel;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import viewmodel.NhanVienModel;

@Entity
@Table(name = "NhanVien")
public class NhanVien {

    @Id
    @Column(columnDefinition = "uniqueidentifier")
    @GeneratedValue
    private UUID IdNhanVien;
    private String HoTen;
    private String Email;
    private String SDT;
    private String GioiTinh;
    private String TrangThai;
    private String Image;
    private String MaNhanVien;

    public NhanVien() {
    }

    public NhanVien(String HoTen, String Email, String SDT, String GioiTinh, String TrangThai, String Image, String MaNhanVien) {

        this.HoTen = HoTen;
        this.Email = Email;
        this.SDT = SDT;
        this.GioiTinh = GioiTinh;
        this.TrangThai = TrangThai;
        this.Image = Image;
        this.MaNhanVien = MaNhanVien;
    }

    public NhanVien(NhanVienModel nv) {

        this.HoTen = nv.getHoTen();
        this.Email = nv.getEmail();
        this.SDT = nv.getSDT();
        this.GioiTinh = nv.getGioiTinh();
        this.TrangThai = nv.getTrangThai();
        this.IdNhanVien = nv.getIdNhanVien();
        this.Image = nv.getImage();

    }

    public UUID getIdNhanVien() {
        return IdNhanVien;
    }

    public void setIdNhanVien(UUID IdNhanVien) {
        this.IdNhanVien = IdNhanVien;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }

    @Override
    public String toString() {
        return "NhanVien{" + "IdNhanVien=" + IdNhanVien + ", HoTen=" + HoTen + ", Email=" + Email + ", SDT=" + SDT + ", GioiTinh=" + GioiTinh + ", TrangThai=" + TrangThai + ", Image=" + Image + '}';
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public String getMaNhanVien() {
        return MaNhanVien;
    }

    public void setMaNhanVien(String MaNhanVien) {
        this.MaNhanVien = MaNhanVien;
    }

}
