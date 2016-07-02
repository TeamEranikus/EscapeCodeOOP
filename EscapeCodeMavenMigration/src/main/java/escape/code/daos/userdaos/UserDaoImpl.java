package escape.code.daos.userdaos;

import escape.code.configurations.HibernateUtils;
import escape.code.models.User;
import org.hibernate.Query;
import org.hibernate.Session;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private Session session = HibernateUtils.openSession();


    @Override
    public User getLogedUser(String username, String password){
        session.beginTransaction();
        Query query = session.createQuery("select us from User as us where us.name = :name");
        query.setParameter("name",username);
        List<User> users = query.list();

        if (users.isEmpty()){
            throw new IllegalArgumentException("User not found!");
        } else if (users.get(0).getPassword().equals(password)){
            throw new IllegalArgumentException("Password is incorrect!");
        }

        return users.get(0);
    }

    @Override
    public void create(User user) {
        session.beginTransaction();
        session.persist(user);
        session.getTransaction().commit();
    }
}
