package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Transactional
   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> listUsers() {
      return sessionFactory.getCurrentSession().createQuery("from User", User.class).list();
   }

   @Transactional(readOnly = true)
   @Override
   public User getUserByCar(String model, int series) {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(
              "select u from User u where u.car.model = :model and u.car.series = :series", User.class);
      query.setParameter("model", model);
      query.setParameter("series", series);
      return query.getSingleResult();
   }
}