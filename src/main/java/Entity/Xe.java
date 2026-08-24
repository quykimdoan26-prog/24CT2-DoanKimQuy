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
@Table(name = "Car")
public class Xe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CarID")
    private Integer maXe;

    @Column(name = "CarName", nullable = false)
    private String tenXe;

    @Column(name = "CarModelYear", nullable = false)
    private Integer namSanXuat;

    @Column(name = "Color", nullable = false)
    private String mauSac;

    @Column(name = "Capacity", nullable = false)
    private Integer soChoNgoi;

    @Column(name = "Description", nullable = false)
    private String moTa;

    @Column(name = "ImportDate", nullable = false)
    private LocalDate ngayNhap;

    @Column(name = "RentPrice", nullable = false)
    private Double giaThue;

    @Column(name = "Status", nullable = false)
    private String trangThai;

    // Khóa ngoại liên kết tới HangXe (ProducerID)
    @ManyToOne
    @JoinColumn(name = "ProducerID", nullable = false)
    private HangXe hangXe;

    @OneToMany(mappedBy = "xe")
    private List<ThueXe> danhSachThueXe;

    @OneToMany(mappedBy = "xe")
    private List<DanhGia> danhSachDanhGia;

    public Xe() {}

    // Getters & Setters
    public Integer getMaXe() { return maXe; }
    public void setMaXe(Integer maXe) { this.maXe = maXe; }

    public String getTenXe() { return tenXe; }
    public void setTenXe(String tenXe) { this.tenXe = tenXe; }

    public Integer getNamSanXuat() { return namSanXuat; }
    public void setNamSanXuat(Integer namSanXuat) { this.namSanXuat = namSanXuat; }

    public String getMauSac() { return mauSac; }
    public void setMauSac(String mauSac) { this.mauSac = mauSac; }

    public Integer getSoChoNgoi() { return soChoNgoi; }
    public void setSoChoNgoi(Integer soChoNgoi) { this.soChoNgoi = soChoNgoi; }

    public String getMoTa() { return moTa; }
    public void setMoTa(String moTa) { this.moTa = moTa; }

    public LocalDate getNgayNhap() { return ngayNhap; }
    public void setNgayNhap(LocalDate ngayNhap) { this.ngayNhap = ngayNhap; }

    public Double getGiaThue() { return giaThue; }
    public void setGiaThue(Double giaThue) { this.giaThue = giaThue; }

    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }

    public HangXe getHangXe() { return hangXe; }
    public void setHangXe(HangXe hangXe) { this.hangXe = hangXe; }

    public List<ThueXe> getDanhSachThueXe() { return danhSachThueXe; }
    public void setDanhSachThueXe(List<ThueXe> danhSachThueXe) { this.danhSachThueXe = danhSachThueXe; }

    public List<DanhGia> getDanhSachDanhGia() { return danhSachDanhGia; }
    public void setDanhSachDanhGia(List<DanhGia> danhSachDanhGia) { this.danhSachDanhGia = danhSachDanhGia; }
}
