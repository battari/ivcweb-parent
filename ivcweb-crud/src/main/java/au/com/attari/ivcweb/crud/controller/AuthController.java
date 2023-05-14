package au.com.attari.ivcweb.crud.controller;

import au.com.attari.ivcweb.crud.exceptions.NotFoundException;
import au.com.attari.ivcweb.crud.model.Role;
import au.com.attari.ivcweb.crud.model.RoleLookup;
import au.com.attari.ivcweb.crud.model.User;
import au.com.attari.ivcweb.crud.repository.RoleLookupRepository;
import au.com.attari.ivcweb.crud.repository.RoleRepository;
import au.com.attari.ivcweb.crud.repository.UserRepository;
import au.com.attari.ivcweb.crud.service.AuthService;
import au.com.attari.ivcweb.crud.service.JwtService;
import au.com.attari.ivcweb.crud.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/ivcweb/ivcweb-crud")
public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtService.generateJwtToken(authentication);

        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest, HttpServletRequest request) {

        List<Role> inputRoles = registerRequest.getRoles();
        List<Role> roles = new ArrayList<>();

        if (inputRoles == null) {
            throw new RuntimeException("Error: Role is not defined.");
        } else {
            for(Role role: inputRoles) {
                try {
                    RoleLookup r = authService.findByRole(role.getRole());
                    roles.add(role);
                } catch(NotFoundException e) {
                    return ResponseEntity
                            .internalServerError()
                            .body(new ErrorResponse(request.getServletPath(),
                                    "Internal Server Error",
                                    e.getMessage(),
                                    HttpStatus.INTERNAL_SERVER_ERROR.value()));
                }
            }
        }

        User user = new User(registerRequest.getUsername(),
                registerRequest.getEmail(),
                encoder.encode(registerRequest.getPassword()),
                1
        );

        String err = authService.register(user, roles);
        if(err == null || err.length() == 0) {
            return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
        }
        else {
            return ResponseEntity
                    .badRequest()
                    .body(new ErrorResponse(request.getServletPath(), "bad Request",err, HttpStatus.BAD_REQUEST.value()));
        }
    }
}
