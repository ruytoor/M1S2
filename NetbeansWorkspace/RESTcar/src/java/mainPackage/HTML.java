/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mainPackage;

/**
 *
 * @author benjamin
 */
public class HTML {
    
    public static String getHead(){
        return "<html><body>";
    }
    public static String getfoot(){
        return "</html></body>";
    }
    
    
    public static String[] splitParametre(String content){
        String tmp[]=content.split("&");
        String retour[];
        if(tmp.length<2)
            return null;
       String tmpName[]=tmp[0].split("=");
        if(tmpName[0].compareTo("name")!=0)
            return null;
        String tmpPassWord[]=tmp[1].split("=");
        if(tmpPassWord[0].compareTo("pwd")!=0)
            return null;
        
        retour=new String[2];
        retour[0]=tmpName[1];
        retour[1]=tmpPassWord[1];
        return retour;
    }
    
    static public String getParent(String path,String name,String pwd) {
        String path2=null;
        if(path.lastIndexOf("/")!=-1)
            path2="/RESTcar/FTP/"+path.substring(0, path.lastIndexOf("/"));
        else
            path2="/RESTcar/FTP/";
        if(path==null)
            return "";
        else
            return "<form method=\"post\"  action=\""+path2+"\">"
                            +"<input type=\"hidden\" name=\"name\" value=\""+name+"\"/>"
                            +"<input type=\"hidden\" name=\"pwd\" value=\""+pwd+"\"/>"
                            +"<input type=\"submit\" value=\"Retour\" >"
                            +"</form>";
      }
    
    
    static public String getOptionNouveauDossier(String path,String name,String pwd){
        
        return "<form method=\"post\"  action=\""+path+"\">"
                +"<input type=\"hidden\" name=\"name\" value=\""+name+"\"/>"
                +"<input type=\"hidden\" name=\"pwd\" value=\""+pwd+"\"/>"
                +"<input type=\"text\" name=\"nom\" value=\"Nouveau Dossier\"/>"
                +"<input type=\"submit\" name=\"new\" value=\"new\" >"
                +"</form>";
    }
}
