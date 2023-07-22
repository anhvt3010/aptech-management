package com.anhvt.aptechmanagement.Utils;

public class Passwordefault {

    public static final String PASSWORD_DEFAULT = "123456a@";

    public static Passwordefault getInstance(){
        return new Passwordefault();
    }

    public String getPassworDefault(){
        return PASSWORD_DEFAULT;
    }

}

