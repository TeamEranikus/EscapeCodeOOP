package escape.code.daos.userdaos;

import escape.code.models.User;

public interface UserDao {
    User getUserByName(String username);
    void create(User user);
}
