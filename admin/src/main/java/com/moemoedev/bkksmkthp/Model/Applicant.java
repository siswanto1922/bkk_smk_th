package com.moemoedev.bkksmkthp.Model;

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

    public Applicant() {
    }

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNisn() {
        return nisn;
    }

    public void setNisn(String nisn) {
        this.nisn = nisn;
    }

    public String getJk() {
        return jk;
    }

    public void setJk(String jk) {
        this.jk = jk;
    }

    public String getTtl() {
        return ttl;
    }

    public void setTtl(String ttl) {
        this.ttl = ttl;
    }

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getJurusan() {
        return jurusan;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }

    public String getTb() {
        return tb;
    }

    public void setTb(String tb) {
        this.tb = tb;
    }

    public String getBb() {
        return bb;
    }

    public void setBb(String bb) {
        this.bb = bb;
    }

    public String getGraduate() {
        return graduate;
    }

    public void setGraduate(String graduate) {
        this.graduate = graduate;
    }
}
