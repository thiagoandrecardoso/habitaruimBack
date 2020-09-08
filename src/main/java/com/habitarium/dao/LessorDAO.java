package com.habitarium.dao;

import com.habitarium.connection.ConnectionFactory;
import com.habitarium.entity.Lessor;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class LessorDAO implements DAO<Lessor> {

    private EntityManager entityManager = new ConnectionFactory().getConnection();


    @Override
    public Lessor save(Lessor lessor) {
        try {
            this.entityManager.getTransaction().begin();
            this.entityManager.persist(lessor);
            this.entityManager.getTransaction().commit();
        } catch (Exception error) {
            this.entityManager.getTransaction().rollback();
        } finally {
            this.entityManager.close();
        }
        return lessor;
    }

    @Override
    public List<Lessor> getList() {
        Query query = this.entityManager.createQuery("SELECT l FROM Lessor as l");
        return query.getResultList();
    }

    @Override
    public Lessor update(Lessor lessor) {
        Lessor lessorUp = null;
        try {
            this.entityManager.getTransaction().begin();
            if (lessor.getId() == null) {
                this.entityManager.persist(lessor);
            } else {
                lessorUp = this.entityManager.merge(lessor);
            }
            this.entityManager.getTransaction().commit();
        } catch (Exception exception) {
            this.entityManager.getTransaction().rollback();
        } finally {
            this.entityManager.close();
        }
        return lessorUp;
    }

    @Override
    public Lessor delete(Long id) {
        Lessor lessor = null;
        try {
            lessor = entityManager.find(Lessor.class, id);
            if(lessor.getRent() == null){
                this.entityManager.getTransaction().begin();
                this.entityManager.remove(lessor);
                this.entityManager.getTransaction().commit();
            } else {
                // TODO: Throw exception here!!
                System.out.println("Nao eh possivel apagar um locatario vinculado a um aliguel");
            }

        } catch (Exception exception) {
            this.entityManager.getTransaction().rollback();
        } finally {
            this.entityManager.close();
        }
        return lessor;
    }

    @Override
    public Lessor findById(Long id) {
        Lessor lessor = null;
        try {
            lessor = entityManager.find(Lessor.class, id);
        } catch (Exception e) {
            System.out.println("erro ao buscar por id\n" + e);
        }
        return lessor;
    }
}
