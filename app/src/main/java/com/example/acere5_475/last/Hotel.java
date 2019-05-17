package com.example.acere5_475.last;

public class Hotel {
    private int id;
    private String nama;
    private String alamat;
    private String hp;
    private String hotel;
    private String tanggal;

    public Hotel(){

    }

    public Hotel(int id, String nama, String alamat, String hp, String hotel, String tanggal){
        this.id = id;
        this.nama = nama;
        this.alamat = alamat;
        this.hp = hp;
        this.hotel = hotel;
        this.tanggal = tanggal;
    }

    public Hotel (String nama, String alamat, String hp, String hotel, String tanggal){
        this.nama = nama;
        this.nama = nama;
        this.alamat = alamat;
        this.hp = hp;
        this.hotel = hotel;
        this.tanggal = tanggal;
    }

    public int get_id(){
        return this.id;
    }

    public void set_id(int id){
        this.id = id;
    }

    public String get_nama(){
        return nama;
    }

    public void set_nama(String nama){
        this.nama = nama;
    }

    public String get_alamat(){
        return alamat;
    }

    public void set_alamat(String alamat){
        this.alamat = alamat;
    }

    public String get_hp(){
        return hp;
    }

    public void set_hp(String hp){
        this.hp = hp;
    }

    public String get_hotel(){
        return hotel;
    }

    public void set_hotel(String hotel){
        this.hotel = hotel;
    }

    public String get_tanggal(){
        return tanggal;
    }

    public void set_tanggal(String tanggal){
        this.tanggal = tanggal;
    }

}
