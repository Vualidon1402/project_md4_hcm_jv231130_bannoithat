package ra.com.modules.users.dao;

import ra.com.modules.users.Users;

import java.util.List;

public interface IUsersDao {
    List <Users> findAll();
    Users findById(Integer id);
    void save(Users users);
    void deleteById(Integer id);
    Users findByUserName(String userName);

}
