package com.habitarium.dao;

import com.habitarium.connection.ConnectionFactory;
import com.habitarium.entity.MonthPaid;
import com.habitarium.entity.Property;
import com.habitarium.entity.Rent;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class MonthPaidDAO implements DAO<MonthPaid> {

    private EntityManager entityManager = new ConnectionFactory().getConnection();

    @Override
    public MonthPaid save(MonthPaid monthPaid) {
        try {
            this.entityManager.getTransaction().begin();
            this.entityManager.persist(monthPaid);
            this.entityManager.getTransaction().commit();
        } catch (Exception error) {
            this.entityManager.getTransaction().rollback();
        } finally {
            this.entityManager.close();
        }
        return monthPaid;
    }

    @Override
    public List getList() {
        Query query = this.entityManager.createQuery("SELECT m FROM MonthPaid as m");
        return query.getResultList();
    }

    @Override
    public MonthPaid update(MonthPaid monthPaid) {
        MonthPaid monthPaidUp = null;
        try {
            this.entityManager.getTransaction().begin();
            if (monthPaid.getId() == null) {
                this.entityManager.persist(monthPaid);
            } else {
                monthPaidUp = this.entityManager.merge(monthPaid);
            }
            this.entityManager.getTransaction().commit();
        } catch (Exception exception) {
            this.entityManager.getTransaction().rollback();
        } finally {
            this.entityManager.close();
        }
        return monthPaidUp;
    }

    @Override
    public MonthPaid delete(Long id) {
        MonthPaid monthPaid = null;
        try {
            monthPaid = entityManager.find(MonthPaid.class, id);
            this.entityManager.getTransaction().begin();
            this.entityManager.remove(monthPaid);
            this.entityManager.getTransaction().commit();
        } catch (Exception exception) {
            this.entityManager.getTransaction().rollback();
        } finally {
            this.entityManager.close();
        }
        return monthPaid;
    }

    @Override
    public MonthPaid findById(Long id) {
        MonthPaid monthPaid = null;
        try {
            monthPaid = entityManager.find(MonthPaid.class, id);
        } catch (Exception e) {
            // TODO: Throw exception here!!
            System.out.println("erro ao buscar por id\n" + e);
        }
        return monthPaid;
    }

    public void deleteAll(List<Long> ids) {
        // Write all pending changes to the DB and clear persistence context
//        entityManager.flush();
//        entityManager.clear();

        try {
            entityManager.getTransaction().begin();

            Query query = entityManager.createQuery("DELETE MonthPaid mp WHERE id IN (:ids)");
            query.setParameter("ids", ids);
            query.executeUpdate();

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }
}
