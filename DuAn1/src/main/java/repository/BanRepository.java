/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import config.HibernatUtil;
import java.util.List;
import javax.persistence.Query;
import domainmodel.Ban;
import java.util.UUID;
import org.hibernate.Session;

/**
 *
 * @author vutuo
 */
public class BanRepository {

    private static Session session = HibernatUtil.getFACTORY().openSession();
    private String fromTable = "FROM Ban";

    public List<Ban> getAll() {
//        Query query = session.createQuery(fromTable, Ban.class);
//        List<Ban> list = query.getResultList();
//        return list;
        try ( Session session = HibernatUtil.getFACTORY().openSession();) {
            Query q = session.createQuery("FROM Ban");
            List<Ban> list = q.getResultList();
            return list;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return null;
        }
    }
    
    public static void main(String[] args) {
        System.out.println(new BanRepository().getOne(1).toString());
    }
    public List<Object[]> getTotolCustomer(int idTable) {
        Query q = session.createQuery("SELECT COUNT(DISTINCT H.IDKH) FROM HoaDon H JOIN Ban B ON H.IDB = B.IDB WHERE H.IDB = " + idTable);
        List<Object[]> countCus = q.getResultList();
        return countCus;

    }



    public Ban getOne(int IDB) {
        return session.get(Ban.class, IDB);
    }

    public Boolean add(Ban ban) {
        try ( Session session = HibernatUtil.getFACTORY().openSession();) {
            session.getTransaction().begin();
            session.save(ban);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return false;
        }
    }

    public Boolean update(Ban ban) {
        try ( Session session = HibernatUtil.getFACTORY().openSession();) {
            session.getTransaction().begin();
            session.saveOrUpdate(ban);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return false;
        }
    }

    public Boolean updateTable(Ban ban, int IDB) {

        try {
            Ban newsUpdate = session.get(Ban.class, IDB);
            newsUpdate.setTrangThaiBan(ban.getTrangThaiBan());
            session.getTransaction().begin();
            session.save(newsUpdate);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
        }
        return null;
    }

    public Boolean delete(Ban ban) {
        try ( Session session = HibernatUtil.getFACTORY().openSession();) {
            session.getTransaction().begin();
            session.delete(ban);
            session.getTransaction().commit();

            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return false;
        }
    }

//    public static void main(String[] args) {
//        BanRepository repository = new BanRepository();
//        List<Ban> list = repository.getAll();
//        for (Ban ban : list) {
//            System.out.println(ban);
//        }
//        System.out.println(repository.getOne(1));
//    }
//        private static final Logger logger = Logger.getLogger(CategoryRepository.class);
//
//    private Session session = HibernatUtil.getFACTORY().openSession();
//
//    private String fromTable = "FROM Category "; // HQL
//
//    public List<Category> getAll() {
//        Query query = session.createQuery(fromTable, Category.class);
//        List<Category> categorys = query.getResultList();
//        if (categorys.isEmpty()) {
//            logger.debug("List category empty ");
//        }
//        return categorys == null ? new ArrayList<>() : categorys;
//    }
//
//    public Category getOne(Long id) {
//        String sql = fromTable + " WHERE id = :id";
//        Query query = session.createQuery(sql, Category.class);
//        query.setParameter("id", id);
//        Category category = (Category) query.getSingleResult();
//        if (ObjectUtils.isEmpty(category)) {
//            logger.debug("Category null ");
//        }
//        return category == null ? null : category;
//    }
//
//    public Boolean add(Category category) {
//        Transaction transaction = null;
//        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
//            transaction = session.beginTransaction();
//            session.save(category);
//            transaction.commit();
//            return true;
//        } catch (Exception e) {
//        }
//        return null;
//    }
//
//    public Boolean update(Category category, Long id) {
//        Transaction transaction = null;
//        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
//            transaction = session.beginTransaction();
//            session.saveOrUpdate(category);
//            transaction.commit();
//            return true;
//        } catch (Exception e) {
//        }
//        return null;
//    }
//
//    public Boolean delete(Category category) {
//        Transaction transaction = null;
//        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
//            transaction = session.beginTransaction();
//            //            Query query = session.createQuery("update LopHoc set tenLop = :tenLop"
////                    + " where maLop = :maLop");
////            query.setParameter("tenLop", "Lop update123");
////            query.setParameter("maLop", 113);
////            query.executeUpdate();
//            session.delete(category);
//            transaction.commit();
//            return true;
//        } catch (Exception e) {
//        }
//        return null;
//    }
////
////    public static void main(String[] args) {
////        new CategoryRepository().getAll().forEach(s -> System.out.println(s.toString()));
////        System.out.println(new CategoryRepository().getOne(1L).toString());
////    }
//}
}
