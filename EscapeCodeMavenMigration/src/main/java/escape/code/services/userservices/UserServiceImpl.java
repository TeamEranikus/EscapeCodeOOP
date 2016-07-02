package escape.code.services.userservices;

import escape.code.daos.userdaos.UserDao;
import escape.code.daos.userdaos.UserDaoImpl;
import escape.code.models.User;

public class UserServiceImpl implements UserService {

    UserDao userDao = new UserDaoImpl();

    @Override
    public void createUser(String username, String password) {
        User user = new User();
        user.setName(username);
        user.setPassword(password);
        user.setHasBook(false);
        user.setHasKey(false);
        this.userDao.create(user);

    }

    @Override
    public User getUser(String username, String password){
        User user = this.userDao.getLogedUser(username,password);
        return user;
    }
}
