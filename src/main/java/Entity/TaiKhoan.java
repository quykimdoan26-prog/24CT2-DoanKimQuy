package Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Account")
public class TaiKhoan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AccountID")
    private Integer maTaiKhoan;

    @Column(name = "AccountName", nullable = false)
    private String tenTaiKhoan;

    @Column(name = "Role", nullable = false)
    private String vaiTro;

    // Quan hệ 1-1 hoặc 1-N với KhachHang
    @OneToMany(mappedBy = "taiKhoan", cascade = CascadeType.ALL)
    private List<KhachHang> danhSachKhachHang;

    public TaiKhoan() {}

    public TaiKhoan(Integer maTaiKhoan, String tenTaiKhoan, String vaiTro) {
        this.maTaiKhoan = maTaiKhoan;
        this.tenTaiKhoan = tenTaiKhoan;
        this.vaiTro = vaiTro;
    }

    // Getters và Setters
    public Integer getMaTaiKhoan() { return maTaiKhoan; }
    public void setMaTaiKhoan(Integer maTaiKhoan) { this.maTaiKhoan = maTaiKhoan; }

    public String getTenTaiKhoan() { return tenTaiKhoan; }
    public void setTenTaiKhoan(String tenTaiKhoan) { this.tenTaiKhoan = tenTaiKhoan; }

    public String getVaiTro() { return vaiTro; }
    public void setVaiTro(String vaiTro) { this.vaiTro = vaiTro; }

    public List<KhachHang> getDanhSachKhachHang() { return danhSachKhachHang; }
    public void setDanhSachKhachHang(List<KhachHang> danhSachKhachHang) { this.danhSachKhachHang = danhSachKhachHang; }
}
