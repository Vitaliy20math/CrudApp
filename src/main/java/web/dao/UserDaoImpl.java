package web.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.collection.internal.PersistentList;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaDialect;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import web.config.AppConfig;
import web.models.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    public List<User> listAllUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    public User getUser(int Id) {
        return entityManager.find(User.class, Id);
    }
    public void save(User user) {
        entityManager.persist(user);
        entityManager.flush();
    }
    public void update(User user) {
        entityManager.merge(user);
        entityManager.flush();
    }
    public void delete(int id) {
        User user = getUser(id);
        entityManager.remove(user);
        entityManager.flush();
    }

}
