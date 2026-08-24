package Entity;

import java.io.Serializable;
import java.util.Objects;

public class DanhGiaId implements Serializable {
    private Integer khachHang; // hoặc maKhachHang (phải khớp tên thuộc tính @Id trong DanhGia)
    private Integer xe;        // hoặc maXe

    public DanhGiaId() {}

    public DanhGiaId(Integer khachHang, Integer xe) {
        this.khachHang = khachHang;
        this.xe = xe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DanhGiaId that = (DanhGiaId) o;
        return Objects.equals(khachHang, that.khachHang) && Objects.equals(xe, that.xe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(khachHang, xe);
    }
}