/*    */ package com.eyesbet.business.domain;
/*    */ 
/*    */ public class User
/*    */ {
/*    */   private int id;
/*    */   private String firstName;
/*    */   private String lastName;
/*    */   private String email;
/*    */   private String city;
/*    */   private String password;
/*    */   private String username;
/*    */ 
/*    */   public User(int id)
/*    */   {
/* 15 */     this.id = id;
/*    */   }
/*    */ 
/*    */   public String getFirstName() {
/* 19 */     return this.firstName;
/*    */   }
/*    */   public void setFirstName(String firstName) {
/* 22 */     this.firstName = firstName;
/*    */   }
/*    */   public String getLastName() {
/* 25 */     return this.lastName;
/*    */   }
/*    */   public void setLastName(String lastName) {
/* 28 */     this.lastName = lastName;
/*    */   }
/*    */   public String getEmail() {
/* 31 */     return this.email;
/*    */   }
/*    */   public void setEmail(String email) {
/* 34 */     this.email = email;
/*    */   }
/*    */   public String getCity() {
/* 37 */     return this.city;
/*    */   }
/*    */   public void setCity(String city) {
/* 40 */     this.city = city;
/*    */   }
/*    */   public String getPassword() {
/* 43 */     return this.password;
/*    */   }
/*    */   public void setPassword(String password) {
/* 46 */     this.password = password;
/*    */   }
/*    */   public String getUsername() {
/* 49 */     return this.username;
/*    */   }
/*    */   public void setUsername(String username) {
/* 52 */     this.username = username;
/*    */   }
/*    */ 
/*    */   public int getId() {
/* 56 */     return this.id;
/*    */   }
/*    */ }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.business.domain.User
 * JD-Core Version:    0.6.2
 */