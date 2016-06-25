package escape.code.daos.userdaos;

import escape.code.configurations.HibernateUtils;
import escape.code.models.User;
import org.hibernate.Session;

public class UserDaoImpl implements UserDao {

    private Session session = HibernateUtils.openSession();

    //TODO
    @Override
    public User getUserByName(String username) {
        return null;
    }

    @Override
    public void create(User user) {

    }
}
