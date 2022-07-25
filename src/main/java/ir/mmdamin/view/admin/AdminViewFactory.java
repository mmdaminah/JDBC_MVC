package ir.mmdamin.view.admin;

import ir.mmdamin.controller.admin.AdminImp;
import ir.mmdamin.model.admin.dao.AdminJDBCDao;
import ir.mmdamin.model.datasource.DataSource;
import ir.mmdamin.view.IndexView;

public class AdminViewFactory {
    public static IndexView create(){
        var dataSource = new DataSource();
        var adminDao = new AdminJDBCDao(dataSource);
        var adminController = new AdminImp(adminDao);
        var adminFinder = new AdminView(adminController);
        return new IndexView(adminFinder);
    }
}
