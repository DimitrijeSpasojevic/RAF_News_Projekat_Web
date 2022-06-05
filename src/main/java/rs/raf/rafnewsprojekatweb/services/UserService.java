package rs.raf.rafnewsprojekatweb.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.apache.commons.codec.digest.DigestUtils;
import rs.raf.rafnewsprojekatweb.dto.UserUpdateDto;
import rs.raf.rafnewsprojekatweb.entities.User;
import rs.raf.rafnewsprojekatweb.repositories.user.UserRepository;

import javax.inject.Inject;
import java.util.Date;

public class UserService {

    @Inject
    UserRepository userRepository;

    public String login(String email, String password) {
        String hashedPassword = DigestUtils.sha256Hex(password);

        User user = this.userRepository.findUser(email);
        if (user == null || !user.getPassword().equals(hashedPassword)) {
            return null;
        }

        Date issuedAt = new Date();
        Date expiresAt = new Date(issuedAt.getTime() + 24*60*60*1000); // One day

        Algorithm algorithm = Algorithm.HMAC256("secret");

        // JWT-om mozete bezbedono poslati informacije na FE
        // Tako sto sve sto zelite da posaljete zapakujete u claims mapu
        return JWT.create()
                .withIssuedAt(issuedAt)
                .withExpiresAt(expiresAt)
                .withSubject(email)
                .withClaim("type",user.getType())
                .withClaim("status",user.getActive())
                .sign(algorithm);
    }

    public boolean isAuthorized(String token){
        Algorithm algorithm = Algorithm.HMAC256("secret");
        JWTVerifier verifier = JWT.require(algorithm)
                .build();
        DecodedJWT jwt = verifier.verify(token);

        String email = jwt.getSubject();
        jwt.getClaim("type").asString();

        User user = this.userRepository.findUser(email);

        if (user == null){
            return false;
        }

        return true;
    }

    public User addUser(User user){
       String password = DigestUtils.sha256Hex(user.getPassword());
       user.setPassword(password);
       return userRepository.addUser(user);
    }

    public void deleteUser(String email){
        userRepository.delete(email);
    }

    public UserUpdateDto updateUser(UserUpdateDto userUpdateDto){
       return userRepository.updateUser(userUpdateDto);
    }
}
