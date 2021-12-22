import java.sql.*;

public class SQLiteJDBC {
  public static void main( String args[] ) {
	     Connection c = null;
	      Statement stmt = null;
	      
	      try {
	         Class.forName("org.sqlite.JDBC");
	         c = DriverManager.getConnection("jdbc:sqlite:test.db");
	         System.out.println("Opened database successfully");

	         dropTable(c,"COMPANY");

	         stmt = c.createStatement();
	         String sql = "CREATE TABLE COMPANY " +
	                        "(ID INT PRIMARY KEY     NOT NULL," +
	                        " NAME           TEXT    NOT NULL, " + 
	                        " AGE            INT     NOT NULL, " + 
	                        " ADDRESS        CHAR(50), " + 
	                        " SALARY         REAL)"; 
	         stmt.executeUpdate(sql);
	         stmt.close();
		     System.out.println("Table created successfully");
	         
	         insertData(c);
	         readData(c);
	         
	         
	         c.close();
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
	      }
	   }
  
  public static void dropTable(Connection c, String name)
  {
	  String sql = "DROP TABLE "+ name;
	  Statement stmt = null;
	  try {
		  stmt = c.createStatement();
		  stmt.executeUpdate(sql);
		  stmt.close();
	  }
	  catch( Exception e)
	  {
		  
	  }
	  System.out.println(" Deleted table "+ name);
  }
  
  public static void insertData(Connection c) throws Exception
  {
	  Statement stmt = c.createStatement();
      String sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
              "VALUES (1, 'Paul', 32, 'California', 20000.00 );"; 
      stmt.executeUpdate(sql);

      sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
        "VALUES (2, 'Allen', 25, 'Texas', 15000.00 );"; 
      stmt.executeUpdate(sql);

      sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
        "VALUES (3, 'Teddy', 23, 'Norway', 20000.00 );"; 
      stmt.executeUpdate(sql);

      sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
        "VALUES (4, 'Mark', 25, 'Rich-Mond ', 65000.00 );"; 
      stmt.executeUpdate(sql);
      stmt.close();
      System.out.println("Inserted 4 rows");
  }
  
  public static void readData(Connection c) throws Exception
  {
      Statement stmt = c.createStatement();
      ResultSet rs = stmt.executeQuery( "SELECT * FROM COMPANY;" );
      
      while ( rs.next() ) {
         int id = rs.getInt("id");
         String  name = rs.getString("name");
         int age  = rs.getInt("age");
         String  address = rs.getString("address");
         float salary = rs.getFloat("salary");
         
         System.out.println( "ID = " + id );
         System.out.println( "NAME = " + name );
         System.out.println( "AGE = " + age );
         System.out.println( "ADDRESS = " + address );
         System.out.println( "SALARY = " + salary );
         System.out.println();
      }
      rs.close();
      stmt.close();
  }
}