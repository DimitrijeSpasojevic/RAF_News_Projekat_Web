package rs.raf.rafnewsprojekatweb.repositories.user;

import rs.raf.rafnewsprojekatweb.entities.User;

public interface UserRepository {
    User findUser(String email);
    User addUser(User user);
}
