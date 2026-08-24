package DAO;

import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import Entity.ThueXe;
import Entity.ThueXeId;

public class ThueXeDAO extends GenericDAO<ThueXe, ThueXeId> {
    public ThueXeDAO() {
        super(ThueXe.class);
    }
    /**
     * Lấy danh sách lịch sử thuê xe theo CustomerID (Dành cho chức năng View transaction history của Customer)
     */
    public List<ThueXe> findByCustomerIdList(int customerId) throws Exception{
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Lưu ý: Nếu trong entity CarRental bạn ánh xạ quan hệ là Customer customer -> dùng r.customer.customerID
            // Nếu bạn ánh xạ khóa là kiểu Integer customerID thông thường (hoặc EmbeddedId) -> dùng r.customerID
            String hql = "FROM ThueXe tx WHERE tx.khachHang.maKhachHang = :cId ORDER BY tx.ngayNhanXe DESC";
            return session.createQuery(hql, ThueXe.class)
                          .setParameter("cId", customerId)
                          .getResultList();
        } catch (Exception e) {
        		System.out.println("có lỗi truy vấn lịch sử:"+ e);
            return Collections.emptyList(); // Tránh trả về null gây crash TableView
        }
    }
}