/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mainPackage;

import java.io.IOException;
import java.io.InputStream;
import java.net.Inet4Address;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

/**
 *
 * @author benjamin
 */
public class Clientftp {
    static public FTPClient getFTPClient(String name,String pwd){
        FTPClient f=new FTPClient();
        try {
            f.connect(Inet4Address.getLocalHost());
            f.enterLocalPassiveMode();
            if(f.isConnected())
                if(!f.login(name, pwd)){
                    f.disconnect();
                    f=null;
                }
        } catch (IOException ex) {
            //  Logger.getLogger(Clientftp.class.getName()).log(Level.SEVERE, null, ex);
            f=null;
        }
        return f;
    }
    
    static public void newFolder(FTPClient f,String path) throws IOException{
        f.makeDirectory(path);
    }
    
    static public String getFileInHTLM(FTPClient f,String path,String name,String pwd) throws IOException{
        if(!f.isConnected())
            return "";
        String retour="";
            if(path!=null){
                String path1 = "/"+path;
                f.changeWorkingDirectory(path1);
                path+="/";
            }else{
                path="";
            }
            for(FTPFile file: f.listFiles()){
                retour+=("------------------------------------<br><h2>"+file.getName()+"</h2>"
                        +"<form method=\"post\"  action=\""+path+file.getName()+"\">"
                        +"<input type=\"hidden\" name=\"name\" value=\""+name+"\"/>"
                        +"<input type=\"hidden\" name=\"pwd\" value=\""+pwd+"\"/>"
                        +"<input type=\"hidden\" name=\"path\" value=\""+file.getName()+"\"/>"
                        +"<input type=\"submit\" "+(file.isFile()?"name=\"d\" value=\"download":"name=\"o\" value=\"ouvrir")+"\" >"
                        +"<input type=\"submit\" "+(file.isFile()?"name=\"sf\" value=\"Supprimer":"name=\"sd\" value=\"Supprimer")+"\" >"
                        +"</form>"
                        );
                //retour+=("<br><a href=\""+file.getName()+"?name="+name+"&pwd="+pwd+" \">"+file.getName())+"</a>";
            }
        return retour;
    }
    
   static public InputStream getFile(final FTPClient f,final String path) throws IOException{
       InputStream in; 
           in=f.retrieveFileStream(path);
         //   out=new ByteArrayOutputStream(256);
         //  Util.copyStream(in, out, 256, 256, null, true);
       return in;
   } 
    
    static public void disconnect(FTPClient f){
        try {
            f.logout();
            f.disconnect();
        } catch (IOException ex) {
            Logger.getLogger(Clientftp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
