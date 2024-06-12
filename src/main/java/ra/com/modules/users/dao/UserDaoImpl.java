package ra.com.modules.users.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ra.com.modules.users.Users;

import java.util.List;

@Repository
public class UserDaoImpl implements IUsersDao {
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public List<Users> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Users", Users.class).list();
    }

    @Override
    public Users findById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Users.class, id);
    }

    @Override
    public void save(Users users) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(users);
    }

    @Override
    public void deleteById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        Users users = session.get(Users.class, id);
        session.delete(users);
    }

    @Override
    public Users findByUserName(String userName) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Users where userName = :userName", Users.class)
                .setParameter("userName", userName)
                .uniqueResult();
    }
}
