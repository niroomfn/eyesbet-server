 package com.eyesbet.dao;
 
 import java.sql.Connection;
 import java.sql.ResultSet;
 import java.sql.SQLException;
 
 public abstract class Dao
 {
   protected Connection getConnection()
     throws SQLException
   {
     return AppDataSource.getInstance().getDataSource().getConnection();
   }
 
   protected void closeResultSet(ResultSet rs)
   {
     if (rs != null) {
       try {
         rs.close();
       }
       catch (SQLException e) {
         e.printStackTrace();
       }
       rs = null;
     }
   }
 
   public void closeConnection(Connection conn)
   {
     try
     {
       if ((conn != null) && (!conn.isClosed())) {
         conn.close();
       }
 
     }
     catch (SQLException e)
     {
       e.printStackTrace();
     }
 
     conn = null;
   }
 
   public static enum Action
   {
     insert, update, delete;
   }
 }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.dao.Dao
 * JD-Core Version:    0.6.2
 */