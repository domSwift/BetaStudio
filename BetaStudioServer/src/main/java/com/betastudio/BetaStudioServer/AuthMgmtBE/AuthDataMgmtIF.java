package com.betastudio.BetaStudioServer.AuthMgmtBE;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthDataMgmtIF {

    @PostMapping("/api/v1/login/")
    public UserType login(@RequestBody LoginFormData loginForm);

}
