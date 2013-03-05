// -----------------------------------------------------------------------
// Extraction d'attributs de forme,
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
// Fonctions d'affichage et de chargement en memoire d'un fichier contour
// -----------------------------------------------------------------------


function descRetour = rdfAnnuleDescFourier(desc,ratio)
    mid=desc(size(desc,1)/2);
    
    descRetour=desc;
endfunction

// Fonction d'affichage du contour d'une forme
function rdfAfficheContour (cont, nfigure, couleur)
  // Creer une nouvelle figure ou activer une figure existante
  hf = scf (nfigure);
  hf.figure_name = "Forme numero %d";
  // Echelle identique sur les deux axes
  ha = gca ();
  ha.isoview = "on";
  ha.axes_reverse = ["off", "on", "off"];
  // Tracer les points
  plot (real (cont), imag (cont), couleur + "o-");
endfunction

// Fonction de lecture d'un contour dans un fichier
function contour = rdfChargeFichierContour (nom)
  // Ouverture du fichier en lecture
  hf = mopen (nom, "r");
  // Lire le nombre de points
  nbpoints = msscanf (rdfLireLigneFichier (hf), "%d");
  // Lire les points
  for i = 1 : nbpoints
    // Lire une ligne du fichier
    chaine = rdfLireLigneFichier (hf);
    [n, x, y] = msscanf (chaine, "%g,%g");
    // Convertir en nombre complexe
    contour(i) = x + %i * y;
  end
  // Fermer le fichier
  mclose (hf);
endfunction

// Lire une ligne dans un fichier sans tenir compte des commentaires
function chaine = rdfLireLigneFichier (hf)
  while 1 == 1
    chaine = mgetl (hf, 1);
    if (part (chaine, 1) ~= "#") then
      break;
    end
  end
endfunction

// -----------------------------------------------------------------------
// Descripteurs de Fourier
// -----------------------------------------------------------------------

// Calcul des descripteurs de Fourier d'un contour
function desc = rdfDescFourier (cont)
  // Test si nombre pair
  if modulo (size (cont, 1), 2) ~= 0 then
    // Retirer un point
    cont = cont (1 : size (cont, 1) - 1);
  end
  // Transformee de Fourier
  desc = fft (cont) / size (cont, 1);
endfunction

// Calcul du contour a partir des descripteurs de Fourier
function cont = rdfInverseDescFourier (desc)
  // Calcul de la transformee inverse
  cont = ifft (size (desc, 1) * desc);
endfunction

// Valeur du descripteur en fonction de l'indice
function valeur = rdfValeurDescFourier (desc, indice)
  // Position modulo taille + 1
  valeur = desc (pmodulo (indice, size (desc, 1)) + 1);
endfunction

// -----------------------------------------------------------------------
// Fonctions de codage d'un contour
// -----------------------------------------------------------------------

// Suivi du contour d'une forme
function cont = rdfContour (image)
  // Recherche du premier point non nul
  premier = 0;
  for y = 1 : size (image, 1)
    for x = 1 : size (image, 2)
      if image(y, x) ~= 0 then
        premier = x + %i * y;
        break;
      end
      if premier ~= 0 then
        break;
      end
    end
  end
  // Initialisation
  offset = [1,1-%i,-%i,-1-%i,-1,-1+%i,%i,1+%i];
  direction = 0;
  nb = 1;
  cont(1) = premier;
  courant = premier;
  // Suivi du contour
  while 1 == 1
    // Essayer vers la gauche
    inc = 2;
    ndirection = pmodulo ((direction + inc), 8);
    suivant = courant + offset (ndirection + 1);
    // Tant que le point est nul tourner
    while image (imag (suivant), real (suivant)) == 0 & inc > -4 then
      inc = inc - 1;
      ndirection = pmodulo ((direction + inc), 8);
      suivant = courant + offset (ndirection + 1);
    end
    // Si changement maximal de direction, arreter
    if inc == -4 then
      break;
    end
    // Enregistrer le point
    courant = suivant;
    direction = ndirection;
    // Retour au debut ?
    if courant == premier then
      break;
    else
      nb = nb + 1;
      cont(nb) = courant;
    end
  end
endfunction

// Algorithme de la corde
function ncont = rdfAlgorithmeCorde (cont, dmax)
  // Nombre de points
  n = size (cont, 1);
  if n <= 2 then
    ncont = cont;
  else
    // Debut et fin
    debut = cont (1);
    fin = cont (n);
    // Distances des autres points a la droite debut<->fin
    // A modifier: le vecteur d, de meme taille que le vecteur
    // 'cont' doit contenir les distances
    d = zeros (cont);
    // Si maximum de d < dmax, retourner les extremites
    if max (d) < dmax then
      ncont = [debut; fin];
    // Sinon, decouper en deux sous-contours
    else
      // Indice du point le plus eloigne
      loin = find (d == max (d), 1);
      // Decomposer les deux sous-contours
      cont1 = rdfAlgorithmeCorde (cont (1:loin), dmax)
      cont2 = rdfAlgorithmeCorde (cont (loin:n), dmax)
      // Valeur de retour (en enlevant 1 point)
      n = size (cont2, 1);
      cont2 = cont2 (2:n);
      ncont = [cont1;cont2];
    end
  end
endfunction

