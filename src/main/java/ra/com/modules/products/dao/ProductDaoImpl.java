package ra.com.modules.products.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ra.com.modules.products.Product;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.util.List;

@Repository
public class ProductDaoImpl implements IProductDao {
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public List<Product> findAll() {
        // mở 1 session hoặc lấy ra session hiện tại
        Session session = sessionFactory.getCurrentSession();
//        TypedQuery<Product> query  = session.createQuery("from Product",Product.class);
//        List<Product> list = query.getResultList();
//        return list;
        return session.createQuery("from Product where isDeleted = false ", Product.class).list();
//
//        // su dung Criteria - ko sủ dụng HQL
//        CriteriaBuilder builder = session.getCriteriaBuilder();
//        CriteriaQuery<Product> criteria = builder.createQuery(Product.class); // chỉ định lớp thực hiện truy vấn
//        Root<Product> root = criteria.from(Product.class); // chỉ định các coojt muốn lây
//        criteria.select(root); // lấy tất cả cột của Product
//        return session.createQuery(criteria).getResultList();
    }

    @Override
    public List<Product> findByPagination(Integer page, Integer size) {
        // hibernate ko hỗ trợ phần trang (limit )
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Product where isDeleted = false", Product.class)
                .setMaxResults(size)
                .setFirstResult(page*size)
                .list();
    }

    @Override
    public List<Product> searchByName(String keyword) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Product p where p.isDeleted = false and p.name like concat('%',:key,'%')", Product.class)
                .setParameter("key",keyword)
                .list();
    }

    @Override
    public Product findById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Product.class,id);
    }

    @Override
    public boolean existByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        return !session.createQuery("from Product where name like :name")
                .setParameter("name",name).list().isEmpty();
    }

    @Override
    public void save(Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(product); // kieemr tra theo dia chi tham chieu
    }

    @Override
    public void deleteById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        Product product = session.get(Product.class,id);
        session.delete(product);
    }

    @Override
    public long getTotalsElement() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select count(P) from Product P where isDeleted = false ",Long.class)
                .getSingleResult();
    }
}
