/*    */ package com.eyesbet.dao;
/*    */ 
/*    */ import javax.naming.Context;
/*    */ import javax.naming.InitialContext;
/*    */ import javax.naming.NamingException;
/*    */ import javax.sql.DataSource;
/*    */ 
/*    */ public class AppDataSource
/*    */ {
/* 16 */   private static AppDataSource me = new AppDataSource();
/*    */   private DataSource ds;
/*    */ 
/*    */   private AppDataSource()
/*    */   {
/*    */     try
/*    */     {
/* 24 */       Context envContext = (Context)new InitialContext().lookup("java:/comp/env");
/* 25 */       this.ds = ((DataSource)envContext.lookup("jdbc/eyesbet"));
/*    */     }
/*    */     catch (NamingException e) {
/* 28 */       throw new RuntimeException(e.toString());
/*    */     } catch (Exception e) {
/* 30 */       throw new RuntimeException(e);
/*    */     }
/*    */   }
/*    */ 
/*    */   public static AppDataSource getInstance()
/*    */   {
/* 40 */     return me;
/*    */   }
/*    */ 
/*    */   public DataSource getDataSource()
/*    */   {
/* 47 */     return this.ds;
/*    */   } }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.dao.AppDataSource
 * JD-Core Version:    0.6.2
 */