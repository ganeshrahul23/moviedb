
package main;


import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import utils.*;

public class URLFileDB {
    public static void main(String[] args) {
       String path = "F:\\Videos\\Movies\\English\\2012";
       ArrayList <String> movies = FileUtils.getMovieNames(path);
       //JOptionPane.showMessageDialog(null, "1\n2\n3\n4\n5\n6\n7\n8\n9\n10");
      //  JProgressBar.
//                    for(Object o:movies){
//                    HashMap<String,String> hm = UrlUtils.getMovieDetailsByName(o.toString());
//                    if(hm != null) {
//                            JOptionPane.showMessageDialog(null, hm.get("Title"));                          
//                    }
//            }
       
		
       // System.out.println(movies);
        DbUtils.initDb();
       DbUtils.createMovieLibraryFolderDb();
      DbUtils.createMovieDb();
       // DbUtils.createMovieLibraryFolderDb();
//        System.out.println("Db created");
//        for(Object o:movies){
//                HashMap<String,String> hm = UrlUtils.getMovieDetailsByName(o.toString());
//                if(hm != null) {
//                        alsm.add(hm);
//                        DbUtils.storeInMovieDb(hm);                       
//                }
//        }
        
        //alsm.clear();
        //DbUtils.getMovieDetails();
        
    }
}
