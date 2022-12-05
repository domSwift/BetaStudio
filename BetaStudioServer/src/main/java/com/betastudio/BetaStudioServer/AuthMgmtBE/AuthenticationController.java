package com.betastudio.BetaStudioServer.AuthMgmtBE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController implements AuthDataMgmtIF{

    private final LoginService loginService;

    @Autowired //indica che il campo verrà istanziato automaticamente e passato come parametro al costruttore
    //ma funziona soltanto se DoctorService è marcato come Servizio
    public AuthenticationController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/api/v1/login/")
    public UserType login(@RequestBody LoginFormData loginForm){
        return loginService.login(loginForm);
    }

}

