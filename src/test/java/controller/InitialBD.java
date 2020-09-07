package test.java.controller;

import main.java.dao.UserDAO;
import main.java.entity.User;
import org.junit.Ignore;

import static org.junit.Assert.fail;

public class InitialBD {

    @Ignore
    public void initializeBD() {
        try {
            UserDAO userDAO = new UserDAO();
            User user = new User();
            user.setLogin("admin");
            user.setPassword("admin");
            userDAO.save(user);
        } catch (ExceptionInInitializerError e) {
            fail();
        }
    }
}
