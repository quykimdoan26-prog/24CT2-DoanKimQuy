package Entity;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CarProducer")
public class HangXe {

    @Id
    @Column(name = "ProducerID")
    private Integer maHangXe;

    @Column(name = "ProducerName", nullable = false)
    private String tenHangXe;

    @Column(name = "Address", nullable = false)
    private String diaChi;

    @Column(name = "Country", nullable = false)
    private String quocGia;

    // Một hãng xe có nhiều xe
    @OneToMany(mappedBy = "hangXe", cascade = CascadeType.ALL)
    private List<Xe> danhSachXe;

    public HangXe() {}

    public Integer getMaHangXe() { return maHangXe; }
    public void setMaHangXe(Integer maHangXe) { this.maHangXe = maHangXe; }

    public String getTenHangXe() { return tenHangXe; }
    public void setTenHangXe(String tenHangXe) { this.tenHangXe = tenHangXe; }

    public String getDiaChi() { return diaChi; }
    public void setDiaChi(String diaChi) { this.diaChi = diaChi; }

    public String getQuocGia() { return quocGia; }
    public void setQuocGia(String quocGia) { this.quocGia = quocGia; }

    public List<Xe> getDanhSachXe() { return danhSachXe; }
    public void setDanhSachXe(List<Xe> danhSachXe) { this.danhSachXe = danhSachXe; }
}
