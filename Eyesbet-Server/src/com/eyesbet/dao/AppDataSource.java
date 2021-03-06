 package com.eyesbet.dao;
 
 import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.eyesbet.app.Constants;
 
 public class AppDataSource
 {
   private static AppDataSource me = new AppDataSource();
   private DataSource ds;
 
   private AppDataSource()
   {
     try
     {
       Constants constants = Constants.getInstance();
       Context envContext = (Context)new InitialContext().lookup("java:/comp/env");
       this.ds = ((DataSource)envContext.lookup(constants.getJndiName()));
     }
     catch (NamingException e) {
       throw new RuntimeException(e.toString());
     } catch (Exception e) {
       throw new RuntimeException(e);
     }
   }
 
   public static AppDataSource getInstance()
   {
     return me;
   }
 
   public DataSource getDataSource()
   {
     return this.ds;
   } }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.dao.AppDataSource
 * JD-Core Version:    0.6.2
 */