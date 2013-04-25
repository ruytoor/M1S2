<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Recherche</title>
    </head>
    <body>
        <h1>Recherche de livre</h1>

<FORM method=post action=".">
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
	<INPUT type=text name="annee" value="<%= (request.getParameter("annee")==null?"":request.getParameter("annee")) %>">
	</TD>
</TR>
<TR>
	<TD COLSPAN=2>
	<INPUT type="submit" value="Envoyer">
	</TD>
</TR>
</TABLE>
</FORM>
<%= (request.getParameter("titre")==null?"":"<h2> Récapitulatif </h2>") %>
<%= (request.getParameter("titre")==null?"":"Titre :"+request.getParameter("titre")+"<br>") %>
<%= (request.getParameter("auteur")==null?"":"Auteur :"+request.getParameter("auteur")+"<br>") %>
<%= (request.getParameter("annee")==null?"":"Année :"+request.getParameter("annee")+"<br>") %>

    </body>
</html>
