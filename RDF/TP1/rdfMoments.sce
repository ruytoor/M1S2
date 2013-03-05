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
exec ("rdfMoments.sci", -1);

// Noms de toutes les images
nom = "rdf-rectangle-horizontal.png";
nom="rdf-rectangle-vertical.png";
//nom="rdf-rectangle-diagonal.png";
image = im2double (imread (nom));
imshow (image);

// Calcul de la surface
surface = rdfSurface (image)

inertie=[rdfMomentCentre(image,2,0) rdfMomentCentre(image,1,1); rdfMomentCentre(image,1,1) rdfMomentCentre(image,0,2)];

[vect,val]=spec(inertie);

if vect(1,1)>vect(2,2) then
    axeinertie=vect(1,1);
else
    axeinertie=vect(2,2);
end

valModifie=inv(vect)*inertie*vect
