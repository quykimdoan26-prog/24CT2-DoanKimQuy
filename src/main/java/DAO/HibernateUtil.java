package DAO; // Hoặc package UTIL;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    // 1. Biến SessionFactory khai báo static private (chỉ tạo 1 lần)
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Tự động đọc file hibernate.cfg.xml ở thư mục src/main/resources
            return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Lỗi khởi tạo SessionFactory: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    // 2. Phương thức static public để các lớp DAO gọi lấy SessionFactory
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    // 3. Đóng SessionFactory khi tắt ứng dụng (nếu cần)
    public static void shutdown() {
        getSessionFactory().close();
    }
}