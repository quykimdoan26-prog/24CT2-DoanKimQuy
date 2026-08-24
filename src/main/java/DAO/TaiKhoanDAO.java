package DAO;

import org.hibernate.Session;

import Entity.KhachHang;
import Entity.TaiKhoan;
public class TaiKhoanDAO extends GenericDAO<TaiKhoan, Integer> {
    public TaiKhoanDAO() {
        super(Entity.TaiKhoan.class);
    }
    // Hàm truy vấn tài khoản
    public KhachHang checkLogin(String txtten, String txtmk) {
        KhachHang account = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Tìm tài khoản khớp CẢ tên đăng nhập VÀ mật khẩu
            String hql = "FROM KhachHang a WHERE a.tenKhachHang = :accName AND a.matKhau = :accPass";
            
            account = session.createQuery(hql, KhachHang.class)
                             .setParameter("accName", txtten)
                             .setParameter("accPass", txtmk)
                             .uniqueResult(); // uniqueResult() trả về 1 đối tượng duy nhất hoặc null       
        } catch (Exception e) {
            System.out.println("Lỗi đăng nhập: " + e.getMessage());
        } 
        return account; // Nếu khớp sẽ trả về đối tượng Account, không khớp trả về null
    }

}
