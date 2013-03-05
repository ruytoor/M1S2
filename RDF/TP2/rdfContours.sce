// -----------------------------------------------------------------------
// Extraction d'attributs de forme,
// Module RdF, reconnaissance de formes
// Copyright (C) 2010  Universite Lille 1
//
// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program.  If not, see <http://www.gnu.org/licenses/>.
// -----------------------------------------------------------------------

// Chargement des fonctions externes
exec ("rdfContours.sci", -1);

// Chargement d'un contour
nom = "carre-80.txt";
cont = rdfChargeFichierContour (nom);

//               cont tableau de nombre imagianire r+i

nom2 = "cercle-80.txt";
cont2 = rdfChargeFichierContour (nom2);
b=size(cont2);
cont2bis=cont2(1:2:b(1));
cont2prim=cont2(1:4:b(1));

// Afficher, fenetre 1, contour en noir "k"
rdfAfficheContour (cont, 1, "k");
rdfAfficheContour (cont2, 2, "r");
rdfAfficheContour (cont2bis, 2, "b");
rdfAfficheContour (cont2prim, 2, "g");

desc=rdfDescFourier(cont);
//desc(size(desc,1)/2)=5+5*%i;
contLeRetour=rdfInverseDescFourier(desc);
rdfAfficheContour (contLeRetour, 1, "r");
// plot (real (desc(size(desc,1)/2)+0*%i), imag (cont), "r" + "x-"); milieu
mid=desc(size(desc,1)/2);
descRetour=desc;
for i
end
