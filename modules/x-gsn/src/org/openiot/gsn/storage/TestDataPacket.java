package org.openiot.gsn.storage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import junit.framework.TestCase;

public class TestDataPacket extends TestCase {
   
   String     dbURL = "jdbc:hsqldb:mem:.";
   
   Connection con;
   
   protected void setUp ( ) throws Exception {
      super.setUp( );
      Class.forName( "org.hsqldb.jdbcDriver" );
      Properties properties = new Properties( );
      properties.put( "user" , "sa" );
      properties.put( "password" , "" );
      properties.put( "ignorecase" , "true" );
      properties.put( "autocommit" , "true" );
      con = DriverManager.getConnection( dbURL , properties );
      con.createStatement( ).execute( "create table TEST1 (TIMED BIGINT  , FIELD1 VARCHAR(20), FIELD2 INT, PK BIGINT NOT NULL IDENTITY   )" );
      con.createStatement( ).execute( "create table TEST2 (FIELD1 CHAR(20), TIMED BIGINT , FIELD2 INT,PK BIGINT NOT NULL IDENTITY  )" );
      con.createStatement( ).execute( "create table TEST3 (FIELD1 BINARY, FIELD2 INT,TIMED BIGINT ,PK BIGINT NOT NULL IDENTITY )" );
      con.createStatement( ).execute( "create table TEST4 (FIELD1 CHAR(20), FIELD2 INT,TIME2D BIGINT, PK BIGINT NOT NULL IDENTITY)" );
      
   }
   
   protected void tearDown ( ) throws Exception {
      super.tearDown( );
   }
   
   public void testResultSetToStreamElements ( ) throws Exception {
      // Test 1
      cleanTables( );
      PreparedStatement ps1 = con.prepareStatement( "insert into test1 values (?,?,?,?)" );
      for ( int i = 0 ; i < 100 ; i++ ) {
         ps1.setLong( 1 , i % 50 );
         ps1.setString( 2 , "i" + " BLA" );
         ps1.setInt( 3 , i * 1000 );
         ps1.setInt( 4 , i );
         ps1.executeUpdate( );
      }
      
   }
   
   private void cleanTables ( ) throws SQLException {
      con.createStatement( ).execute( "delete from TEST1" );
      con.createStatement( ).execute( "delete from TEST2" );
      con.createStatement( ).execute( "delete from TEST3" );
      con.createStatement( ).execute( "delete from TEST4" );
      
   }
   
}
