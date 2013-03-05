// -----------------------------------------------------------------------
// Extraction d'attributs de pixels pour la classification,
// Module RdF, reconnaissance de formes
// Copyright (C) 2009  Universite Lille 1
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

// -----------------------------------------------------------------------
// Fonctions pour la segmentation avec un seul attribut par pixel
// -----------------------------------------------------------------------

// Calcul de l'histogramme 1D d'une image
function hist = rdfCalculeHistogramme1D (image, bins)
    result = zeros (1, bins);
    for y = 1:size (image, 1)
        for x = 1:size (image, 2)
            i = int (image (y, x) * (bins - 1)) + 1;
            result (i) = result (i) + 1;
        end
    end
    hist = result;
endfunction

// Affichage de l'histogramme 1D d'une image
function rdfAfficheHistogramme1D (hist, nfigure, couleur)
    // Creer une nouvelle figure ou activer une figure existante
    hf = scf (nfigure);
    hf.figure_name = "Histogramme numero %d";
    // Tracer l'histogramme
    pas = 1 / size (hist, 2);
    x = 0:pas:1-pas;
    plot (x, hist, couleur + "o-");
endfunction

// Classifieur lineaire 1D
function classe = rdfClassifieurLineaire1D (image, a, b)
    result = zeros (image);
    for y = 1:size (image, 1)
        for x = 1:size (image, 2)
            i = image (y, x);
            if a * i + b > 0 then
                result (y, x) = 1;
                else
                result (y, x) = 0;
            end
        end
    end
    classe = result;
endfunction

// -----------------------------------------------------------------------
// Fonctions pour le calcul de l'attribut de texture
// -----------------------------------------------------------------------

// Calcul de la moyenne locale d'une image: fenetre (2 * taille + 1)^2
function moyenne = rdfMoyenneImage (image, taille)
    // Definition du masque de moyennage
    filtre = fspecial ("average", 2 * taille + 1);
    // Convolution
    moyenne = imfilter (image, filtre);
endfunction

// Calcul de l'ecart type normalise des voisinages carres d'une image
function ect = rdfTextureEcartType (image, taille)
    moyenne = rdfMoyenneImage (image, taille);
    carre = (image - moyenne) .^ 2;
    result = sqrt (rdfMoyenneImage (carre, taille));
    ect = result / max (result);
endfunction

// -----------------------------------------------------------------------
// Fonctions pour la segmentation avec deux attributs par pixel
// -----------------------------------------------------------------------

// Calcul de l'histogramme 2D (log + normalise) de deux images
function hist = rdfCalculeHistogramme2D (image1, bins1, image2, bins2)
    result = zeros (bins2, bins1);
    for y = 1:size (image1, 1)
        for x = 1:size (image1, 2)
            //
        end
    end
    // Version logarithmique
    result = log (1 + result);
    // Normalisation a une valeur maximale = 1
    hist = result / max (result);
endfunction

// Classifieur lineaire 2D
function classe = rdfClassifieurLineaire2D (image1, image2, a, b, c)
    result = zeros (image1);
    for y = 1:size (image1, 1)
        for x = 1:size (image1, 2)
            i = image1 (y, x);
            j = image2 (y, x);
            if a * i + b * j + c > 0 then
                result (y, x) = 1;
                else
                result (y, x) = 0;
            end
        end
    end
    classe = result;
endfunction
