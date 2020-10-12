package com.moemoedev.bkksmkthp;

public class Applicant {
    public String id;
    public String name;
    public String email;
    public String nisn;
    public String jk;
    public String ttl;
    public String telp;
    public String address;
    public String jurusan;
    public String tb;
    public String bb;
    public String graduate;

    public Applicant(String id, String name, String email, String nisn, String jk, String ttl, String telp, String address, String jurusan, String tb, String bb, String graduate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.nisn = nisn;
        this.jk = jk;
        this.ttl = ttl;
        this.telp = telp;
        this.address = address;
        this.jurusan = jurusan;
        this.tb = tb;
        this.bb = bb;
        this.graduate = graduate;
    }

    public String getApplicantId() {
        return id;
    }
    public void setApplicantId(String id){
        this.id = id;
    }

    public String getApplicantName() {
        return name;
    }
    public void setApplicantName(String name){
        this.name = name;
    }
}
