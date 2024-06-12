package ra.com.modules.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.com.modules.users.Users;
import ra.com.modules.users.dao.IUsersDao;
import ra.com.modules.users.dto.request.UsersRequest;
import ra.com.modules.users.dto.response.UsersResponse;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UsersService implements IUsersService{
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
    public void save(UsersRequest users) {
        Users user = new Users();
        if (users.getId() != null){
            user = usersDao.findById(users.getId());
        }else {
            user.setUserRole(Users.userRole.MEMBER);
            user.setUserStatus(Users.userStatus.ACTIVE);
        }
        user.setUserName(users.getUserName());
        user.setUserPassword(users.getUserPassword());
        user.setUserEmail(users.getUserEmail());
        user.setUserAddress(users.getUserAddress());
        user.setUserGender(users.getUserGender());
        user.setUserPhone(users.getUserPhone());
        user.setCreatedDate(users.getCreatedDate());
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
}
