package ir.mmdamin.view;

import ir.mmdamin.view.admin.AdminView;

import java.util.Scanner;

public class IndexView {
    public IndexView(AdminView adminView) {
        this.adminView = adminView;
    }

    public AdminView adminView;

    public void mainMenu(){
        var terminateApp = false;
        System.out.println("""
                1. Find Admin By ID
                2. Get All Admins
                3. Delete Admin By ID
                4. Create Admin
                5. Update Admin
                """);
        var action = new Scanner(System.in).nextInt();
        switch(action){
            case 1 -> adminView.show();
            case 2 -> adminView.showAll();
            case 3 -> adminView.deleteAdmin();
            case 4 -> adminView.createAdmin();
            case 5 -> adminView.updateAdmin();
        }
    }
}
