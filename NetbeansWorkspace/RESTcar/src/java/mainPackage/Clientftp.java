package mainPackage;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Inet4Address;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

/**
 *
 * @author Benjamin Ruytoor et Aurore Allart
 */
public class Clientftp {
    
    /**
     * retourne le client FTP 
     * @param name le nom de l'utilisateur
     * @param pwd le mot de passe de l'utilisateur
     * @return un client FTP
     */
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
            f=null;
        }
        return f;
    }
    
    /**
     * creation d'un nouveau fichier
     * @param f le client FTP
     * @param path le chemin absolu du dossier
     * @throws IOException 
     */
    static public void newFolder(FTPClient f,String path) throws IOException{
        f.makeDirectory(path);
    }
    
    /**
     * liste tous les fichiers avec les options ouvrir/download et supprimer
     * @param f le client FTP
     * @param path le chemin absolu du dossier
     * @param name le nom de l'utilisateur
     * @param pwd le mot de passe de l'utilisateur
     * @return liste tous les fichiers
     * @throws IOException 
     */
    static public String getFileInHTML(FTPClient f,String path,String name,String pwd) throws IOException{
        if(!f.isConnected())
            return "";
        String retour="";
        if(path!=null){
            String path1 = "/"+path;
            if(!f.changeWorkingDirectory(path1))
                return null;
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
            }
        return retour;
    }
    
    /**
     * permet de telecharger le fichier
     * @param f le client FTP
     * @param path le nom du chemin absolu du fichier
     * @return un output pour telecharger le fichier
     * @throws IOException 
     */
    static public StreamingOutput getFile(final FTPClient f,final String path) throws IOException{
        StreamingOutput out=new StreamingOutput() {
            public void write(OutputStream output) throws IOException, WebApplicationException {
                InputStream in=f.retrieveFileStream(path);
                BufferedOutputStream w=new BufferedOutputStream(output);
                byte tmp[]=new byte[10240];
                int size;
                while((size=in.read(tmp))>0){
                    w.write(tmp, 0, size);
                    w.flush();
                }
            Clientftp.disconnect(f);
            }
        };
        return  out;
    }
    
    /**
     * deconnection de la session 
     * @param f le client FTP
     */
    static public void disconnect(FTPClient f){
        try {
            f.logout();
            f.disconnect();
        } catch (IOException ex) {
            Logger.getLogger(Clientftp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
