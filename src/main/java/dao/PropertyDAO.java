package main.java.dao;

import main.java.connection.ConnectionFactory;
import main.java.entity.Property;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class PropertyDAO implements DAO<Property> {

    private final EntityManager entityManager = new ConnectionFactory().getConnection();

    @Override
    public Property save(Property property) {
        try {
            this.entityManager.getTransaction().begin();
            this.entityManager.persist(property);
            this.entityManager.getTransaction().commit();
        } catch (Exception error) {
            this.entityManager.getTransaction().rollback();
        } finally {
            this.entityManager.close();
        }
        return property;
    }

    @Override
    public List<Property> getList() {
        Query query = this.entityManager.createQuery("SELECT p FROM Property as p");
        return query.getResultList();
    }

    @Override
    public Property update(Property property) {
        Property propertyUp = null;
        try {
            this.entityManager.getTransaction().begin();
            if (property.getId() == null) {
                this.entityManager.persist(property);
            } else {
                propertyUp = this.entityManager.merge(property);
            }
            this.entityManager.getTransaction().commit();
        } catch (Exception exception) {
            this.entityManager.getTransaction().rollback();
        } finally {
            this.entityManager.close();
        }
        return propertyUp;
    }

    @Override
    public Property delete(Long id) {
        Property property = null;
        try {
            property = entityManager.find(Property.class, id);
            if(property.getRent() == null){
                this.entityManager.getTransaction().begin();
                this.entityManager.remove(property);
                this.entityManager.getTransaction().commit();
            } else {
                // TODO: Throw exception here!!
                System.out.println("Nao eh possivel apagar uma propriedade vinculada a um aluguel");
            }

        } catch (Exception exception) {
            this.entityManager.getTransaction().rollback();
        } finally {
            this.entityManager.close();
        }
        return property;
    }

    @Override
    public Property findById(Long id) {
        Property property = null;
        try {
            property = entityManager.find(Property.class, id);
        } catch (Exception e) {
            // TODO: Throw exception here!!
            System.out.println("erro ao buscar por id\n" + e);
        }
        return property;
    }

    public List<Property> getPropertyNotRented() {
        Query query = this.entityManager.createQuery("SELECT p FROM Property as p WHERE p.rent.id = NULL");
        return query.getResultList();
    }
}
