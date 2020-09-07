package main.java.dao;

import main.java.connection.ConnectionFactory;
import main.java.entity.Property;
import main.java.entity.Rent;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class RentDAO implements DAO<Rent> {

    private EntityManager entityManager = new ConnectionFactory().getConnection();

    @Override
    public Rent save(Rent rent) {
        try {
            this.entityManager.getTransaction().begin();
            this.entityManager.persist(rent);
            this.entityManager.getTransaction().commit();
        } catch (Exception error) {
            this.entityManager.getTransaction().rollback();
        } finally {
            this.entityManager.close();
        }
        return rent;
    }

    @Override
    public List getList() {
        Query query = this.entityManager.createQuery("SELECT r FROM Rent as r");
        return query.getResultList();
    }

    public List getListToBePaidToday(int today){
        List query = this.entityManager.createQuery("SELECT r FROM Rent r WHERE r.payDay LIKE ?1").
                setParameter(1, today).getResultList();
        return query;
    }

    @Override
    public Rent update(Rent rent) {
        Rent rentUp = null;
        try {
            this.entityManager.getTransaction().begin();
            if (rent.getId() == null) {
                this.entityManager.persist(rent);
            } else {
                rentUp = this.entityManager.merge(rent);
            }
            this.entityManager.getTransaction().commit();
        } catch (Exception exception) {
            this.entityManager.getTransaction().rollback();
        } finally {
            this.entityManager.close();
        }
        return rentUp;
    }

    @Override
    public Rent delete(Long id) {
        Rent rent = null;
        try {
            rent = entityManager.find(Rent.class, id);

            if (rent.getProperty() != null) {
                PropertyDAO propertyDAO = new PropertyDAO();
                Property property = propertyDAO.findById(rent.getProperty().getId());
                property.setRent(null);
                propertyDAO.update(property);
            }
            rent.setProperty(null);

            this.entityManager.getTransaction().begin();
            this.entityManager.remove(rent);
            this.entityManager.getTransaction().commit();
        } catch (Exception exception) {
            this.entityManager.getTransaction().rollback();
        } finally {
            this.entityManager.close();
        }
        return rent;
    }

    @Override
    public Rent findById(Long id) {
        Rent rent = null;
        try {
            rent = entityManager.find(Rent.class, id);
        } catch (Exception e) {
            System.out.println("erro ao buscar por id\n" + e);
        }
        return rent;
    }
}
