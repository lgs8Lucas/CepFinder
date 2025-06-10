package br.com.alura.cepfinder.models;

public class Address {
    private String street;
    private String city;
    private String state;
    private String zip;
    private String neighborhood;
    private String ddd;
    private String stateSimple;

    public Address(AddressViaCep address) {
        this.street = address.logradouro();
        this.ddd = address.ddd();
        this.city = address.localidade();
        this.state = address.estado();
        this.zip = address.cep();
        this.neighborhood = address.bairro();
        this.stateSimple = address.uf();
    }

    @Override
    public String toString() {
        return street + ", " + neighborhood + ", " + city + " - " + stateSimple  + " DDD:" + ddd;
    }

    public String getZip() {
        return zip;
    }
}
