/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mainPackage;

import java.io.IOException;
import java.net.Inet4Address;
import java.util.logging.Level;
import java.util.logging.Logger;
//import javax.ejb.Singleton;
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
            if(path==null)
                for(FTPFile file: f.listFiles()){
                    retour+=("<br><a href=\""+file.getName()+"?name="+name+"&pwd="+pwd+" \">"+file.getName())+"</a>";
                }
            else{
                for(FTPFile file: f.listFiles()){
                    retour+=("<br><a href=\""+file.getName()+"\">"+file.getName())+"</a>";
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Clientftp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retour;
    }
    
    
    static public void disconnect(FTPClient f){
        try {
            f.disconnect();
        } catch (IOException ex) {
            Logger.getLogger(Clientftp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
