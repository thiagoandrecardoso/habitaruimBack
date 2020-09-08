package com.habitarium.dao;

import com.habitarium.connection.ConnectionFactory;
import com.habitarium.entity.User;

import javax.persistence.EntityManager;
import java.util.List;

public class UserDAO implements DAO<User> {
    private EntityManager entityManager = new ConnectionFactory().getConnection();

    @Override
    public User save(User user) {
        try {
            this.entityManager.getTransaction().begin();
            this.entityManager.persist(user);
            this.entityManager.getTransaction().commit();
        } catch (Exception error) {
            this.entityManager.getTransaction().rollback();
        } finally {
            this.entityManager.close();
        }
        return user;
    }

    @Override
    public List getList() {
        return null;
    }

    @Override
    public User update(User user) {
        User userUp = null;
        try {
            this.entityManager.getTransaction().begin();
            if (user.getId() == null) {
                this.entityManager.persist(user);
            } else {
                userUp = this.entityManager.merge(user);
            }
            this.entityManager.getTransaction().commit();
        } catch (Exception exception) {
            this.entityManager.getTransaction().rollback();
        } finally {
            this.entityManager.close();
        }
        return userUp;
    }

    @Override
    public User delete(Long id) {
        User user = null;
        try {
            user = entityManager.find(User.class, id);
            if (user != null) {
                this.entityManager.getTransaction().begin();
                this.entityManager.remove(user);
                this.entityManager.getTransaction().commit();
            }
        } catch (Exception exception) {
            this.entityManager.getTransaction().rollback();
        } finally {
            this.entityManager.close();
        }
        return user;
    }

    @Override
    public User findById(Long id) {
        User user = null;
        try {
            user = entityManager.find(User.class, id);
        } catch (Exception e) {
            System.out.println("erro ao buscar por id\n" + e);
        }
        return user;
    }

    public User findByLogin(String login) {
        User user = null;
        user = (User) this.entityManager.createQuery("SELECT u FROM User u WHERE u.login LIKE ?1").
                setParameter(1, login).getSingleResult();
        return user;
    }
}
