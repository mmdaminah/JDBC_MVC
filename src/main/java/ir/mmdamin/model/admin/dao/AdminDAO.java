package ir.mmdamin.model.admin.dao;

import java.util.Optional;
import java.util.List;
import ir.mmdamin.model.admin.domain.Admin;

public interface AdminDAO {
    Optional<Admin> get(Integer id);
    List<Admin> getAll();
    void update(Admin admin);
    boolean delete(Integer id);
    boolean create(Admin admin);
}
