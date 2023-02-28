package com.asozial.api;


import com.asozial.model.User;
import com.asozial.model.dto.CredentialsDTO;
import com.asozial.repository.UserRepository;
import io.smallrye.jwt.build.Jwt;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.jwt.Claims;

import javax.inject.Inject;
import javax.json.Json;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.Instant;
import java.util.Optional;
import java.util.Set;

@Path("/auth")
public class AuthResource {
    @Inject
    UserRepository userRepository;
    @Inject
    @ConfigProperty(name = "smallrye.jwt.new-token.lifespan")
    long lifespan;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(CredentialsDTO credentials) {
        Optional<User> optionalUser = userRepository
                .find("email", credentials.email)
                .stream()
                .findAny();
        if (optionalUser.isEmpty()) {
            return  Response.status(Response.Status.UNAUTHORIZED).build();
        }

        User user = optionalUser.get();

        long exp = Instant.now().getEpochSecond() + lifespan;
        String token = Jwt
                .claim(Claims.upn.name(), user.id)
                .groups(Set.of("user")).sign();
        String entity = Json.createObjectBuilder()
                .add("token", token)
                .add("expires_at", exp)
                .build().toString();
        return Response.ok().entity(entity).build();
    }
}