/**

  @author F. Aubert
  **/

#include "Entity.h"

using namespace prog3d;
using namespace std;

/**
@author F. Aubert
*/

// Mapping de la structure de données stockée dans obj vers buffers OpenGL
void Entity::initBuffer() {
    // On commence par tout mettre dans des tableaux "bas niveaux" pour pouvoir les passer à OpenGL
    float *pts;
    pts=new float[this->nbVertex()*3]; // *3 car on mettra pour chaque sommet les 3 coordonnées x,y,z
    unsigned int *indice;
    indice=new unsigned int[this->nbFace()*3]; // *3 car on doit mettre les 3 indices constituant chacun des triangles.




    // A Compléter : mapping dans les tableaux + initialisation des buffers OpenGL
    // this->nbVertex() donne le nombre de sommets de l'objet
    // this->nbFace() donne le nombre de l'objet (CU : l'objet doit être uniquement constitué de triangles)
    // this->vertex(i) donne les coordonnées du i-ieme sommet (de type Vector3 : this->vertex(i).x() pour x, this->vertex(i).y() pour y, this->vertex(i).z() pour z).
    // this->normalVertex(i) donne les coordonnées de la normale du i-ieme sommet (de type Vector3).
    // this->indexVertex(i,j) donne l'indice k du j-ieme sommet dans la face i (k étant l'indice dans le tableau des sommets : this->vertex(k) pour avoir ses coordonnées).

    // 1) initialiser les tableaux pts et indice

    for(int i=0;i<this->nbVertex()*3;i+=3){
        pts[i]=this->vertex(i).x();
        pts[i+1]=this->vertex(i).y();
        pts[i+2]=this->vertex(i).z();
    }

    for(int i=0;i<this->nbFace()*3;i+=3){
        indice[i]=this->indexVertex(i,0);
        indice[i+1]=this->indexVertex(i,1);
        indice[i+2]=this->indexVertex(i,2);
    }
    // 2) créer les buffers openGL et recopier les tableaux pts et indice dans ces buffers
    glGenBuffers(1,&_vertexBuffer);
    glBindBuffer(GL_ARRAY_BUFFER,_vertexBuffer);
    glBufferData(GL_ARRAY_BUFFER,this->nbVertex()*3*sizeof(GLfloat),pts,GL_STATIC_DRAW);


    glGenBuffers(1,&_indiceBuffer);
    glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,_indiceBuffer);
    glBufferData(GL_ELEMENT_ARRAY_BUFFER,this->nbFace()*3*sizeof(GLfloat),indice,GL_STATIC_DRAW);

    // on supprime les tableaux de la mémoire centrale (tout est maintenant dans la mémoire OpenGL)
    delete[] pts;
    delete[] indice;

}


void Entity::drawBuffer() {
    // TODO


}

/** ********************************************************************** */
/** ********************************************************************** */

Entity::Entity() {  //ctor
}

Entity::~Entity() { //dtor
}

void Entity::read(string nom) {
    MeshObj::read(nom);
    this->triangulate(); // assure un modèle triangulaire (suppose des faces convexes)
    this->computeNormal();
    this->scaleInBoxMin(-2,2,-2,2,-2,2); // le modèle sera dans la boite x=[-2,2],y=[-2,2] etc (changement d'échelle)
}


