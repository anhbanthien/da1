/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import config.HibernatUtil;
import domainmodel.KhuyenMai;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author hungn
 */
public class KhuyenMaiRepository {

    private String fromTable = "From KhuyenMai";

    private Session session = HibernatUtil.getFACTORY().openSession();

    public List<KhuyenMai> getALl() {
        Query query = session.createQuery(fromTable, KhuyenMai.class);
        List<KhuyenMai> listkm = query.getResultList();
        return listkm;
    }

    public boolean add(KhuyenMai khuyenmai) {
        Transaction transaction = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.save(khuyenmai);
            transaction.commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(KhuyenMai khuyenmai) {
        Transaction transaction = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.update(khuyenmai);
            transaction.commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean delete(KhuyenMai khuyenmai) {
        Transaction transaction = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.delete(khuyenmai);
            transaction.commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void main(String[] args) {
        List<KhuyenMai> list = new KhuyenMaiRepository().getALl();
        for (KhuyenMai km : list) {
            System.out.println(km.toString());
        }
    }
}
