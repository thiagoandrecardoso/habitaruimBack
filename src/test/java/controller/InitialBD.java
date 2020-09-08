package controller;

import com.habitarium.dao.UserDAO;
import com.habitarium.entity.User;
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
