package ir.mmdamin.model.admin.dao;
import ir.mmdamin.model.admin.domain.Admin;
import ir.mmdamin.model.datasource.DataSource;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

public class AdminJDBCDao implements AdminDAO{
    private final DataSource dataSource;

    public AdminJDBCDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Optional<Admin> get(Integer id){
        try(var connection = new DataSource().getConnection()){
            var selectSql = "select * from admins where id = ?";
            try(var selectStatement = connection.prepareStatement(selectSql)){
                selectStatement.setInt(1,id);
                var foundAdmin = selectStatement.executeQuery();

                if(foundAdmin.next()){
                    return Optional.of(
                            new Admin(
                                    id,
                                    foundAdmin.getString("first_name"),
                                    foundAdmin.getString("last_name"),
                                    foundAdmin.getString("username"),
                                    foundAdmin.getString("password"),
                                    foundAdmin.getString("national_code"),
                                    foundAdmin.getString("address")
                            )
                    );
                }else{
                    return Optional.empty();
                }
            }
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public List<Admin> getAll(){
        try(var connection = new DataSource().getConnection()){
           var getAllAdminsSql = "select * from admins";
           List<Admin> admins = new ArrayList<Admin>();
           try(var getAllAdminsStatement = connection.prepareStatement(getAllAdminsSql)){
               var result = getAllAdminsStatement.executeQuery();
               while(result.next()){
                   admins.add(
                           new Admin(
                               Integer.parseInt(result.getString("id")),
                               result.getString("first_name"),
                               result.getString("last_name"),
                               result.getString("username"),
                               result.getString("password"),
                               result.getString("national_code"),
                               result.getString("address")
                           )
                   );
               }
               return admins;
           }
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public boolean create(Admin admin){
        try(var connection = dataSource.getConnection()){
            var query = """
                   insert into admins( first_name, last_name,
                                       username , password, 
                                       national_code, address)
                    values(?,?,?,?,?,?)                    
                    """;
            try(var statement = connection.prepareStatement(query)){
                statement.setString(1,admin.getFirstName());
                statement.setString(2,admin.getLastName());
                statement.setString(3,admin.getUsername());
                statement.setString(4,admin.getPassword());
                statement.setString(5,admin.getNationalCode());
                statement.setString(6,admin.getAddress());
                return statement.execute();
            }
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public boolean delete(Integer id){
        try(var connection = new DataSource().getConnection()){
            var deleteSql = "delete from admins where id = ?";
            try(var deleteStatement = connection.prepareStatement(deleteSql)){
                deleteStatement.setInt(1,id);
                return deleteStatement.execute();
            }
        }
        catch(SQLException e){
           throw new RuntimeException(e);
        }
    }
    public void update(Admin admin){
        try(var connection = new DataSource().getConnection()){
            var query = "select * from admins where id = ?";
            try(var statement = connection.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE)){
                statement.setInt(1,admin.getId());
                var result = statement.executeQuery();
                result.next();
                System.out.println(result.getString("first_name"));
                result.updateString("first_name",admin.getFirstName());
                result.updateString("last_name",admin.getLastName());
                result.updateString("username",admin.getUsername());
                result.updateString("password",admin.getPassword());
                result.updateString("national_code",admin.getNationalCode());
                result.updateString("address",admin.getAddress());
                result.updateRow();
            }
        }
        catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
}
