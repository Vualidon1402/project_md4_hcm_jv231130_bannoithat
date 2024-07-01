package ra.com.modules.users.service;

import ra.com.modules.users.Users;
import ra.com.modules.users.dto.request.UsersRequest;
import ra.com.modules.users.dto.response.UsersResponse;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface IUsersService {
    List<UsersResponse> findAll();

    Users findById(Integer id);

    void save(UsersRequest users);

    void deleteById(Integer id);

    Users findByUserName(String userName);

    boolean existsByUserName(String userName);

    Users lockUser(String userName);

    Users unlockUser(String userName);

    long getTotalsElement();

    List<Users> findByPagination(Integer page ,Integer size);

    List<Users> searchByName(String keyword);

    void update(UsersRequest request, HttpSession session);
}
