#include "Avion.h"
#include "UtilGL.h"

using namespace prog3d;
using namespace std;

Avion::Avion() {
    _increment=1.0;
    _accelerate=0.01;
    _vitesse=0.0;
    _orientation.setIdentity();
    _position.set(0,0,0);
}

Avion::~Avion() {

}


/* Tracer l'objet :
Utilisez les attributs suivants :
  - _vertex : tableau de type Vector3 contenant les coordonnées des sommets (exemple : _vertex[3].x() = coordonnée x du sommet d'indice 3)
  - _face : un tableau d'entiers à deux dimensions (un tableau de tableau) contient les indices des sommets de chaque polygone :
      Exemple : _face[3] décrit la face 3; _face[3][4] donne l'indice (dans le tableau _vertex) du 5ième sommet de la face 3.
  - _face.size() et _vertex.size() donnent le nombre de faces et de sommets de l'objet.
  - _face[i].size() donne le nombre de sommets de la face i
*/
void Avion::drawLocal() {
    glColor3f(0,0.2,0.5);
    for(int i =0; i<_face.size();++i){
        glBegin(GL_POLYGON);
        for(int j=0 ; j< _face[i].size();++j){
            glNormal3dv(_normalVertex[_face[i][j]].dv());
            glVertex3f(_vertex[_face[i][j]].x(),_vertex[_face[i][j]].y(),_vertex[_face[i][j]].z());
        }
        glEnd();
    }
}

void Avion::drawWorld() {
    glPushMatrix();

 //   glRotatef(_angleY,0,1,0);
 //   glRotatef(_angleX,1,0,0);
 //   glRotatef(_angleZ,0,0,1);

    double angle;
    Vector3 v;
    _orientation.copyToAngleAxis(&angle,&v);
    glTranslatef(_position.x(),_position.y(),_position.z());
    glRotatef(angle,v.x(),v.y(),v.z());
    drawLocal();

    glPopMatrix();
}

void Avion::move() {
    Vector3 vtmp(0,0,-1);
    _position+=(_orientation*vtmp)*_vitesse;
}

void Avion::pitchUp() {
    Vector3 v(1,0,0);
    _orientation.rotate(_increment,v);
    _angleX+=_increment;
}

void Avion::pitchDown() {
Vector3 v(1,0,0);
_orientation.rotate(-_increment,v);
    _angleX-=_increment;
}

void Avion::rollLeft() {
    Vector3 v(0,0,1);
    _orientation.rotate(-_increment,v);
    _angleZ+=_increment;
}

void Avion::rollRight() {
    Vector3 v(0,0,1);
    _orientation.rotate(_increment,v);
    _angleZ-=_increment;
}

void Avion::yawLeft() {
    Vector3 v(0,1,0);
    _orientation.rotate(_increment,v);
    _angleY+=_increment;
}

void Avion::yawRight() {
    Vector3 v(0,1,0);
    _orientation.rotate(-_increment,v);
    _angleY-=_increment;
}

void Avion::accelerate() {
    _vitesse+=_accelerate;
}

void Avion::decelerate() {
    _vitesse-=_accelerate;
}

// lecture fichier .obj
void Avion::read(const string &filename) {
    this->MeshObj::read(filename);
    this->scaleInBoxMin(-1,1,-1,1,-1,1);
    this->computeNormal();
}


