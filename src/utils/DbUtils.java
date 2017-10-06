package utils;

import java.util.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbUtils {
    private static Connection con;
    private static String databaseurl = "jdbc:derby://localhost:1527/ganesh";
    private static String password =  "ganesh";
    private static String user =  "ganesh";
 
    public static void initDb()
	{     
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con = DriverManager.getConnection(databaseurl, user, password);       
            
        } catch (Exception e) {
            System.out.println(e);
        }       
    }
    
    public static void createMovieDb()
    {
        try {      
          PreparedStatement ps1 = con.prepareStatement("create table Movies ("
                    + "id integer generated always as identity (start with 1,increment by 1),"
                    + "MovieTitle varchar(100), "
                    + "MovieId varchar(10), "
                    + "Genre varchar(70), "
                    + "MovieCast varchar(5000), "
                    + "PosterPath varchar(50), "
                    + "FolderName varchar(100), "
                    + "Overview varchar(1000)"
                    + ")");
          ps1.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }   
    
    public static void createMovieLibraryFolderDb()
    {
        try {      
          PreparedStatement ps1 = con.prepareStatement("create table LibraryFolder ("
                    + "id integer generated always as identity (start with 1,increment by 1),"
                    + "FolderPath varchar(50) "
                    + ")");
          ps1.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    } 
	
	public static ArrayList<HashMap<String,String>> getMovieDetails()
	{
        try {
            Statement st = con.createStatement();
            ResultSet r = st.executeQuery("SELECT * FROM MOVIES");
            ArrayList<HashMap<String,String>> alsm = new ArrayList<HashMap<String,String>>();             
            while(r.next()){
               HashMap<String,String> hm = new HashMap<>();
               hm.put("Title", r.getString(2));
               hm.put("Id", r.getString(3));
               hm.put("Genres", r.getString(4));
               hm.put("Cast", r.getString(5));
               hm.put("PosterPath", r.getString(6));
               hm.put("FolderName",r.getString(7));
               hm.put("Overview", r.getString(8));
               alsm.add(hm);
           }    
           return alsm;
        } catch (SQLException ex) {
            Logger.getLogger(DbUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
		
    public static ArrayList<String> getCastBasedMovieDetails(String searchCast)
	{	
        ArrayList<String> hm = new ArrayList<>();
        try {
            Statement st = con.createStatement();
            String query ="SELECT * FROM MOVIES WHERE MovieCast LIKE '%" + searchCast.replace("'", "''")+ "%'";
            ResultSet r = st.executeQuery(query);       
            while(r.next())
			{     
                 hm.add(r.getString(2).replace("''", "'"));            
            }  
            Collections.sort(hm);
            return hm;
        } catch (SQLException ex) {
            Logger.getLogger(DbUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
        
    public static ArrayList<String> getGenreBasedMovieDetails(String searchCast)
	{
        ArrayList<String> hm = new ArrayList<>();
        try {
            Statement st = con.createStatement();
            String query ="SELECT * FROM MOVIES WHERE Genre LIKE '%" + searchCast+ "%'";
            ResultSet r = st.executeQuery(query);       
            while(r.next())
			{     
                 hm.add(r.getString(2).replace("''", "'"));            
            }  
            Collections.sort(hm);
            return hm;
        } catch (SQLException ex) {
            Logger.getLogger(DbUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
           	
	public static String[] getLibraryFolders()
	{
        try {
            Statement st = con.createStatement();
            ResultSet r = st.executeQuery("SELECT * FROM LIBRARYFOLDER");
            int count = rowCount("LIBRARYFOLDER");
            String[] folders = new String[count];
            int i=0;
            while(r.next())
			{
                folders[i] = r.getString(2);
                i = i+1;
            }
            r.close();
            return folders;
        } catch (SQLException ex) {
            Logger.getLogger(DbUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
	}
	
    public static void storeInMovieDb(HashMap<String,String> hm){
        String id, title, overview, posterPath, genres, cast, folderName;
        try {
            PreparedStatement ps1=con.prepareStatement("INSERT INTO Movies(MovieTitle, MovieId,"
					+"Genre, MovieCast, PosterPath, FolderName, Overview) VALUES(?,?,?,?,?,?,?)");
            id = hm.get("Id");
            title = hm.get("Title").replace("'", "''");
            System.out.println(title);
            overview = hm.get("Overview");
            posterPath = hm.get("PosterPath");
            genres = hm.get("Genres");
            cast = hm.get("Cast");
            folderName = hm.get("FolderName");
            ps1.setString(1, title);
            ps1.setString(2,id);
            ps1.setString(3,genres);
            ps1.setString(4,cast);
            ps1.setString(5,posterPath);
            ps1.setString(6,folderName);           
            ps1.setString(7,overview);
            ps1.executeUpdate();                   
        } catch (SQLException ex) {
            System.out.println(ex);
        }             
    }
	
	public static void storeInMovieLibraryFolderDbDb(String addedFolder){
        String id, title, overview, posterPath, genres, cast, folderName;
        try {
            PreparedStatement ps1=con.prepareStatement("INSERT INTO LibraryFolder(FolderPath) VALUES(?)");
            ps1.setString(1, addedFolder);
            ps1.executeUpdate();                   
        } catch (SQLException ex) {
            System.out.println(ex);
        }             
    }
	
	public static void deleteLibraryFolders(String folder)
	{
        try {
            String query = "DELETE FROM LIBRARYFOLDER WHERE FOLDERPATH = '" + folder+"'";
            //System.out.println(query);
            PreparedStatement ps1 = con.prepareStatement(query);
            ps1.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DbUtils.class.getName()).log(Level.SEVERE, null, ex);
		}
	
	}
        
    public static void deleteMoviesById(String movieID)
	{
        try {
            String query = "DELETE FROM MOVIES WHERE  MOVIEID = '" + movieID+"'";
            //System.out.println(query);
            PreparedStatement ps1 = con.prepareStatement(query);
            ps1.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DbUtils.class.getName()).log(Level.SEVERE, null, ex);
    }
	
}  
        
    public static void deleteMoviesByFolderName(String FolderName)
	{
        try {
            String query = "DELETE FROM MOVIES WHERE  FolderName = '" + FolderName.replace("'", "''")+"'";
            //System.out.println(query);
            PreparedStatement ps1 = con.prepareStatement(query);
            ps1.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DbUtils.class.getName()).log(Level.SEVERE, null, ex);
    }
	
}        

    public static int rowCount(String table)
	{            
        try {
            Statement st = con.createStatement();
           ResultSet r = st.executeQuery("SELECT COUNT(*) AS rowcount FROM " + table);
            r.next();
            int count = r.getInt("rowcount");
            r.close();
            return count;
        } catch (SQLException ex) {
            Logger.getLogger(DbUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }            

    public static void setDatabaseurl(String aDatabaseurl) {
        databaseurl = aDatabaseurl;
    }

    public static void setPassword(String aPassword) {
        password = aPassword;
    }

    public static void setUser(String aUser) {
        user = aUser;
    }
}
