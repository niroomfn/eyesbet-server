 package com.eyesbet.business.domain;
 
 public class User
 {
   private int id;
   private String firstName;
   private String lastName;
   private String email;
   private String city;
   private String password;
   private String username;
 
   public User(int id)
   {
     this.id = id;
   }
 
   public String getFirstName() {
     return this.firstName;
   }
   public void setFirstName(String firstName) {
     this.firstName = firstName;
   }
   public String getLastName() {
     return this.lastName;
   }
   public void setLastName(String lastName) {
     this.lastName = lastName;
   }
   public String getEmail() {
     return this.email;
   }
   public void setEmail(String email) {
     this.email = email;
   }
   public String getCity() {
     return this.city;
   }
   public void setCity(String city) {
     this.city = city;
   }
   public String getPassword() {
     return this.password;
   }
   public void setPassword(String password) {
     this.password = password;
   }
   public String getUsername() {
     return this.username;
   }
   public void setUsername(String username) {
     this.username = username;
   }
 
   public int getId() {
     return this.id;
   }
 }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.business.domain.User
 * JD-Core Version:    0.6.2
 */