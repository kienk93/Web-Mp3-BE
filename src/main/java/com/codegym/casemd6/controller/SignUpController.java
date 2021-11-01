package com.codegym.casemd6.controller;


import com.codegym.casemd6.dto.LoginAccount;
import com.codegym.casemd6.model.Account;
import com.codegym.casemd6.model.AppRole;
import com.codegym.casemd6.model.Image;
import com.codegym.casemd6.service.account.IServiceAccount;
import com.codegym.casemd6.service.approle.IServiceAppRole;
import com.codegym.casemd6.service.image.IServiceImage;
import com.codegym.casemd6.service.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/account")
public class SignUpController {
    @Autowired
    IServiceAccount serviceAccount;
    @Autowired
    IServiceAppRole serviceAppRole;
    @Autowired
    IServiceImage serviceImage;
    @Autowired
    JwtService jwtService;


    @PostMapping("/signup")
    public ResponseEntity<String> createAcc(@Valid @RequestBody Account account) {
        AppRole role = serviceAppRole.findById(2L).get();
        Image image = serviceImage.findById(1L).get();
        account.setAvatar(image);
        account.setRole(role);
        if (serviceAccount.add(account)) {
            return new ResponseEntity<>("Ok", HttpStatus.CREATED);
        } else
            return new ResponseEntity<>("Exited", HttpStatus.BAD_REQUEST);

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(HttpServletRequest request, @RequestBody Account account) {
        String result = "";
        HttpStatus httpStatus = null;
        try {
            if (serviceAccount.checkLogin(account)) {
                result = jwtService.generateTokenLogin(account.getUsername());
                Account account1 = serviceAccount.loadUserByUserName(account.getUsername());
                LoginAccount loginAccount = new LoginAccount();
                loginAccount.setId(account1.getId());
                loginAccount.setFullName(account1.getFullName());
                loginAccount.setAvatar(account1.getAvatar());
                loginAccount.setToken(result);
                return new ResponseEntity(loginAccount, HttpStatus.OK);
            } else {
                result = "Wrong email or password or not verification";
                httpStatus = HttpStatus.BAD_REQUEST;
            }
        } catch (Exception ex) {
            result = "Server Error";
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(result, httpStatus);
    }

    @PutMapping("/addroleandimage")
    public ResponseEntity<String> fixRoleAndDefaultAvatar() {
        List<AppRole> appRoles = (List<AppRole>) serviceAppRole.findAll();
        List<Image> images = (List<Image>) serviceImage.findAll();
        if (appRoles.size() == 0) {
            AppRole admin = new AppRole();
            admin.setId(1L);
            admin.setRole("ROLE_ADMIN");
            AppRole user = new AppRole();
            user.setRole("ROLE_USER");
            user.setId(2L);
            serviceAppRole.save(admin);
            serviceAppRole.save(user);
        }
        if (images.size() == 0) {
            Image image = new Image();
            image.setId(1L);
            image.setPath("https://firebasestorage.googleapis.com/v0/b/filebase-70567.appspot.com/o/images%2F84156601_1148106832202066_479016465572298752_o.jpg?alt=media&token=4ca2d074-2b3b-4524-a017-e951067fa3f5");
            serviceImage.save(image);
        }
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

}
