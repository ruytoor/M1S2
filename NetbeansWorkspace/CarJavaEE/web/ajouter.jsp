<%-- 
    Document   : ajouter
    Created on : 30 avr. 2013, 17:43:07
    Author     : benjamin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ajouter Livre</title>
    </head>
    <body>
        <h1>Ajouter un livre</h1>

<FORM method=get action="ajouter.jsp">
<TABLE BORDER=1>
<TR>
	<TD>Le titre</TD>
	<TD>
	<INPUT type=text name="titre" value="<%= (request.getParameter("titre")==null?"":request.getParameter("titre")) %>">
	</TD>
</TR>

<TR>
	<TD>L'auteur</TD>
	<TD>
	<INPUT type=text name="auteur" value="<%= (request.getParameter("auteur")==null?"":request.getParameter("auteur")) %>">
	</TD>
</TR>
<TR>
	<TD>Année de parution</TD>
	<TD>
	<INPUT type="number" name="annee" value="<%= (request.getParameter("annee")==null?"":request.getParameter("annee")) %>">
	</TD>
</TR>
<TR>
	<TD COLSPAN=2>
	<INPUT type="submit" value="Envoyer">
	</TD>
</TR>
</TABLE>
</FORM>
<%= ((request.getParameter("titre")==null||request.getParameter("titre").isEmpty())&&(request.getParameter("auteur")==null||request.getParameter("auteur").isEmpty())&&(request.getParameter("annee")==null||request.getParameter("annee").isEmpty())?"":"<h2> Récapitulatif </h2>") %>
<%= (request.getParameter("titre")==null||request.getParameter("titre").isEmpty()?"":"Titre : "+request.getParameter("titre")+"<br>") %>
<%= (request.getParameter("auteur")==null||request.getParameter("auteur").isEmpty()?"":"Auteur : "+request.getParameter("auteur")+"<br>") %>
<%= (request.getParameter("annee")==null||request.getParameter("annee").isEmpty()?"":"Année : "+request.getParameter("annee")+"<br>") %>
<%= (request.getParameter("titre")==null||request.getParameter("titre").isEmpty()?"":"<form method=post action=\"addLivre\"><INPUT type=\"hidden\" name=\"titre\" value=\""+request.getParameter("titre")+"\"><INPUT type=\"hidden\" name=\"auteur\" value=\""+request.getParameter("auteur")+"\"><INPUT type=\"hidden\" name=\"annee\" value=\""+request.getParameter("annee")+"\"><INPUT type=\"submit\" value=\"Ajouter\"></form>") %>
<br><a href="index.jsp">accueil</a>
</body>
</html>
