/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import config.HibernatUtil;
import domainmodel.HDCT;
import domainmodel.KhuyenMai;
import java.util.UUID;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author vanlo
 */
public class BanHangRepository {

    Session session = HibernatUtil.getFACTORY().openSession();

    private String fromTableHDCT = "FROM HDCT h";
    private String fromTableKM = "From KhuyenMai";

    public boolean Update(HDCT hdct, UUID id) {
        Transaction transaction = null;

        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            HDCT st = session.get(HDCT.class, id);
            st.setSoLuong(hdct.getSoLuong());
            st.setIDHD(hdct.getIDHD());
            st.setIDSP(hdct.getIDSP());
            session.getTransaction().begin();
            session.save(st);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return false;
    }

  public HDCT getOne(UUID idHD, UUID idSP) {
        String sql = fromTableHDCT + " WHERE h.IDHD = :hd AND h.IDSP = :sp ";
        try {
           Session session = HibernatUtil.getFACTORY().openSession();
        Query query = session.createQuery(sql, HDCT.class);
        query.setParameter("hd", new HoaDonRepository().getOne(idHD));
        query.setParameter("sp", new SanPhamRepository().getOne(idSP));
        HDCT hdct = (HDCT) query.getSingleResult();
        return hdct;
       } catch (Exception e) {
           return null;

       }}

  
        
        
    
   public boolean Delete(UUID hdct) {

        try {
            Session session = HibernatUtil.getFACTORY().openSession();
            session.getTransaction().begin();
            session.delete(session.get(HDCT.class, hdct));
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return false;
    }
   public KhuyenMai getByMaKM(String MaKM) {
        String sql = fromTableKM + " WHERE MaKM = : id";
        Query query = session.createQuery(sql, KhuyenMai.class);
        query.setParameter("id", MaKM);
        KhuyenMai khachHang = (KhuyenMai) query.getSingleResult();
        return khachHang;
    }

    public static void main(String[] args) {


    }
}
