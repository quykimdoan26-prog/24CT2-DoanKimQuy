package DAO;

import Entity.DanhGia;
import Entity.DanhGiaId;

public class DanhGiaDAO extends GenericDAO<DanhGia, DanhGiaId> {
    public DanhGiaDAO() {
        super(DanhGia.class);
    }
}