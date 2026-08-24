package Entity;
	import java.time.LocalDate;
	import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
	@Entity
	@Table(name = "Customer")
	public class KhachHang {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "CustomerID")
	    private Integer maKhachHang;
	    
	    @Column(name = "CustomerName", nullable = false)
	    private String tenKhachHang;

	    @Column(name = "Mobile")
	    private String soDienThoai;

	    @Column(name = "Birthday")
	    private LocalDate ngaySinh;

	    @Column(name = "IdentityCard")
	    private String cccd;

	    @Column(name = "LicenceNumber", nullable = false)
	    private String soGiayPhepLaixe;

	    @Column(name = "LicenceDate")
	    private LocalDate ngayCapGiayPhep;

	    @Column(name = "Email")
	    private String email;

	    @Column(name = "Password", nullable = false)
	    private String matKhau;

	    // Khóa ngoại liên kết tới TaiKhoan (AccountID)
	    @ManyToOne
	    @JoinColumn(name = "AccountID", nullable = false)
	    private TaiKhoan taiKhoan;

	    @OneToMany(mappedBy = "khachHang")
	    private List<ThueXe> danhSachThueXe;

	    @OneToMany(mappedBy = "khachHang")
	    private List<DanhGia> danhSachDanhGia;

	    public KhachHang() {}

	    // Getters & Setters
	    public Integer getMaKhachHang() { return maKhachHang; }
	    public void setMaKhachHang(Integer maKhachHang) { this.maKhachHang = maKhachHang; }

	    public String getTenKhachHang() { return tenKhachHang; }
	    public void setTenKhachHang(String tenKhachHang) { this.tenKhachHang = tenKhachHang; }

	    public String getSoDienThoai() { return soDienThoai; }
	    public void setSoDienThoai(String soDienThoai) { this.soDienThoai = soDienThoai; }

	    public LocalDate getNgaySinh() { return ngaySinh; }
	    public void setNgaySinh(LocalDate ngaySinh) { this.ngaySinh = ngaySinh; }

	    public String getCccd() { return cccd; }
	    public void setCccd(String cccd) { this.cccd = cccd; }

	    public String getSoGiaiPhepLaixe() { return soGiayPhepLaixe; }
	    public void setSoGiaiPhepLaixe(String soGiaiPhepLaixe) { this.soGiayPhepLaixe = soGiaiPhepLaixe; }

	    public LocalDate getNgayCapGiayPhep() { return ngayCapGiayPhep; }
	    public void setNgayCapGiayPhep(LocalDate ngayCapGiayPhep) { this.ngayCapGiayPhep = ngayCapGiayPhep; }

	    public String getEmail() { return email; }
	    public void setEmail(String email) { this.email = email; }

	    public String getMatKhau() { return matKhau; }
	    public void setMatKhau(String matKhau) { this.matKhau = matKhau; }

	    public TaiKhoan getTaiKhoan() { return taiKhoan; }
	    public void setTaiKhoan(TaiKhoan taiKhoan) { this.taiKhoan = taiKhoan; }

	    public List<ThueXe> getDanhSachThueXe() { return danhSachThueXe; }
	    public void setDanhSachThueXe(List<ThueXe> danhSachThueXe) { this.danhSachThueXe = danhSachThueXe; }

	    public List<DanhGia> getDanhSachDanhGia() { return danhSachDanhGia; }
	    public void setDanhSachDanhGia(List<DanhGia> danhSachDanhGia) { this.danhSachDanhGia = danhSachDanhGia; }
	}

