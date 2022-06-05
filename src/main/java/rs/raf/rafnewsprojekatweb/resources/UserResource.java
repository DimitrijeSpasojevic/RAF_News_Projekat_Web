package rs.raf.rafnewsprojekatweb.resources;

import rs.raf.rafnewsprojekatweb.dto.UserUpdateDto;
import rs.raf.rafnewsprojekatweb.entities.News;
import rs.raf.rafnewsprojekatweb.entities.User;
import rs.raf.rafnewsprojekatweb.requests.LoginRequest;
import rs.raf.rafnewsprojekatweb.services.UserService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/users")
public class UserResource {

    @Inject
    private UserService userService;

    @POST
    @Path("/login")
    @Produces({MediaType.APPLICATION_JSON})
    public Response login(@Valid LoginRequest loginRequest)
    {
        Map<String, String> response = new HashMap<>();

        String jwt = this.userService.login(loginRequest.getEmail(), loginRequest.getPassword());
        if (jwt == null) {
            response.put("message", "These credentials do not match our records");
            return Response.status(422, "Unprocessable Entity").entity(response).build();
        }

        response.put("jwt", jwt);

        return Response.ok(response).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public User addUser(@Valid User user) {
        return this.userService.addUser(user);
    }

    @DELETE
    @Path("/{email}")
    public void deleteUser(@PathParam("email") String email){
        userService.deleteUser(email);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public UserUpdateDto updateUser(@Valid UserUpdateDto userUpdateDto){
        return userService.updateUser(userUpdateDto);
    }


}
