package ra.com.modules.orderDetail.dao;

import net.bytebuddy.asm.Advice;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ra.com.modules.orderDetail.OrderDetail;

import javax.persistence.Query;
import java.util.List;
@Repository
public class OrderDetailDaoImpl implements IOrderDetailDao {
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public List<OrderDetail> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from OrderDetail", OrderDetail.class).list();
    }

    @Override
    public OrderDetail findById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(OrderDetail.class, id);
    }

    @Override
    public void save(OrderDetail orderDetail) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(orderDetail);
    }

    @Override
    public void deleteById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        OrderDetail orderDetail = session.get(OrderDetail.class, id);
        session.delete(orderDetail);

    }

    @Override
    public List<OrderDetail> findByOrderId(Integer orderId) {
        return null;
    }




}
