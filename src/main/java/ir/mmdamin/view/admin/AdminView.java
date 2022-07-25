package ir.mmdamin.view.admin;

import ir.mmdamin.controller.admin.AdminController;
import ir.mmdamin.exception.NotFoundAdminException;
import ir.mmdamin.model.admin.domain.Admin;

import java.util.Scanner;

public class AdminView {
    public AdminView(AdminController adminController) {
        this.adminController = adminController;
    }

    private AdminController adminController;
    public void show(){
        System.out.println("Please Enter Admin ID : ");
        var adminId = new Scanner(System.in).nextInt();
        try {
            var foundAdmin = adminController.findAdmin(adminId);
            showAdminInformation(foundAdmin);
        }
        catch (NotFoundAdminException e){
            showError(e.getMessage());
        }
    }
    public void showAll(){
        var admins = adminController.getAllAdmins();
        admins.forEach(this::showAdminInformation);
    }
    public void deleteAdmin(){
        System.out.println("Please Enter Admin ID You Want To Delete : ");
        var adminId = new Scanner(System.in).nextInt();
        try {
            var result = adminController.deleteAdmin(adminId);
            System.out.println(result);
        }
        catch (NotFoundAdminException e){
            showError(e.getMessage());
        }
    }
    public void createAdmin(){
        var reader = new Scanner(System.in);
        System.out.println("Please insert first name : ");
        var firstName = reader.next();
        System.out.println("Please insert last name : ");
        var lastName = reader.next();
        System.out.println("Please insert username : ");
        var username = reader.next();
        System.out.println("Please insert password : ");
        var password = reader.next();
        System.out.println("Please insert national code : ");
        var nationalCode = reader.next();
        System.out.println("Please insert address");
        var address = reader.next();
        try{
            var result = adminController.createAdmin(
                    new Admin(
                            0,
                            firstName,lastName,
                            username, password,
                            nationalCode, address
                    )
            );
            System.out.println(result);
        }
       catch (RuntimeException e){
            showError(e.getMessage());
       }
    }
    public void updateAdmin(){
        var reader = new Scanner(System.in);
        System.out.println("Please insert admin id you want to update : ");
        var id = reader.nextInt();
        try{
            var foundAdmin = adminController.findAdmin(id);
            System.out.printf("first name : (%s) %n new first name : %n",foundAdmin.getFirstName());
            var firstName = reader.next();
            System.out.printf("last name : (%s) %n new last name : %n",foundAdmin.getLastName());
            var lastName = reader.next();
            System.out.printf("username : (%s) %n new username : %n",foundAdmin.getUsername());
            var username = reader.next();
            System.out.printf("password : (%s) %n new password : %n",foundAdmin.getPassword());
            var password = reader.next();
            System.out.printf("national code : (%s) %n new national code : %n",foundAdmin.getNationalCode());
            var nationalCode = reader.next();
            System.out.printf("address : (%s) %n new address : %n",foundAdmin.getAddress());
            var address = reader.next();

            adminController.updateAdmin(
                    new Admin(
                            id,firstName,lastName,
                            username,password,
                            nationalCode,address
                    )
            );
        }
        catch(RuntimeException e){
            showError(e.getMessage());
        }

    }
    private void showError(String message){
        System.out.println(message);
    }
    private void showAdminInformation(Admin admin){
        System.out.printf("id : %d%n" +
                          "first name: %s%n " +
                          "last name: %s%n" +
                          "username: %s%n" +
                          "password : %s%n"  +
                          "national code : %s%n" +
                          "address : %s%n",
                admin.getId(),admin.getFirstName(),admin.getLastName(),
                admin.getUsername(),admin.getPassword(),admin.getNationalCode(),
                admin.getAddress()
        );
    }
}
