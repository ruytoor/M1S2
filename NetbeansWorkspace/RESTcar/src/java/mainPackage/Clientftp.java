/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mainPackage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Inet4Address;
import java.util.logging.Level;
import java.util.logging.Logger;
//import javax.ejb.Singleton;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;
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
    
    
    static public String getFileInHTLM(FTPClient f,String path,String name,String pwd){
        if(!f.isConnected())
            return "";
        String retour="";
        try {
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
                        +"<input type=\"hidden\" name=\"path\" value=\""+(path==null?file.getName():"/"+file.getName())+file.getName()+"\"/>"
                        +"<input type=\"submit\" "+(file.isFile()?"name=\"d\" value=\"download":"name=\"o\" value=\"ouvrir")+"\" >"
                        +"</form>"
                        );
                //retour+=("<br><a href=\""+file.getName()+"?name="+name+"&pwd="+pwd+" \">"+file.getName())+"</a>";
            }
        } catch (IOException ex) {
            retour+="Error";
            Logger.getLogger(Clientftp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retour;
    }
    
   static public InputStream getFile(final FTPClient f,final String path){
       InputStream in; 
       try {
           new StreamingOutput() {

                public void write(OutputStream output) throws IOException, WebApplicationException {
                    throw new UnsupportedOperationException("Not supported yet.");
                    boolean retrieveFile = f.retrieveFile(path, null);
                }
            };
           in=f.retrieveFileStream(path);
            
        } catch (IOException ex) {
            Logger.getLogger(Clientftp.class.getName()).log(Level.SEVERE, null, ex);
            in=null;
        }
       return in;
   } 
    
    static public void disconnect(FTPClient f){
        try {
            f.disconnect();
        } catch (IOException ex) {
            Logger.getLogger(Clientftp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
