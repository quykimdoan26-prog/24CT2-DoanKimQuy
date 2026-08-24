package Entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CarRental")
@IdClass(ThueXeId.class)
public class ThueXe implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "CustomerID", nullable = false)
    private KhachHang khachHang;

    @Id
    @ManyToOne
    @JoinColumn(name = "CarID", nullable = false)
    private Xe xe;

    @Column(name = "PickupDate", nullable = false)
    private LocalDate ngayNhanXe;

    @Column(name = "ReturnDate", nullable = false)
    private LocalDate ngayTraXe;

    @Column(name = "RentPrice", nullable = false)
    private Double giaThue;

    @Column(name = "Status", nullable = false)
    private String trangThai;

    public ThueXe() {}

    public KhachHang getKhachHang() { return khachHang; }
    public void setKhachHang(KhachHang khachHang) { this.khachHang = khachHang; }

    public Xe getXe() { return xe; }
    public void setXe(Xe xe) { this.xe = xe; }

    public LocalDate getNgayNhanXe() { return ngayNhanXe; }
    public void setNgayNhanXe(LocalDate ngayNhanXe) { this.ngayNhanXe = ngayNhanXe; }

    public LocalDate getNgayTraXe() { return ngayTraXe; }
    public void setNgayTraXe(LocalDate ngayTraXe) { this.ngayTraXe = ngayTraXe; }

    public Double getGiaThue() { return giaThue; }
    public void setGiaThue(Double giaThue) { this.giaThue = giaThue; }

    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }
}
