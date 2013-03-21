#include "Sphere.h"
#include <cmath>
#include <iostream>
#include "UtilGL.h"
#include "IntersectionArray.h"

/**
@file
@author Fabrice Aubert
*/

using namespace prog3d;
using namespace std;

Sphere::Sphere() : Primitive() {};


/**
  donne la liste croissante issus de l'intersection de ray=(A,u)  et de la sphere x^2+y^2=1
  (2 ou 0 intersection)
*/
void Sphere::intersection(const Ray &ray,IntersectionArray *result) {

    /**
   * A COMPLETER : il faut résoudre l'équation en lambda, puis créer les intersections correspondant aux lambda trouvés dans le résultat :
   * !! pour ajouter une intersection à la fin de la liste result, utilisez : result->addIntersection(lambda)
   * ray est déjà dans le repère local de la sphere unitaire centrée à l'origine :
   *   - ray.point() : donne l'origine du rayon (Vector3)
   *   - ray.direction() : donne le vecteur directeur (Vector3)
   * il faut impérativement retourner 0 ou 2 intersections pour assurer une cohérence dans l'intersection.
   * il faut que l'intersection la plus petite de A soit en premier (respect d'une contrainte de listes triées pour l'intersection avec le CSG)
   *
   *  rappel : a.dot(b) donne le produit scalaire de a par b
   */
    Vector3 A = ray.point();
    Vector3 u = ray.direction();
    result->clear(); // initialisation : liste d'intersection vide
    double delta = (2*A.dot(u))*(2*A.dot(u)) - (4*u.dot(u)*(A.dot(A)-1));
    //cout<<"-------------- "<<delta<<endl;
    if (delta >0){
        double x1,x2;
        x1 = ((-2*A.dot(u))-sqrt(delta))/(2*u.dot(u));
        x2 = ((-2*A.dot(u))+sqrt(delta))/(2*u.dot(u));
        result->addIntersection(x1);
        result->addIntersection(x2);
    }


}


/** ****************************************************** **/
/** ****************************************************** **/
/** ****************************************************** **/
/** ****************************************************** **/

Vector3 Sphere::computeNormal(const Vector3 &p) {
    return p;
}


void Sphere::drawGL() {
    colorGL();
    UtilGL::drawSphere(20,20);
}

