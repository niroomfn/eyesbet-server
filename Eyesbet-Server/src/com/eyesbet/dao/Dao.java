/*    */ package com.eyesbet.dao;
/*    */ 
/*    */ import java.sql.Connection;
/*    */ import java.sql.ResultSet;
/*    */ import java.sql.SQLException;
/*    */ 
/*    */ public abstract class Dao
/*    */ {
/*    */   protected Connection getConnection()
/*    */     throws SQLException
/*    */   {
/* 23 */     return AppDataSource.getInstance().getDataSource().getConnection();
/*    */   }
/*    */ 
/*    */   protected void closeResultSet(ResultSet rs)
/*    */   {
/* 33 */     if (rs != null) {
/*    */       try {
/* 35 */         rs.close();
/*    */       }
/*    */       catch (SQLException e) {
/* 38 */         e.printStackTrace();
/*    */       }
/* 40 */       rs = null;
/*    */     }
/*    */   }
/*    */ 
/*    */   public void closeConnection(Connection conn)
/*    */   {
/*    */     try
/*    */     {
/* 50 */       if ((conn != null) && (!conn.isClosed())) {
/* 51 */         conn.close();
/*    */       }
/*    */ 
/*    */     }
/*    */     catch (SQLException e)
/*    */     {
/* 57 */       e.printStackTrace();
/*    */     }
/*    */ 
/* 60 */     conn = null;
/*    */   }
/*    */ 
/*    */   public static enum Action
/*    */   {
/* 16 */     insert, update, delete;
/*    */   }
/*    */ }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.dao.Dao
 * JD-Core Version:    0.6.2
 */