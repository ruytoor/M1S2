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

// Calcul de la surface d'une forme
function s = rdfSurface (im)
  // Surface = somme des valeurs
  s = sum (im);
endfunction

// Calcul d'un moment geometrique
function m = rdfMoment (im, p, q)
  // Initialiser les vecteurs x et y
  x = (0 : size (im, 2) - 1)' ^ p;
  y = (0 : size (im, 1) - 1) ^ q;
  // Calcul du moment
  m = y * im * x;
endfunction

// Calcul d'un moment centre
function m = rdfMomentCentre (im, p, q)
  // Barycentre
  s = rdfSurface (im);
  cx = rdfMoment (im, 1, 0) / s;
  cy = rdfMoment (im, 0, 1) / s;
  // Initialiser les vecteurs x et y
  x = ((0 : size (im, 2) - 1) - cx)' ^ p;
  y = ((0 : size (im, 1) - 1) - cy) ^ q;
  // Calcul du moment centre
  m = y * im * x;
endfunction

