package ht.me.fraganya.uliminet;

import java.util.HashMap;

public class User {
    public int id;
    private String username;
    private String password;
    private String firstname;
    private String surname;
    private String email;
    private String specialization;
    private String pref_lang;
    private String location;
    private String phone_number;

    public User(){
        super();
    }

    public User(int id, String username,String firstname, String surname){
        super();
        this.id = id;
        this.username = username;
        this.firstname = firstname;
        this.surname = surname;
    }

    public User(String username,String firstname, String surname){
        super();
        this.username = username;
        this.firstname = firstname;
        this.surname = surname;
    }

    public User(HashMap<String,String> userDetails){
        super();

        this.username = userDetails.get("username");
        this.firstname = userDetails.get("firstname");
        this.surname = userDetails.get("surname");
        this.email = userDetails.get("email");
        this.password = userDetails.get("password");
        this.specialization = userDetails.get("specialization");
        this.pref_lang = userDetails.get("pref_lang");
        this.location = userDetails.get("location");
        this.phone_number = userDetails.get("phone_number");

    }

    public User(String username,String password){
        super();
        this.username = username;
        this.password = password;
    }

    public  boolean authenticate(){
        //authenticate user on the server

        return true;
    }


    public boolean register(){
        //register user on the server


        return true;
    }


    //setter and getters for relevant fields
    public String getUsername() {
        return username;
    }

    public int getId(){
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getPref_lang() {
        return pref_lang;
    }

    public String getLocation() {
        return location;
    }

    public String getPhone_number() {
        return phone_number;
    }



}
