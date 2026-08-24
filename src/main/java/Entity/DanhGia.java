package Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Review")
@IdClass(DanhGia.class)
public class DanhGia implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "CustomerID", nullable = false)
    private KhachHang khachHang;

    @Id
    @ManyToOne
    @JoinColumn(name = "CarID", nullable = false)
    private Xe xe;

    @Column(name = "ReviewStar", nullable = false)
    private Integer soSaoDanhGia;

    @Column(name = "Comment", nullable = false)
    private String bìnhLuan;

    public DanhGia() {}

    public KhachHang getKhachHang() { return khachHang; }
    public void setKhachHang(KhachHang khachHang) { this.khachHang = khachHang; }

    public Xe getXe() { return xe; }
    public void setXe(Xe xe) { this.xe = xe; }

    public Integer getSoSaoDanhGia() { return soSaoDanhGia; }
    public void setSoSaoDanhGia(Integer soSaoDanhGia) { this.soSaoDanhGia = soSaoDanhGia; }

    public String getBinhLuan() { return bìnhLuan; }
    public void setBinhLuan(String bìnhLuan) { this.bìnhLuan = bìnhLuan; }
}
