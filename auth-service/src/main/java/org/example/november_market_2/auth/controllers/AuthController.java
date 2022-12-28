package org.example.november_market_2.auth.controllers;


import lombok.RequiredArgsConstructor;
import org.example.november_market_2.api.JwtRequest;
import org.example.november_market_2.api.JwtResponse;
import org.example.november_market_2.api.StringResponse;
import org.example.november_market_2.auth.entities.User;
import org.example.november_market_2.auth.exceptions.AppError;
import org.example.november_market_2.auth.services.UserService;
import org.example.november_market_2.auth.utils.JwtTokenUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),
                    authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new AppError("CHECK_TOKEN_ERROR", "Некорректный логин или пароль"),
                    HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @GetMapping("/about_me")
    public StringResponse getCurrentUserInfo(@RequestHeader String username) {
        User user = userService.findByUsername(username).get();
        return new StringResponse(user.getUsername() + " " + user.getEmail());
    }
}
