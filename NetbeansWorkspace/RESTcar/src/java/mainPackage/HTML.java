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
        if(tmp.length==3){
            String tmpPath[]=tmp[0].split("=");
            if(tmpPath[0].compareTo("path")!=0)
                return null;
            retour=new String[3];
            retour[0]=tmpName[1];
            retour[1]=tmpPassWord[1];
            retour[2]=tmpPath[1];
            return retour;
        }
        
        retour=new String[2];
        retour[0]=tmpName[1];
        retour[1]=tmpPassWord[1];
        return retour;
    }
    
    static public String getParent(String path) {
        if(path==null)
            return "";
        else
            return "<br><a href=\"..\">..</a>";
        /*
         * String tmp[]=path.split("/");
         * String cummule=new String();
         * for(int i=0;i<tmp.length-2;++i){
         * cummule+="/"+tmp[i];
         * }
         * return cummule;*/
    }
}
