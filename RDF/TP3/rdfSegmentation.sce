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

// Chargement des fonctions externes
exec ("rdfSegmentation.sci", -1);

// Chargement de toutes les images
nom = "rdf-2-classes-texture-0.png";
nom1 = "rdf-2-classes-texture-1.png";
nom2 = "rdf-2-classes-texture-2.png";
nom3 = "rdf-2-classes-texture-3.png";
nom4 = "rdf-2-classes-texture-4.png";
image = im2double (imread (nom));
image1 = im2double (imread (nom1));
image2 = im2double (imread (nom2));
image3 = im2double (imread (nom3));
image4 = im2double (imread (nom4));


// Calcul et affichage de son histogramme
//le 256 represente le découpage de niveaux de gris
hist = rdfCalculeHistogramme1D (image, 256);
//rdfAfficheHistogramme1D (hist, 1, "r");


//hist1 = rdfCalculeHistogramme1D (image1, 256);
//rdfAfficheHistogramme1D (hist1, 1, "k");
//hist2 = rdfCalculeHistogramme1D (image2, 256);
//rdfAfficheHistogramme1D (hist2, 1, "b");
//hist3 = rdfCalculeHistogramme1D (image3, 256);
//rdfAfficheHistogramme1D (hist3, 1, "g");
//hist4 = rdfCalculeHistogramme1D (image4, 256);
//rdfAfficheHistogramme1D (hist4, 1, "m");
//
//
//// Binarisation de l'image
//// le seuil permet de distinguer les formes de la figure
seuil = 0.5;
binaire = rdfClassifieurLineaire1D (image, 1, -seuil);
seuil1 = 0.56;
binaire1 = rdfClassifieurLineaire1D (image1, 1, -seuil1);
//seuil2 = 0.4;
//binaire2 = rdfClassifieurLineaire1D (image2, -1, seuil2);
//seuil3 = 0.43;
//binaire3 = rdfClassifieurLineaire1D (image3, -1, seuil3);
//seuil4 = 0.6;
//binaire4 = rdfClassifieurLineaire1D (image4, -1, seuil4);
//
//// Affichage des deux images
////etre de la meme taille de matrice
//imshow ([image, binaire,binaire1, binaire2, binaire3, binaire4]);
//

////calcul des niveaux de gris
////ecart = rdfTextureEcartType(image,5);
//imageEcart = im2double (ecart);
//histoEcart = rdfCalculeHistogramme1D(imageEcart,256);
//rdfAfficheHistogramme1D(histoEcart,1,"k");
////imshow([image, binaire,ecart]);
ecart1 = rdfTextureEcartType(image1,3);
//imageEcart1 = im2double (ecart1);
//histoEcart1 = rdfCalculeHistogramme1D(imageEcart1,256);
//seuilEcart1 = 0.5;
//binairi1 = 
//rdfAfficheHistogramme1D(histoEcart1,2,"k");
//image3=image1-ecart1;
//imshow([image1, binaire1,ecart1,image3]);
//ecart2 = rdfTextureEcartType(image2,5);
//imageEcart2 = im2double (ecart2);
//histoEcart2 = rdfCalculeHistogramme1D(imageEcart2,256);
//rdfAfficheHistogramme1D(histoEcart2,3,"k");
//
//ecart3 = rdfTextureEcartType(image3,5);
//imageEcart3 = im2double (ecart3);
//histoEcart3 = rdfCalculeHistogramme1D(imageEcart3,256);
//rdfAfficheHistogramme1D(histoEcart3,4,"k");
//
//ecart4 = rdfTextureEcartType(image4,5);
//imageEcart4 = im2double (ecart4);
//histoEcart4 = rdfCalculeHistogramme1D(imageEcart4,256);
//rdfAfficheHistogramme1D(histoEcart4,5,"k");
//

//appercu de l'histogramme conjoint
histoconj = rdfCalculeHistogramme2D(image1,256,ecart1,256);
imshow([image1,ecart1]);

plot3d1(1:256,1:256,histoconj);
//imshow(histoconj);
