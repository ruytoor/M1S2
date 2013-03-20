#include "IntersectionArray.h"
#include <iostream>

using namespace prog3d;
using namespace std;

/**
@file
@author Fabrice Aubert
*/

/**
Construit la liste d'intersection this comme la fusion des 2 listes triées g et d selon l'opération op
L'opération op peut prendre les valeurs CsgTree::INTERSECTION, CsgTree::UNION ou CsgTree::DIFFERENCE
!!!! effet de bord : gauche et droite sont invalides à la fin
*/
void IntersectionArray::fusion(IntersectionArray &gauche,IntersectionArray &droite,CsgTree::EOperation op) {

  bool eG,eD,eN,ePN;
  eG=false;
  eD=false;
  eN=false;
  ePN=false;

  unsigned int iG=0;
  unsigned int iD=0;

    /**
    * il faut ajouter à this (liste d'intersections initialement vide) les intersections résultantes de la fusion (algo vu en cours) :
    * - gauche et droite (paramètres de la méthode) sont les tableaux (triés) des intersections à droite et à gauche qu'on doit fusionner.
    * - op (paramètre de la méthode) est l'opérateur de fusion (valeurs symboliques possibles : CsgTree::INTERSECTION, CsgTree::UNION et CsgTree::DIFFERENCE).
    * - gauche[iG] et droite[iD] permettent de récupérer chaque intersection (de type Intersection *)
    * - une_intersection->lambda() donne le lambda d'une Intersection *
    * - this->push_back(une_intersection) ajoute une_intersection à la fin de la liste à construire.
    * - eG, eD, eN sont les états courants des listes gauche, droite et résultat
    * - ePN est l'état résultant de la combinaison des états courants eG et eD (pour déterminer s'il y a changement d'état).
    *
    * Attention !!!!!! Faire un delete sur une intersection qui n'est pas retenue dans le résultat : par exemple delete gauche[iG]
    */
  this->clear(); // initialisation de la fusion
  // while(....)





}






/** *********************************************************************************************** */

void IntersectionArray::addIntersection(double lambda) {
  this->push_back(new Intersection(lambda));
}

void IntersectionArray::deleteAll() {
  for(IntersectionArray::iterator i=begin(); i!=end(); i++) {
    if (*i) delete (*i);
  }
  clear();
}


IntersectionArray::IntersectionArray() {
  //ctor
  this->clear();
}

IntersectionArray::~IntersectionArray() {
  //dtor
}



