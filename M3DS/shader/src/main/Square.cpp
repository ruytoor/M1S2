/**

  @author F. Aubert
  **/

#include "Square.h"

#include <iostream>

using namespace std;


/**
  Initialisation des buffers pour le carré
*/
void Square::initBuffer() {
    // TODO
    float color[]={0.0,0.0,0.0,0.0,0.0,0.0,1.0,0.0,1.0,0.0,0.0,0.0,0.0,1.0,0.0,0.0};
    float vertex[]={-1,-1,0,-1,1,0,1,-1,0,1,1,0};
    float texture[]={1,0,1,1,0,0,0,1};


    glGenBuffers(1,&_bufferVertex);
    glBindBuffer(GL_ARRAY_BUFFER,_bufferVertex);
    glBufferData(GL_ARRAY_BUFFER,12*sizeof(GLfloat),vertex,GL_STATIC_DRAW);

    glGenBuffers(1,&_bufferColor);
    glBindBuffer(GL_ARRAY_BUFFER,_bufferColor);
    glBufferData(GL_ARRAY_BUFFER,16*sizeof(GLfloat),color,GL_STATIC_DRAW);

    glGenBuffers(1,&_bufferTexCoord[0]);
    glBindBuffer(GL_ARRAY_BUFFER,_bufferTexCoord[0]);
    glBufferData(GL_ARRAY_BUFFER,8*sizeof(GLfloat),texture,GL_STATIC_DRAW);

}

/**
  Tracé par buffer du carré
*/
void Square::drawBuffer() {
    // TODO
    glEnableClientState(GL_VERTEX_ARRAY);
    glBindBuffer(GL_ARRAY_BUFFER,_bufferVertex);
    glVertexPointer(3,GL_FLOAT,0,0);

    glEnableClientState(GL_COLOR_ARRAY);// coleur
    glBindBuffer(GL_ARRAY_BUFFER,_bufferColor);
    glColorPointer(4,GL_FLOAT,0,0);

    glClientActiveTexture(GL_TEXTURE0);// coordonné de texture
    glEnableClientState(GL_TEXTURE_COORD_ARRAY);
    glBindBuffer(GL_ARRAY_BUFFER,_bufferTexCoord[0]);
    glTexCoordPointer(2,GL_FLOAT,0,0);

    glClientActiveTexture(GL_TEXTURE1);// coordonné de texture
    glEnableClientState(GL_TEXTURE_COORD_ARRAY);
    glBindBuffer(GL_ARRAY_BUFFER,_bufferTexCoord[0]);
    glTexCoordPointer(2,GL_FLOAT,0,0);

    glClientActiveTexture(GL_TEXTURE2);// coordonné de texture
    glEnableClientState(GL_TEXTURE_COORD_ARRAY);
    glBindBuffer(GL_ARRAY_BUFFER,_bufferTexCoord[0]);
    glTexCoordPointer(2,GL_FLOAT,0,0);

    glActiveTexture(GL_TEXTURE0);// la texture
    glBindTexture(GL_TEXTURE_2D,_idTexture[0]);

    glActiveTexture(GL_TEXTURE1);// la texture
    glBindTexture(GL_TEXTURE_2D,_idTexture[1]);

    glActiveTexture(GL_TEXTURE2);// la texture
    glBindTexture(GL_TEXTURE_2D,_idTexture[2]);

    glDrawArrays(GL_TRIANGLE_STRIP,0,4);

    glActiveTexture(GL_TEXTURE0);
    glDisableClientState(GL_TEXTURE_COORD_ARRAY);
    glActiveTexture(GL_TEXTURE1);
    glDisableClientState(GL_TEXTURE_COORD_ARRAY);
    glActiveTexture(GL_TEXTURE2);
    glDisableClientState(GL_TEXTURE_COORD_ARRAY);
    glDisableClientState(GL_VERTEX_ARRAY);
    glDisableClientState(GL_COLOR_ARRAY);
}


Square::Square() {
    _idTexture.resize(3);
    _bufferTexCoord.resize(3);
}

Square::~Square(){
}

void Square::texture(unsigned int unite,GLuint id) {
    _idTexture[unite]=id;
}



