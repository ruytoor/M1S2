#include "Material.h"
#include "CsgTree.h"
#include "Primitive.h"
#include "Raytrace.h"
#include <cmath>
#include "Line.h"
#include "Error.h"
#include <iostream>
#include "Ray.h"
#include "UtilGL.h"
#include <ctime>
#include <cmath>


/**
@file
@author Fabrice Aubert
*/


using namespace prog3d;
using namespace std;


/**
Calcul de la couleur pour un rayon donné : la fonction sera récursive lors de l'ajout des rayons réfléchis
 - profondeur détermine l'arrêt de la récursion (pour les rayons de réflexion);
 - contrib donne le pourcentage (contrib \in [0,1]) de la contribution à l'éclairage total (cumul par appels récursif) : si la contribution devient trop faible, on arrête la récursivité
*/
Color Raytrace::computeRayColor(const Ray &ray,int profondeur,double contrib) {

    /**
  * Remarques :
  - inter->material().reflexionCoefficient() donnera le coefficient de contribution à la réflexion (i.e. 1=réflexion totale, 0=réflexion nulle)
  - inter->computeReflectRay() : permet de calculer le rayon réfléchi
  */
    Color color=Color(0,0,0);
    // Intersection de la scène avec le rayon (donne uniquement l'intersection la "plus proche").
    Intersection *inter=_scene->intersection(ray,0.1); // 0.1 pour prendre une intersection qui se trouve un peu devant le "point de départ" (origine) du rayon

    if (inter!=NULL&&profondeur>0&&contrib>0.001) { // existe-t-il une intersection avec la scène ?
        color=computePhongColor(*inter); // calcul de la couleur par Phong
        double coefReflect=inter->material().reflectionCoefficient();
        double coefRefract=inter->material().refractionCoefficient();
        if(coefReflect>0){
            Ray r=inter->computeReflectRay();
            Ray refract=inter->computeRefractRay();
            Color colorReflect=computeRayColor(r,profondeur-1,coefReflect*contrib);
            Color colorRefract=computeRayColor(refract,profondeur-1,coefRefract*contrib);

            color=(1-coefReflect-coefRefract)*color + coefReflect*colorReflect + coefRefract*colorRefract;
        }
        // libération mémoire de inter
        delete inter;
    }




    return color;
}


/**
  Calcul de la couleur locale à un point d'intersection : Phong avec ombres portées.
  - toutes les données nécessaires au point d'intersection sont dans le paramêtre intersection (point, normale, noeud intersecté)
  - les données de la scène (sources lumineuses) sont accessibles par scene()->...
*/
Color Raytrace::computePhongColor(const Intersection &intersection) {
    /**
  * P est le point d'intersection (Vector3)
  * L est le vecteur d'éclairement (Vector3)
  * N est la normale au point d'intersection (Vector3)
  * V est le vecteur d'observation
  * m contient le materiel au point : m.diffuse() donne la couleur diffuse (de type Color : on peut utiliser les opérateurs *, +, etc), de même m.specular(), m.shininess()
  * intersection.incident() donne le rayon qui a provoqué l'intersection
  * Pour les sources :
  *   - _scene->nbLight() donne le nombre de source lumineuses
  *   - _scene->lightPosition(i) donne la position de la source i (uniquement des sources ponctuelles).
  * Remarque : il faut faire la somme des couleurs obtenues pour chacune des sources (risque de saturation si plusieurs sources lumineuses).
  */

    Vector3 P;
    Vector3 L;
    Vector3 N;
    Vector3 V;

    N=intersection.normal();
    P=intersection.point();
    V = -intersection.incident().direction();

    N.normalize();
    V.normalize();
    //  P.normalize();
    Material m=intersection.node()->primitive()->material();

    Color result=m.ambient();//Color(0,0,0); // =m.ambient(); // remarque : le type Color est similaire à Vector3 : vous pouvez utilisez +,-,*,+= etc pour manipuler les couleurs
    for (int i=0; i<_scene->nbLight();i++){

        L = _scene->lightPosition(i)-P;
        Ray shadow(P,L);
        Intersection *inter=_scene->intersection(shadow,0.1);
        if(inter==NULL||(inter->lambda()<0||inter->lambda()>1)/*||inter->point().distance2(P)-L.length2()>0*/){
            L.normalize();
            if(V.dot(N)<0)
                N=-N;
            float shininess=pow(max(V.dot((2*(N.dot(L))*N-L)),0.0),m.shininess());
            float intensite = max(L.dot(N),0.0);
            result.add(intensite*m.diffuse()+m.specular()*shininess);
        }
        delete inter;
    }
    return result;
}


/** *************************************************************** **/
/** *************************************************************** **/
/** *************************************************************** **/
/** *************************************************************** **/
/** *************************************************************** **/
/** *************************************************************** **/
/** *************************************************************** **/
void Raytrace::run() {
    _stopRequest=false;
    computeImage();
}


/**
Boucle principale du lancer de rayon

*/
void Raytrace::computeImage() {
    Vector3 eye(0.0,0.0,0.0);
    Vector3 pixel_eye; // pixel dans le repère observateur
    _image->fill(Qt::black);

    clock_t clockStart=clock();

    Matrix4 csg2Camera=Matrix4::fromQuaternion(_scene->trackball()).inverse();
    csg2Camera.mul(_scene->camera().matrix());

    for(unsigned int y=0; y<_height; y++) {
        for(unsigned int x=0; x<_width; x++) {
            if (_stopRequest) goto fin;
            pixel_eye=applyEyeWindow(x,y,60.0); // exprime le rayon dans le repère de l'observateur. 60 = angle d'ouverture sur l'écran

            Ray rayon=Ray(eye,pixel_eye);  // rayon primaire
            rayon.transform(csg2Camera);

            Color c=computeRayColor(rayon,4,1.0); // calcule la couleur du pixel; 10=profondeur max de récursion, 1.0=attenuation; tous les calculs sont entendus dans le repère G
            // mise à jour de la couleur du pixel dans l'image résultante
            QRgb color=qRgb(c.getByteR(),c.getByteG(),c.getByteB());
            _image->setPixel(x,y,color); // affecte à l'image la couleur calculée
        }
    }
fin:
    clock_t clockElapsed=clock()-clockStart;
    cout << "Raytracing finished in " << double(clockElapsed)/CLOCKS_PER_SEC << " seconds" << endl;
}



/** *********************************************************************************************************** */
/** *********************************************************************************************************** */
/** *********************************************************************************************************** */

Raytrace::Raytrace(unsigned int width,unsigned int height) {
    _image=new QImage(width,height,QImage::Format_RGB32);
    _width=width;
    _height=height;
}


Raytrace::~Raytrace() {
    close();
    delete _image;
}


Vector3 Raytrace::applyEyeWindow(int x,int y,float fovy) {
    Vector3 res;
    float d=1.0/tan(M_PI*fovy/360.0); // fovy=2* angle [0,1]
    res.set((2.0*(float)x/(float)_width-1.0)*(float)_width/(float)_height,
            (2.0*(-(float)y/(float)_height)+1.0), // attention : suppose un écran carré (pas de ratio)
            -d);
    res=res+Vector3(1.0/float(_width),-1.0/float(_height),0.0);
    return res;
}



void Raytrace::close() {
    if (isRunning()) {
        _stopRequest=true;
        while (isRunning()) {
            cout << "Im waiting" << endl;
            usleep(100);
        }
    }
}








