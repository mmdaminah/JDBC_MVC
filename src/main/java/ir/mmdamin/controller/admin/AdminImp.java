package ir.mmdamin.controller.admin;

import ir.mmdamin.exception.NotFoundAdminException;
import ir.mmdamin.model.admin.dao.AdminDAO;
import ir.mmdamin.model.admin.domain.Admin;

import java.util.List;

public class AdminImp implements AdminController {
    private AdminDAO adminDao;

    public AdminImp(AdminDAO adminDao) {
        this.adminDao = adminDao;
    }

    public Admin findAdmin(Integer id){
        return adminDao.get(id).orElseThrow(()->new NotFoundAdminException("Admin not found!"));
    }

    @Override
    public List<Admin> getAllAdmins() {
        return adminDao.getAll();
    }

    public boolean deleteAdmin(Integer id){
        return adminDao.delete(id);
    }
    public boolean createAdmin(Admin admin){
        return adminDao.create(admin);
    }
    public void updateAdmin(Admin admin){
        adminDao.update(admin);
    }
}
