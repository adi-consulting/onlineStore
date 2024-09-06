package adit.manager.controllers;

import adit.manager.config.security.jwt.JwtService;
import adit.manager.dto.ManagerAuthDto;
import adit.manager.dto.ManagerLoginDto;
import adit.manager.entities.Manager;
import adit.manager.exceptions.ErrorResponse;
import adit.manager.exceptions.ManagerAldreadyExistException;
import adit.manager.models.LoginResponse;
import adit.manager.services.AuthentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthenticationController {


    @Autowired
    private AuthentService authentService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/signup")
    public ResponseEntity<Manager> signup(@RequestBody ManagerAuthDto managerAuthDto) {
        Manager manager = null;
        try {
            manager = authentService.signup(managerAuthDto);
        } catch (ManagerAldreadyExistException m) {
            log.error(m.getMessage());
        }
        return ResponseEntity.ok(manager);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody ManagerLoginDto managerLoginDto){
        UserDetails userDetails = authentService.authenticate(managerLoginDto);

        String jwtToken = jwtService.generateToken(userDetails);

        LoginResponse loginResponse = new LoginResponse(jwtToken, jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);

    }

    // Exception Handler method added in CustomerController to handle CustomerAlreadyExistsException
    @ExceptionHandler(value = ManagerAldreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleManagerAlreadyExistsException(ManagerAldreadyExistException ex) {
        return new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
    }

    /*@ExceptionHandler(value = ManagerAldreadyExistException.class)
    public ResponseEntity<?> handleManagerAlreadyExistsException2(ManagerAldreadyExistException ex) {
        return ResponseEntity.badRequest().body("");
    }*/
}
