/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import model.HoaDonChiTiet;
import model.SanPham;
import repository.HDCTRepository;
import repository.SanPhamRepository;
import viewsmodel.QLHDCT;
import viewsmodel.QLSanPham;

/**
 *
 * @author trong
 */
public class ManageHDCTService implements IManageHDCTService {

    HDCTRepository hDCTRepository = new HDCTRepository();
    SanPhamRepository sanPhamRepository = new SanPhamRepository();

    //HoaDonRepository hoaDonRepository = new HoaDonRepository();
    @Override
    public List<QLHDCT> getAll() {
        List<HoaDonChiTiet> lstHDCT = hDCTRepository.getAll();
        List<QLHDCT> lstQLHDCT = new ArrayList<>();
        for (HoaDonChiTiet x : lstHDCT) {
            QLHDCT qlhdct = new QLHDCT();
            qlhdct.setIdHDCT(x.getIdHDCT());
           // qlhdct.setIdHD(x.getHoaDon().getIdHD);
            qlhdct.setIdSP(x.getSanPham().getIdSP());
            qlhdct.setSoLuong(x.getSoLuong());
            qlhdct.setSoBanCT(x.getSoBanCT());
            lstQLHDCT.add(qlhdct);
        }
        return lstQLHDCT;
    }

    @Override
    public QLHDCT getOne(UUID id) {
        HoaDonChiTiet hdct = hDCTRepository.getOne(id);
        QLHDCT qlhdct = new QLHDCT();
        qlhdct.setIdHDCT(hdct.getIdHDCT());
      //  qlhdct.setIdHD(hdct.getHoaDon().getIdHD);
        qlhdct.setIdSP(hdct.getSanPham().getIdSP());
        qlhdct.setSoLuong(hdct.getSoLuong());
        qlhdct.setSoBanCT(hdct.getSoBanCT());
        return qlhdct;
    }

    @Override
    public String add(QLHDCT qlhdct) {
        HoaDonChiTiet hdct = new HoaDonChiTiet();
        hdct.setIdHDCT(qlhdct.getIdHD());
        //hdct.setHoaDon(hoaDonRepository.getOne(qlhdct.getIdHD()));
        hdct.setSanPham(sanPhamRepository.getOne(qlhdct.getIdSP()));
        hdct.setSoLuong(qlhdct.getSoLuong());
        hdct.setSoBanCT(qlhdct.getSoBanCT());

        return hDCTRepository.Add(hdct) ? "Thêm thành công" : "Thêm thất bại";
    }

    @Override
    public String update(UUID id, QLHDCT qlhdct) {
        HoaDonChiTiet hdct = new HoaDonChiTiet();
        hdct.setIdHDCT(qlhdct.getIdHD());
        //hdct.setHoaDon(hoaDonRepository.getOne(qlhdct.getIdHD()));
        hdct.setSanPham(sanPhamRepository.getOne(qlhdct.getIdSP()));
        hdct.setSoLuong(qlhdct.getSoLuong());
        hdct.setSoBanCT(qlhdct.getSoBanCT());
        
        return hDCTRepository.Update(id, hdct)?"Sửa thành công": "Sửa thất bại";
    }

    @Override
    public String delete(UUID id) {
        return hDCTRepository.Delete(id)?"Xóa thành công":"Xóa thất bại";
    }

}
