package com.moemoedev.client.Model;

public class User {
    public String name,email,pass,nisn,jk,ttl,address,jurusan,graduate;

    public User(String name, String email, String password, String nisn, String ttl, String address){

    }

    public User(String name, String email,String pass, String nisn,String jk, String ttl, String address, String jurusan, String graduate) {
        this.name = name;
        this.email = email;
        this.pass = pass;
        this.nisn = nisn;
        this.jk = jk;
        this.ttl = ttl;
        this.address = address;
        this.jurusan = jurusan;
        this.graduate = graduate;
    }
}
