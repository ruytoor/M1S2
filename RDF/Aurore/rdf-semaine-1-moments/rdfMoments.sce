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
//nom = "rdf-rectangle-horizontal.png";
//nom = "rdf-rectangle-vertical.png";
//nom = "rdf-rectangle-diagonal.png";
//nom = "rdf-rectangle-diagonal-lisse.png";
//nom = "rdf-carre-6.png";
//nom = "rdf-carre-10.png";
//nom = "rdf-carre-10-30deg.png";
//nom = "rdf-carre-10-45deg.png";
//nom = "rdf-carre-20.png";
//nom = "rdf-triangle-10-15deg.png";
//nom = "rdf-triangle-10-45deg.png";
//nom = "rdf-triangle-10-60deg.png";
nom = "rdf-triangle-10.png";

image = im2double (imread (nom));
//imshow (image);

// Calcul de la surface
surface = rdfSurface (image)

// Calcul de la matrice d'inertie
inertie = [rdfMomentCentre(image,2,0) rdfMomentCentre(image,1,1); rdfMomentCentre(image,1,1) rdfMomentCentre(image,0,2)]

// Calcul des valeurs propres et vecteurs propres de la matrice d'inertie
[vect,val] = spec(inertie);

// Determine les valeurs propres
val

// Determine l'axe principal d'inertie
vect

// Calcul des moments principaux d'inertie
valModifie = inv(vect)*inertie*vect

// Calcul des moments normalises
InertieMomentOrdreDeux = [rdfMomentCentreNormalise(image,2,0) rdfMomentCentreNormalise(image,1,1); rdfMomentCentreNormalise(image,1,1) rdfMomentCentreNormalise(image,0,2)]

// Calcul des valeurs propres et vecteurs propres de la matrice d'inertie calculee a partir des moments centres normalises
[vect2,val2] = spec(InertieMomentOrdreDeux);

//Determine les valeurs propres à partir d'inertie du moment d'ordre 2
val2

//Determine les vecteurs propres à partir d'inertie du moment d'ordre 2
vect2

// Calcul des moments principaux d'inertie du moment d'ordre 2
valModifie2 = inv(vect2)*InertieMomentOrdreDeux*vect2

// Calcul des moments invariants
invariant = rdfMomentsInvariants(image)
