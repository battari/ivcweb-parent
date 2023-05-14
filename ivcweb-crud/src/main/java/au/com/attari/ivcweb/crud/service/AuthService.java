package au.com.attari.ivcweb.crud.service;

import au.com.attari.ivcweb.crud.exceptions.NotFoundException;
import au.com.attari.ivcweb.crud.model.Role;
import au.com.attari.ivcweb.crud.model.RoleLookup;
import au.com.attari.ivcweb.crud.model.User;
import au.com.attari.ivcweb.crud.repository.RoleLookupRepository;
import au.com.attari.ivcweb.crud.repository.RoleRepository;
import au.com.attari.ivcweb.crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class AuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    RoleLookupRepository roleLookupRepository;

    public String register(User user, List<Role> roles) {
        StringBuffer errorResp = new StringBuffer();

        if (userRepository.existsByUsername(user.getUsername())) {
            errorResp.append("Error: Username is already taken!");
        }
        else if (userRepository.existsByEmail(user.getEmail())) {
            errorResp.append("Error: Email is already in use!");
        }
        else {
            roles.stream().forEach(item -> item.setRole("ROLE_" + item.getRole()));
            user.setRoles(roles);
            userRepository.save(user);
        }

        return errorResp.toString();
    }

    public RoleLookup findByRole(String role) {
        RoleLookup roleLookup = roleLookupRepository.findByRole(role)
                .orElseThrow( () -> new NotFoundException(
                        String.format("Role %s is not defined",role)
                       // "Role %s is not defined".formatted(role)
                ));
        return roleLookup;
    }

}
