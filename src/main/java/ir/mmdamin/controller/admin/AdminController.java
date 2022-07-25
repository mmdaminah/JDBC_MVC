package ir.mmdamin.controller.admin;

import ir.mmdamin.model.admin.domain.Admin;

import java.util.List;

public interface AdminController {
    public Admin findAdmin(Integer id);
    public List<Admin> getAllAdmins();
    public boolean deleteAdmin(Integer id);
    public boolean createAdmin(Admin admin);
    public void updateAdmin(Admin admin);
}
