/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import config.HibernatUtil;
import domainmodel.HDCT;
import domainmodel.HoaDon;
import domainmodel.SanPham;
import java.util.List;
import java.util.UUID;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author trong
 */
public class HDCTRepository {

    Session session = HibernatUtil.getFACTORY().openSession();
    private String fromTable = "FROM HDCT";

    public List<HDCT> getAll() {
        Query query = session.createQuery(fromTable, HDCT.class);
        List<HDCT> lists = query.getResultList();
        return lists;
    }

    public HDCT getOne(UUID id) {
        String sql = fromTable + " WHERE IDHDCT = :id ";
        Query query = session.createQuery(sql, HDCT.class);
        query.setParameter("id", id);
        HDCT hdct = (HDCT) query.getSingleResult();
        return hdct;
    }
    public HDCT getOne(UUID idHD, UUID idSP) {
        String sql = fromTable + " WHERE IDHD = :idh ,IDHD = :ids ";
        Query query = session.createQuery(sql, HDCT.class);
        query.setParameter("idh", idHD);
        query.setParameter("ids", idSP);
        HDCT hdct = (HDCT) query.getSingleResult();
        return hdct;
    }
//    public HDCT getOne(UUID idHD) {
//        String sql = fromTable + " WHERE IDHDCT = :id ";
//        Query query = session.createQuery(sql, HDCT.class);
//        query.setParameter("id", id);
//        HDCT hdct = (HDCT) query.getSingleResult();
//        return hdct;
//    }

    public boolean Add(HDCT hdct) {
        Transaction transaction = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.save(hdct);
            transaction.commit();
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    public boolean Update(UUID id, HDCT hdct) {
        Transaction transaction = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(hdct);
            transaction.commit();
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    public boolean Delete(UUID id) {
        Transaction transaction = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.delete(id);
            transaction.commit();
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    public static void main(String[] args) {
       new HoaDonRepository().getAll().forEach(s -> System.out.println(s.toString()));
        
    }
}
