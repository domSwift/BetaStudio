package com.betastudio.BetaStudioServer.AuthMgmtBE;

public class LoginFormData {

    private String loginEmail;
    private String loginPassword;

    public LoginFormData(String loginEmail, String loginPassword) {
        this.loginEmail = loginEmail;
        this.loginPassword = loginPassword;
    }

    public String getLoginEmail() {
        return loginEmail;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

}
