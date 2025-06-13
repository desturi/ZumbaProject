package com.example.model;

public class Participant {

   private int id;
   private String name, phone, email;
   private int batch_id;

   public Participant() { }

    public Participant(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public Participant(int id, String name, String phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public Participant(int id, String name, String phone, String email, int batch_id) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.batch_id = batch_id;
    }

    public Participant(String name, String phone, String email, int batch_id) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.batch_id = batch_id;
    }

    public int getId() {
       return id;
    }

    public void setId(int id) {
       this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getBatch_id() {
        return batch_id;
    }

    public void setBatch_id(int batch_id) {
        this.batch_id = batch_id;
    }


}
