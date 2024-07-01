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

    @Override
    public boolean existsByUserName(String userName) {
        Session session = sessionFactory.getCurrentSession();
        Long count = (Long) session.createQuery("select count(u.id) from Users u where u.userName = :userName")
                .setParameter("userName", userName)
                .uniqueResult();
        return count > 0;
    }

    @Override
    public Users lockUser(String userName) {
        Session session = sessionFactory.getCurrentSession();
        Users user = session.createQuery("from Users where userName = :userName", Users.class)
                .setParameter("userName", userName)
                .uniqueResult();

        if (user != null) {
            user.setUserStatus(Users.userStatus.INACTIVE); // Lock the user
            session.saveOrUpdate(user);
        }
        return user;
    }

    @Override
    public Users unlockUser(String userName) {
        Session session = sessionFactory.getCurrentSession();
        Users user = session.createQuery("from Users where userName = :userName", Users.class)
                .setParameter("userName", userName)
                .uniqueResult();

        if (user != null) {
            user.setUserStatus(Users.userStatus.ACTIVE); // Unlock the user
            session.saveOrUpdate(user);
        }
        return user;
    }

    @Override
    public long getTotalsElement() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select count(u) from Users u", Long.class)
                .uniqueResult();
    }

    @Override
    public List<Users> findByPagination(Integer page, Integer size) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Users", Users.class)
                .setMaxResults(size)
                .setFirstResult(page * size)
                .list();
    }

    @Override
    public List<Users> searchByName(String keyword) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Users u where u.userName like concat('%',:key,'%')", Users.class)
                .setParameter("key", keyword)
                .list();
    }
    @Override
    public void update(Users users) {
        Session session = sessionFactory.getCurrentSession();
        session.update(users);
    }
}
