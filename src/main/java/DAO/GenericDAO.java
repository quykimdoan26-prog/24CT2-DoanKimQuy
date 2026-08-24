package DAO;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class GenericDAO<T, ID extends Serializable> {
    private final Class<T> entityType;

    public GenericDAO(Class<T> entityType) {
        this.entityType = entityType;
    }

    // 1. LƯU MỚI (SAVE)
    public void save(T entity) throws Exception {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("❌ Lỗi khi save " + entityType.getSimpleName() + ":");
            e.printStackTrace();
            throw new Exception("Lỗi truy vấn CSDL: " + e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    // 2. CẬP NHẬT (UPDATE)
    public void update(T entity) throws Exception {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("Lỗi khi update " + entityType.getSimpleName() + ":");
            e.printStackTrace();
            throw new Exception("Lỗi truy vấn CSDL: " + e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    // 3. XÓA (DELETE)
    public void delete(T entity) throws Exception {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("Lỗi khi delete " + entityType.getSimpleName() + ":");
            e.printStackTrace();
            throw new Exception("Lỗi truy vấn CSDL: " + e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    // 4. TÌM THEO ID (FIND BY ID)
    public T findById(ID id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(entityType, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 5. LẤY TẤT CẢ DANH SÁCH (FIND ALL) - CHUẨN GENERIC
    public List<T> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM " + entityType.getSimpleName();
            return session.createQuery(hql, entityType).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList(); // Tránh trả về null gây crash TableView
        }
    }
}