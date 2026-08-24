package Entity;

import java.io.Serializable;
import java.util.Objects;

public class ThueXeId implements Serializable {
    private Integer khachHang;
    private Integer xe;

    public ThueXeId() {}

    public ThueXeId(Integer khachHang, Integer xe) {
        this.khachHang = khachHang;
        this.xe = xe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ThueXeId thueXeId = (ThueXeId) o;
        return Objects.equals(khachHang, thueXeId.khachHang) && Objects.equals(xe, thueXeId.xe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(khachHang, xe);
    }
}
