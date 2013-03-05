#include <math.h>
#include "Voiture.h"
#include "glsupport.h"
#include "UtilGL.h"
#include "Quaternion.h"

using namespace prog3d;
using namespace std;



// ******************************************************************
// méthodes à compléter lors du TP
void Voiture::tracerJante() {

    glColor3f(1,0,0);

    glPushMatrix();
    glRotatef(90,1,0,0);
    for(int i = 1 ;i<9;++i){
        glRotatef(45,0,1,0);
        glPushMatrix();
        glScalef(0.05,0.05,1);
        UtilGL::drawCylinder();
        glPopMatrix();
    }

    glPopMatrix();
}

void Voiture::tracerRoue() {
    this->tracerJante();
    glColor3f(0,0.2,1);
    UtilGL::drawTorus(1.1,0.1);
}

void Voiture::tracerEssieu() {
    glColor3f(0,1,0);
    glPushMatrix();
    glRotatef(90,0,1,0);
    glTranslatef(0,0,-2);
    glPushMatrix();
    glScalef(0.1,0.1,4);
    UtilGL::drawCylinder();
    glPopMatrix();
    this->tracerRoue();
    glTranslatef(0,0,4);
    this->tracerRoue();
    glPopMatrix();
}

void Voiture::tracerCarrosserie() {
    glColor3f(0.3,0,1.0);
    glPushMatrix();
    glTranslatef(0,0,-1);
    glScalef(1,1,3);
    UtilGL::drawCube();
    glPopMatrix();
    glPushMatrix();
    glTranslatef(0,1,0);
    UtilGL::drawCube();
    glPopMatrix();

    // UtilGL::drawAxes(1,0.05,0.2);

}

void Voiture::drawLocal() {
    this->tracerCarrosserie();
    glTranslatef(0,-0.5,0);
    glPushMatrix();
    glRotatef(this->_angle,1,0,0);
    glScalef(0.45,0.45,0.45);
    this->tracerEssieu();
    glPopMatrix();
    glPushMatrix();
    glTranslatef(0,0,-2);
    glRotatef(this->_braquage,0,1,0);
    glRotatef(this->_angle,1,0,0);
    glScalef(0.45,0.45,0.45);
    this->tracerEssieu();
    glPopMatrix();
}


void Voiture::drawWorld() {
    Vector3 u;
    double a;
    _orientation.copyToAngleAxis(&a,&u); // permet de covertir le quaternion en rotation d'angle a et d'axe u, qu'on pourra alors donner à OpenGL

    glPushMatrix();
    glTranslatef(_position.x(),_position.y(),_position.z());
    glRotatef(a,u.x(),u.y(),u.z());
    drawLocal();
    glPopMatrix();
}

void Voiture::avancer() {
    Vector3 v(0,0,0.1);
    Vector3 vo(0,1,0);
    _orientation.rotate(_braquage/10,vo);
    v=_orientation*v;
    _position+=-v;
    angle(this->_angle-2.5);
}

void Voiture::reculer() {
    Vector3 v(0,0,0.1);
    Vector3 vo(0,1,0);
    _orientation.rotate(-_braquage/10,vo);
    v=_orientation*v;
    _position+=v;
     angle(this->_angle+2.5);
}

void Voiture::gauche(){
    if(_braquage<30){
        _braquage+=0.5;
    }

}
void Voiture::droite(){
    if(_braquage>-30){
        _braquage-=0.5;
    }
}

// ************************************************************
Voiture::Voiture() {
    _orientation.setIdentity();
    _position.set(0,-2.0,0);
}


