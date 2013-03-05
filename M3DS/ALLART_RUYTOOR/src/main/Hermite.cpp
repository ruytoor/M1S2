#include "Hermite.h"

#include <iostream>
#include <sstream>
#include "UtilGL.h"

/**
@file
@author F. Aubert
*/


using namespace prog3d;
using namespace std;

/**
* Evaluation de la courbe de hermite P(t) :
* - _a,_b,_na et _nb sont les données géométriques (points extrèmes et tangentes).
* - on peut utiliser les opérateurs *, + sur les points. Exemple : p=matrix[0+i]*_a+...
**/
Vector3 Hermite::eval(double t) {
    // initialiser la matrice 4x4 : (cf cours)
    double matrix2[16]={0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
    double matrix[16]={2.0,-2.0,1.0,1.0,
                       -3.0,3.0,-2.0,-1.0,
                       0.0,0.0,1.0,0.0,
                       1.0,0.0,0.0,0.0};

    for(int i =0;i<4;++i){
        matrix2[0+i*4]=(matrix[0+i*4]*_a.x()+matrix[1+i*4]*_b.x()+matrix[2+i*4]*_na.x()+matrix[3+i*4]*_nb.x());
        matrix2[1+i*4]=(matrix[0+i*4]*_a.y()+matrix[1+i*4]*_b.y()+matrix[2+i*4]*_na.y()+matrix[3+i*4]*_nb.y());
        matrix2[2+i*4]=(matrix[0+i*4]*_a.z()+matrix[1+i*4]*_b.z()+matrix[2+i*4]*_na.z()+matrix[3+i*4]*_nb.z());
        matrix2[3+i*4]=(matrix[0+i*4]*1+matrix[1+i*4]*1+matrix[2+i*4]*0+matrix[3+i*4]*0);
    }

    Vector4 tmp(t*t*t,t*t,t,1);

    Vector3 res(0,0,0);

    res.x(tmp.x()*matrix2[0]+tmp.y()*matrix2[4]+tmp.z()*matrix2[8]+tmp.w()*matrix2[12]);
    res.y(tmp.x()*matrix2[1]+tmp.y()*matrix2[5]+tmp.z()*matrix2[9]+tmp.w()*matrix2[13]);
    res.z(tmp.x()*matrix2[2]+tmp.y()*matrix2[6]+tmp.z()*matrix2[10]+tmp.w()*matrix2[14]);

    return res;
}

/**
* Trace la courbe de hermite (100 points)
**/
void Hermite::draw() {
    Vector3 oldp,newp;
    float pas=1.0/100.0;
    float t;
    oldp=_a;

    for(int i=0;i<99;++i){
        t=pas*(1+i);
        newp=eval(t);
        UtilGL::draw(oldp,newp);
        oldp=newp;
    }
    UtilGL::draw(oldp,_b);

    // A COMPLETER : tracer 100 segments pour décrire la courbe en appelant eval
    // (remarque : UtilGL::draw(oldp,newp) trace le segment entre oldp et newp)

}


/** **************************************************************************************** */
Hermite::Hermite(const Vector3 &a,const Vector3 &na,const Vector3 &b,const Vector3 &nb) {
    _a=a;
    _b=b;
    _na=na;
    _nb=nb;
}

void Hermite::set(const Vector3 &a,const Vector3 &na,const Vector3 &b,const Vector3 &nb) {
    _a=a;
    _b=b;
    _na=na;
    _nb=nb;
}



Hermite::Hermite() {
    //ctor
}

Hermite::~Hermite() {
    //dtor
}
