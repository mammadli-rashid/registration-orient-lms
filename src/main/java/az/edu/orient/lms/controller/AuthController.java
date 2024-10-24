package az.edu.orient.lms.controller;

import az.edu.orient.lms.config.JwtUtil;
import az.edu.orient.lms.exception.incorrectinput.IncorrectUsernameOrPasswordException;
import az.edu.orient.lms.model.ReqLogin;
import az.edu.orient.lms.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public String createAuthenticationToken(@RequestBody ReqLogin request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
        } catch (Exception e) {
            throw new IncorrectUsernameOrPasswordException();
        }

        final UserDetails userDetails = userService.loadUserByUsername(request.getUsername());

        return jwtUtil.generateToken(userDetails.getUsername());
    }
}
