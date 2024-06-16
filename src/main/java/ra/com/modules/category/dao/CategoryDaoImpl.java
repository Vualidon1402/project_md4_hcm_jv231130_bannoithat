package ra.com.modules.category.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ra.com.modules.category.Category;

import java.util.List;


@Repository
public class CategoryDaoImpl implements ICategoryDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Category> findAll() {
        // mở 1 session hoặc lấy ra session hiện tại
        Session session = sessionFactory.getCurrentSession();
//        TypedQuery<Product> query  = session.createQuery("from Product",Product.class);
//        List<Product> list = query.getResultList();
//        return list;
        return session.createQuery("from Category where isDeleted = false ", Category.class).list();
//
//        // su dung Criteria - ko sủ dụng HQL
//        CriteriaBuilder builder = session.getCriteriaBuilder();
//        CriteriaQuery<Product> criteria = builder.createQuery(Product.class); // chỉ định lớp thực hiện truy vấn
//        Root<Product> root = criteria.from(Product.class); // chỉ định các coojt muốn lây
//        criteria.select(root); // lấy tất cả cột của Product
//        return session.createQuery(criteria).getResultList();
    }

    @Override
    public List<Category> findByPagination(Integer page, Integer size) {
        // hibernate ko hỗ trợ phần trang (limit )
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Category where isDeleted = false", Category.class)
                .setMaxResults(size)
                .setFirstResult(page * size)
                .list();
    }

    @Override
    public List<Category> searchByName(String keyword) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Category p where p.isDeleted = false and p.name like concat('%',:key,'%')", Category.class)
                .setParameter("key", keyword)
                .list();
    }

    @Override
    public Category findById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Category.class, id);
    }

    @Override
    public Category findByIdForProduct(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Category.class, id);
    }

    @Override
    public boolean existByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        return !session.createQuery("from Category where name like :name")
                .setParameter("name", name).list().isEmpty();
    }

    @Override
    public void save(Category category) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(category); // kieemr tra theo dia chi tham chieu
    }

    @Override
    public void deleteById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        Category category = session.get(Category.class, id);
        category.setIsDeleted(true);
        session.saveOrUpdate(category);
    }

    @Override
    public long getTotalsElement() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select count(P) from Category P where isDeleted = false ", Long.class)
                .getSingleResult();
    }


}
