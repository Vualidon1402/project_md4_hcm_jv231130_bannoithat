package ra.com.modules.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.com.modules.users.Users;
import ra.com.modules.users.dao.IUsersDao;
import ra.com.modules.users.dto.request.UsersRequest;
import ra.com.modules.users.dto.response.UsersResponse;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UsersService implements IUsersService {
    @Autowired
    private IUsersDao usersDao;

    @Override
    public List<UsersResponse> findAll() {
        List<Users> users = usersDao.findAll();
        return users.stream()
                .map(UsersResponse::new)
                .collect(Collectors.toList());
    }

    @Override
    public Users findById(Integer id) {
        return usersDao.findById(id);
    }

    @Override
    public void save(UsersRequest request) {
        Users user = new Users();
        if (request.getId() != null) {
            user = usersDao.findById(request.getId());
        } else {
            user.setCreatedDate(new Date(System.currentTimeMillis()));
            user.setUserStatus(Users.userStatus.ACTIVE);
        }
        user.setUserName(request.getUserName());
        user.setUserPassword(request.getUserPassword());
        user.setUserAddress(request.getUserAddress());
        user.setUserEmail(request.getUserEmail());
        user.setUserPhone(request.getUserPhone());
        user.setUserGender(request.getUserGender());

        usersDao.save(user);
    }


    @Override
    public void deleteById(Integer id) {
        usersDao.deleteById(id);
    }

    @Override
    public Users findByUserName(String userName) {
        return usersDao.findByUserName(userName);
    }

    @Override
    public boolean existsByUserName(String userName) {
        return usersDao.existsByUserName(userName);
    }

    @Override
    public Users lockUser(String userName) {
        return usersDao.lockUser(userName);
    }

    @Override
    public Users unlockUser(String userName) {
        return usersDao.unlockUser(userName);
    }

    @Override
    public long getTotalsElement() {
        return usersDao.getTotalsElement();
    }

    @Override
    public List<Users> findByPagination(Integer page, Integer size) {
        return usersDao.findByPagination(page, size);
    }

    @Override
    public List<Users> searchByName(String keyword) {
        return usersDao.searchByName(keyword);
    }


    @Override
    public void update(UsersRequest request, HttpSession session) {
        // Lấy người dùng từ session
        Users currentUser = (Users) session.getAttribute("user");

        if (currentUser != null) {
            currentUser.setUserName(request.getUserName());
            currentUser.setUserPassword(request.getUserPassword());
            currentUser.setUserAddress(request.getUserAddress());
            currentUser.setUserEmail(request.getUserEmail());
            currentUser.setUserPhone(request.getUserPhone());
            currentUser.setUserGender(request.getUserGender());

            usersDao.save(currentUser);
        }
    }
}
