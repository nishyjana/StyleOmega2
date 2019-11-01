package com.nishy.hp.styleomega;

public class customers {
    public String id;
    public String name;
    public String password;
    public String email;
    public String phonr_num;

    public customers(String id, String name, String password, String email, String phonr_num) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.phonr_num = phonr_num;
    }

    public customers(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
