package com.fredrikpedersen.proveeksamen2.domain;

/**
 * @author Fredrik Pedersen
 * @version 1.0
 * @since 03/06/2020 at 12:16
 */
public class Bruker {

    private String brukernavn;
    private String passord;

    public Bruker(String brukernavn, String passord) {
        this.brukernavn = brukernavn;
        this.passord = passord;
    }

    public Bruker() {
    }

    public String getBrukernavn() {
        return brukernavn;
    }

    public void setBrukernavn(String brukernavn) {
        this.brukernavn = brukernavn;
    }

    public String getPassord() {
        return passord;
    }

    public void setPassord(String passord) {
        this.passord = passord;
    }

    @Override
    public String toString() {
        return "Bruker{" +
                "brukernavn='" + brukernavn + '\'' +
                ", passord='" + passord + '\'' +
                '}';
    }
}