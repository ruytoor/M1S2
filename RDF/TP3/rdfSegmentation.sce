// -----------------------------------------------------------------------
// Extraction d'attributs de pixels pour la classification,
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

clear;

// Chargement des fonctions externes
exec ("rdfSegmentation.sci", -1);

// Chargement d'une image
nom = "rdf-2-classes-texture-4.png";
image = im2double (imread (nom));
// AVERAGE == MOYENNE
filter = fspecial('unsharp');
filter2 = fspecial('average');
imf = imfilter(image, filter);
t=rdfTextureEcartType(image,5);
//imshow(imf);

// Calcul et affichage de son histogramme
hist = rdfCalculeHistogramme1D (image, 256);
//rdfAfficheHistogramme1D (hist, 1, "r");
imagei=image;
image=image-t;

hist = rdfCalculeHistogramme1D (t, 256);

seuil = 0.25;
binaire = rdfClassifieurLineaire1D (image, 1, -seuil);

//image2=image+imf;
// Affichage des deux images
imshow ([imagei,t,image,binaire]);



