
package com.meybise.Accounts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbConnection {
    public static Connection Connection() throws SQLException{
    	
  	 Connection conn = null;
      try {
    	  Class.forName("org.postgresql.Driver");
    	  conn = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/account",
	            "root", "root");
    	  conn.setAutoCommit(false);
        	    
        	    return conn;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
