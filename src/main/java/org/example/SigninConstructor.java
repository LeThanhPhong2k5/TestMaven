package org.example;

public class SigninConstructor {
    private String usernameSignin;
    private String passwordSignin;
    private String repairPassword;

    public SigninConstructor(){

    }

    public SigninConstructor(String usernameSignin, String passwordSignin, String repairPassword) {
        this.usernameSignin = usernameSignin;
        this.passwordSignin = passwordSignin;
        this.repairPassword = repairPassword;
    }

    public String getUsernameSignin() {
        return usernameSignin;
    }

    public void setUsernameSignin(String usernameSignin) {
        this.usernameSignin = usernameSignin;
    }

    public String getPasswordSignin() {
        return passwordSignin;
    }

    public void setPasswordSignin(String passwordSignin) {
        this.passwordSignin = passwordSignin;
    }

    public String getRepairPassword() {
        return repairPassword;
    }

    public void setRepairPassword(String repairPassword) {
        this.repairPassword = repairPassword;
    }
}
