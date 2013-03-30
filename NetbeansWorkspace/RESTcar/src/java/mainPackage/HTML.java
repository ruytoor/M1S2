package mainPackage;

/**
 * création de la page HTML
 * @author Benjamin Ruytoor et Aurore Allart
 */
public class HTML {
    
    /**
     * ouverture des balises pour l'entete de page
     * @return balise d'entete de page
     */
    public static String getHead(){
        return "<html><body>";
    }
    
    /**
     * fermeture des balises pour le pied de page
     * @return les balises de pied de page
     */
    public static String getfoot(){
        return "</html></body>";
    }
    
    /**
     * découpe les parametres
     * @param content le lien du site
     * @return un tableau contenant les parametres
     */
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
    
    /**
     * retourne le dossier parent pour la navigation
     * @param path le chemin absolu du dossier 
     * @param name le nom de l'utilisateur
     * @param pwd le mot de passe de l'utilisateur
     * @return le nom du dossier parent
     */
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
    
    /**
     * genere le formulaire pour le telechargement
     * @param path le chemin absolu du dossier
     * @param name le nom de l'utilisateur
     * @param pwd le mot de passe de l'utilisateur
     * @return le formulaire du telechargement
     */
    static public String getOptionUpload(String path,String name,String pwd){
        return "<form method=\"post\" action=\""+path+"\" enctype=\"multipart/form-data\">"
                +"<input type=\"hidden\" name=\"name\" value=\""+name+"\"/>"
                +"<input type=\"hidden\" name=\"pwd\" value=\""+pwd+"\"/>"
                + "<input type=\"file\" name=\"file\" />"
                +" Nom :<input type=\"text\" name=\"nomf\" value=\"Nouveau fichier\"/>"
                +"<input type=\"submit\" name=\"up\" value=\"upload\" >"
                + "</form>";
    }
    
    /**
     * génere le formulaire pour créer un nouveau dossier
     * @param path le chemin absolu du dossier
     * @param name le nom de l'utilisateur
     * @param pwd le mot de passe de l'utilisateur
     * @return le formulaire du nouveau dossier
     */
    static public String getOptionNouveauDossier(String path,String name,String pwd){
        
        return "<form method=\"post\"  action=\""+path+"\">"
                +"<input type=\"hidden\" name=\"name\" value=\""+name+"\"/>"
                +"<input type=\"hidden\" name=\"pwd\" value=\""+pwd+"\"/>"
                +"<input type=\"text\" name=\"nom\" value=\"Nouveau Dossier\"/>"
                +"<input type=\"submit\" name=\"new\" value=\"new\" >"
                +"</form>";
    }
}
