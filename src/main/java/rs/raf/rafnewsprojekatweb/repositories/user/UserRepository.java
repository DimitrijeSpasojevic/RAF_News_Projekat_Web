package rs.raf.rafnewsprojekatweb.repositories.user;

import rs.raf.rafnewsprojekatweb.dto.UserUpdateDto;
import rs.raf.rafnewsprojekatweb.entities.User;

public interface UserRepository {
    User findUser(String email);
    User addUser(User user);
    void delete(String email);
    UserUpdateDto updateUser(UserUpdateDto userUpdateDto);
}
